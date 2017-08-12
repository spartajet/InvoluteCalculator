package com.ptb.involutecalculator.involute;

import com.ptb.involutecalculator.math.TrigonometricUtil;



/**
 * The type Diameter base.
 *
 * @description
 * @create 2017 -05-09 下午2:59
 * @email spartajet.guo @gmail.com
 */
public class DiameterBase extends ParaReal {
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
    public DiameterBase(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
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

    /**
     * Calculate value.
     *
     * @param diameterReference the diameter reference
     * @param anglePressure     the angle pressure real
     * @param teethNumber       the teeth number
     * @param moduleTransverse  the module transverse
     * @param moduleNormal      the module normal
     * @param angleHelix        the angle helix real
     * @param moduleBasic       the module basic
     */
    public void calculateValue(DiameterReference diameterReference, IAnglePressure anglePressure, TeethNumber teethNumber, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, IAngleHelix angleHelix, ModuleBasic moduleBasic) {
        double db = Double.MAX_VALUE;
        if (!(diameterReference.isCalculationSucceed() && anglePressure.calculationSucceed() && teethNumber.isCalculationSucceed() && moduleTransverse.isCalculationSucceed() && moduleNormal.isCalculationSucceed() && angleHelix.calculationSucceed() && moduleBasic.isCalculationSucceed())) {
            this.calculateCount++;
            this.calculationSucceed = false;
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = Double.MAX_VALUE;
            this.resultValueMin = -Double.MAX_VALUE;
            if (!this.calculation_05) {
                onceMore = true;
                calculation_05 = true;
            }
            return;
        }
        if (diameterReference.isKnown() && anglePressure.known() && this.calculationSucceed) {
            this.calculateCount++;
            db = this.db01(diameterReference.getResultValue(), anglePressure.resultValue());
            this.refreshValue(db);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (teethNumber.isKnown() && moduleTransverse.isKnown() && anglePressure.known() && this.calculationSucceed) {
            this.calculateCount++;
            db = this.db02(teethNumber.getResultValue(), moduleTransverse.getResultValue(), anglePressure.resultValue());
            this.refreshValue(db);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (teethNumber.isKnown() && moduleNormal.isKnown() && anglePressure.known() && angleHelix.known() && this.calculationSucceed) {
            this.calculateCount++;
            db = this.db03(teethNumber.getResultValue(), moduleNormal.getResultValue(), anglePressure.resultValue(), angleHelix.resultValue());
            this.refreshValue(db);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (teethNumber.isKnown() && moduleBasic.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            db = this.db04(teethNumber.getResultValue(), moduleBasic.getResultValue());
            this.refreshValue(db);
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
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

    /**
     * Calculate contradiction.
     *
     * @param diameterReference the diameter reference
     * @param anglePressure     the angle pressure real
     * @param teethNumber       the teeth number
     * @param moduleTransverse  the module transverse
     * @param moduleNormal      the module normal
     * @param angleHelix        the angle helix real
     * @param moduleBasic       the module basic
     */
    public void calculateContradiction(DiameterReference diameterReference, IAnglePressure anglePressure, TeethNumber teethNumber, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, IAngleHelix angleHelix, ModuleBasic moduleBasic) {
        if (this.resultValue == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && diameterReference.isFixed() && anglePressure.fixed() && teethNumber.isFixed() && moduleTransverse.isFixed() && moduleNormal.isFixed() && angleHelix.fixed() && moduleBasic.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterReference.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && teethNumber.isFixed() && moduleTransverse.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && teethNumber.isFixed() && moduleNormal.isFixed() && anglePressure.fixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleTransverse.isFixed() && teethNumber.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && teethNumber.isFixed() && moduleBasic.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }
    }

    /**
     * Calculate base diameter according to equation 13 from ISO 21771:2007
     * If the calculation is succeed base diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterReference reference diameter inputValue
     * @param alphaT            pressure angle inputValue
     * @return base diameter
     */
    private double db01(double diameterReference, double alphaT) {
        try {
            double diameterBase = (diameterReference * TrigonometricUtil.cos(alphaT));
            this.calculationSucceed = true;
            //logger.info("db01 calculate success return value: " + diameterBase);
            return diameterBase;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("db01 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate base diameter according to equation 19 from ISO 21771:2007
     * If the calculation is succeed base diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param z                numbers of teeth inputValue
     * @param moduleTransverse transverse module inputValue
     * @param alphaT           pressure angle inputValue
     * @return base diameter
     */
    private double db02(int z, double moduleTransverse, double alphaT) {
        try {
            double diameterBase = z * moduleTransverse * TrigonometricUtil.cos(alphaT);
            this.calculationSucceed = true;
            //logger.info("db02 calculate success return value: " + diameterBase);
            return diameterBase;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("db02 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate base diameter according to equation 19 from ISO 21771:2007
     * If the calculation is succeed base diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param z            numbers of teeth inputValue
     * @param moduleNormal normal module inputValue
     * @param alphaT       pressure angle inputValue
     * @param beta         beta inputValue
     * @return base diameter
     */
    private double db03(int z, double moduleNormal, double alphaT, double beta) {
        try {
            double diameterBase = (z * moduleNormal * (TrigonometricUtil.cos(alphaT) / TrigonometricUtil.cos(beta)));
            this.calculationSucceed = true;
            //logger.info("db03 calculate success return value: " + diameterBase);
            return diameterBase;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("db03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate base diameter according to equation 2.5 from DIN 3960
     * If the calculation is succeed base diameter is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param z           numbers of teeth inputValue
     * @param moduleBasic basic module inputValue
     * @return base diameter
     */
    private double db04(int z, double moduleBasic) {
        try {
            double diameterBase = z * moduleBasic;
            this.calculationSucceed = true;
            //logger.info("db04 calculate success return value: " + diameterBase);
            return diameterBase;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("db04 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }
}
