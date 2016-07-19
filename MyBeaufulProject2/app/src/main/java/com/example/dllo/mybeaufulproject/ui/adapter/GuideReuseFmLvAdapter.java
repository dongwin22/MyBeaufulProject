package com.example.dllo.mybeaufulproject.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.GuideReuseBean;
import com.example.dllo.mybeaufulproject.model.bean.LocalGuideReuseLvBean;
import com.example.dllo.mybeaufulproject.model.net.VolleyInstance;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/14.
 * 这里是复用fragment里面Listview的适配器
 */
public class GuideReuseFmLvAdapter extends BaseAdapter {
    private List<LocalGuideReuseLvBean> datas;
    private Context context;

    public GuideReuseFmLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<LocalGuideReuseLvBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GuiReuseFmLvHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_item_lv, parent, false);
            holder = new GuiReuseFmLvHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GuiReuseFmLvHolder) convertView.getTag();
        }
        LocalGuideReuseLvBean bean = datas.get(position);
        holder.categoryTv.setText(bean.getCategory());
        holder.nikeNameTv.setText(bean.getNickName());
        holder.shortTitleTv.setText(bean.getShortTitle());
        holder.titleTv.setText(bean.getTitle());
        holder.likesCountTv.setText(bean.getLikesCount());
        VolleyInstance.loaderImage(bean.getAvatarUrl(), holder.avatarIv, context);
        VolleyInstance.loaderImage(bean.getImageUrl(), holder.coverImageIv, context);
        return convertView;
    }

    class GuiReuseFmLvHolder {
        private TextView titleTv, shortTitleTv, likesCountTv, nikeNameTv, categoryTv;
        private ImageView coverImageIv;
        private CircleImageView avatarIv;

        public GuiReuseFmLvHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.guideItemLv_title);
            shortTitleTv = (TextView) view.findViewById(R.id.guideItemLv_shortTitleTv);
            likesCountTv = (TextView) view.findViewById(R.id.guideItemLv_likesCount);
            nikeNameTv = (TextView) view.findViewById(R.id.guideItemLv_nickNameTv);
            coverImageIv = (ImageView) view.findViewById(R.id.guideItemLv_contentImageView);
            categoryTv = (TextView) view.findViewById(R.id.guideItemLv_categoryBtn);
            avatarIv = (CircleImageView) view.findViewById(R.id.guideItemLv_avatarIv);

        }
    }
}
