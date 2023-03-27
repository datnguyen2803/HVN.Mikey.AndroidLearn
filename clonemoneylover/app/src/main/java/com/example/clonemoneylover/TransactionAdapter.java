package com.example.clonemoneylover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

//    View holder for each item in list,
//    build base on transaction_item.xml
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView transIcon;
        public TextView transMinorType;
        public TextView transDetail;
        public TextView transMoney;

        public ViewHolder(View itemView) {
            super(itemView);

            transIcon = (ImageView) itemView.findViewById(R.id.transIcon);
            transMinorType = (TextView) itemView.findViewById(R.id.transMinorType);
            transDetail = (TextView) itemView.findViewById(R.id.transDetail);
            transMoney = (TextView) itemView.findViewById(R.id.transMoney);

        }
    }

    private List<TransactionModel> mTransactionList;

    public TransactionAdapter(List<TransactionModel> _list){
        mTransactionList = _list;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View transactionView = inflater.inflate(R.layout.transaction_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(transactionView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TransactionAdapter.ViewHolder holder, int position){
        // Get the data model based on position
        TransactionModel trans = mTransactionList.get(position);

        // Set item views based on your views and data model
        ImageView transIcon = holder.transIcon;
        transIcon.setImageResource(trans.getTransType().getIconURI());
//        transIcon = trans.getTransType().getIcon();

        TextView transMinorType = holder.transMinorType;
        transMinorType.setText(trans.getTransType().getMinorTypeName());

        TextView transDetail = holder.transDetail;
        transDetail.setText(trans.getDescription());

        TextView transMoney = holder.transMoney;
        transMoney.setText(trans.getMoney() + "₫");
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTransactionList.size();
    }
}

