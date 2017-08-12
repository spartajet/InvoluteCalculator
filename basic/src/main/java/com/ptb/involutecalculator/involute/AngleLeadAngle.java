package com.ptb.involutecalculator.involute;

import com.ptb.involutecalculator.math.TrigonometricUtil;

/**
 * @description
 * @create 2017-06-15 上午11:26
 * @email spartajet.guo@gmail.com
 */
public class AngleLeadAngle extends ParaAngle implements IAngleLead {

    /**
     * Instantiates a new Para angle.
     *
     * @param fixed                the fixed
     * @param unit                 the unit
     * @param valueLimitMax        the value limit max
     * @param valueLimitMin        the value limit min
     * @param digitsAfterDotSecond the digits after dot second
     */
    public AngleLeadAngle(boolean fixed, String unit, Angle valueLimitMax, Angle valueLimitMin, int digitsAfterDotSecond) {
        super(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond);
    }

    @Override
    public void calculateValue(ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleAxial moduleAxial, IAngle angleHelix) {

        double gamma = Double.MAX_VALUE;
        if (!(moduleTransverse.isCalculationSucceed() && moduleNormal.isCalculationSucceed() && moduleAxial.isCalculationSucceed() && angleHelix.calculationSucceed())) {
            this.calculateCount++;
            this.resultValue = new Angle(Angle.ANGLE_MAX_VALUE);
            this.resultValueMax = new Angle(Angle.ANGLE_MAX_VALUE);
            this.resultValueMin = new Angle(Angle.ANGLE_MAX_VALUE);
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
            this.refreshValue(new Angle(gamma));
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (moduleNormal.isKnown() && moduleAxial.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            gamma = this.gamma02(moduleNormal.getResultValue(), moduleAxial.getResultValue());
            this.refreshValue(new Angle(gamma));
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (moduleNormal.isKnown() && moduleAxial.isKnown() && angleHelix.known() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            this.calculateCount++;
            gamma = this.gamma03(moduleNormal.getResultValue(), angleHelix.resultValue(), moduleTransverse.getResultValue());
            this.refreshValue(new Angle(gamma));
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
        if (this.resultValue.equals(Angle.ANGLE_MAX_VALUE)) {
            this.contradiction = new Angle(Double.MAX_VALUE);
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && angleHelix.fixed() && moduleAxial.isFixed() && moduleTransverse.isFixed() && moduleNormal.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleNormal.isFixed() && moduleAxial.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleNormal.isFixed() && angleHelix.fixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else {
            this.contradiction = new Angle();
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
        return this.resultValue.toReal();
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
        return this.roundValue.toString();
    }

    @Override
    public String getRoundContradictionString() {
        return this.roundContradiction.toString();
    }

    @Override
    public String getInputValueString() {
        return this.inputValue.toString();
    }

}
