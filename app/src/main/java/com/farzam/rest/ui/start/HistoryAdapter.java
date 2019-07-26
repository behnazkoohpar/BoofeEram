package com.farzam.rest.ui.start;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.farzam.rest.R;
import com.farzam.rest.data.model.api.HistoryResponse;

import java.util.List;

/**
 * Created by Behnaz on 05/19/2017.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<HistoryResponse> stList;
    public static Context context;

    public HistoryAdapter(List<HistoryResponse> SlistS) {
        this.stList = SlistS;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, totalPrice,code,unitPrice,value,invoiceDate,factorNo;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            totalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            code = (TextView) itemView.findViewById(R.id.code);
            unitPrice = (TextView) itemView.findViewById(R.id.unitPrice);
            value = (TextView) itemView.findViewById(R.id.value);
            invoiceDate = (TextView) itemView.findViewById(R.id.invoiceDate);
            factorNo = (TextView) itemView.findViewById(R.id.factorNo);
            Animation animation = null;
            animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down);
            animation.setDuration(2000);
            itemView.startAnimation(animation);
            animation = null;
        }
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.name.setText(this.stList.get(position).getStuff_Text());
        viewHolder.totalPrice.setText( this.stList.get(position).getTotalPrice());
        viewHolder.code.setText( this.stList.get(position).getCode());
        viewHolder.unitPrice.setText(this.stList.get(position).getUnitPrice());
        viewHolder.value.setText(this.stList.get(position).getValue());
        viewHolder.invoiceDate.setText(this.stList.get(position).getInvoiceDate());
        viewHolder.factorNo.setText(this.stList.get(position).getFactorNo());
    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }


}