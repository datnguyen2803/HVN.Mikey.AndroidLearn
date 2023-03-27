package com.example.clonemoneylover;


public class TransactionType {

    private CommonValue.TransactionMajorType eMajorType;
    private CommonValue.TransactionMinorType eMinorType;
    private String majorTypeName;
    private String minorTypeName;
    private int mIconURI;


    public TransactionType() {
        this.eMajorType = CommonValue.TransactionMajorType.eTRANSFER_MONEY_MAX;
        this.eMinorType = CommonValue.TransactionMinorType.eTRANSFER_MAX;

        this.majorTypeName = String.valueOf(CommonValue.TransactionMajorTypeString[eMajorType.getInt()]);
        this.minorTypeName = String.valueOf(CommonValue.TransactionMinorTypeString[eMinorType.getInt()]);
        this.mIconURI = CommonValue.IconURIList[eMinorType.getInt()];
    }

    public TransactionType(CommonValue.TransactionMajorType eMajorType, CommonValue.TransactionMinorType eMinorType) {
        this.eMajorType = eMajorType;
        this.eMinorType = eMinorType;

        this.majorTypeName = String.valueOf(CommonValue.TransactionMajorTypeString[eMajorType.getInt()]);
        this.minorTypeName = String.valueOf(CommonValue.TransactionMinorTypeString[eMinorType.getInt()]);
        this.mIconURI = CommonValue.IconURIList[eMinorType.getInt()];
    }


    //    Getter and setter
    public CommonValue.TransactionMajorType getMajorType() {
        return eMajorType;
    }

    public void setMajorType(CommonValue.TransactionMajorType eMajorType) {
        this.eMajorType = eMajorType;
    }

    public CommonValue.TransactionMinorType getMinorType() {
        return eMinorType;
    }

    public void setMinorType(CommonValue.TransactionMinorType eMinorType) {
        this.eMinorType = eMinorType;
    }

    public String getMajorTypeName() {
        return majorTypeName;
    }

    public void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }

    public String getMinorTypeName() {
        return minorTypeName;
    }

    public void setMinorTypeName(String minorTypeName) {
        this.minorTypeName = minorTypeName;
    }

    public int getIconURI() {
        return mIconURI;
    }

    public void setIconURI(int iconURI) {
        this.mIconURI = iconURI;
    }
}
