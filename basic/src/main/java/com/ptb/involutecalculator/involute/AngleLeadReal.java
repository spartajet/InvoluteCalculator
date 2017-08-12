package com.ptb.involutecalculator.involute;

import com.ptb.involutecalculator.math.TrigonometricUtil;

/**
 * @description
 * @create 2017-05-09 下午2:58
 * @email spartajet.guo@gmail.com
 */
public class AngleLeadReal extends ParaReal implements IAngleLead {

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
    public AngleLeadReal(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
        super(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit);
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        super.clear();
    }

    @Override
    protected boolean calculateValue() {
        return false;
    }

    @Override
    public void calculateValue(ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleAxial moduleAxial, IAngle angleHelix) {

        double gamma = Double.MAX_VALUE;
        if (!(moduleTransverse.isCalculationSucceed() && moduleNormal.isCalculationSucceed() && moduleAxial.isCalculationSucceed() && angleHelix.calculationSucceed())) {
            this.calculateCount++;
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = Double.MAX_VALUE;
            this.resultValueMin = -Double.MAX_VALUE;
            this.calculationSucceed = false;
            if (!calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
            return;
        }
        if (angleHelix.known() && this.calculationSucceed) {
            this.calculateCount++;
            gamma = this.gamma01(angleHelix.resultValue());
            this.refreshValue(gamma);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (moduleNormal.isKnown() && moduleAxial.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            gamma = this.gamma02(moduleNormal.getResultValue(), moduleAxial.getResultValue());
            this.refreshValue(gamma);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (moduleNormal.isKnown() && moduleAxial.isKnown() && angleHelix.known() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            this.calculateCount++;
            gamma = this.gamma03(moduleNormal.getResultValue(), angleHelix.resultValue(), moduleTransverse.getResultValue());
            this.refreshValue(gamma);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
    }

    @Override
    protected boolean calculateContradiction() {
        return false;
    }

    @Override
    public void calculateContradiction(ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleAxial moduleAxial, IAngle angleHelix) {
        if (this.resultValue == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && angleHelix.fixed() && moduleAxial.isFixed() && moduleTransverse.isFixed() && moduleNormal.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleNormal.isFixed() && moduleAxial.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleNormal.isFixed() && angleHelix.fixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }

    }

    /**
     * Calculate gamma according to equation 4.3.2 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param beta beta  inputValue
     * @return gamma calculated
     */
    private double gamma01(double beta) {
        if (beta < 0 || beta >= 90) {
            //logger.info("gamma01 calculate fail, return double max");
            this.calculationSucceed = false;
            return Double.MAX_VALUE;
        }
        double gamma = 90 - beta;
        //logger.info("gamma01 calculate success return value: " + gamma);
        this.calculationSucceed = true;
        return gamma;
    }


    /**
     * Calculate gamma according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param moduleAxial  axis module inputValue
     * @return gamma calculated
     */
    private double gamma02(double moduleNormal, double moduleAxial) {
        try {
            double temp = moduleNormal / moduleAxial;
            double gamma = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(gamma);
            //logger.info("gamma02 calculate success return value: " + degree);
            this.calculationSucceed = true;
            return degree;
        } catch (Exception e) {
            //logger.info("gamma02 calculate fail, return double max");
            this.calculationSucceed = false;
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate gamma according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param moduleNormal     normal module inputValue
     * @param beta             beta inputValue
     * @param moduleTransverse transverse module inputValue
     * @return beta calculated
     */
    private double gamma03(double moduleNormal, double beta, double moduleTransverse) {
        try {
            double temp = (moduleNormal * TrigonometricUtil.tan(beta)) / moduleTransverse;
            double gamma = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(gamma);
            //logger.info("gamma03 calculate success return value: " + degree);
            this.calculationSucceed = true;
            return degree;
        } catch (Exception e) {
            //logger.info("gamma03 calculate fail, return double max");
            this.calculationSucceed = false;
            return Double.MAX_VALUE;
        }
    }

    @Override
    public double resultValue() {
        return this.resultValue;
    }

    @Override
    public boolean known() {
        return super.known;
    }

    @Override
    public boolean calculationSucceed() {
        return super.calculationSucceed;
    }

    @Override
    public boolean fixed() {
        return super.fixed;
    }

    @Override
    public String getRoundValueString() {
        return String.valueOf(this.roundValue);
    }

    @Override
    public String getRoundContradictionString() {
        return String.valueOf(roundContradiction);
    }

    @Override
    public void setInputValue(Angle value) {
        this.inputValue = value.toReal();
    }

    @Override
    public String getInputValueString() {
        return String.valueOf(this.inputValue);
    }

//    @Override
//    public double getValueLimitMax() {
//        return super.getValueLimitMax();
//    }
}
