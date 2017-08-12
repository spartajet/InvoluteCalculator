package com.ptb.involutecalculator.involute;

import com.ptb.involutecalculator.math.TrigonometricUtil;



/**
 * @description
 * @create 2017-05-15 上午10:26
 * @email spartajet.guo@gmail.com
 */
public class AnglePressureNormalReal extends ParaReal implements IAnglePressureNormal {

    //logger //logger = LogManager.get//logger(AnglePressureNormalReal.class);

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
    public AnglePressureNormalReal(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
        super(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit);
    }

    @Override
    protected boolean calculateValue() {
        return false;
    }

    @Override
    public void calculateValue(IAngle angleHelix, IAngle anglePressure, TeethNumber teethNumber, ModuleNormal moduleNormal, DiameterBase diameterBase, ModuleBasic moduleBasic, ModuleTransverse moduleTransverse) {
        double alphaN = Double.MAX_VALUE;
        if (!(angleHelix.calculationSucceed() && anglePressure.calculationSucceed() && teethNumber.isCalculationSucceed() && moduleNormal.isCalculationSucceed() && diameterBase.isCalculationSucceed() && moduleBasic.isCalculationSucceed() && moduleTransverse.isCalculationSucceed())) {
            this.calculateCount++;
            this.calculationSucceed = false;
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = -Double.MAX_VALUE;
            this.resultValueMin = Double.MAX_VALUE;
            if (!calculation_05) {
                onceMore = true;
                this.calculation_05 = true;
            }
            return;
        }
        if (angleHelix.known() && anglePressure.known() && this.calculationSucceed) {
            this.calculateCount++;
            alphaN = alphaN01(angleHelix.resultValue(), anglePressure.resultValue());
            this.refreshValue(alphaN);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (teethNumber.isKnown() && moduleNormal.isKnown() && diameterBase.isKnown() && angleHelix.known() && this.calculationSucceed) {
            this.calculateCount++;
            alphaN = alphaN02(teethNumber.getResultValue(), moduleNormal.getResultValue(), diameterBase.getResultValue(), angleHelix.resultValue());
            this.refreshValue(alphaN);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (moduleNormal.isKnown() && moduleBasic.isKnown() && angleHelix.known() && this.calculationSucceed) {
            this.calculateCount++;
            alphaN = alphaN03(moduleNormal.getResultValue(), moduleBasic.getResultValue(), angleHelix.resultValue());
            this.refreshValue(alphaN);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (moduleNormal.isKnown() && moduleTransverse.isKnown() && anglePressure.known() && this.calculationSucceed) {
            this.calculateCount++;
            alphaN = alphaN04(moduleNormal.getResultValue(), moduleTransverse.getResultValue(), anglePressure.resultValue());
            this.refreshValue(alphaN);
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
    }


    @Override
    protected boolean calculateContradiction() {
        return false;
    }

    @Override
    public void calculateContradiction(IAngle angleHelix, IAngle anglePressure, TeethNumber teethNumber, ModuleNormal moduleNormal, DiameterBase diameterBase, ModuleBasic moduleBasic, ModuleTransverse moduleTransverse) {
        if (this.resultValue == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && angleHelix.fixed() && anglePressure.fixed() && teethNumber.isFixed() && moduleNormal.isFixed() && diameterBase.isFixed() && moduleBasic.isFixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && angleHelix.fixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (teethNumber.isFixed() && moduleNormal.isFixed() && diameterBase.isFixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (moduleNormal.isFixed() && moduleBasic.isFixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleNormal.isFixed() && moduleTransverse.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }
    }

    /**
     * Calculate normal pressure angle according to equation 14 from ISO 21771:2007 If the calculation
     * is succeed normal pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param beta   beta value
     * @param alphaT pressure angle value
     * @return normal pressure angle calculated
     */
    private double alphaN01(double beta, double alphaT) {
        try {
            double alphaN = Math.atan(TrigonometricUtil.cos(beta) * TrigonometricUtil.tan(alphaT));
            double degree = TrigonometricUtil.Radian2Grad(alphaN);
            //logger.info("alphaN01 calculate success return value: " + degree);
            this.calculationSucceed = true;
            return degree;
        } catch (Exception e) {
            //logger.info("alphaN01 calculate fail, return double max");
            this.calculationSucceed = false;
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal pressure angle according to equation 19 from ISO 21771:2007 If the calculation
     * is succeed normal pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param z            numbers of teeth value
     * @param moduleNormal module normal value
     * @param diameterBase base diameter value
     * @param beta         beta value
     * @return normal pressure angle calculated
     */
    private double alphaN02(int z, double moduleNormal, double diameterBase, double beta) {
        try {
            double alphaN = Math.atan(Math.sqrt(Math.pow(((z * moduleNormal) / diameterBase), 2) - Math.pow(TrigonometricUtil.cos(beta), 2)));
            double degree = TrigonometricUtil.Radian2Grad(alphaN);
            this.calculationSucceed = true;
            //logger.info("alphaN02 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaN02 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal pressure angle according to equation 14 from ISO 21771:2007 If the calculation
     * is succeed normal pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param moduleNormal normal module value
     * @param moduleBasic  basic module value
     * @param beta         beta value
     * @return normal pressure angle calculated
     */
    private double alphaN03(double moduleNormal, double moduleBasic, double beta) {
        try {
            double alphaN = Math.atan(Math.sqrt((Math.pow(moduleNormal, 2) / Math.pow(moduleBasic, 2)) - Math.pow(TrigonometricUtil.cos(beta), 2)));
            double degree = TrigonometricUtil.Radian2Grad(alphaN);
            this.calculationSucceed = true;
            //logger.info("alphaN03 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaN03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal pressure angle calculated according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed normal pressure angle calculated is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param moduleNormal     normal module value
     * @param moduleTransverse transverse module value
     * @param alfaT            pressure angle value
     * @return normal pressure angle calculated
     */
    private double alphaN04(double moduleNormal, double moduleTransverse, double alfaT) {
        try {
            double alphaN = Math.atan((moduleNormal / moduleTransverse) * TrigonometricUtil.tan(alfaT));
            double degree = TrigonometricUtil.Radian2Grad(alphaN);
            this.calculationSucceed = true;
            //logger.info("alphaN04 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("alphaN04 calculate fail, return double max");
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
}
