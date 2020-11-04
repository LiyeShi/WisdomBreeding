package com.example.wisdombreeding.bean;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/11/04
 * desc   :
 * version: 1.0
 */
public class TemAndHumDataBean {
    /**
     * sign : 347286a627a99a5b
     * type : 1
     * data : {"temp_hum_det_system":{"temperature":16,"humidity":38}}
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
         * temp_hum_det_system : {"temperature":16,"humidity":38}
         */

        private TempHumDetSystemBean temp_hum_det_system;

        public TempHumDetSystemBean getTemp_hum_det_system() {
            return temp_hum_det_system;
        }

        public void setTemp_hum_det_system(TempHumDetSystemBean temp_hum_det_system) {
            this.temp_hum_det_system = temp_hum_det_system;
        }

        public static class TempHumDetSystemBean {
            /**
             * temperature : 16
             * humidity : 38
             */

            private int temperature;
            private int humidity;

            public int getTemperature() {
                return temperature;
            }

            public void setTemperature(int temperature) {
                this.temperature = temperature;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }
        }
    }
}
