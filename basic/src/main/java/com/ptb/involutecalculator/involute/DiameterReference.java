package com.ptb.involutecalculator.involute;

import com.ptb.involutecalculator.math.TrigonometricUtil;



/**
 * The type Diameter refrence.
 *
 * @description
 * @create 2017 -05-09 下午2:59
 * @email spartajet.guo @gmail.com
 */
public class DiameterReference extends ParaReal {

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
    public DiameterReference(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
        super(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit);
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        super.clear();
    }


    /**
     * Calculate inputValue boolean.
     *
     * @return the boolean
     */
    @Override
    protected boolean calculateValue() {
        return false;
    }

    public void calculateValue(ModuleTransverse moduleTransverse, IAngleHelix angleHelix, ModuleNormal moduleNormal, TeethNumber teethNumber, DiameterBase diameterBase, IAnglePressure anglePressure, IAnglePressureNormal anglePressureNormal, ModuleBasic moduleBasic) {
        double d = Double.MAX_VALUE;
        if (!(moduleTransverse.isCalculationSucceed() && angleHelix.calculationSucceed() && moduleNormal.isCalculationSucceed() && teethNumber.isCalculationSucceed() && diameterBase.isCalculationSucceed() && anglePressure.calculationSucceed() && anglePressureNormal.calculationSucceed() && moduleBasic.isCalculationSucceed() && this.calculationSucceed)) {
            this.calculateCount++;
            this.calculationSucceed = false;
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = Double.MAX_VALUE;
            this.resultValueMin = -Double.MAX_VALUE;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
            return;
        }
        if (moduleTransverse.isKnown() && teethNumber.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            d = this.d01(moduleTransverse.getResultValue(), teethNumber.getResultValue());
            this.refreshValue(d);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (teethNumber.isKnown() && moduleNormal.isKnown() && angleHelix.known() && this.calculationSucceed) {
            this.calculateCount++;
            d = this.d02(teethNumber.getResultValue(), moduleNormal.getResultValue(), angleHelix.resultValue());
            this.refreshValue(d);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (diameterBase.isKnown() && anglePressure.known() && this.calculationSucceed) {
            this.calculateCount++;
            d = this.d03(diameterBase.getResultValue(), anglePressure.resultValue());
            this.refreshValue(d);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (teethNumber.isKnown() && moduleNormal.isKnown() && anglePressure.known() && anglePressureNormal.known() && angleHelix.known() && this.calculationSucceed) {
            this.calculateCount++;
            d = this.d04(teethNumber.getResultValue(), moduleNormal.getResultValue(), anglePressure.resultValue(), anglePressureNormal.resultValue(), angleHelix.resultValue());
            this.refreshValue(d);
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
        if (moduleBasic.isKnown() && teethNumber.isKnown() && anglePressure.known() && this.calculationSucceed) {
            this.calculateCount++;
            d = this.d05(moduleBasic.getResultValue(), teethNumber.getResultValue(), anglePressure.resultValue());
            this.refreshValue(d);
            this.first = true;
            if (!this.calculation_05) {
                onceMore = true;
                this.calculation_05 = true;
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

    public void calculateContradiction(ModuleTransverse moduleTransverse, IAngleHelix angleHelix, ModuleNormal moduleNormal, TeethNumber teethNumber, DiameterBase diameterBase, IAnglePressure anglePressure, IAnglePressureNormal anglePressureNormal, ModuleBasic moduleBasic) {
        if (this.resultValue == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && moduleTransverse.isFixed() && angleHelix.fixed() && moduleNormal.isFixed() && teethNumber.isFixed() && diameterBase.isFixed() && anglePressure.fixed() && anglePressureNormal.fixed() && moduleBasic.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleTransverse.isFixed() && teethNumber.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && teethNumber.isFixed() && moduleNormal.isFixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleBasic.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && teethNumber.isFixed() && moduleNormal.isFixed() && anglePressure.fixed() && anglePressureNormal.fixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleBasic.isFixed() && teethNumber.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }
    }

    /**
     * Calculate reference diameter according to equation 1 from ISO 21771:2007
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param transverseModule transverse normal inputValue
     * @param z                numbers of teeth inputValue
     * @return reference diameter
     */
    private double d01(double transverseModule, int z) {
        try {
            double diameterReference = transverseModule * z;
            this.calculationSucceed = true;
            //logger.info("d01 calculate success return value: " + diameterReference);
            return diameterReference;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("d01 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate reference diameter according to equation 1 from ISO 21771:2007
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param z            numbers of teeth inputValue
     * @param moduleNormal normal module inputValue
     * @param beta         beta inputValue
     * @return reference diameter
     */
    private double d02(int z, double moduleNormal, double beta) {
        try {
            double diameterReference = ((z * moduleNormal) / TrigonometricUtil.cos(beta));
            this.calculationSucceed = true;
            //logger.info("d02 calculate success return value: " + diameterReference);
            return diameterReference;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("d02 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate reference diameter according to equation 13 from ISO 21771:2007
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterBase base diameter inputValue
     * @param alphaT       pressure angle inputValue
     * @return reference diameter
     */
    private double d03(double diameterBase, double alphaT) {
        try {
            double diameterReference = (diameterBase / TrigonometricUtil.cos(alphaT));
            this.calculationSucceed = true;
            //logger.info("d03 calculate success return value: " + diameterReference);
            return diameterReference;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("d03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate reference diameter according to equation 19 from ISO 21771:2007
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param z            numbers of teeth inputValue
     * @param moduleNormal normal module inputValue
     * @param alphaT       pressure angle inputValue
     * @param alphaN       normal pressure angle inputValue
     * @param beta         beta inputValue
     * @return reference diameter
     */
    private double d04(int z, double moduleNormal, double alphaT, double alphaN, double beta) {
        try {
            double diameterReference = ((z * moduleNormal) / (TrigonometricUtil.cos(alphaT) * Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaN), 2) + Math.pow(TrigonometricUtil.cos(beta), 2))));
            this.calculationSucceed = true;
            //logger.info("d04 calculate success return value: " + diameterReference);
            return diameterReference;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("d04 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate reference diameter according to equation 2.5 DIN 3960
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleBasic basic module inputValue
     * @param z           numbers of teeth inputValue
     * @param alphaT      pressure angle inputValue
     * @return reference diameter
     */
    private double d05(double moduleBasic, int z, double alphaT) {
        try {
            double diameterReference = (moduleBasic * z) / TrigonometricUtil.cos(alphaT);
            this.calculationSucceed = true;
            //logger.info("d05 calculate success return value: " + diameterReference);
            return diameterReference;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("d05 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }
}
