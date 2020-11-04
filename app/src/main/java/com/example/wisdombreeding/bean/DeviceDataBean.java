package com.example.wisdombreeding.bean;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/10/19
 * desc   : 产品信息Bean类
 * version: 1.0
 */
public class DeviceDataBean {

    /**
     * code : 200
     * message : 获取设备信息成功
     * success : true
     * data : {"name":"智慧养殖","type":"6","protocol_type":"3","imei":"","online":"0","logo":null,"sign":"347286a627a99a5b","data_type":"1","create_time":"2020-10-11 11:09:14","type_str":"工业物联","protocol_type_str":"MQTT","data_type_str":"JSON","online_str":"离线"}
     */

    private int code;
    private String message;
    private boolean success;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 智慧养殖
         * type : 6
         * protocol_type : 3
         * imei :
         * online : 0
         * logo : null
         * sign : 347286a627a99a5b
         * data_type : 1
         * create_time : 2020-10-11 11:09:14
         * type_str : 工业物联
         * protocol_type_str : MQTT
         * data_type_str : JSON
         * online_str : 离线
         */

        private String name;
        private String type;
        private String protocol_type;
        private String imei;
        private String online;
        private Object logo;
        private String sign;
        private String data_type;
        private String create_time;
        private String type_str;
        private String protocol_type_str;
        private String data_type_str;
        private String online_str;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProtocol_type() {
            return protocol_type;
        }

        public void setProtocol_type(String protocol_type) {
            this.protocol_type = protocol_type;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getData_type() {
            return data_type;
        }

        public void setData_type(String data_type) {
            this.data_type = data_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getType_str() {
            return type_str;
        }

        public void setType_str(String type_str) {
            this.type_str = type_str;
        }

        public String getProtocol_type_str() {
            return protocol_type_str;
        }

        public void setProtocol_type_str(String protocol_type_str) {
            this.protocol_type_str = protocol_type_str;
        }

        public String getData_type_str() {
            return data_type_str;
        }

        public void setData_type_str(String data_type_str) {
            this.data_type_str = data_type_str;
        }

        public String getOnline_str() {
            return online_str;
        }

        public void setOnline_str(String online_str) {
            this.online_str = online_str;
        }
    }
}
