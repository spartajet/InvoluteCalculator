package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.InputCheckReal;
import com.ptb.involutecalculator.io.check.InputCheckResult;
import com.ptb.involutecalculator.io.check.InputCheckResultReal;

import java.math.BigDecimal;

/**
 * The type Para real.
 *
 * @description
 * @create 2017 -05-09 下午2:03
 * @email spartajet.guo @gmail.com
 */
public abstract class ParaReal extends Para {
    /**
     * The Value.
     */
    protected double inputValue;
    /**
     * The Length allowed dot before.
     */
    protected int lengthAllowedDotBefore;
    /**
     * The Length allowed dot after.
     */
    protected int lengthAllowedDotAfter;
    /**
     * The Value limit max.
     */
    protected double valueLimitMax;
    /**
     * The Value limit min.
     */
    protected double valueLimitMin;
    /**
     * The Unit.
     */
    protected String unit;
    /**
     * The Result value.
     */
    protected double resultValue;
    /**
     * The Result min.
     */
    protected double resultValueMin;
    /**
     * The Result max.
     */
    protected double resultValueMax;
    /**
     * The First value.
     */
    protected double firstValue;
    /**
     * The Round value.
     */
    protected double roundValue;
    /**
     * The Round contradiction.
     */
    protected double roundContradiction;


    /**
     * Instantiates a new Para real.
     *
     * @param fixed                  the fixed
     * @param lengthAllowedDotBefore the length allowed dot before
     * @param lengthAllowedDotAfter  the length allowed dot after
     * @param valueLimitMax          the inputValue limit max
     * @param valueLimitMin          the inputValue limit min
     * @param unit                   the unit
     */
    public ParaReal(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
        super(fixed);
        this.lengthAllowedDotBefore = lengthAllowedDotBefore;
        this.lengthAllowedDotAfter = lengthAllowedDotAfter;
        this.inputValue = Double.MAX_VALUE;
        this.valueLimitMax = valueLimitMax;
        this.valueLimitMin = valueLimitMin;
        this.unit = unit;
        this.inputCheck = new InputCheckReal(lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin);
    }

    /**
     * Add char input check result.
     *
     * @param c the c
     * @return the input check result
     */
    @Override
    public InputCheckResult addChar(char c) {
        InputCheckResultReal resultReal = (InputCheckResultReal) this.inputCheck.addChar(c);
        this.inputValue = resultReal.getValue();
        return resultReal;
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        super.clear();
        this.inputCheck.clear();
        this.inputValue = 0;
        this.refresh();
    }

    /**
     * Refresh.
     */
    @Override
    public void refresh() {
        super.refresh();
        this.known = this.inputValue != Double.MAX_VALUE && this.inputValue != 0.0;
        if (this.known) {
            this.resultValue = this.inputValue;
//            this.firstValue = this.inputValue;
        } else {
            this.resultValue = Double.MAX_VALUE;
//            this.firstValue = Double.MAX_VALUE;
        }
        this.firstValue = Double.MAX_VALUE;
        this.resultValueMax = -Double.MAX_VALUE;
        this.resultValueMin = Double.MAX_VALUE;
        this.roundValue = Double.MAX_VALUE;
        this.contradiction = Double.MAX_VALUE;
        this.roundContradiction = Double.MAX_VALUE;
    }

    /**
     * Refresh value.
     *
     * @param number the number
     */
    protected void refreshValue(double number) {
        if (this.checkLimit(number)) {
            this.resultValue = number;
            this.refreshMinMax(number);
            this.fixed(number);
        } else {
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = Double.MAX_VALUE;
            this.resultValueMin = -Double.MAX_VALUE;
        }
    }

    /**
     * Fixed.
     *
     * @param number the number
     */
    private void fixed(double number) {
        if (this.first) {
            this.resultValue = firstValue;
        } else {
            this.firstValue = number;
        }
        if (this.fixed) {
            this.resultValue = this.inputValue;
//            this.refreshMinMax(number);
        }
    }

    /**
     * Refresh min max.
     *
     * @param number the number
     */
    private void refreshMinMax(double number) {
        if (this.resultValueMin > number) {
            this.resultValueMin = number;
        }
        if (this.resultValueMax < number) {
            this.resultValueMax = number;
        }
    }

    /**
     * Check limit boolean.
     *
     * @param number the number
     * @return the boolean
     */
    protected boolean checkLimit(double number) {
        if (number >= this.valueLimitMin && number < this.valueLimitMax) {
            this.known = true;
        } else {
            this.known = false;
            this.calculationSucceed = false;
        }
        return known;
    }

    /**
     * Gets inputValue.
     *
     * @return the inputValue
     */
    public double getInputValue() {
        return inputValue;
    }

    /**
     * Sets inputValue.
     *
     * @param inputValue the inputValue
     */
    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
        this.resultValue = inputValue;
    }

    /**
     * Round value boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean roundValue() {
        if (this.resultValue == Double.MAX_VALUE) {
            return false;
        }
        BigDecimal resultDecimal = new BigDecimal(this.resultValue);
        resultDecimal = resultDecimal.setScale(this.getLengthAllowedDotAfter() + 1, BigDecimal.ROUND_HALF_UP);
        this.roundValue = resultDecimal.doubleValue();
        return true;
    }

    /**
     * Round contradiction boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean roundContradiction() {
        if (this.contradiction == Double.MAX_VALUE) {
            return false;
        }
        BigDecimal contradictionDecimal = new BigDecimal(this.contradiction);
        contradictionDecimal = contradictionDecimal.setScale(this.getLengthAllowedDotAfter() + 1, BigDecimal.ROUND_HALF_UP);
        this.roundContradiction = contradictionDecimal.doubleValue();
        return true;
    }

    @Override
    public String toString() {
        return "input value: " + this.inputValue + "; know: " + this.known + "; fixed: " + fixed + "; result value: " + resultValue + "; first value: " + firstValue + "; contradiction: " + contradiction + "; result value max: " + resultValueMax + "; result value min: " + resultValueMin + "; calculate success: " + calculationSucceed + "; round value: " + roundValue + "; round contradiction: " + contradiction;
    }

    /**
     * Gets length allowed dot before.
     *
     * @return the length allowed dot before
     */
    public int getLengthAllowedDotBefore() {
        return lengthAllowedDotBefore;
    }

    /**
     * Sets length allowed dot before.
     *
     * @param lengthAllowedDotBefore the length allowed dot before
     */
    public void setLengthAllowedDotBefore(int lengthAllowedDotBefore) {
        this.lengthAllowedDotBefore = lengthAllowedDotBefore;
    }

    /**
     * Gets length allowed dot after.
     *
     * @return the length allowed dot after
     */
    public int getLengthAllowedDotAfter() {
        return lengthAllowedDotAfter;
    }

    /**
     * Sets length allowed dot after.
     *
     * @param lengthAllowedDotAfter the length allowed dot after
     */
    public void setLengthAllowedDotAfter(int lengthAllowedDotAfter) {
        this.lengthAllowedDotAfter = lengthAllowedDotAfter;
    }

    /**
     * Gets inputValue limit max.
     *
     * @return the inputValue limit max
     */
    public double getValueLimitMax() {
        return valueLimitMax;
    }

    /**
     * Sets inputValue limit max.
     *
     * @param valueLimitMax the inputValue limit max
     */
    public void setValueLimitMax(double valueLimitMax) {
        this.valueLimitMax = valueLimitMax;
    }

    /**
     * Gets inputValue limit min.
     *
     * @return the inputValue limit min
     */
    public double getValueLimitMin() {
        return valueLimitMin;
    }

    /**
     * Sets inputValue limit min.
     *
     * @param valueLimitMin the inputValue limit min
     */
    public void setValueLimitMin(double valueLimitMin) {
        this.valueLimitMin = valueLimitMin;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets result min.
     *
     * @return the result min
     */
    public double getResultValueMin() {
        return resultValueMin;
    }

    /**
     * Sets result min.
     *
     * @param resultValueMin the result min
     */
    public void setResultValueMin(double resultValueMin) {
        this.resultValueMin = resultValueMin;
    }

    /**
     * Gets result max.
     *
     * @return the result max
     */
    public double getResultValueMax() {
        return resultValueMax;
    }

    /**
     * Sets result max.
     *
     * @param resultValueMax the result max
     */
    public void setResultValueMax(double resultValueMax) {
        this.resultValueMax = resultValueMax;
    }

    /**
     * Gets result value.
     *
     * @return the result value
     */
    public double getResultValue() {
        return resultValue;
    }

    /**
     * Sets result value.
     *
     * @param resultValue the result value
     */
    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    /**
     * Gets first value.
     *
     * @return the first value
     */
    public double getFirstValue() {
        return firstValue;
    }

    /**
     * Sets first value.
     *
     * @param firstValue the first value
     */
    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    /**
     * Gets round value.
     *
     * @return the round value
     */
    public double getRoundValue() {
        return roundValue;
    }

    /**
     * Gets round contradiction.
     *
     * @return the round contradiction
     */
    public double getRoundContradiction() {
        return roundContradiction;
    }
}
