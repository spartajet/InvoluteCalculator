package com.ptb.involutecalculator.involute;

import com.ptb.involutecalculator.math.TrigonometricUtil;



/**
 * The type Teeth number.
 *
 * @description
 * @create 2017 -04-20 下午1:42
 * @email spartajet.guo @gmail.com
 */
public class TeethNumber extends ParaInt {
    /**
     * Instantiates a new Teeth number.
     *
     * @param fixed          the fixed
     * @param unit           the unit
     * @param valueLimitMax  the inputValue limit max
     * @param valueLimitMin  the inputValue limit min
     * @param digitsLimitMax the digits limit max
     */
    public TeethNumber(boolean fixed, String unit, int valueLimitMax, int valueLimitMin, int digitsLimitMax) {
        super(fixed, unit, valueLimitMax, valueLimitMin, digitsLimitMax);
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
     * @param diameterReference   the diameter reference
     * @param moduleTransverse    the module transverse
     * @param angleHelix          the angle helix real
     * @param moduleNormal        the module normal
     * @param diameterBase        the diameter base
     * @param anglePressure       the angle pressure real
     * @param angleLead           the angle lead real
     * @param moduleBasic         the module basic
     * @param anglePressureNormal the angle pressure normal real
     */
    public void calculateContradiction(DiameterReference diameterReference, ModuleTransverse moduleTransverse, IAngleHelix angleHelix, ModuleNormal moduleNormal, DiameterBase diameterBase, IAnglePressure anglePressure, IAngleLead angleLead, ModuleBasic moduleBasic, IAnglePressureNormal anglePressureNormal) {
        if (this.resultValue == Integer.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && diameterReference.isFixed() && moduleTransverse.isFixed() && angleHelix.fixed() && moduleNormal.isFixed() && diameterBase.isFixed() && anglePressure.fixed() && anglePressureNormal.fixed() && moduleBasic.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterReference.isFixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterReference.isFixed() && angleHelix.fixed() && moduleNormal.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && moduleTransverse.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && anglePressureNormal.fixed() && angleHelix.fixed() && moduleNormal.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && anglePressure.fixed() && moduleTransverse.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && moduleBasic.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }

    }

    /**
     * Calculate inputValue.
     *
     * @param diameterReference   the diameter reference
     * @param moduleTransverse    the module transverse
     * @param angleHelix          the angle helix real
     * @param moduleNormal        the module normal
     * @param diameterBase        the diameter base
     * @param anglePressure       the angle pressure real
     * @param angleLead           the angle lead real
     * @param moduleBasic         the module basic
     * @param anglePressureNormal the angle pressure normal real
     */
    public void calculateValue(DiameterReference diameterReference, ModuleTransverse moduleTransverse, IAngleHelix angleHelix, ModuleNormal moduleNormal, DiameterBase diameterBase, IAnglePressure anglePressure, IAngleLead angleLead, ModuleBasic moduleBasic, IAnglePressureNormal anglePressureNormal) {
        int z = Integer.MAX_VALUE;
        if (!(diameterReference.isCalculationSucceed() && moduleTransverse.isCalculationSucceed() && angleHelix.calculationSucceed() && moduleNormal.isCalculationSucceed() && diameterBase.isCalculationSucceed() && anglePressure.calculationSucceed() && angleLead.calculationSucceed() && moduleBasic.isCalculationSucceed() && anglePressureNormal.calculationSucceed())) {
            this.calculateCount++;
            this.resultValue = Integer.MAX_VALUE;
            this.resultValueMax = Integer.MAX_VALUE;
            this.resultValueMin = Integer.MIN_VALUE;
            if (!this.calculation_07) {
                onceMore = true;
                this.calculationSucceed = false;
            }
            return;
        }
        if (diameterReference.isKnown() && moduleTransverse.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            z = this.z01(diameterReference.getResultValue(), moduleTransverse.getResultValue());
            this.refreshValue(z);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (diameterReference.isKnown() && angleHelix.known() && moduleNormal.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            z = this.z02(diameterReference.getResultValue(), angleHelix.resultValue(), moduleNormal.getResultValue());
            this.refreshValue(z);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (diameterBase.isKnown() && moduleTransverse.isKnown() && anglePressure.known() && this.calculationSucceed) {
            this.calculateCount++;
            z = this.z03(diameterBase.getResultValue(), moduleTransverse.getResultValue(), anglePressure.resultValue());
            this.refreshValue(z);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (diameterBase.isKnown() && anglePressureNormal.known() && angleHelix.known() && moduleNormal.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            z = this.z04(diameterBase.getResultValue(), anglePressureNormal.resultValue(), angleHelix.resultValue(), moduleNormal.getResultValue());
            this.refreshValue(z);
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
        if (diameterBase.isKnown() && anglePressure.known() && moduleTransverse.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            z = this.z05(diameterBase.getResultValue(), anglePressure.resultValue(), moduleTransverse.getResultValue());
            this.refreshValue(z);
            this.first = true;
            if (!this.calculation_05) {
                onceMore = true;
                this.calculation_05 = true;
            }
        }
        if (diameterBase.isKnown() && moduleBasic.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            z = z06(diameterBase.getResultValue(), moduleBasic.getResultValue());
            this.refreshValue(z);
            this.first = true;
            if (!this.calculation_06) {
                onceMore = true;
                this.calculation_06 = true;
            }
        }

    }

    /**
     * Calculate numbers of teeth according to equation 1 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI
     *
     * @param diameterReference reference diameter inputValue
     * @param moduleTransverse  transverse module inputValue
     * @return numbers of teeth
     */
    private int z01(double diameterReference, double moduleTransverse) {
        try {
            int teethNumber = (int) Math.round(diameterReference / moduleTransverse);
            this.calculationSucceed = true;
            //logger.info("z01 calculate success return value: " + teethNumber);
            return teethNumber;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("z01 calculate fail, return double max");
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Calculate numbers of teeth according to equation 1 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI
     *
     * @param diameterReference reference diameter inputValue
     * @param beta              beta inputValue
     * @param moduleNormal      normal module inputValue
     * @return numbers of teeth
     */
    private int z02(double diameterReference, double beta, double moduleNormal) {
        try {
            int teethNumber = (int) Math.round((diameterReference * TrigonometricUtil.cos(beta)) / moduleNormal);
            this.calculationSucceed = true;
            //logger.info("z02 calculate success return value: " + teethNumber);
            return teethNumber;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("z02 calculate fail, return double max");
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Calculate numbers of teeth according to equation 19 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI
     *
     * @param diameterBase     reference diameter inputValue
     * @param moduleTransverse normal transverse inputValue
     * @param alphaT           pressure angle inputValue
     * @return numbers of teeth
     */
    private int z03(double diameterBase, double moduleTransverse, double alphaT) {
        try {
            int teethNumber = (int) Math.round(diameterBase / (moduleTransverse * TrigonometricUtil.cos(alphaT)));
            this.calculationSucceed = true;
            //logger.info("z03 calculate success return value: " + teethNumber);
            return teethNumber;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("z03 calculate fail, return double max");
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Calculate numbers of teeth according to equation 19 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI
     *
     * @param diameterBase reference diameter inputValue
     * @param alphaN       pressure angle inputValue
     * @param beta         beta inputValue
     * @param moduleNormal module normal inputValue
     * @return numbers of teeth
     */
    private int z04(double diameterBase, double alphaN, double beta, double moduleNormal) {
        try {
            int teethNumber = (int) Math.round((diameterBase / moduleNormal) * (Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaN), 2) + (Math.pow(TrigonometricUtil.cos(beta), 2)))));
            this.calculationSucceed = true;
            //logger.info("z04 calculate success return value: " + teethNumber);
            return teethNumber;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("z04 calculate fail, return double max");
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Calculate numbers of teeth according to equation 1 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI
     *
     * @param diameterBase     reference diameter inputValue
     * @param alphaT           pressure angle inputValue
     * @param moduleTransverse transverse module inputValue
     * @return numbers of teeth
     */
    private int z05(double diameterBase, double alphaT, double moduleTransverse) {
        try {
            int teethNumber = (int) Math.round((diameterBase / moduleTransverse) * (Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaT), 2) + 1)));
            this.calculationSucceed = true;
            //logger.info("z05 calculate success return value: " + teethNumber);
            return teethNumber;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("z05 calculate fail, return double max");
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Calculate numbers of teeth according to equation 1 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI
     *
     * @param diameterBase reference diameter inputValue
     * @param moduleBasic  basic module inputValue
     * @return numbers of teeth
     */
    private int z06(double diameterBase, double moduleBasic) {
        try {
            int teethNumber = (int) Math.round(diameterBase / moduleBasic);
            this.calculationSucceed = true;
            //logger.info("z06 calculate success return value: " + teethNumber);
            return teethNumber;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("z06 calculate fail, return double max");
            return Integer.MAX_VALUE;
        }
    }

}
