package com.ptb.length.involutecalculator.util;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;

/**
 * @description
 * @create 2017-08-10 上午11:31
 * @email spartajet.guo@gmail.com
 */

public class InvoluteEditText extends android.support.v7.widget.AppCompatEditText {
    public InvoluteEditText(Context context) {
        super(context);
        this.setCursorVisible(false);
        this.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
        this.setEms(10);
        this.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    InvoluteEditText editText = (InvoluteEditText) v;
                    editText.setSelection(editText.getText().length());
                }
            }
        });
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        this.setSelection(this.getText().length());
    }
}
