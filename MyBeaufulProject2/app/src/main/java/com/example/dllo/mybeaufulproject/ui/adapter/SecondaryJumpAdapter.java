package com.example.dllo.mybeaufulproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.SecondaryJumpBean;
import com.example.dllo.mybeaufulproject.model.net.SingleImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/18.
 * 轮播图的2级界面listview的适配器
 */
public class SecondaryJumpAdapter extends BaseAdapter{
    private SecondaryJumpBean bean;
    private Context context;

    public SecondaryJumpAdapter(Context context) {
        this.context = context;
    }

    public void setBean(SecondaryJumpBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean.getData().getPosts().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getPosts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JumpLvHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_item_lv,parent,false);
            lvHolder = new JumpLvHolder(convertView);
            convertView.setTag(lvHolder);
        }else{
            lvHolder = (JumpLvHolder) convertView.getTag();
        }
        lvHolder.titleTv.setText(bean.getData().getPosts().get(position).getTitle());
        lvHolder.shortTitleTv.setText(bean.getData().getPosts().get(position).getColumn().getTitle());
        lvHolder.likesCountTv.setText(String.valueOf(bean.getData().getPosts().get(position).getLikes_count()));
        lvHolder.nikeNameTv.setText(bean.getData().getPosts().get(position).getAuthor().getNickname());
        lvHolder.categoryTv.setText(bean.getData().getPosts().get(position).getColumn().getCategory());
        SingleImageLoader.loaderImage(bean.getData().getPosts().get(position).getCover_image_url(),lvHolder.coverImageIv,context);
        SingleImageLoader.loaderImage(bean.getData().getPosts().get(position).getAuthor().getAvatar_url(),lvHolder.avatarIv,context);


        return convertView;
    }
    class JumpLvHolder{
        private TextView titleTv, shortTitleTv, likesCountTv, nikeNameTv, categoryTv;
        private ImageView coverImageIv;
        private CircleImageView avatarIv;
        public JumpLvHolder(View view) {
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
