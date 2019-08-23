package com.farzam.rest.ui.listFactor;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farzam.rest.R;
import com.farzam.rest.data.model.api.ListFactorDetailResponse;
import com.farzam.rest.data.model.api.ListFactorResponse;

import java.util.ArrayList;
import java.util.List;

public class ListFactorAdapter extends RecyclerView.Adapter<ListFactorAdapter.ViewHolder> {
    private List<ListFactorResponse> stList;
    public static Context context;
    OnItemClickListener listener;

    public ListFactorAdapter(List<ListFactorResponse> SlistS) {
        this.stList = SlistS;
    }

    public void setOnitemclickListener(OnItemClickListener onitemclickListener) {
        listener = onitemclickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Button showDetail;
        private final LinearLayout layout;
        private final RecyclerView listDetail;
        private final ImageView web, reception, card, personel, accepted, printed, tablet;
        public LinearLayoutManager layoutManagerListDetail;
        public ListDetailAdapter mAdapter;
        public List<ListFactorDetailResponse> listFactorDetailResponses = new ArrayList<>();
        private TextView price, date, shFactor, name, shCard, paid, time, shLocker, txtshLocker, txtshCard,
                txtTaradod, taradod, txtName, txtPersonelName, personelName;

        public ViewHolder(final View itemView) {
            super(itemView);
            listDetail = (RecyclerView) itemView.findViewById(R.id.listDetail);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            showDetail = (Button) itemView.findViewById(R.id.showDetail);
            price = (TextView) itemView.findViewById(R.id.price);
            paid = (TextView) itemView.findViewById(R.id.paid);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            shFactor = (TextView) itemView.findViewById(R.id.shFactor);
            txtshLocker = (TextView) itemView.findViewById(R.id.txtshLocker);
            shLocker = (TextView) itemView.findViewById(R.id.shLocker);
            txtshCard = (TextView) itemView.findViewById(R.id.txtshCard);
            shCard = (TextView) itemView.findViewById(R.id.shCard);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            name = (TextView) itemView.findViewById(R.id.name);
            txtPersonelName = (TextView) itemView.findViewById(R.id.txtPersonelName);
            personelName = (TextView) itemView.findViewById(R.id.personelName);
            txtTaradod = (TextView) itemView.findViewById(R.id.txtTaradod);
            taradod = (TextView) itemView.findViewById(R.id.taradod);
            web = (ImageView) itemView.findViewById(R.id.web);
            accepted = (ImageView) itemView.findViewById(R.id.accepted);
            printed = (ImageView) itemView.findViewById(R.id.printed);
            tablet = (ImageView) itemView.findViewById(R.id.tablet);
            card = (ImageView) itemView.findViewById(R.id.cardImage);
            personel = (ImageView) itemView.findViewById(R.id.personImage);
            reception = (ImageView) itemView.findViewById(R.id.reception);
            Animation animation = null;
            animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down);
            animation.setDuration(2000);
            itemView.startAnimation(animation);
            animation = null;
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_factor, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.price.setText(this.stList.get(position).getTotalPrice());
        viewHolder.paid.setText(this.stList.get(position).getPaymentedTotalAmount());
        viewHolder.date.setText(this.stList.get(position).getInvoiceDate());
        viewHolder.time.setText(this.stList.get(position).getInvoiceTime());
        viewHolder.shFactor.setText(this.stList.get(position).getFactorNo());

        viewHolder.listFactorDetailResponses = new ArrayList<>();
        viewHolder.listFactorDetailResponses = this.stList.get(position).getListFactorDetailResponseList();
        viewHolder.layoutManagerListDetail = new LinearLayoutManager(context);
        viewHolder.listDetail.setLayoutManager(viewHolder.layoutManagerListDetail);
        viewHolder.mAdapter = new ListDetailAdapter(viewHolder.listFactorDetailResponses);
        viewHolder.listDetail.setAdapter(viewHolder.mAdapter);

        if (this.stList.get(position).getIsDeliverd())
            viewHolder.accepted.setVisibility(View.VISIBLE);
        else
            viewHolder.accepted.setVisibility(View.GONE);
        if (Integer.parseInt(this.stList.get(position).getIsPerson()) == 1) {
            viewHolder.txtPersonelName.setVisibility(View.VISIBLE);
            viewHolder.personelName.setVisibility(View.VISIBLE);
            viewHolder.txtTaradod.setVisibility(View.VISIBLE);
            viewHolder.taradod.setVisibility(View.VISIBLE);
            viewHolder.txtshLocker.setVisibility(View.GONE);
            viewHolder.shLocker.setVisibility(View.GONE);
            viewHolder.txtshCard.setVisibility(View.GONE);
            viewHolder.shCard.setVisibility(View.GONE);
            viewHolder.txtName.setVisibility(View.GONE);
            viewHolder.name.setVisibility(View.GONE);
            viewHolder.personelName.setText(this.stList.get(position).getPerson_FullName());
            viewHolder.taradod.setText(this.stList.get(position).getPerson_BiometricCode());
            viewHolder.personel.setVisibility(View.VISIBLE);
            viewHolder.card.setVisibility(View.GONE);
        } else {
            viewHolder.txtPersonelName.setVisibility(View.GONE);
            viewHolder.personelName.setVisibility(View.GONE);
            viewHolder.txtTaradod.setVisibility(View.GONE);
            viewHolder.taradod.setVisibility(View.GONE);
            viewHolder.txtshLocker.setVisibility(View.VISIBLE);
            viewHolder.shLocker.setVisibility(View.VISIBLE);
            viewHolder.txtshCard.setVisibility(View.VISIBLE);
            viewHolder.shCard.setVisibility(View.VISIBLE);
            viewHolder.txtName.setVisibility(View.VISIBLE);
            viewHolder.name.setVisibility(View.VISIBLE);
            viewHolder.name.setText(this.stList.get(position).getMembership_FullName());
            viewHolder.shLocker.setText(this.stList.get(position).getLockerNumber());
            viewHolder.shCard.setText(this.stList.get(position).getMembership_CardNumber());
            viewHolder.personel.setVisibility(View.GONE);
            viewHolder.card.setVisibility(View.VISIBLE);
        }
        if (Integer.parseInt(this.stList.get(position).getIsPoolReception()) == 1)
            viewHolder.reception.setVisibility(View.VISIBLE);
        else
            viewHolder.reception.setVisibility(View.GONE);
        if (this.stList.get(position).getRegisterType().equalsIgnoreCase("ثبت از تبلت")) {
            viewHolder.tablet.setVisibility(View.VISIBLE);
            viewHolder.web.setVisibility(View.GONE);
        } else {
            viewHolder.web.setVisibility(View.VISIBLE);
            viewHolder.tablet.setVisibility(View.GONE);
        }
        if (this.stList.get(position).getPrinted())
            viewHolder.printed.setVisibility(View.VISIBLE);
        else
            viewHolder.printed.setVisibility(View.GONE);

        viewHolder.showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (viewHolder.layout.getVisibility() > 0)
                    viewHolder.layout.setVisibility(View.VISIBLE);
//                else
//                    viewHolder.layout.setVisibility(View.GONE);

                listener.callDetailFactor(viewHolder, position, stList.get(position).getSalesInvoiceID());

            }
        });

    }

    public void setListInList(ViewHolder viewholder, int positionn, List<ListFactorDetailResponse> listFactorDetailResponses) {


    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }


}
