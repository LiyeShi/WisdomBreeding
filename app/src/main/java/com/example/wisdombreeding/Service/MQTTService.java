package com.example.wisdombreeding.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.wisdombreeding.bean.FanDataBean;
import com.example.wisdombreeding.bean.FeedingDataBean;
import com.example.wisdombreeding.bean.LightDataBean;
import com.example.wisdombreeding.bean.SmokeDataBean;
import com.example.wisdombreeding.bean.TemAndHumDataBean;
import com.google.gson.Gson;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 上行：发布消息
 * 下行：订阅消息
 */
public class MQTTService extends Service {

    private static final String TAG = "MQTTService";
    //     MQTT 客户端
    private MqttAndroidClient client;
    //   消息代理服务器地址  1883 是MQTT非加密协议端口
    private String serverURL = "tcp://47.92.249.234:1883";
    //     客户端 ID，用以识别客户端  平台提供的默认值
    private String clientId = "7cc28801504fee1f0fa0ef3bbc24dd1c";
    //     连接参数设置
    private MqttConnectOptions mConnectOptions;
    //     要发送消息的主题
    private String mTopic_post = "device/347286a627a99a5b/up";
    private String mTopic_set = "device/347286a627a99a5b/down";
    //    发送消息的质量
    private int Qos = 2;
    private OnDataArrivedListener onDataArrivedListener;

    public MQTTService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        try {
//            设置不同的客户端id
            clientId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            Log.d(TAG, "onCreate: clientId==>" + clientId);
            if (clientId == null) {
                clientId = "7cc28801504fee1f0fa0ef3bbc24dd1c";
            }
//                  创建客户端
            client = new MqttAndroidClient(getApplicationContext(), serverURL, clientId);
//                  设置回调
            client.setCallback(new MQTTService.MqttCallback());
            initConnectOptions();
//                  开始连接
            client.connect(mConnectOptions, null, new MqttListener());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void initConnectOptions() {
//        连接设置
        mConnectOptions = new MqttConnectOptions();
//        设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        mConnectOptions.setCleanSession(true);
//        设置连接的用户名
        mConnectOptions.setUserName("bkrc");
//        设置连接的密码
        mConnectOptions.setPassword("88888888".toCharArray());
//        设置连接超时时间 单位为秒
        mConnectOptions.setConnectionTimeout(3);
//        设置会话心跳时间 单位为秒 服务器会每隔1.5*10秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        mConnectOptions.setKeepAliveInterval(10);
//        设置自动重连
        mConnectOptions.setAutomaticReconnect(true);
//       setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        mConnectOptions.setWill(mTopic_post, "close".getBytes(), Qos, true);
    }


    public class MyBinder extends Binder {
        /**
         * 发布消息
         *
         * @throws MqttException
         */
        public void pushMessage(final String msg) throws MqttException {
            Log.d(TAG, "pushMessage: message==>" + msg);
            MqttMessage message = new MqttMessage();
            message.setQos(Qos);
            message.setPayload(msg.getBytes());
            client.publish(mTopic_post, message, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "onSuccess: 发送成功");
                    Toast.makeText(getApplicationContext(), "指令成功下达", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(getApplicationContext(), "消息发送失败，正在重新连接，请稍后重试！", Toast.LENGTH_SHORT).show();
                    try {

                        client.connect(mConnectOptions, null, new MqttListener());
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onFailure: 消息" + msg + "发送失败\n");
                    if (exception != null) {
                        Log.d(TAG, "onFailure: 消息" + msg + "发送失败\n" + exception.getMessage());
                    }
                }
            });
        }

        /**
         * @return
         */
        public MQTTService getService() {
            return MQTTService.this;
        }

    }

    /**
     * 订阅主题的回调
     */
    class MqttCallback implements MqttCallbackExtended {

        @Override
        public void connectionLost(Throwable cause) {


        }

        @Override
        public void messageArrived(String topic, MqttMessage message) {
            String msg = message.toString();
            Log.d(TAG, "messageArrived: msg==>" + msg);
            if (msg.contains("SUCCESS")) {

            } else if (msg.contains("MSG_FREQUENTLY")) {
                Toast.makeText(getApplicationContext(), "指令发送过于频繁，请重试！", Toast.LENGTH_SHORT).show();
            }
            Gson gson = new Gson();
            String state = "";
            if (msg.contains("fan")) {
//            和通风有关的信息
                FanDataBean fanDataBean = gson.fromJson(msg, FanDataBean.class);
                boolean isFan = fanDataBean.getData().getVentilation_sys().isFan();
                state = conversionToChinese(isFan);
                onDataArrivedListener.onReceive(0, state);
            } else if (msg.contains("floodlight")) {
//             和灯光有关的信息
                LightDataBean lightDataBean = gson.fromJson(msg, LightDataBean.class);
                boolean floodlight = lightDataBean.getData().getLighting_sys().isFloodlight();
                state = conversionToChinese(floodlight);
                onDataArrivedListener.onReceive(1, state);
            } else if (msg.contains("feeding")) {
//              和投喂有关的信息
                FeedingDataBean feedingDataBean = gson.fromJson(msg, FeedingDataBean.class);
                boolean feeding = feedingDataBean.getData().getFeeding_sys().isFeeding();
                onDataArrivedListener.onReceive(4, conversionToChinese(feeding));
            } else if (msg.contains("smoke")) {
                //              和烟雾有关的信息
                SmokeDataBean smokeDataBean = gson.fromJson(msg, SmokeDataBean.class);
                int smoke = smokeDataBean.getData().getSmoke_detection_sys().getSmoke();
                onDataArrivedListener.onReceive(3, String.valueOf(smoke));
            } else if (msg.contains("temp_hum_det_system")) {
//              和温湿度有关的信息
                Log.d(TAG, "messageArrived: 接收到温湿度");
                TemAndHumDataBean temAndHumDataBean = gson.fromJson(msg, TemAndHumDataBean.class);
                int temperature = temAndHumDataBean.getData().getTemp_hum_det_system().getTemperature();
                int humidity = temAndHumDataBean.getData().getTemp_hum_det_system().getHumidity();
                onDataArrivedListener.onReceive(2, temperature + "℃," + humidity + "%");
            } else {

            }


        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }

        @Override
        public void connectComplete(boolean reconnect, String serverURI) {


        }
    }

    /**
     * 将true false 改成 开启 关闭
     *
     * @param rawState
     * @return
     */
    private String conversionToChinese(boolean rawState) {
        String state;
        if (String.valueOf(rawState).equals("false")) {
            state = "已关闭";
        } else {
            state = "已开启";
        }
        return state;
    }

    public interface OnDataArrivedListener {
        void onReceive(int index, String msg);
    }

    public void setOnMsgArrived(OnDataArrivedListener onDataArrivedListener) {
        this.onDataArrivedListener = onDataArrivedListener;
    }


    class MqttListener implements IMqttActionListener {

        @Override
        public void onSuccess(IMqttToken asyncActionToken) {
            Log.d(TAG, "onSuccess: 连接成功");
            Toast.makeText(getApplicationContext(), "成功与云平台建立连接", Toast.LENGTH_SHORT).show();
//            订阅上行主题 接收服务端发送的信息
            try {
                subscribeTopic(mTopic_post);
                subscribeTopic(mTopic_set);
            } catch (MqttException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
            Log.d(TAG, "onFailure: 连接失败");
            Toast.makeText(getApplicationContext(), "与云平台连接失败，请重试！", Toast.LENGTH_SHORT).show();

        }


    }

    /**
     * 订阅主题
     *
     * @throws MqttException
     */
    private void subscribeTopic(String topic) throws MqttException {
        client.subscribe(topic, Qos, null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.d(TAG, "onSuccess: 主题订阅成功");

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }
}
