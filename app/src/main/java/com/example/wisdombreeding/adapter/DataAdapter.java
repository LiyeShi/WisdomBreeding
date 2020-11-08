package com.example.wisdombreeding.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wisdombreeding.R;
import com.example.wisdombreeding.bean.CellDataBean;

import java.util.ArrayList;


/**
 * @author : 鑫宇
 * e-mail : 1769314609@qq.com
 * time   : 2020/10/13
 * desc   :
 * version: 1.0
 */
public class DataAdapter extends BaseAdapter {
    private static final String TAG = "DataAdapter";
    private CellDataBean body;
    private ArrayList<String> mList;


    @Override
    public int getCount() {
        return body.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.data_show_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.dataPointName.setText(body.getData().get(position).getName());
        viewHolder.dataPointDes.setText(body.getData().get(position).getModel().get(0).getName());
        if (mList.get(position).equals("已关闭")) {
            viewHolder.dataPointValue.setTextColor(Color.RED);
        }else {
            viewHolder.dataPointValue.setTextColor(Color.BLACK);
        }
        viewHolder.dataPointValue.setText(mList.get(position));
        return convertView;
    }

    public void setData(CellDataBean body) {
        this.body = body;

    }

    public void setDataList(ArrayList body) {
        this.mList =body;
    }

    private static class ViewHolder {
        public View rootView;
        public TextView dataPointName;
        public TextView dataPointValue;
        public TextView dataPointDes;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            // 找到子项布局的控件
            this.dataPointName = rootView.findViewById(R.id.data_point_name);
            this.dataPointValue = rootView.findViewById(R.id.data_point_value);
            this.dataPointDes = rootView.findViewById(R.id.data_point_des);
        }

    }
}
