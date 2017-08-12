package com.ptb.involutecalculator.involute;

import com.ptb.involutecalculator.math.TrigonometricUtil;

/**
 * @description
 * @create 2017-06-15 上午11:37
 * @email spartajet.guo@gmail.com
 */
public class AnglePressureAngle extends ParaAngle implements IAnglePressure {
    /**
     * Instantiates a new Para angle.
     *
     * @param fixed                the fixed
     * @param unit                 the unit
     * @param valueLimitMax        the value limit max
     * @param valueLimitMin        the value limit min
     * @param digitsAfterDotSecond the digits after dot second
     */
    public AnglePressureAngle(boolean fixed, String unit, Angle valueLimitMax, Angle valueLimitMin, int digitsAfterDotSecond) {
        super(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond);
    }

    @Override
    public void calculateValue(IAngle anglePressureNormal, IAngle angleHelix, DiameterReference diameterReference, DiameterBase diameterBase, TeethNumber teethNumber, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleBasic moduleBasic) {
        double alphaT = Double.MAX_VALUE;
        if (!(anglePressureNormal.calculationSucceed() && angleHelix.calculationSucceed() && diameterReference.isCalculationSucceed() && diameterBase.isCalculationSucceed() && teethNumber.isCalculationSucceed() && moduleTransverse.isCalculationSucceed() && moduleBasic.isCalculationSucceed() && moduleNormal.isCalculationSucceed())) {
            this.calculationSucceed = false;
            this.resultValue = new Angle(Angle.ANGLE_MAX_VALUE);
            this.resultValueMax = new Angle(Angle.ANGLE_MAX_VALUE);
            this.resultValueMin = new Angle(Angle.ANGLE_MAX_VALUE);
            if (!this.calculation_08) {
                onceMore = true;
                this.calculation_08 = true;
            }
            return;
        }
        if (anglePressureNormal.known() && angleHelix.known() && this.calculationSucceed) {
            this.calculateCount++;
            alphaT = this.alphaT01(anglePressureNormal.resultValue(), angleHelix.resultValue());
            this.refreshValue(new Angle(alphaT));
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (diameterBase.isKnown() && diameterReference.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            alphaT = this.alphaT02(diameterBase.getResultValue(), diameterReference.getResultValue());
            this.refreshValue(new Angle(alphaT));
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (diameterBase.isKnown() && teethNumber.isKnown() && moduleTransverse.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            alphaT = this.alphaT03(diameterBase.getResultValue(), teethNumber.getResultValue(), moduleTransverse.getResultValue());
            this.refreshValue(new Angle(alphaT));
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (diameterBase.isKnown() && angleHelix.known() && teethNumber.isKnown() && moduleNormal.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            alphaT = this.alphaT04(diameterBase.getResultValue(), angleHelix.resultValue(), teethNumber.getResultValue(), moduleTransverse.getResultValue());
            this.refreshValue(new Angle(alphaT));
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
        if (moduleBasic.isKnown() && angleHelix.known() && moduleNormal.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            alphaT = this.alphaT05(moduleBasic.getResultValue(), angleHelix.resultValue(), moduleNormal.getResultValue());
            this.refreshValue(new Angle(alphaT));
            this.first = true;
            if (!this.calculation_05) {
                onceMore = true;
                this.calculation_05 = true;
            }
        }
        if (moduleBasic.isKnown() && moduleTransverse.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            alphaT = this.alphaT06(moduleBasic.getResultValue(), moduleTransverse.getResultValue());
            this.refreshValue(new Angle(alphaT));
            this.first = true;
            if (!this.calculation_06) {
                onceMore = true;
                this.calculation_06 = true;
            }
        }
        if (moduleBasic.isKnown() && teethNumber.isKnown() && diameterReference.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            alphaT = this.alphaT07(moduleBasic.getResultValue(), teethNumber.getResultValue(), diameterReference.getResultValue());
            this.refreshValue(new Angle(alphaT));
            this.first = true;
            if (!this.calculation_07) {
                onceMore = true;
                this.calculation_07 = true;
            }
        }
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

    @Override
    public void calculateContradiction(IAngle anglePressureNormal, IAngle angleHelix, DiameterReference diameterReference, DiameterBase diameterBase, TeethNumber teethNumber, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleBasic moduleBasic) {
        if (this.resultValue.equals(Angle.ANGLE_MAX_VALUE)) {
            this.contradiction = new Angle(Angle.ANGLE_MAX_VALUE);
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && anglePressureNormal.fixed() && angleHelix.fixed() && diameterReference.isFixed() && diameterBase.isFixed() && teethNumber.isFixed() && moduleTransverse.isFixed() && moduleNormal.isFixed() && moduleBasic.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && anglePressureNormal.fixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && diameterBase.isFixed() && diameterReference.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && diameterBase.isFixed() && teethNumber.isFixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && diameterBase.isFixed() && angleHelix.fixed() && teethNumber.isFixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && diameterBase.isFixed() && angleHelix.fixed() && teethNumber.isFixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleBasic.isFixed() && angleHelix.fixed() && moduleNormal.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleBasic.isFixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleBasic.isFixed() && teethNumber.isFixed() && diameterReference.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else {
            this.contradiction = new Angle();
        }
    }

    /**
     * Calculate pressure angle according to equation 14 from ISO 21771:2007 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param alphaN normal pressure angle inputValue
     * @param beta   beta inputValue
     * @return pressure angle calculated
     */
    private double alphaT01(double alphaN, double beta) {
        try {
            double alphaT = Math.atan(TrigonometricUtil.tan(alphaN) / TrigonometricUtil.cos(beta));
            double degree = TrigonometricUtil.Radian2Grad(alphaT);
            this.calculationSucceed = true;
            //logger.info("alphaT01 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaT01 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate pressure angle according to equation 2 from ISO 21771:2007 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param baseDiameter      base diameter inputValue
     * @param referenceDiameter reference diameter inputValue
     * @return pressure angle calculated
     */
    private double alphaT02(double baseDiameter, double referenceDiameter) {
        try {
            double temp = baseDiameter / referenceDiameter;
            double alphaT = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(alphaT);
            this.calculationSucceed = true;
            //logger.info("alphaT02 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaT02 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate pressure angle according to equation 4.3.2 from ISO 21771:2007 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param baseDiameter     base diameter inputValue
     * @param z                numbers of teeth inputValue
     * @param transverseModule transverse module inputValue
     * @return pressure angle calculated
     */
    private double alphaT03(double baseDiameter, int z, double transverseModule) {
        try {
            double temp = baseDiameter / (z * transverseModule);
            double alphaT = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(alphaT);
            this.calculationSucceed = true;
            //logger.info("alphaT03 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaT03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate pressure angle according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param baseDiameter     base diameter inputValue
     * @param beta             beta inputValue
     * @param z                numbers of teeth inputValue
     * @param transverseModule transverse module inputValue
     * @return pressure angle calculated
     */
    private double alphaT04(double baseDiameter, double beta, int z, double transverseModule) {
        try {
            double temp = baseDiameter * TrigonometricUtil.cos(beta) / (z * transverseModule);
            double alphaT = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(alphaT);
            this.calculationSucceed = true;
            //logger.info("alphaT04 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaT04 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate pressure angle according to equation 2.5 from DIN 3960 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param basicModule  basic module inputValue
     * @param beta         beta inputValue
     * @param normalModule normal module inputValue
     * @return pressure angle calculated
     */
    private double alphaT05(double basicModule, double beta, double normalModule) {
        try {
            double temp = (basicModule * TrigonometricUtil.cos(beta)) / normalModule;
            double alphaT = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(alphaT);
            this.calculationSucceed = true;
            //logger.info("alphaT05 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaT05 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate pressure angle according to equation 2.5 from DIN 3960 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param basicModule      basic module inputValue
     * @param transverseModule transverse module inputValue
     * @return pressure angle calculated
     */
    private double alphaT06(double basicModule, double transverseModule) {
        try {
            double temp = basicModule / transverseModule;
            double alphaT = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(alphaT);
            this.calculationSucceed = true;
            //logger.info("alphaT06 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaT06 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate pressure angle according to equation 2.5 from DIN 3960 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param basicModule       basic module inputValue
     * @param z                 numbers of teeth inputValue
     * @param referenceDiameter reference diameter inputValue
     * @return pressure angle calculated
     */
    private double alphaT07(double basicModule, int z, double referenceDiameter) {
        try {
            double temp = basicModule * z / referenceDiameter;
            double alphaT = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(alphaT);
            this.calculationSucceed = true;
            //logger.info("alphaT07 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaT07 calculate fail, return double max");
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
