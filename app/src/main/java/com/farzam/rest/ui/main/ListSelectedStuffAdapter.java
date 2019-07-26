package com.farzam.rest.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.farzam.rest.R;
import com.farzam.rest.data.model.api.StuffListResponse;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by cmos on 04/05/2018.
 */

public class ListSelectedStuffAdapter extends RecyclerView.Adapter<ListSelectedStuffAdapter.ViewHolder> {
    public List<StuffListResponse> stList;
    public static Context context;
    private ListSelectedStuffAdapter.OnItemClickListener mListener;
    NumberFormat numberFormat = NumberFormat.getNumberInstance();

    public ListSelectedStuffAdapter(List<StuffListResponse> SlistS) {
        stList = SlistS;
    }

    public interface OnItemClickListener {
        void onIncreaseClick(int position);

        void onDecreaseClick(int position);
    }

    public void setOnitemclickListener(ListSelectedStuffAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView price, name, numeric, sumprice;
        private final ImageView increase, decrease;

        public ViewHolder(final View itemView, final ListSelectedStuffAdapter.OnItemClickListener listener) {
            super(itemView);
            increase = (ImageView) itemView.findViewById(R.id.increase);
            decrease = (ImageView) itemView.findViewById(R.id.decrease);
            price = (TextView) itemView.findViewById(R.id.price);
            sumprice = (TextView) itemView.findViewById(R.id.sumprice);
            name = (TextView) itemView.findViewById(R.id.name);
            numeric = (TextView) itemView.findViewById(R.id.numeric);

            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onIncreaseClick(position);
                        }
                    }
                }
            });
            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDecreaseClick(position);
                        }
                    }
                }
            });
        }
    }

    public ListSelectedStuffAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_selected, parent, false);
        ListSelectedStuffAdapter.ViewHolder viewHolder = new ListSelectedStuffAdapter.ViewHolder(itemLayoutView, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListSelectedStuffAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.price.setText(numberFormat.format(Integer.parseInt(stList.get(position).getPrice())));
        viewHolder.sumprice.setText(numberFormat.format(Long.parseLong(stList.get(position).getPrice()) * stList.get(position).getNumber()));
        viewHolder.numeric.setText(String.valueOf(stList.get(position).getNumber()));
        viewHolder.name.setText(this.stList.get(position).getStuffName());

    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
