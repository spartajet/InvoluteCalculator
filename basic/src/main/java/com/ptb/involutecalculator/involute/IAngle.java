package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.ICheckable;
import com.ptb.involutecalculator.io.check.InputCheckResult;

/**
 * @description
 * @create 2017-06-15 上午10:49
 * @email spartajet.guo@gmail.com
 */
public interface IAngle extends ICheckable {
    void refresh();

    void clear();

    void setFixed(boolean value);

    boolean calculationSucceed();

    boolean known();

    double resultValue();

    boolean fixed();

    boolean roundValue();

    boolean roundContradiction();

    String getRoundValueString();

    String getRoundContradictionString();

    InputCheckResult addChar(char c);

    double getValueLimitMin();

    double getValueLimitMax();

    void setInputValue(Angle value);

    void setValueString(String valueString);

    String getInputValueString();
}
