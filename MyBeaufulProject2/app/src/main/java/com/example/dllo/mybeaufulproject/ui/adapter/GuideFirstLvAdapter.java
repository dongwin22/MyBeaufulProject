package com.example.dllo.mybeaufulproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.GuideFirstLvBean;
import com.example.dllo.mybeaufulproject.model.bean.LocalGuideFirstLvBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/7/13.
 * 这里是首页精品里面listview的适配器
 */
public class GuideFirstLvAdapter extends BaseAdapter{
    private GuideFirstLvBean datas;
    private Context context;

    public GuideFirstLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(GuideFirstLvBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null ? datas.getData().getItems().size():0 ;
    }

    @Override
    public Object getItem(int position) {
        return datas != null ? datas.getData().getItems().get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GuideFirstFmHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_item_lv,parent,false);
            lvHolder = new GuideFirstFmHolder(convertView);
            convertView.setTag(lvHolder);
        }else{
            lvHolder = (GuideFirstFmHolder) convertView.getTag();
        }
        lvHolder.titleTv.setText(datas.getData().getItems().get(position).getTitle());
        lvHolder.likesCountTv.setText(String.valueOf(datas.getData().getItems().get(position).getLikes_count()));
        Picasso.with(context).load(datas.getData().getItems().get(position).getCover_image_url()).into(lvHolder.coverIv);
        return convertView;
    }
    class GuideFirstFmHolder{
        private TextView titleTv,likesCountTv;
        private ImageView coverIv;//封面;
        public GuideFirstFmHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.guideItemLv_title);
            likesCountTv = (TextView) view.findViewById(R.id.guideItemLv_likesCount);
            coverIv = (ImageView) view.findViewById(R.id.guideItemLv_contentImageView);
        }
    }
}
