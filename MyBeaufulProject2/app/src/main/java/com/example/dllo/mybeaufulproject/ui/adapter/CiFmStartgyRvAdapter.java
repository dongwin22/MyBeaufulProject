package com.example.dllo.mybeaufulproject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.CiFmStrategyRvBean;
import com.example.dllo.mybeaufulproject.model.net.SingleImageLoader;

import java.util.List;

/**
 * Created by dllo on 16/7/15.
 */
public class CiFmStartgyRvAdapter extends RecyclerView.Adapter<CiFmStartgyRvAdapter.StartegyRvHolder> {
    private List<CiFmStrategyRvBean.DataBean.ColumnsBean> datas;
    private Context context;

    public CiFmStartgyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CiFmStrategyRvBean.DataBean.ColumnsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public StartegyRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StartegyRvHolder startegyRvHolder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.classify_strategy_rv_item, parent, false);
        startegyRvHolder = new StartegyRvHolder(view);
        return startegyRvHolder;
    }

    @Override
    public void onBindViewHolder(StartegyRvHolder holder, int position) {
        CiFmStrategyRvBean.DataBean.ColumnsBean bean = datas.get(position);
        holder.subTitleTv.setText(bean.getSubtitle());
        holder.authorTv.setText(bean.getAuthor());
        holder.titleTv.setText(bean.getTitle());
        SingleImageLoader.loaderImage(bean.getBanner_image_url(), holder.contentImage, context);
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }


    class StartegyRvHolder extends RecyclerView.ViewHolder {
        TextView titleTv, subTitleTv, authorTv;
        ImageView contentImage;

        public StartegyRvHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.sort_strategy_item_title);
            subTitleTv = (TextView) itemView.findViewById(R.id.sort_strategy_item_subTitle);
            authorTv = (TextView) itemView.findViewById(R.id.sort_strategy_item_author);
            contentImage = (ImageView) itemView.findViewById(R.id.sort_strategy_item_contentImage);
        }
    }
}
