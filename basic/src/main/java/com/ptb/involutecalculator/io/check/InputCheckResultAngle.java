package com.ptb.involutecalculator.io.check;


import com.ptb.involutecalculator.involute.Angle;

/**
 * @description
 * @create 2017-06-15 下午3:46
 * @email spartajet.guo@gmail.com
 */
public class InputCheckResultAngle extends InputCheckResult {
    private Angle value;

    public Angle getValue() {
        return value;
    }

    public void setValue(Angle value) {
        this.value = value;
    }
}
