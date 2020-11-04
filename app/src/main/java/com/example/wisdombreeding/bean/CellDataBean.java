package com.example.wisdombreeding.bean;

import java.util.List;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/10/13
 * desc   :
 * version: 1.0
 */
public class CellDataBean {

    /**
     * code : 200
     * message : 获取模块列表成功
     * success : true
     * data : [{"id":906,"name":"通风系统","sign":"ventilation_sys","hex_start":4,"model":[{"id":1168,"cell_id":906,"name":"风扇","unit":"","sign":"fan","hex_start":0,"data_type":"3","data_type_str":"布尔型"}]},{"id":908,"name":"照明系统","sign":"lighting_sys","hex_start":3,"model":[{"id":1169,"cell_id":908,"name":"照明灯","unit":"","sign":"floodlight","hex_start":0,"data_type":"3","data_type_str":"布尔型"}]},{"id":909,"name":"温湿度监测系统","sign":"temp_hum_det_system","hex_start":1,"model":[{"id":1170,"cell_id":909,"name":"温度","unit":"℃","sign":"temperature","hex_start":0,"data_type":"2","data_type_str":"浮点型"},{"id":1171,"cell_id":909,"name":"湿度","unit":"%","sign":"humidity","hex_start":1,"data_type":"1","data_type_str":"整数型"}]},{"id":910,"name":"烟雾监测系统","sign":"smoke_detection_sys","hex_start":0,"model":[{"id":1172,"cell_id":910,"name":"烟雾感知","unit":"","sign":"smoke","hex_start":0,"data_type":"2","data_type_str":"浮点型"}]},{"id":911,"name":"环境异常监测系统","sign":"abnormal_environ_sys","hex_start":2,"model":[{"id":1173,"cell_id":911,"name":"环境音","unit":"db","sign":"environmental_sound","hex_start":0,"data_type":"1","data_type_str":"整数型"}]}]
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
         * model : [{"id":1168,"cell_id":906,"name":"风扇","unit":"","sign":"fan","hex_start":0,"data_type":"3","data_type_str":"布尔型"}]
         */

        private int id;
        private String name;
        private String sign;
        private int hex_start;
        private List<ModelBean> model;

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

        public List<ModelBean> getModel() {
            return model;
        }

        public void setModel(List<ModelBean> model) {
            this.model = model;
        }

        public static class ModelBean {
            /**
             * id : 1168
             * cell_id : 906
             * name : 风扇
             * unit :
             * sign : fan
             * hex_start : 0
             * data_type : 3
             * data_type_str : 布尔型
             */

            private int id;
            private int cell_id;
            private String name;
            private String unit;
            private String sign;
            private int hex_start;
            private String data_type;
            private String data_type_str;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCell_id() {
                return cell_id;
            }

            public void setCell_id(int cell_id) {
                this.cell_id = cell_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
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

            public String getData_type() {
                return data_type;
            }

            public void setData_type(String data_type) {
                this.data_type = data_type;
            }

            public String getData_type_str() {
                return data_type_str;
            }

            public void setData_type_str(String data_type_str) {
                this.data_type_str = data_type_str;
            }
        }
    }
}
