package com.ptb.involutecalculator.involute;
/**
 * "Represents the helix angle Beta, is the angle between a tangent to a
 * reference helix and the reference cylinder envelope line through the tangent
 * contact point" .<br>
 * Equation:
 * 1 - 2 from ISO 21771:2007 (Beta= arccos(Mn/Mt))
 * 2 - 23 from ISO 21771:2007 (Beta = arccos((z*Mn/Dr)))
 * 3 - 4.3.2 from ISO 21771:2007  (Beta = 90-gamma)
 * 4 - 3 from ISO 21771:2007  (Beta = atan (((cos(gamma)*Mt))/Mn))
 * 5 - 14 from ISO 21771:2007  (Beta = arccos(tan(alfaN)/tan(alfaT)))
 * 6 - 2.3 from DIN 3960 (Beta = arccos(sqrt((Mn2/Mb2) - (tan2 alfaN))))
 * 7 - 2.5 from DIN 3960 (Beta = arccos((Mn*cos(alfaT))/Mb))
 * <p>
 * <p>
 * According to ISO/FDIS 21771:2007 Value is given in radians<br>
 * <p>
 * Copyright &copy 2010 <a href="http://www.ptb.de">Physikalisch-Technische
 * Bundesanstalt, Braunschweig und Berlin, Germany</a> <br>
 * All rights reserved
 *
 * @author <a href="mailto:frank.haertig@ptb.de">Frank H&aumlrtig (Frank
 * Haertig)</a>
 * @author Cristiam Cometta Conde
 * @author Marcus Diego Pinto Freitas
 * @version 1.0 2010-03-02 (cc, hae, mf)
 */

import com.ptb.involutecalculator.math.TrigonometricUtil;



/**
 * The type Module basic.
 *
 * @description
 * @create 2017 -03-27 下午2:39
 * @email gxz04220427 @163.com
 */
public class ModuleBasic extends ParaReal {
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
    public ModuleBasic(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
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
     * @param moduleNormal            the module normal
     * @param anglePressureNormal the angle pressure normal real
     * @param angleHelix          the angle helix real
     * @param diameterBase            the diameter base
     * @param teethNumber             the teeth number
     * @param anglePressure       the angle pressure real
     * @param moduleTransverse        the module transverse
     * @param diameterReference       the diameter reference
     */
    public void calculateValue(ModuleNormal moduleNormal, IAnglePressureNormal anglePressureNormal, IAngleHelix angleHelix, DiameterBase diameterBase, TeethNumber teethNumber, IAnglePressure anglePressure, ModuleTransverse moduleTransverse, DiameterReference diameterReference) {
        double mb = Double.MAX_VALUE;
        if (!(moduleNormal.isCalculationSucceed() && anglePressureNormal.calculationSucceed() && angleHelix.calculationSucceed() && diameterBase.isCalculationSucceed() && teethNumber.isCalculationSucceed() && anglePressure.calculationSucceed() && moduleTransverse.isCalculationSucceed() && diameterReference.isCalculationSucceed())) {
            this.calculateCount++;
            this.calculationSucceed = false;
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = Double.MAX_VALUE;
            this.resultValueMin = -Double.MAX_VALUE;
            if (!this.calculation_06) {
                onceMore = true;
                this.calculation_06 = true;
            }
            return;
        }
        if (moduleNormal.isKnown() && anglePressureNormal.known() && angleHelix.known() && this.calculationSucceed) {
            this.calculateCount++;
            mb = this.mb01(moduleNormal.getResultValue(), anglePressureNormal.resultValue(), angleHelix.resultValue());
            this.refreshValue(mb);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (diameterBase.isKnown() && teethNumber.isKnown() && this.calculationSucceed) {
            mb = this.mb02(diameterBase.getResultValue(), teethNumber.getResultValue());
            this.calculateCount++;
            this.refreshValue(mb);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (moduleNormal.isKnown() && anglePressure.known() && angleHelix.known()&&this.calculationSucceed) {
            mb = this.mb03(moduleNormal.getResultValue(), anglePressure.resultValue(), angleHelix.resultValue());
            this.calculateCount++;
            this.refreshValue(mb);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (moduleTransverse.isKnown() && anglePressure.known() && this.calculationSucceed) {
            mb = this.mb04(moduleTransverse.getResultValue(), anglePressure.resultValue());
            this.calculateCount++;
            this.refreshValue(mb);
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
        if (diameterReference.isKnown() && anglePressure.known() && teethNumber.isKnown() && this.calculationSucceed) {
            mb = this.mb05(diameterReference.getResultValue(), anglePressure.resultValue(), teethNumber.getResultValue());
            this.calculateCount++;
            this.refreshValue(mb);
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

    public void calculateContradiction(ModuleNormal moduleNormal, IAnglePressureNormal anglePressureNormal, IAngleHelix angleHelix, DiameterBase diameterBase, TeethNumber teethNumber, IAnglePressure anglePressure, ModuleTransverse moduleTransverse, DiameterReference diameterReference) {
        if (this.resultValue == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && moduleNormal.isFixed() && anglePressureNormal.fixed() && angleHelix.fixed() && teethNumber.isFixed() && anglePressure.fixed() && moduleTransverse.isFixed() && diameterReference.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleNormal.isFixed() && anglePressure.fixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && teethNumber.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleNormal.isFixed() && anglePressure.fixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleTransverse.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterReference.isFixed() && anglePressure.fixed() && teethNumber.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }
    }

    /**
     * Calculate basic module  according to equation 2.3 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param alphaN       normal pressure angle inputValue
     * @param beta         beta inputValue
     *
     * @return basic module
     */
    private double mb01(double moduleNormal, double alphaN, double beta) {
        try {
            double moduleBasic = moduleNormal / (Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaN), 2) + Math.pow(TrigonometricUtil.cos(beta), 2)));
            this.calculationSucceed = true;
            //logger.info("mb01 calculate success return value: " + moduleBasic);
            return moduleBasic;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mb01 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate basic module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterBase base diameter inputValue
     * @param z            numbers of teeth inputValue
     *
     * @return basic module
     */
    private double mb02(double diameterBase, int z) {
        try {
            double moduleBasic = diameterBase / z;
            this.calculationSucceed = true;
            //logger.info("mb02 calculate success return value: " + moduleBasic);
            return moduleBasic;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mb02 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate basic module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param alphaT       pressure angle inputValue
     * @param beta         beta inputValue
     *
     * @return basic module
     */
    private double mb03(double moduleNormal, double alphaT, double beta) {
        try {
            double moduleBasic = moduleNormal * (TrigonometricUtil.cos(alphaT) / TrigonometricUtil.cos(beta));
            this.calculationSucceed = true;
            //logger.info("mb03 calculate success return value: " + moduleBasic);
            return moduleBasic;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mb03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate basic module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleTransverse transverse module inputValue
     * @param alphaT           pressure angle inputValue
     *
     * @return basic module
     */
    private double mb04(double moduleTransverse, double alphaT) {
        try {
            double moduleBasic = moduleTransverse * TrigonometricUtil.cos(alphaT);
            this.calculationSucceed = true;
            //logger.info("mb04 calculate success return value: " + moduleBasic);
            return moduleBasic;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mb04 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate basic module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterReference reference diameter inputValue
     * @param alphaT            pressure angle inputValue
     * @param z                 numbers of teeth inputValue
     *
     * @return basic module
     */
    private double mb05(double diameterReference, double alphaT, int z) {
        try {
            double moduleBasic = (diameterReference * TrigonometricUtil.cos(alphaT)) / z;
            this.calculationSucceed = true;
            //logger.info("mb05 calculate success return value: " + moduleBasic);
            return moduleBasic;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mb05 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }
}
