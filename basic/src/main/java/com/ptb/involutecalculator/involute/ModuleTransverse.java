package com.ptb.involutecalculator.involute;

import com.ptb.involutecalculator.math.TrigonometricUtil;



/**
 * The type Module transverse.
 *
 * @description
 * @create 2017 -05-09 下午2:51
 * @email spartajet.guo @gmail.com
 */
public class ModuleTransverse extends ParaReal {

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
    public ModuleTransverse(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
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

    public void calculateValue(ModuleNormal moduleNormal, IAngleHelix angleHelix, DiameterReference diameterReference, TeethNumber teethNumber, IAngleLead angleLead, IAnglePressure anglePressure, DiameterBase diameterBase, IAnglePressureNormal anglePressureNormal, ModuleBasic moduleBasic, ModuleAxial moduleAxial) {
        double mt = Double.MAX_VALUE;
        if (!(moduleNormal.isCalculationSucceed() && angleHelix.calculationSucceed() && diameterReference.isCalculationSucceed() && teethNumber.isCalculationSucceed() && angleLead.calculationSucceed() && anglePressure.calculationSucceed() && diameterBase.isCalculationSucceed() && anglePressureNormal.calculationSucceed() && moduleBasic.isCalculationSucceed() && moduleAxial.isCalculationSucceed())) {
            this.calculateCount++;
            this.calculationSucceed = false;
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = Double.MAX_VALUE;
            this.resultValueMin = -Double.MAX_VALUE;
            if (!this.calculation_09) {
                onceMore = true;
                this.calculation_09 = true;
            }
            return;
        }
        if (moduleNormal.isKnown() && angleHelix.known() && this.calculationSucceed) {
            mt = this.mt01(moduleNormal.getResultValue(), angleHelix.resultValue());
            this.calculateCount++;
            this.refreshValue(mt);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (diameterReference.isKnown() && teethNumber.isKnown() && this.calculationSucceed) {
            mt = this.mt02(diameterReference.getResultValue(), teethNumber.getResultValue());
            this.calculateCount++;
            this.refreshValue(mt);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (moduleNormal.isKnown() && angleHelix.known() && angleLead.known() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            mt = this.mt03(moduleNormal.getResultValue(), angleHelix.resultValue(), angleLead.resultValue());
            this.calculateCount++;
            this.refreshValue(mt);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (diameterBase.isKnown() && teethNumber.isKnown() && angleLead.known() && this.calculationSucceed) {
            mt = this.mt04(diameterBase.getResultValue(), teethNumber.getResultValue(), anglePressure.resultValue());
            this.calculateCount++;
            this.refreshValue(mt);
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
        if (moduleNormal.isKnown() && anglePressure.known() && anglePressureNormal.known() && angleHelix.known() && this.calculationSucceed) {
            mt = this.mt05(moduleNormal.getResultValue(), anglePressure.resultValue(), anglePressureNormal.resultValue(), angleHelix.resultValue());
            this.calculateCount++;
            this.refreshValue(mt);
            this.first = true;
            if (!this.calculation_05) {
                onceMore = true;
                this.calculation_05 = true;
            }
        }
        if (moduleBasic.isKnown() && anglePressure.known() && this.calculationSucceed) {
            mt = this.mt06(moduleBasic.getResultValue(), anglePressure.resultValue());
            this.calculateCount++;
            this.refreshValue(mt);
            this.first = true;
            if (!this.calculation_06) {
                onceMore = true;
                this.calculation_06 = true;
            }
        }
        if (moduleAxial.isKnown() && angleHelix.known() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            mt = this.mt07(moduleAxial.getResultValue(), angleHelix.resultValue());
            this.calculateCount++;
            this.refreshValue(mt);
            this.first = true;
            if (!this.calculation_07) {
                onceMore = true;
                this.calculation_07 = true;
            }
        }
        if (diameterBase.isKnown() && anglePressure.known() && teethNumber.isKnown() && this.calculationSucceed) {
            mt = this.mt08(diameterBase.getResultValue(), anglePressure.resultValue(), teethNumber.getResultValue());
            this.calculateCount++;
            this.refreshValue(mt);
            this.first = true;
            if (!this.calculation_08) {
                onceMore = true;
                this.calculation_08 = true;
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

    public void calculateContradiction(ModuleNormal moduleNormal, IAngleHelix angleHelix, DiameterReference diameterReference, TeethNumber teethNumber, IAngleLead angleLead, IAnglePressure anglePressure, DiameterBase diameterBase, IAnglePressureNormal anglePressureNormal, ModuleBasic moduleBasic, ModuleAxial moduleAxial) {
        if (this.resultValue == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && moduleNormal.isFixed() && angleHelix.fixed() && diameterReference.isFixed() && teethNumber.isFixed() && angleLead.fixed() && anglePressure.fixed() && diameterBase.isFixed() && anglePressureNormal.fixed() && moduleAxial.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterReference.isFixed() && teethNumber.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleNormal.isFixed() && angleHelix.fixed() && angleLead.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && teethNumber.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && anglePressure.fixed() && moduleNormal.isFixed() && anglePressureNormal.fixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleBasic.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleAxial.isFixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }
    }

    /**
     * Calculate transverse module according to equation 2 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param beta         beta inputValue
     * @return transverse module
     */
    private double mt01(double moduleNormal, double beta) {
        try {
            double moduleTransverse = moduleNormal / TrigonometricUtil.cos(beta);
            this.calculationSucceed = true;
            //logger.info("mt01 calculate success return value: " + moduleTransverse);
            return moduleTransverse;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mt01 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate transverse module according to equation 2.4 from DIN 3960
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterReference reference diameter inputValue
     * @param z                 numbers of teeth inputValue
     * @return transverse module
     */

    private double mt02(double diameterReference, int z) {
        try {
            double moduleTransverse = (diameterReference / z);
            this.calculationSucceed = true;
            //logger.info("mt02 calculate success return value: " + moduleTransverse);
            return moduleTransverse;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mt02 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate transverse module according to equation 3 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param beta         beta inputValue
     * @param gamma        gamma inputValue
     * @return transverse module
     */
    private double mt03(double moduleNormal, double beta, double gamma) {
        try {
            double moduleTransverse = ((moduleNormal * TrigonometricUtil.tan(beta)) / TrigonometricUtil.cos(gamma));
            this.calculationSucceed = true;
            //logger.info("mt03 calculate success return value: " + moduleTransverse);
            return moduleTransverse;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mt03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate transverse module according to equation 19 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterBase base diameter inputValue
     * @param z            numbers of teeth inputValue
     * @param alphaT       pressure angle inputValue
     * @return transverse module
     */
    private double mt04(double diameterBase, int z, double alphaT) {
        try {
            double moduleTransverse = diameterBase / (z * TrigonometricUtil.cos(alphaT));
            this.calculationSucceed = true;
            //logger.info("mt04 calculate success return value: " + moduleTransverse);
            return moduleTransverse;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mt04 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate transverse module according to equation 19 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param alphaT       pressure angle inputValue
     * @param alphaN       normal pressure angle inputValue
     * @param beta         beta inputValue
     * @return transverse module
     */
    private double mt05(double moduleNormal, double alphaT, double alphaN, double beta) {
        try {
            double moduleTransverse = moduleNormal / (TrigonometricUtil.cos(alphaT) * Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaN), 2) + Math.pow(TrigonometricUtil.cos(beta), 2)));
            this.calculationSucceed = true;
            //logger.info("mt05 calculate success return value: " + moduleTransverse);
            return moduleTransverse;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mt05 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate transverse module according to equation 2.5 from DIN 3960
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleBasic basic module inputValue
     * @param alphaT      pressure angle inputValue
     * @return transverse module
     */
    private double mt06(double moduleBasic, double alphaT) {
        try {
            double moduleTransverse = moduleBasic / TrigonometricUtil.cos(alphaT);
            this.calculationSucceed = true;
            //logger.info("mt06 calculate success return value: " + moduleTransverse);
            return moduleTransverse;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mt06 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate transverse module according to equation 3 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleAxial axial module inputValue
     * @param beta        beta inputValue
     * @return transverse module
     */
    private double mt07(double moduleAxial, double beta) {
        try {
            double moduleTransverse = moduleAxial * TrigonometricUtil.tan(beta);
            this.calculationSucceed = true;
            //logger.info("mt07 calculate success return value: " + moduleTransverse);
            return moduleTransverse;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mt07 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate transverse module according to equation 19 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterBase base diameter inputValue
     * @param alphaT       pressure angle inputValue
     * @param z            number of teeth inputValue
     * @return transverse module
     */
    private double mt08(double diameterBase, double alphaT, int z) {
        try {
            double moduleTransverse = (diameterBase * Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaT), 2) + 1)) / z;
            this.calculationSucceed = true;
            //logger.info("mt08 calculate success return value: " + moduleTransverse);
            return moduleTransverse;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mt08 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }
}
