package com.example.clonemoneylover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<TransactionModel> mTransactionList;
    public TransactionAdapter(List<TransactionModel> _list){
        mTransactionList = _list;
    }
    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }

    //    View holder for each item in list,
    //    build base on transaction_list_item.xml
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        public ImageView transIcon;
        public TextView transMinorType;
        public TextView transDetail;
        public TextView transMoney;

        private ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);

            transIcon = (ImageView) itemView.findViewById(R.id.transIcon);
            transMinorType = (TextView) itemView.findViewById(R.id.transMinorType);
            transDetail = (TextView) itemView.findViewById(R.id.transDetail);
            transMoney = (TextView) itemView.findViewById(R.id.transMoney);

            itemView.setOnClickListener(this); // Mấu chốt ở đây , set sự kiên onClick cho View
            itemView.setOnLongClickListener(this);

        }
        //Tạo setter cho biến itemClickListenenr
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false); // Gọi interface , false là vì đây là onClick
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true); // Gọi interface , true là vì đây là onLongClick
            return true;
        }
    }



    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View transactionView = inflater.inflate(R.layout.transaction_list_item, parent, false);

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

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                {
                    Toast.makeText(holder.itemView.getContext(), "Long press item "+position, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(holder.itemView.getContext(), "Short press item "+position, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTransactionList.size();
    }
}

