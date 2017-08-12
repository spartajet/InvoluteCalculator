package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.InputCheckInt;
import com.ptb.involutecalculator.io.check.InputCheckResult;

/**
 * The type Para int.
 *
 * @description
 * @create 2017 -05-09 下午2:01
 * @email spartajet.guo @gmail.com
 */
public abstract class ParaInt extends Para {
    /**
     * The Value.
     */
    protected int inputValue;
    /**
     * The Unit.
     */
    protected String unit;
    /**
     * The Value limit max.
     */
    protected int ValueLimitMax;
    /**
     * The Value limit min.
     */
    protected int ValueLimitMin;

    /**
     * The Digits limit max.
     */
    protected int digitsLimitMax;
    /**
     * The Input check int.
     */
//    protected InputCheckInt inputCheckInt;
    /**
     * The Result value.
     */
    protected int resultValue;
    /**
     * The Result min.
     */
    protected int resultValueMin;
    /**
     * The Result max.
     */
    protected int resultValueMax;
    /**
     * The First value.
     */
    protected int firstValue;
    /**
     * The Round value.
     */
    protected int roundValue;
    /**
     * The Round contradiction.
     */
    protected int roundContradiction;


    /**
     * Instantiates a new Para int.
     *
     * @param fixed          the fixed
     * @param unit           the unit
     * @param valueLimitMax  the inputValue limit max
     * @param valueLimitMin  the inputValue limit min
     * @param digitsLimitMax the digits limit max
     */
    ParaInt(boolean fixed, String unit, int valueLimitMax, int valueLimitMin, int digitsLimitMax) {
        super(fixed);
        this.unit = unit;
        ValueLimitMax = valueLimitMax;
        ValueLimitMin = valueLimitMin;
        this.digitsLimitMax = digitsLimitMax;
        this.inputValue = Integer.MAX_VALUE;
        this.inputCheck = new InputCheckInt(valueLimitMax, valueLimitMin, digitsLimitMax);
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
        this.known = this.inputValue != 0 && this.inputValue != Integer.MAX_VALUE;
        if (this.known) {
            this.resultValue = this.inputValue;
            this.firstValue = this.inputValue;
        } else {
            this.resultValue = Integer.MAX_VALUE;
            this.firstValue = Integer.MAX_VALUE;
        }
        this.firstValue = Integer.MAX_VALUE;
//        this.resultValue = this.inputValue;
        this.resultValueMax = -Integer.MAX_VALUE;
        this.resultValueMin = Integer.MAX_VALUE;
//        this.firstValue = this.inputValue;
        this.roundValue = Integer.MAX_VALUE;
        this.contradiction = Double.MAX_VALUE;
        this.roundContradiction = Integer.MAX_VALUE;
    }

    /**
     * Check limit boolean.
     *
     * @param number the number
     * @return the boolean
     */
    protected boolean checkLimit(int number) {
        if (number >= this.ValueLimitMin && number < this.ValueLimitMax) {
            this.known = true;
        } else {
            this.known = false;
            this.calculationSucceed = false;
        }
        return known;
    }

    /**
     * Refresh inputValue.
     *
     * @param number the number
     */
    protected void refreshValue(int number) {
        if (this.checkLimit(number)) {
            this.resultValue = number;
            this.refreshMinMax(number);
            this.fixed(number);
        } else {
            this.resultValue = Integer.MAX_VALUE;
            this.resultValueMax = -Integer.MAX_VALUE;
            this.resultValueMin = Integer.MAX_VALUE;
        }
    }

    /**
     * Fixed.
     *
     * @param number the number
     */
    private void fixed(int number) {
        if (this.first) {
            this.resultValue = firstValue;
        } else {
            this.firstValue = number;
        }
        if (this.fixed) {
            this.resultValue = this.inputValue;
            this.refreshMinMax(number);
        }
    }

    /**
     * Refresh min max.
     *
     * @param number the number
     */
    private void refreshMinMax(int number) {
        if (this.resultValueMin > number) {
            this.resultValueMin = number;
        }
        if (this.resultValueMax < number) {
            this.resultValueMax = number;
        }
    }


    @Override
    public String toString() {
        return "input value: " + this.inputValue + "; know: " + this.known + "; fixed: " + fixed + "; result value: " + resultValue + "; first value: " + firstValue + "; contradiction: " + contradiction + "; result value max: " + resultValueMax + "; result value min: " + resultValueMin + "; calculate success: " + calculationSucceed + "; round value: " + roundValue + "; round contradiction: " + contradiction;
    }

    /**
     * Add char input check result.
     *
     * @param c the c
     * @return the input check result
     */
    @Override
    public InputCheckResult addChar(char c) {
        return this.inputCheck.addChar(c);
    }

    /**
     * Gets inputValue.
     *
     * @return the inputValue
     */
    public int getInputValue() {
        return inputValue;
    }

    /**
     * Sets inputValue.
     *
     * @param inputValue the inputValue
     */
    public void setInputValue(int inputValue) {
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
        if (this.resultValue == Integer.MAX_VALUE) {
            return false;
        }
        this.roundValue = this.resultValue;
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
        this.roundContradiction = (int) this.contradiction;
        return true;
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
     * Gets inputValue limit max.
     *
     * @return the inputValue limit max
     */
    public int getValueLimitMax() {
        return ValueLimitMax;
    }

    /**
     * Sets inputValue limit max.
     *
     * @param valueLimitMax the inputValue limit max
     */
    public void setValueLimitMax(int valueLimitMax) {
        ValueLimitMax = valueLimitMax;
    }

    /**
     * Gets inputValue limit min.
     *
     * @return the inputValue limit min
     */
    public int getValueLimitMin() {
        return ValueLimitMin;
    }

    /**
     * Sets inputValue limit min.
     *
     * @param valueLimitMin the inputValue limit min
     */
    public void setValueLimitMin(int valueLimitMin) {
        ValueLimitMin = valueLimitMin;
    }

    /**
     * Gets digits limit max.
     *
     * @return the digits limit max
     */
    public int getDigitsLimitMax() {
        return digitsLimitMax;
    }

    /**
     * Sets digits limit max.
     *
     * @param digitsLimitMax the digits limit max
     */
    public void setDigitsLimitMax(int digitsLimitMax) {
        this.digitsLimitMax = digitsLimitMax;
    }

    /**
     * Gets result min.
     *
     * @return the result min
     */
    public int getResultValueMin() {
        return resultValueMin;
    }

    /**
     * Sets result min.
     *
     * @param resultValueMin the result min
     */
    public void setResultValueMin(int resultValueMin) {
        this.resultValueMin = resultValueMin;
    }

    /**
     * Gets result max.
     *
     * @return the result max
     */
    public int getResultValueMax() {
        return resultValueMax;
    }

    /**
     * Sets result max.
     *
     * @param resultValueMax the result max
     */
    public void setResultValueMax(int resultValueMax) {
        this.resultValueMax = resultValueMax;
    }

    /**
     * Gets result value.
     *
     * @return the result value
     */
    public int getResultValue() {
        return resultValue;
    }

    /**
     * Sets result value.
     *
     * @param resultValue the result value
     */
    public void setResultValue(int resultValue) {
        this.resultValue = resultValue;
    }

    /**
     * Gets first value.
     *
     * @return the first value
     */
    public int getFirstValue() {
        return firstValue;
    }

    /**
     * Sets first value.
     *
     * @param firstValue the first value
     */
    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    /**
     * Gets round value.
     *
     * @return the round value
     */
    public int getRoundValue() {
        return roundValue;
    }

    /**
     * Gets round contradiction.
     *
     * @return the round contradiction
     */
    public int getRoundContradiction() {
        return roundContradiction;
    }
}
