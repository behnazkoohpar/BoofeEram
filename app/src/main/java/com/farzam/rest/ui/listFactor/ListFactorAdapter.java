package com.farzam.rest.ui.listFactor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farzam.rest.R;
import com.farzam.rest.data.model.api.ListFactorResponse;

import java.util.List;

public class ListFactorAdapter extends RecyclerView.Adapter<ListFactorAdapter.ViewHolder> {
    private List<ListFactorResponse> stList;
    public static Context context;

    public ListFactorAdapter(List<ListFactorResponse> SlistS) {
        this.stList = SlistS;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView showDetail;
        private final LinearLayout layout;
        private TextView price, date, shFactor, name, takhfif, pasAzTakhfif, avarezMaliat, ghabelePardakht, pardakhtNashode, serialPaziresh,
                shomareDastband, vahedPaziresh, vahedSazmani, sanadfactor, sanadPoorsant, noefactor;

        public ViewHolder(final View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            showDetail = (ImageView) itemView.findViewById(R.id.showDetail);
            price = (TextView) itemView.findViewById(R.id.price);
            date = (TextView) itemView.findViewById(R.id.date);
            shFactor = (TextView) itemView.findViewById(R.id.shFactor);
            name = (TextView) itemView.findViewById(R.id.name);
            takhfif = (TextView) itemView.findViewById(R.id.takhfif);
            pasAzTakhfif = (TextView) itemView.findViewById(R.id.pasAzTakhfif);
            avarezMaliat = (TextView) itemView.findViewById(R.id.avarezMaliat);
            ghabelePardakht = (TextView) itemView.findViewById(R.id.ghabelePardakht);
            pardakhtNashode = (TextView) itemView.findViewById(R.id.pardakhtNashode);
            serialPaziresh = (TextView) itemView.findViewById(R.id.serialPaziresh);
            shomareDastband = (TextView) itemView.findViewById(R.id.shomareDastband);
            vahedPaziresh = (TextView) itemView.findViewById(R.id.vahedPaziresh);
            vahedSazmani = (TextView) itemView.findViewById(R.id.vahedSazmani);
            sanadfactor = (TextView) itemView.findViewById(R.id.sanadfactor);
            sanadPoorsant = (TextView) itemView.findViewById(R.id.sanadPoorsant);
            noefactor = (TextView) itemView.findViewById(R.id.noefactor);
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

        viewHolder.price.setText(this.stList.get(position).getTotalPrice());
        viewHolder.date.setText(this.stList.get(position).getInvoiceDate());
        viewHolder.shFactor.setText(this.stList.get(position).getFactorNo());

        viewHolder.showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.layout.getVisibility() > 0)
                    viewHolder.layout.setVisibility(View.VISIBLE);
                else
                    viewHolder.layout.setVisibility(View.GONE);
            }
        });
    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }


}
