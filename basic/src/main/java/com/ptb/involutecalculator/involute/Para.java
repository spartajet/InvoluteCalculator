package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.ICheckable;
import com.ptb.involutecalculator.io.check.InputCheck;
import com.ptb.involutecalculator.io.check.InputCheckResult;

import java.util.Objects;

/**
 * The type Para.
 *
 * @description
 * @create 2017 -05-09 下午1:55
 * @email spartajet.guo @gmail.com
 */
public abstract class Para implements ICheckable {
    /**
     * The Input check.
     */
    protected InputCheck inputCheck;
    /**
     * The Check result.
     */
//    protected InputCheckResult checkResult;
    /**
     * The Fixed.
     */
    protected boolean fixed;

    /**
     * The Value string.
     */
    protected String valueString;
    /**
     * The Calculate count.
     */
    protected int calculateCount;

    /**
     * Calculate inputValue boolean.
     *
     * @return the boolean
     */
    protected abstract boolean calculateValue();

    /**
     * The Contradiction.
     */
    protected double contradiction;
    /**
     * The known.
     */
    protected boolean known;
    /**
     * The Calculation succeed.
     */
    protected boolean calculationSucceed;
    /**
     * The First.
     */
    protected boolean first;
    /**
     * The Run.
     */
    protected boolean run;
    /**
     * The Once more.
     */
    protected static boolean onceMore;

    /**
     * The Calculation 01.
     */
    protected boolean calculation_01;
    /**
     * The Calculation 02.
     */
    protected boolean calculation_02;
    /**
     * The Calculation 03.
     */
    protected boolean calculation_03;
    /**
     * The Calculation 04.
     */
    protected boolean calculation_04;
    /**
     * The Calculation 05.
     */
    protected boolean calculation_05;
    /**
     * The Calculation 06.
     */
    protected boolean calculation_06;
    /**
     * The Calculation 07.
     */
    protected boolean calculation_07;
    /**
     * The Calculation 08.
     */
    protected boolean calculation_08;
    /**
     * The Calculation 09.
     */
    protected boolean calculation_09;
    /**
     * The Calculation 10.
     */
    protected boolean calculation_10;
    /**
     * The Calculation 11.
     */
    protected boolean calculation_11;

    /**
     * Calculate contradiction boolean.
     *
     * @return the boolean
     */
    protected abstract boolean calculateContradiction();

    /**
     * Round value boolean.
     *
     * @return the boolean
     */
    public abstract boolean roundValue();

    /**
     * Round contradiction boolean.
     *
     * @return the boolean
     */
    public abstract boolean roundContradiction();

    /**
     * Add char input check result.
     *
     * @param c the c
     * @return the input check result
     */
    protected abstract InputCheckResult addChar(char c);

    /**
     * Clear.
     */
    public void clear() {
        this.fixed = false;
        this.valueString = "";
        this.known = false;
        this.inputCheck.clear();
        this.refresh();
    }

    /**
     * Refresh.
     */
    public void refresh() {
        this.calculationSucceed = true;
        this.contradiction = Double.MAX_VALUE;
        this.calculateCount = 0;
        this.calculation_01 = false;
        this.calculation_02 = false;
        this.calculation_03 = false;
        this.calculation_04 = false;
        this.calculation_05 = false;
        this.calculation_06 = false;
        this.calculation_07 = false;
        this.calculation_08 = false;
        this.calculation_09 = false;
        this.calculation_10 = false;
        this.calculation_11 = false;
        onceMore = false;
        this.first = false;
    }

    /**
     * Instantiates a new Para.
     *
     * @param fixed the fixed
     */
    Para(boolean fixed) {
        this.fixed = fixed;
        this.contradiction = 0;
        this.valueString = "";
        this.known = false;
        this.calculationSucceed = true;
        this.calculateCount = 0;
        this.first = false;
        this.run = false;
        onceMore = false;
        this.calculation_01 = false;
        this.calculation_02 = false;
        this.calculation_03 = false;
        this.calculation_04 = false;
        this.calculation_05 = false;
        this.calculation_06 = false;
        this.calculation_07 = false;
        this.calculation_08 = false;
        this.calculation_09 = false;
        this.calculation_10 = false;
        this.calculation_11 = false;
    }

    /**
     * Gets input check.
     *
     * @return the input check
     */
    @Override
    public InputCheck getInputCheck() {
        return inputCheck;
    }

    /**
     * Sets input check.
     *
     * @param inputCheck the input check
     */
    public void setInputCheck(InputCheck inputCheck) {
        this.inputCheck = inputCheck;
    }

    /**
     * Is fixed boolean.
     *
     * @return the boolean
     */
    public boolean isFixed() {
        return fixed;
    }

    /**
     * Sets fixed.
     *
     * @param fixed the fixed
     */
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    /**
     * Gets contradiction.
     *
     * @return the contradiction
     */
    public double getContradiction() {
        return contradiction;
    }

    /**
     * Gets inputValue string.
     *
     * @return the inputValue string
     */
    public String getValueString() {
        return valueString;
    }

    /**
     * Sets inputValue string.
     *
     * @param valueString the inputValue string
     */
    public void setValueString(String valueString) {
        this.valueString = valueString;
        this.known = !Objects.equals(valueString, "");
    }

    /**
     * Is known boolean.
     *
     * @return the boolean
     */
    public boolean isKnown() {
        return known;
    }

    /**
     * Sets known.
     *
     * @param known the known
     */
    public void setKnown(boolean known) {
        this.known = known;
    }

    /**
     * Is calculation succeed boolean.
     *
     * @return the boolean
     */
    public boolean isCalculationSucceed() {
        return calculationSucceed;
    }

    /**
     * Sets calculation succeed.
     *
     * @param calculationSucceed the calculation succeed
     */
    public void setCalculationSucceed(boolean calculationSucceed) {
        this.calculationSucceed = calculationSucceed;
    }

    /**
     * Gets calculate count.
     *
     * @return the calculate count
     */
    public int getCalculateCount() {
        return calculateCount;
    }

    /**
     * Sets calculate count.
     *
     * @param calculateCount the calculate count
     */
    public void setCalculateCount(int calculateCount) {
        this.calculateCount = calculateCount;
    }

    /**
     * Is once more boolean.
     *
     * @return the boolean
     */
    public static boolean isOnceMore() {
        return onceMore;
    }

    /**
     * Sets once more.
     *
     * @param onceMore the once more
     */
    public static void setOnceMore(boolean onceMore) {
        Para.onceMore = onceMore;
    }
}
