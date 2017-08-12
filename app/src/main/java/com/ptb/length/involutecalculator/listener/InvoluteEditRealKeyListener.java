package com.ptb.length.involutecalculator.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.ptb.involutecalculator.involute.ParaReal;
import com.ptb.involutecalculator.io.check.InputCheckResultReal;
import com.ptb.length.involutecalculator.util.InvoluteEditText;
import com.ptb.length.involutecalculator.util.KeyCodeUtil;

/**
 * @description
 * @create 2017-08-10 下午3:19
 * @email spartajet.guo@gmail.com
 */

public class InvoluteEditRealKeyListener extends DigitsKeyListener implements TextWatcher {
    private static final String TAG = "InvoluteEditRealKeyList";
    private InvoluteEditText editText;
    private ParaReal paraReal;

    public InvoluteEditRealKeyListener(InvoluteEditText editText, ParaReal paraReal) {
        super(false, true);
        this.editText = editText;
        this.paraReal = paraReal;
    }

    @Override
    public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
        char c = KeyCodeUtil.keyCode2Char(keyCode);
        InputCheckResultReal result = (InputCheckResultReal) this.paraReal.addChar(c);
        if (result.getCode() != 0) {
            Toast.makeText(view.getContext(), result.getMessage(), Toast.LENGTH_LONG).show();

        }
        InvoluteEditText editText = (InvoluteEditText) view;
        editText.setText(result.getValueString());
        return true;
//        return super.onKeyDown(view, content, keyCode, event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0 && s.charAt(s.length() - 1) == '.') {
            InputCheckResultReal result = (InputCheckResultReal) this.paraReal.addChar('.');
            if (s.length() == 1) {
                this.editText.setText(result.getValueString());
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
