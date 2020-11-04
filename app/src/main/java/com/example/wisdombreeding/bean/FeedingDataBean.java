package com.example.wisdombreeding.bean;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/11/04
 * desc   :通讯后返回的消息解析
 * version: 1.0
 */
public class FeedingDataBean {
    /**
     * sign : 347286a627a99a5b
     * type : 1
     * data : {"feeding_sys":{"feeding":true}}
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
         * feeding_sys : {"feeding":true}
         */

        private FeedingSysBean feeding_sys;

        public FeedingSysBean getFeeding_sys() {
            return feeding_sys;
        }

        public void setFeeding_sys(FeedingSysBean feeding_sys) {
            this.feeding_sys = feeding_sys;
        }

        public static class FeedingSysBean {
            /**
             * feeding : true
             */

            private boolean feeding;

            public boolean isFeeding() {
                return feeding;
            }

            public void setFeeding(boolean feeding) {
                this.feeding = feeding;
            }
        }
    }
}
