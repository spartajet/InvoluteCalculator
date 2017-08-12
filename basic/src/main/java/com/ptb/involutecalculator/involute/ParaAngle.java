package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.InputCheckAngle;
import com.ptb.involutecalculator.io.check.InputCheckResult;
import com.ptb.involutecalculator.io.check.InputCheckResultAngle;

import java.math.BigDecimal;

/**
 * The type Para angle.
 *
 * @description
 * @create 2017 -06-14 下午8:36
 * @email spartajet.guo @gmail.com
 */
public abstract class ParaAngle extends Para implements IAngle {
    /**
     * The Input value.
     */
    protected Angle inputValue;
    /**
     * The Unit.
     */
    protected String unit;
    /**
     * The Value limit max.
     */
    protected Angle ValueLimitMax;
    /**
     * The Value limit min.
     */
    protected Angle ValueLimitMin;

    /**
     * digits after dot of second
     */
    protected int digitsAfterDotSecond;
    /**
     * The Result value.
     */
    protected Angle resultValue;
    /**
     * The Result min.
     */
    protected Angle resultValueMin;
    /**
     * The Result max.
     */
    protected Angle resultValueMax;

    /**
     * The First value.
     */
    protected Angle firstValue;
    /**
     * The Round value.
     */
    protected Angle roundValue;
    /**
     * The Round contradiction.
     */
    protected Angle roundContradiction;
    /**
     * The Contradiction.
     */
    protected Angle contradiction;

    /**
     * Instantiates a new Para angle.
     *
     * @param fixed                the fixed
     * @param unit                 the unit
     * @param valueLimitMax        the value limit max
     * @param valueLimitMin        the value limit min
     * @param digitsAfterDotSecond the digits after dot second
     */
    public ParaAngle(boolean fixed, String unit, Angle valueLimitMax, Angle valueLimitMin, int digitsAfterDotSecond) {
        super(fixed);
        this.unit = unit;
        ValueLimitMax = valueLimitMax;
        ValueLimitMin = valueLimitMin;
        this.inputValue = new Angle();
        this.digitsAfterDotSecond = digitsAfterDotSecond;
        this.inputCheck = new InputCheckAngle(digitsAfterDotSecond, valueLimitMax, valueLimitMin);
    }

    @Override
    public InputCheckResult addChar(char c) {
        InputCheckResultAngle resultAngle = (InputCheckResultAngle) this.inputCheck.addChar(c);
        this.inputValue = new Angle(resultAngle.getValue());
        return resultAngle;
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        super.clear();
        this.inputCheck.clear();
        this.inputValue = new Angle();
        this.refresh();
    }

    /**
     * Refresh.
     */
    @Override
    public void refresh() {
        super.refresh();
        this.known = !this.inputValue.equals(Angle.ANGLE_ZERO_VALUE) && !this.inputValue.equals(Angle.ANGLE_MAX_VALUE);
        if (this.known) {
            this.resultValue = new Angle(this.inputValue);
        } else {
            this.resultValue = new Angle(Angle.ANGLE_MAX_VALUE);
        }
        this.firstValue = new Angle(Angle.ANGLE_MAX_VALUE);
        this.resultValueMax = new Angle(Angle.ANGLE_MIN_VALUE);
        this.resultValueMin = new Angle(Angle.ANGLE_MAX_VALUE);
        this.roundValue = new Angle(Angle.ANGLE_MAX_VALUE);
        this.contradiction = new Angle(Angle.ANGLE_MAX_VALUE);
        this.roundContradiction = new Angle(Angle.ANGLE_MAX_VALUE);
    }

    /**
     * Refresh value.
     *
     * @param angle the angle
     */
    protected void refreshValue(Angle angle) {
        if (this.checkLimit(angle)) {
            this.resultValue = new Angle(angle);
            this.refreshMinMax(angle);
            this.fixed(angle);
        } else {
            this.resultValue = new Angle(Angle.ANGLE_MAX_VALUE);
            this.resultValueMin = new Angle(Angle.ANGLE_MAX_VALUE);
            this.resultValueMax = new Angle(Angle.ANGLE_MIN_VALUE);
        }
    }

    /**
     * Fixed.
     *
     * @param angle the angle
     */
    protected void fixed(Angle angle) {
        if (first) {
            this.resultValue = new Angle(firstValue);
        } else {
            this.firstValue = new Angle(angle);
        }
        if (this.fixed) {
            this.resultValue = new Angle(this.inputValue);
        }
    }

    /**
     * Refresh min max.
     *
     * @param angle the angle
     */
    protected void refreshMinMax(Angle angle) {
        if (this.ValueLimitMin.bigger(angle)) {
            this.ValueLimitMin = new Angle(angle);
        }
        if (this.ValueLimitMax.smaller(angle)) {
            this.ValueLimitMax = new Angle(angle);
        }
    }

    /**
     * Check limit boolean.
     *
     * @param angle the angle
     * @return the boolean
     */
    protected boolean checkLimit(Angle angle) {
        if (angle.bigger(this.ValueLimitMin) && angle.smaller(this.ValueLimitMax)) {
            this.known = true;
        } else {
            this.known = false;
            this.calculationSucceed = false;
        }
        return known;
    }

    /**
     * Calculate value boolean.
     *
     * @return the boolean
     */
    @Override
    protected boolean calculateValue() {
        return false;
    }

    /**
     * Calculate contradiction boolean.
     *
     * @return the boolean
     */
    @Override
    protected boolean calculateContradiction() {
        return false;
    }

    /**
     * Round value boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean roundValue() {
        if (this.resultValue.equals(Angle.ANGLE_MAX_VALUE)) {
            return false;
        }
        BigDecimal resultSecondDecimal = new BigDecimal(this.resultValue.getSecond());
        resultSecondDecimal = resultSecondDecimal.setScale(this.digitsAfterDotSecond + 1, BigDecimal.ROUND_HALF_UP);
        double roundSecond = resultSecondDecimal.doubleValue();
        this.roundValue = new Angle(this.resultValue.getDegree(), this.resultValue.getMinute(), roundSecond);
        return true;
    }

    /**
     * Round contradiction boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean roundContradiction() {
        if (this.contradiction.equals(Angle.ANGLE_MAX_VALUE)) {
            return false;
        }
        BigDecimal contradictionSecondDecimal = new BigDecimal(this.contradiction.getSecond());
        contradictionSecondDecimal = contradictionSecondDecimal.setScale(this.digitsAfterDotSecond + 1, BigDecimal.ROUND_HALF_UP);
        double roundContradictionSecond = contradictionSecondDecimal.doubleValue();
        this.roundContradiction = new Angle(this.contradiction.getDegree(), this.contradiction.getMinute(), roundContradictionSecond);
        return true;
    }


    @Override
    public String toString() {
        return "input value: " + this.inputValue + "; know: " + this.known + "; fixed: " + fixed + "; result value: " + resultValue + "; first value: " + firstValue + "; contradiction: " + contradiction + "; result value max: " + resultValueMax + "; result value min: " + resultValueMin + "; calculate success: " + calculationSucceed + "; round value: " + roundValue + "; round contradiction: " + contradiction;
    }

    public Angle getInputValue() {
        return inputValue;
    }

    public void setInputValue(Angle inputValue) {
        this.inputValue = new Angle(inputValue);
        this.resultValue = new Angle(inputValue);
    }

    public double getValueLimitMax() {
        return ValueLimitMax.toReal();
    }

    public void setValueLimitMax(Angle valueLimitMax) {
        ValueLimitMax = valueLimitMax;
    }

    public double getValueLimitMin() {
        return ValueLimitMin.toReal();
    }

    public void setValueLimitMin(Angle valueLimitMin) {
        ValueLimitMin = valueLimitMin;
    }
}
