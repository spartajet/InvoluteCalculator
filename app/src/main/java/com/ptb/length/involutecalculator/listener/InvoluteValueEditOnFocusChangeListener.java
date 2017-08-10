package com.ptb.length.involutecalculator.listener;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * @description
 * @create 2017-08-10 上午9:23
 * @email spartajet.guo@gmail.com
 */

public class InvoluteValueEditOnFocusChangeListener implements View.OnFocusChangeListener {
    private static final String TAG = "InvoluteValueOnFocus";
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            EditText editText = (EditText) v;
            Log.i(TAG, "onFocusChange: "+editText);
            editText.setSelection(editText.getText().length());
        }
    }
}
