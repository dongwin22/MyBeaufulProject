package com.example.dllo.mybeaufulproject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.mybeaufulproject.R;
import com.example.dllo.mybeaufulproject.model.bean.GuideFristRvBeam;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/7/13.
 * 这里是首页精品的适配器
 */
public class GuideFirstFmAdapter extends RecyclerView.Adapter<GuideFirstFmAdapter.GdFmRvHolder> {
    private GuideFristRvBeam rvBeam;
    private Context context;
    private GdFmRvHolder holder;
    private GdFmRvOnClick gdFmRvOnClick;

    public void setGdFmRvOnClick(GdFmRvOnClick gdFmRvOnClick) {
        this.gdFmRvOnClick = gdFmRvOnClick;
    }

    public GuideFirstFmAdapter(Context context) {
        this.context = context;
    }

    public void setRvBeam(GuideFristRvBeam rvBeam) {
        this.rvBeam = rvBeam;
        notifyDataSetChanged();
    }

    @Override
    public GdFmRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.guide_item_rv,null);
        holder = new GdFmRvHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final GdFmRvHolder holder, int position) {
        Picasso.with(context).load(rvBeam.getData().getSecondary_banners().get(position).getImage_url()).into(holder.imageView);
        if (gdFmRvOnClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    gdFmRvOnClick.onClickListener(pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return rvBeam !=null ? rvBeam.getData().getSecondary_banners().size():0;
    }

//    //对Rv添加监听事件
//    @Override
//    public void onClick(View v) {
//        int pos = holder.getAdapterPosition();
//        gdFmRvOnClick.onClickListener(pos);
//    }


    class GdFmRvHolder extends RecyclerView.ViewHolder{
       private ImageView imageView;
        public GdFmRvHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.guideRv_imageView);
        }
    }
}
