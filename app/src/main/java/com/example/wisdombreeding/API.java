package com.example.wisdombreeding;

import com.example.wisdombreeding.bean.CellDataBean;
import com.example.wisdombreeding.bean.CellDataListBean;
import com.example.wisdombreeding.bean.DeviceDataBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/10/13
 * desc   :
 * version: 1.0
 */
public interface API {

    /**
     * 模块-模型列表查询
     * @return
     */
    @GET("347286a627a99a5b/get-cell-model-list.html")
   Call<CellDataBean> getCellModelList();

    /**
     * 模块最新数据查询
     * @return
     */
    @GET("347286a627a99a5b/get-cell-data-list.html")
    Call<CellDataListBean> getCellDataList();

    /**
     * 产品信息
     * @return
     */
    @GET("347286a627a99a5b/get-device.html")
    Call<DeviceDataBean> getDeviceInfo();



}
