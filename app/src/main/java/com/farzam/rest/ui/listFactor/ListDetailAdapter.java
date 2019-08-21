package com.farzam.rest.ui.listFactor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.farzam.rest.R;
import com.farzam.rest.data.model.api.ListFactorDetailResponse;

import java.util.List;

public class ListDetailAdapter extends RecyclerView.Adapter<ListDetailAdapter.ViewHolder> {
    private List<ListFactorDetailResponse> listDetail;
    public static Context context;

    public ListDetailAdapter(List<ListFactorDetailResponse> SlistS) {
        this.listDetail = SlistS;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, code, price, priceSum, number;

        public ViewHolder(final View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            code = (TextView) itemView.findViewById(R.id.code);
            price = (TextView) itemView.findViewById(R.id.price);
            priceSum = (TextView) itemView.findViewById(R.id.priceSum);
            number = (TextView) itemView.findViewById(R.id.number);
            Animation animation = null;
            animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down);
            animation.setDuration(2000);
            itemView.startAnimation(animation);
            animation = null;
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_factor, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.name.setText(this.listDetail.get(position).getStuff_Text());
        viewHolder.code.setText(this.listDetail.get(position).getCode());
        viewHolder.price.setText(this.listDetail.get(position).getUnitPrice());
        viewHolder.priceSum.setText(this.listDetail.get(position).getTotalPrice());
        viewHolder.number.setText(this.listDetail.get(position).getValue());
    }

    public int getItemCount() {
        if (this.listDetail != null)
            return this.listDetail.size();
        return 0;
    }
}
