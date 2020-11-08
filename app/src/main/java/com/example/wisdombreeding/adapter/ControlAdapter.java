package com.example.wisdombreeding.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.wisdombreeding.activity.MainActivity;

import com.example.wisdombreeding.R;

import java.util.ArrayList;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/10/13
 * desc   :
 * version: 1.0
 */
public class ControlAdapter extends BaseAdapter {
    private static final String TAG = "ControlAdapter";
    private Context mContext;
    private  ArrayList<String> mInstructionNameList;
    private  ArrayList<String> mInstructionList;
    private MainActivity mActivity;

    public ControlAdapter(Activity activity) {
        mActivity= (MainActivity) activity;
        mInstructionNameList = new ArrayList<>();
        mInstructionList=new ArrayList<>();
        mInstructionNameList.add("打开风扇");
        mInstructionNameList.add("关闭风扇");
        mInstructionNameList.add("打开灯光");
        mInstructionNameList.add("关闭灯光");
        mInstructionNameList.add("投喂饲料");
//        风扇
        mInstructionList.add("{\"sign\":\"347286a627a99a5b\",\"type\":1,\"data\":{\"ventilation_sys\":{\"fan\":true}}}");
        mInstructionList.add("{\"sign\":\"347286a627a99a5b\",\"type\":1,\"data\":{\"ventilation_sys\":{\"fan\":false}}}");
//        灯光
        mInstructionList.add("{\"sign\":\"347286a627a99a5b\",\"type\":1,\"data\":{\"lighting_sys\":{\"floodlight\":true}}}");
        mInstructionList.add("{\"sign\":\"347286a627a99a5b\",\"type\":1,\"data\":{\"lighting_sys\":{\"floodlight\":false}}}");
//        投喂饲料
        mInstructionList.add("{\"sign\":\"347286a627a99a5b\",\"type\":1,\"data\":{\"feeding_sys\":{\"feeding\":true}}}");



    }

    @Override
    public int getCount() {
        return mInstructionNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        mContext = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.control_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.controlName.setText(mInstructionNameList.get(position));
        // 控制器控制   投喂饲料按钮 因为没有平台信息反馈 所以自己控制时间 更新UI
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:position==》"+position);
                AlertDialog alertDialog2 = new AlertDialog.Builder(mContext)
                        .setTitle("确认操作")
                        .setIcon(R.drawable.yiwen)
                        .setMessage("确定下发 "+ mInstructionNameList.get(position)+" 指令?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mActivity.sendCommand(mInstructionList.get(position));

                            }
                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(mContext, "已取消下发命令", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog2.show();
//                说明点击的是投喂饲料按钮
                if (position == mInstructionNameList.size()-1) {
                    Message message = Message.obtain();
                    Bundle bundle=new Bundle();
                    bundle.putInt("index", 4);
                    bundle.putString("msg", "正在投喂...");
                    message.setData(bundle);
                    mActivity.mHandler.sendMessage(message);
                    Log.d(TAG, "onClick: ");
                    Log.d(TAG, "onClick: 点击了投喂饲料按钮");
                }
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        public View rootView;
        public TextView controlName;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            // 找到小布局的控件
            this.controlName = rootView.findViewById(R.id.control_name);

        }

    }

}
