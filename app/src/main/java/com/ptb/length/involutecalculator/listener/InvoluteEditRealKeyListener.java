package com.ptb.length.involutecalculator.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.View;
import com.ptb.length.involutecalculator.util.InvoluteEditText;

/**
 * @description
 * @create 2017-08-10 下午3:19
 * @email spartajet.guo@gmail.com
 */

public class InvoluteEditRealKeyListener extends DigitsKeyListener implements TextWatcher {
    private static final String TAG = "InvoluteEditRealKeyList";
    private InvoluteEditText editText;

    public InvoluteEditRealKeyListener(InvoluteEditText editText) {
        super(false, true);
        this.editText = editText;
    }

    @Override
    public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {

        return super.onKeyDown(view, content, keyCode, event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
