package com.example.wisdombreeding.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;

import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wisdombreeding.API;
import com.example.wisdombreeding.IcallBack;
import com.example.wisdombreeding.Service.MQTTService;
import com.example.wisdombreeding.R;
import com.example.wisdombreeding.adapter.ControlAdapter;
import com.example.wisdombreeding.adapter.DataAdapter;
import com.example.wisdombreeding.bean.CellDataBean;
import com.example.wisdombreeding.bean.CellDataListBean;
import com.example.wisdombreeding.bean.DeviceDataBean;
import com.jaeger.library.StatusBarUtil;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements IcallBack {
    private static final String TAG = "MainActivity";

    private MQTTService.MyBinder mBinder;
    private Retrofit mRetrofit;
    private GridView mGvData;
    private GridView mGvControl;
    private DataAdapter mDataAdapter;
    private API mApi;
    private TextView mTvDeviceInfo;
    private TextView mTvProductName;
    public ArrayList mData;
    public Handler mHandler;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: log");
        setFullScreen();
        initView();
        initListener();
//        获取最新数据
        getDatafromBroker();
//        获取产品信息 没太大用
        getDeviceInfo();
        Intent intent = new Intent(this, MQTTService.class);
//        开启服务，后台接收传感器数据
        startService(intent);
        bindService(intent, new Connection(), BIND_AUTO_CREATE);
        mGvControl.setAdapter(new ControlAdapter(this));

        /**
         * 发送指令后首页更新信息
         */
        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                Log.d(TAG, "handleMessage: msg==>"+msg);
                Log.d(TAG, "handleMessage: data=>"+data.toString());
                int index = 0;
                if ( data != null&&mData!=null) {
                    index= (int) data.get("index");
                    String msg1 = (String) data.get("msg");
                    Log.d(TAG, "handleMessage: index==>" + index + "\t" + msg1);
                    mData.set(index, msg1);
                }
                mDataAdapter.notifyDataSetChanged();
            }
        };


    }

    private void setFullScreen() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void getDeviceInfo() {
        Call<DeviceDataBean> deviceInfo = mApi.getDeviceInfo();
        deviceInfo.enqueue(new Callback<DeviceDataBean>() {
            @Override
            public void onResponse(Call<DeviceDataBean> call, Response<DeviceDataBean> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    DeviceDataBean info = response.body();
                    Log.d(TAG, "onResponse: 产品信息返回成功");
                    DeviceDataBean.DataBean data = info.getData();
                    mTvProductName.setText(data.getName());
                    mTvDeviceInfo.setText("产品类型:" + data.getType_str() + "\t协议类型:" + data.getProtocol_type_str() + "\t报文类型:" + data.getData_type_str());

                }
            }

            @Override
            public void onFailure(Call<DeviceDataBean> call, Throwable t) {

            }
        });
    }

    private void getDatafromBroker() {
        mDataAdapter = new DataAdapter();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://www.r8c.com/index/iot/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = mRetrofit.create(API.class);
        mApi.getCellModelList().enqueue(new Callback<CellDataBean>() {
            @Override
            public void onResponse(Call<CellDataBean> call, Response<CellDataBean> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    CellDataBean body = response.body();
                    Log.d(TAG, "onResponse: 返回信息==>" + body);
                    mDataAdapter.setData(body);
                    loadListFinish();
                }
            }

            @Override
            public void onFailure(Call<CellDataBean> call, Throwable t) {
                Log.d(TAG, "onFailure: 信息获取失败");

            }
        });

    }

    private void initListener() {

    }

    private void initView() {
        mGvData = findViewById(R.id.datapoint_gridview);
        mGvControl = findViewById(R.id.control_gridview);
        mTvDeviceInfo = findViewById(R.id.tv_device_info);
        mTvProductName = findViewById(R.id.product_name);


    }


    @Override
    public void sendCommand(String cmd) {
        try {
            mBinder.pushMessage(cmd);
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }


    /**
     * 模型列表加载完加载模型下的信息
     */
    public void loadListFinish() {
        mApi.getCellDataList().enqueue(new Callback<CellDataListBean>() {
            @Override
            public void onResponse(Call<CellDataListBean> call, Response<CellDataListBean> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    CellDataListBean body = response.body();
                    mData = ProcessData(body);
                    mDataAdapter.setDataList(mData);
                    mGvData.setAdapter(mDataAdapter);

                }
            }

            @Override
            public void onFailure(Call<CellDataListBean> call, Throwable t) {

            }
        });
    }

    private ArrayList<String> ProcessData(CellDataListBean body) {
        List<CellDataListBean.DataBean> beanList = body.getData();
        ArrayList<String> dataList = new ArrayList<>();
        String[] split;
        String new_data;
        for (int i = 0; i < beanList.size(); i++) {
            CellDataListBean.DataBean dataBean = beanList.get(i);
            Log.d(TAG, "ProcessData: dataBean==>" + dataBean);
            if (dataBean.getNew_data() != null) {
//                能够获取到该模块下的模型数据
                new_data = dataBean.getNew_data().getContent();
                Log.d(TAG, "ProcessData: new_data==>" + new_data);
//           下面是处理数据的过程 先去掉花括号
                new_data = new_data.substring(1, new_data.length() - 1);
//            分离模型数据
                split = new_data.split(",");
                Log.d(TAG, "ProcessData: split==>" + Arrays.toString(split) + split.length);
//            处理每一个模型下的数据
                StringBuffer stringBuffer = new StringBuffer();
//            说明该模块下有两个模型数据
                if (split.length == 2) {
                    for (int j = 0; j < split.length; j++) {
                        String s = split[j];
                        String[] strings = s.split("\":");
                        Log.d(TAG, "ProcessData: strings==>" + Arrays.toString(strings));
                        if (j == 0) {
                            stringBuffer = stringBuffer.append(strings[1] + "℃,");
                        } else {
                            stringBuffer = stringBuffer.append(strings[1] + "%");
                        }

                    }
                    dataList.add(stringBuffer.toString());
                } else if (split.length == 1) {
//                说明该模块下只有一个数据，直接用：切割
                    String[] strings = split[0].split(":");
                    Log.d(TAG, "ProcessData: 结果==》" + strings[1]);
//                    用中文显示
                    if ("true".equals(strings[1])) {
                        dataList.add("已开启");
                    } else if ("false".equals(strings[1])) {

                        dataList.add("已关闭");

                    } else {
                        dataList.add(strings[1]);
                    }
                } else {
                    Toast.makeText(this, "数据异常", Toast.LENGTH_SHORT).show();
                }
            } else {
//            该模块下尚未上传过数据
                new_data = "暂无数据";
                dataList.add(new_data);
            }

        }
        Log.d(TAG, "ProcessData: dataListSize==>" + dataList.size());
        Log.d(TAG, "ProcessData: dataList==>" + dataList.toString());
        return dataList;
    }


    class Connection implements ServiceConnection {

        /**
         * 服务成功连接时调用
         *
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MQTTService.MyBinder) service;
            MQTTService mqttService = mBinder.getService();
            mqttService.setOnMsgArrived(new MQTTService.OnDataArrivedListener() {
                @Override
                public void onReceive(int index, String msg) {
                    Message message = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", index);
                    bundle.putString("msg", msg);
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                }
            });


        }

        /**
         * 服务失去连接的时候调用
         *
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}