package com.ptb.length.involutecalculator.util;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

/**
 * @description
 * @create 2017-08-10 上午11:31
 * @email spartajet.guo@gmail.com
 */

public class InvoluteEditText extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener {

    public InvoluteEditText(Context context,InvoluteParameterType type) {
        super(context);
        this.setCursorVisible(false);
        switch (type) {
            case INTEGER:
                this.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case DECIMAL:
                this.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
                break;
        }
        this.setEms(10);
        this.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        this.setOnFocusChangeListener(this);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        this.setSelection(this.getText().length());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            InvoluteEditText editText = (InvoluteEditText) v;
            editText.setSelection(editText.getText().length());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }

    public enum InvoluteParameterType {
        INTEGER,DECIMAL
    }

}
