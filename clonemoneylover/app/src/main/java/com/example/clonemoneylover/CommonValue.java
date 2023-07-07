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
        eTRANSFER_OUT_NONE(0),
        eTRANSFER_OUT_FOOD_AND_DRINK(1),
        eTRANSFER_OUT_TRANSPORTATION(2),
        eTRANSFER_OUT_BILL(3),
        eTRANSFER_OUT_HOME_MAINTENANCE(4),
        eTRANSFER_OUT_MEDICAL(5),
        eTRANSFER_OUT_EDUCATION(6),
        eTRANSFER_OUT_PERSONAL_ITEMS(7),
        eTRANSFER_OUT_FITNESS(8),
        eTRANSFER_OUT_GIFTS_AND_DONATION(9),
        eTRANSFER_OUT_LOAN(10),
        eTRANSFER_OUT_DEBT_REPAYMENT(11),
        eTRANSFER_OUT_OTHER(12),
        eTRANSFER_OUT_MAX(13),

        eTRANSFER_IN_NONE(14),
        eTRANSFER_IN_SALARY(15),
        eTRANSFER_IN_DEBT(16),
        eTRANSFER_IN_DEBT_COLLECTION(17),
        eTRANSFER_IN_OTHER(18),
        eTRANSFER_IN_MAX(19),

        // use for list
        eTRANSFER_MAX(20);

        private int value;
        private TransactionMinorType(int _value)
        {
            this.value = _value;
        }

        public int getInt()
        {
            return value;
        }
        public static TransactionMinorType fromInt(int value) {
            for (TransactionMinorType e : TransactionMinorType.values()) {
                if (e.value == value) {
                    return e;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
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

    public static enum WalletType {
        eWALLET_TYPE_NONE (0),
        eWALLET_TYPE_PAY (1),
        eWALLET_TYPE_SAVING (2);

        private int value;
        private WalletType(int _value)
        {
            this.value = _value;
        }

        public int getInt()
        {
            return value;
        }
        public static WalletType fromInt(int value) {
            for (WalletType e : WalletType.values()) {
                if (e.value == value) {
                    return e;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }


}
