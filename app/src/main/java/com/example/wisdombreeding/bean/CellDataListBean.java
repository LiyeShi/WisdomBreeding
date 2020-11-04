package com.example.wisdombreeding.bean;

import java.util.List;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/10/13
 * desc   :
 * version: 1.0
 */
public class CellDataListBean {

    /**
     * code : 200
     * message : 获取模块最新数据成功
     * success : true
     * data : [{"id":906,"name":"通风系统","sign":"ventilation_sys","hex_start":4,"new_data":{"content":"{\"fan\":false}","create_time":"2020-10-13 18:15:03"}},{"id":908,"name":"照明系统","sign":"lighting_sys","hex_start":3,"new_data":{"content":"{\"floodlight\":false}","create_time":"2020-10-13 18:14:58"}},{"id":909,"name":"温湿度监测系统","sign":"temp_hum_det_system","hex_start":1,"new_data":{"content":"{\"temperature\":80,\"humidity\":19}","create_time":"2020-10-12 17:12:51"}},{"id":910,"name":"烟雾监测系统","sign":"smoke_detection_sys","hex_start":0,"new_data":{"content":"{\"smoke\":232323232}","create_time":"2020-10-12 17:25:18"}},{"id":911,"name":"环境异常监测系统","sign":"abnormal_environ_sys","hex_start":2,"new_data":{"content":"{\"environmental_sound\":18}","create_time":"2020-10-12 17:23:42"}}]
     */

    private int code;
    private String message;
    private boolean success;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 906
         * name : 通风系统
         * sign : ventilation_sys
         * hex_start : 4
         * new_data : {"content":"{\"fan\":false}","create_time":"2020-10-13 18:15:03"}
         */

        private int id;
        private String name;
        private String sign;
        private int hex_start;
        private NewDataBean new_data;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getHex_start() {
            return hex_start;
        }

        public void setHex_start(int hex_start) {
            this.hex_start = hex_start;
        }

        public NewDataBean getNew_data() {
            return new_data;
        }

        public void setNew_data(NewDataBean new_data) {
            this.new_data = new_data;
        }

        public static class NewDataBean {
            /**
             * content : {"fan":false}
             * create_time : 2020-10-13 18:15:03
             */

            private String content;
            private String create_time;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            @Override
            public String toString() {
                return "NewDataBean{" +
                        "content='" + content + '\'' +
                        ", create_time='" + create_time + '\'' +
                        '}';
            }
        }
    }
}
