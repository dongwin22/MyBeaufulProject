package com.example.dllo.mybeaufulproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.CiFmSingleLvBean;
import com.example.dllo.mybeaufulproject.model.net.SingleImageLoader;

import java.util.List;

/**
 * Created by dllo on 16/7/16.
 * 分类页面 右面的listview嵌套着的girdview的适配器 显示不同的内容
 */
public class CiSingleGvAdapter extends BaseAdapter{
    private List<CiFmSingleLvBean.DataBean.CategoriesBean.SubcategoriesBean> datas;
    private Context context;

    public CiSingleGvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CiFmSingleLvBean.DataBean.CategoriesBean.SubcategoriesBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SingleGvHolder gvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_single_lv_gv_item,parent,false);
            gvHolder = new SingleGvHolder(convertView);
            convertView.setTag(gvHolder);
        } else {
            gvHolder = (SingleGvHolder) convertView.getTag();
        }

        gvHolder.tv.setText(datas.get(position).getName());
        SingleImageLoader.loaderImage(datas.get(position).getIcon_url(),gvHolder.iv,context);
        return convertView;
    }


    }
    class SingleGvHolder{
        TextView tv;
        ImageView iv;
        public SingleGvHolder(View view) {
            tv = (TextView) view.findViewById(R.id.singleGv_titleIv);
            iv = (ImageView) view.findViewById(R.id.singleGv_imageIv);

        }
    }

