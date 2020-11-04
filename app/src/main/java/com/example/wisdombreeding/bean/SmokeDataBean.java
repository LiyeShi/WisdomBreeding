package com.example.wisdombreeding.bean;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/11/04
 * desc   :通讯后返回的消息解析
 * version: 1.0
 */
public class SmokeDataBean {
    /**
     * sign : 347286a627a99a5b
     * type : 1
     * data : {"smoke_detection_sys":{"smoke":295}}
     */

    private String sign;
    private int type;
    private DataBean data;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * smoke_detection_sys : {"smoke":295}
         */

        private SmokeDetectionSysBean smoke_detection_sys;

        public SmokeDetectionSysBean getSmoke_detection_sys() {
            return smoke_detection_sys;
        }

        public void setSmoke_detection_sys(SmokeDetectionSysBean smoke_detection_sys) {
            this.smoke_detection_sys = smoke_detection_sys;
        }

        public static class SmokeDetectionSysBean {
            /**
             * smoke : 295
             */

            private int smoke;

            public int getSmoke() {
                return smoke;
            }

            public void setSmoke(int smoke) {
                this.smoke = smoke;
            }
        }
    }

}
