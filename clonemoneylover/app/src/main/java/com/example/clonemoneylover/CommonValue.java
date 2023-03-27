package com.example.clonemoneylover;

import java.util.ArrayList;
import java.util.List;

public class CommonValue {
    public static enum TransactionMajorType {
        eTRANSFER_MONEY_OUTGOING(0),
        eTRANSFER_MONEY_INCOMING (1),

        eTRANSFER_MONEY_MAX(2);

        private int value;
        private TransactionMajorType(int _value)
        {
            this.value = _value;
        }

        public int getInt()
        {
            return value;
        }
    }

    public static enum TransactionMinorType {
        eTRANSFER_OUT_FOOD_AND_DRINK(0),
        eTRANSFER_OUT_TRANSPORTATION(1),
        eTRANSFER_OUT_BILL(2),
        eTRANSFER_OUT_HOME_MAINTENANCE(3),
        eTRANSFER_OUT_MEDICAL(4),
        eTRANSFER_OUT_EDUCATION(5),
        eTRANSFER_OUT_PERSONAL_ITEMS(6),
        eTRANSFER_OUT_FITNESS(7),
        eTRANSFER_OUT_GIFTS_AND_DONATION(8),
        eTRANSFER_OUT_LOAN(9),
        eTRANSFER_OUT_DEBT_REPAYMENT(10),
        eTRANSFER_OUT_OTHER(11),

        eTRANSFER_IN_SALARY(12),
        eTRANSFER_IN_DEBT(13),
        eTRANSFER_IN_DEBT_COLLECTION(14),
        eTRANSFER_IN_OTHER(15),

        // use for list
        eTRANSFER_MAX(16);

        private int value;
        private TransactionMinorType(int _value)
        {
            this.value = _value;
        }

        public int getInt()
        {
            return value;
        }
    }

    public static final String[] TransactionMajorTypeString =
            {
                    "Out going transfer",
                    "Incoming transfer",

                    "",
            };

    public static final String[] TransactionMinorTypeString =
            {
//                    outgoing transfer
                    "Food and Drink",
                    "Transportation",
                    "Bill",
                    "Home maintenance",
                    "Medical checkup",
                    "Education",
                    "Personal items",
                    "Fitness",
                    "Gifts and Donation",
                    "Loan",
                    "Debt repayment",
                    "Other outgoing",

//                    incoming transfer
                    "Salary",
                    "Debt",
                    "Debt collection",
                    "Other incoming",

//                    default
                    "",
            };

    public static final Integer[] IconURIList =
            {
//                    outgoing transfer
                    R.drawable.icon_fnb,
                    R.drawable.icon_transportation,
                    R.drawable.icon_bill,
                    R.drawable.icon_homemaintenance,
                    R.drawable.icon_medical,
                    R.drawable.icon_education,
                    R.drawable.icon_personalitems,
                    R.drawable.icon_fitness,
                    R.drawable.icon_gift,
                    R.drawable.icon_loan,
                    R.drawable.icon_debtrepay,
                    R.drawable.icon_otheroutgoing,

//                    incoming transfer
                    R.drawable.icon_salary,
                    R.drawable.icon_debt,
                    R.drawable.icon_debtcollection,
                    R.drawable.icon_otherincoming,

//                    default
                    R.drawable.icon_transactions,
            };
}
