package com.ptb.length.involutecalculator.listener;

import android.text.Editable;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

/**
 * @description
 * @create 2017-08-12 下午5:08
 * @email spartajet.guo@gmail.com
 */

public class InvoluteEditIntKeyListener extends DigitsKeyListener {
    private static final String TAG = "InvoluteEditIntKey";

    public InvoluteEditIntKeyListener() {
        super(false, true);
    }

    @Override
    public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyDown: key code: " + keyCode);
        return super.onKeyDown(view, content, keyCode, event);
    }
}
