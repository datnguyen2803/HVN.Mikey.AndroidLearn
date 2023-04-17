package com.example.clonemoneylover;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddTransactionActivity extends AppCompatActivity {

    ImageView ivCancel;
    TextView stringSAVE;
    TextView tvFieldMoney;
    ImageView ivCategoryIcon;
    TextView tvCategorySelect;
    EditText etNote;
    TextView tvTime;
    TextView tvWallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        ivCancel = findViewById(R.id.ivCancel);
        stringSAVE = findViewById(R.id.stringSAVE);
        tvFieldMoney = findViewById(R.id.tvFieldMoney);
        ivCategoryIcon = findViewById(R.id.ivCategoryIcon);
        tvCategorySelect = findViewById(R.id.tvCategorySelect);
        etNote = findViewById(R.id.etNote);
        tvTime = findViewById(R.id.tvTime);
        tvWallet = findViewById(R.id.tvWallet);

        init();
    }

    private void init() {
//        icon X cancel
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddTransactionActivity.this, "Go back...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTransactionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        text SAVE
        stringSAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddTransactionActivity.this, "Saving...", Toast.LENGTH_SHORT).show();
            }
        });

//        text Money
        tvFieldMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFieldMoney.setInputType(InputType.TYPE_CLASS_NUMBER);
                tvFieldMoney.setText("");
            }
        });

//        field Time
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTransactionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the date selected by the user
                                tvTime.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });
    }
}