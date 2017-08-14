package com.ptb.length.involutecalculator.listener;

import android.text.Editable;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.ptb.involutecalculator.involute.ParaInt;
import com.ptb.involutecalculator.io.check.InputCheckResultInt;
import com.ptb.length.involutecalculator.util.InvoluteEditText;
import com.ptb.length.involutecalculator.util.KeyCodeUtil;


/**
 * @description
 * @create 2017-08-12 下午5:08
 * @email spartajet.guo@gmail.com
 */

public class InvoluteEditIntKeyListener extends DigitsKeyListener {
    private static final String TAG = "InvoluteEditIntKey";
    private ParaInt paraInt;

    public InvoluteEditIntKeyListener(ParaInt paraInt) {
        super(false, true);
        this.paraInt = paraInt;
    }

    @Override
    public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
        char c = KeyCodeUtil.keyCode2Char(keyCode);
        InputCheckResultInt result = (InputCheckResultInt) this.paraInt.addChar(c);
        if (result.getCode() != 0) {
            Toast.makeText(view.getContext(), result.getMessage(), Toast.LENGTH_LONG).show();
        }
        this.paraInt.setInputValue(result.getValue());
        InvoluteEditText editText = (InvoluteEditText) view;
        editText.setText(result.getValueString());
        Log.i(TAG, "onKeyDown: int check result value: " + result.getValueString());
        Log.i(TAG, "onKeyDown: int result value: " + this.paraInt.getResultValue());
        return true;
//        return super.onKeyDown(view, content, keyCode, event);
    }
}
