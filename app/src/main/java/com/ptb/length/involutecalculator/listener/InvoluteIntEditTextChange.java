package com.ptb.length.involutecalculator.listener;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import com.ptb.length.involutecalculator.util.InvoluteEditText;
import de.ptb.length.involute.ParaInt;
import de.ptb.length.io.check.InputCheckResultInt;

/**
 * @description
 * @create 2017-08-28 上午1:26
 * @email spartajet.guo@gmail.com
 */

public class InvoluteIntEditTextChange implements TextWatcher{
    private InvoluteEditText editText;
    private ParaInt paraInt;
    private Context context;

    public InvoluteIntEditTextChange(InvoluteEditText editText, ParaInt paraInt, Context context) {
        this.editText = editText;
        this.paraInt = paraInt;
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
        InputCheckResultInt result = (InputCheckResultInt) this.paraInt.addChar(c);
        paraInt.setInputValue(result.getValue());
        paraInt.setValueString(result.getValueString());
        if (result.getCode() != 0) {
            Toast.makeText(this.context, result.getMessage(), Toast.LENGTH_LONG).show();
            this.editText.setText(result.getValueString());
        } else {
            before = String.valueOf(s);
        }
    }
}
