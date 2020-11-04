package com.example.wisdombreeding.bean;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/11/04
 * desc   :通讯后返回的消息解析
 * version: 1.0
 */
public class FanDataBean {


    /**
     * sign : 347286a627a99a5b
     * type : 1
     * data : {"ventilation_sys":{"fan":true}}
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
         * ventilation_sys : {"fan":true}
         */

        private VentilationSysBean ventilation_sys;

        public VentilationSysBean getVentilation_sys() {
            return ventilation_sys;
        }

        public void setVentilation_sys(VentilationSysBean ventilation_sys) {
            this.ventilation_sys = ventilation_sys;
        }

        public static class VentilationSysBean {
            /**
             * fan : true
             */

            private boolean fan;

            public boolean isFan() {
                return fan;
            }

            public void setFan(boolean fan) {
                this.fan = fan;
            }
        }
    }
}
