package com.example.wisdombreeding.bean;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/11/04
 * desc   :通讯后返回的消息解析
 * version: 1.0
 */
public class LightDataBean {
    /**
     * sign : 347286a627a99a5b
     * type : 1
     * data : {"lighting_sys":{"floodlight":true}}
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
         * lighting_sys : {"floodlight":true}
         */

        private LightingSysBean lighting_sys;

        public LightingSysBean getLighting_sys() {
            return lighting_sys;
        }

        public void setLighting_sys(LightingSysBean lighting_sys) {
            this.lighting_sys = lighting_sys;
        }

        public static class LightingSysBean {
            /**
             * floodlight : true
             */

            private boolean floodlight;

            public boolean isFloodlight() {
                return floodlight;
            }

            public void setFloodlight(boolean floodlight) {
                this.floodlight = floodlight;
            }
        }
    }
}
