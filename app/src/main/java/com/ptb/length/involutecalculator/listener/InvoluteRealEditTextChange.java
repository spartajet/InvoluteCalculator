package com.ptb.length.involutecalculator.listener;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import com.ptb.length.involutecalculator.util.InvoluteEditText;
import de.ptb.length.involute.ParaReal;
import de.ptb.length.io.check.InputCheckResultReal;

/**
 * @description
 * @create 2017-08-28 上午12:11
 * @email spartajet.guo@gmail.com
 */

public class InvoluteRealEditTextChange implements TextWatcher {
    private static final String TAG = "InvoluteRealEditTextChange";
    private InvoluteEditText editText;
    private ParaReal paraReal;
    private Context context;

    public InvoluteRealEditTextChange(InvoluteEditText editText, ParaReal paraReal, Context context) {
        this.editText = editText;
        this.paraReal = paraReal;
        this.context = context;
    }
    private String before = "";

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (before.equals(s.toString())) {
            return;
        }
        char c = 0;
        if (before.length()>s.length()) {
            c = 8;
        } else {
            c = s.charAt(s.length() - 1);
        }
        InputCheckResultReal result = (InputCheckResultReal) this.paraReal.addChar(c);
        paraReal.setValueString(result.getValueString());
        if (result.getCode() != 0) {
            Toast.makeText(this.context, result.getMessage(), Toast.LENGTH_LONG).show();
            this.editText.setText(result.getValueString());
        } else {
            before = String.valueOf(s);
        }
    }
}
