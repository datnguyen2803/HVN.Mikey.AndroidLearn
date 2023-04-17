package com.example.clonemoneylover;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;



public class AddTransactionFieldView extends View {
    public static enum FieldType {
        eFIELD_NONE,

        eFIELD_MONEY,
        eFIELD_CATEGORY,
        eFIELD_NOTE,
        eFIELD_TIME,
        eFIELD_WALLET,
        eFIELD_WITH,
        eFIELD_EVENT,
        eFIELD_REMIND,

        eFIELD_MAX
    }

    private FieldType mType;
    private ImageView mIcon;
    private TextView mContent;


    public AddTransactionFieldView(Context context) {
        super(context);
        mIcon = new ImageView(context);
        mContent = new TextView(context);
    }

    public AddTransactionFieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mType = FieldType.eFIELD_NONE;
        mIcon = new ImageView(context);
        mContent = new TextView(context);
    }

    private void init() {
        mIcon.setImageResource(R.drawable.icon_add_true);


        mContent.setText("0 ₫");
        mContent.setTextSize(25);



    }
}
