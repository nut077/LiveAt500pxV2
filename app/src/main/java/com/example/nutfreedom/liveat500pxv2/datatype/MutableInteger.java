package com.example.nutfreedom.liveat500pxv2.datatype;

import android.os.Bundle;

public class MutableInteger {

    public MutableInteger(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt("value", value);
        return bundle;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        value = savedInstanceState.getInt("value");
    }

}
