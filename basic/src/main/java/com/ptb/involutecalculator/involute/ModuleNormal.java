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
 * The type Module normal.
 *
 * @description
 * @create 2017 -03-27 下午2:36
 * @email gxz04220427 @163.com
 */
public class ModuleNormal extends ParaReal {

    /**
     * Instantiates a new Module normal.
     *
     * @param fixed                  the fixed
     * @param lengthAllowedDotBefore the length allowed dot before
     * @param lengthAllowedDotAfter  the length allowed dot after
     * @param valueLimitMax          the inputValue limit max
     * @param valueLimitMin          the inputValue limit min
     * @param unit                   the unit
     */
    public ModuleNormal(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
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

    public void calculateValue(ModuleTransverse moduleTransverse, IAngleHelix angleHelix, DiameterReference diameterReference, TeethNumber teethNumber, IAngleLead angleLead, IAnglePressure anglePressure, DiameterBase diameterBase, IAnglePressureNormal anglePressureNormal, ModuleBasic moduleBasic, ModuleAxial moduleAxial) {
        double mn = Double.MAX_VALUE;
        if (!(moduleTransverse.isCalculationSucceed() && angleHelix.calculationSucceed() && diameterReference.isCalculationSucceed() && teethNumber.isCalculationSucceed() && angleLead.calculationSucceed() && anglePressure.calculationSucceed() && diameterBase.isCalculationSucceed() && anglePressureNormal.calculationSucceed() && moduleBasic.isCalculationSucceed() && moduleAxial.isCalculationSucceed())) {
            this.calculateCount++;
            this.calculationSucceed = false;
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = Double.MAX_VALUE;
            this.resultValueMin = -Double.MAX_VALUE;
            if (!this.calculation_11) {
                onceMore = true;
                this.calculation_11 = true;
            }
            return;
        }
        if (moduleTransverse.isKnown() && angleHelix.known() && this.calculationSucceed) {
            mn = this.mn01(moduleTransverse.getResultValue(), angleHelix.resultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (diameterReference.isKnown() && angleHelix.known() && teethNumber.isKnown() && this.calculationSucceed) {
            mn = this.mn02(diameterReference.getResultValue(), angleHelix.resultValue(), teethNumber.getResultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (moduleTransverse.isKnown() && angleLead.known() && angleHelix.known() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            mn = this.mn03(moduleTransverse.getResultValue(), angleLead.resultValue(), angleHelix.resultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (diameterBase.isKnown() && angleHelix.known() && teethNumber.isKnown() && anglePressure.known() && this.calculationSucceed) {
            mn = this.mn04(diameterBase.getResultValue(), angleHelix.resultValue(), teethNumber.getResultValue(), anglePressure.resultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
        if (diameterBase.isKnown() && anglePressure.known() && angleHelix.known() && teethNumber.isKnown() && this.calculationSucceed) {
            mn = this.mn05(diameterBase.getResultValue(), anglePressure.resultValue(), angleHelix.resultValue(), teethNumber.getResultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_05) {
                onceMore = true;
                this.calculation_05 = true;
            }
        }
        if (moduleBasic.isKnown() && anglePressure.known() && angleHelix.known() && this.calculationSucceed) {
            mn = this.mn06(moduleBasic.getResultValue(), anglePressure.resultValue(), angleHelix.resultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_06) {
                onceMore = true;
                this.calculation_06 = true;
            }
        }
        if (moduleAxial.isKnown() && angleLead.known() && this.calculationSucceed) {
            mn = this.mn07(moduleAxial.getResultValue(), angleLead.resultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_07) {
                onceMore = true;
                this.calculation_07 = true;
            }
        }
        if (moduleAxial.isKnown() && angleHelix.known() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            mn = this.mn08(moduleAxial.getResultValue(), angleHelix.resultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_08) {
                onceMore = true;
                this.calculation_08 = true;
            }
        }
        if (diameterBase.isKnown() && angleHelix.known() && anglePressureNormal.known() && teethNumber.isKnown() && this.calculationSucceed) {
            mn = this.mn09(diameterBase.getResultValue(), angleHelix.resultValue(), anglePressure.resultValue(), teethNumber.getResultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_09) {
                onceMore = true;
                this.calculation_09 = true;
            }
        }
        if (moduleBasic.isKnown() && angleHelix.known() && angleLead.known() && this.calculationSucceed) {
            mn = this.mn10(moduleBasic.getResultValue(), angleHelix.resultValue(), anglePressure.resultValue());
            this.calculateCount++;
            this.refreshValue(mn);
            this.first = true;
            if (!this.calculation_10) {
                onceMore = true;
                this.calculation_10 = true;
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

    public void calculateContradiction(ModuleTransverse moduleTransverse, IAngleHelix angleHelix, DiameterReference diameterReference, TeethNumber teethNumber, IAngleLead angleLead, IAnglePressure anglePressure, DiameterBase diameterBase, IAnglePressureNormal anglePressureNormal, ModuleBasic moduleBasic, ModuleAxial moduleAxial) {
        if (this.resultValue == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && moduleTransverse.isFixed() && angleHelix.fixed() && diameterReference.isFixed() && teethNumber.isFixed() && angleLead.fixed() && anglePressure.fixed() && diameterBase.isFixed() && anglePressureNormal.fixed() && moduleBasic.isFixed() && moduleAxial.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleTransverse.isFixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterReference.isFixed() && angleHelix.fixed() && teethNumber.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleTransverse.isFixed() && angleLead.fixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && angleHelix.fixed() && teethNumber.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && anglePressureNormal.fixed() && angleHelix.fixed() && teethNumber.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleBasic.isFixed() && anglePressureNormal.fixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleAxial.isFixed() && angleLead.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleAxial.isFixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && diameterBase.isFixed() && angleHelix.fixed() && anglePressure.fixed() && teethNumber.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleBasic.isFixed() && angleHelix.fixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }
    }

    /**
     * Calculate normal module according to equation 2 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleTransverse transverse module inputValue
     * @param beta             beta inputValue
     * @return normal module
     */
    private double mn01(double moduleTransverse, double beta) {
        try {
            double moduleNormal = moduleTransverse * TrigonometricUtil.cos(beta);
            this.calculationSucceed = true;
            //logger.info("mn01 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn01 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal module according to equation 1 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterReference reference diameter inputValue
     * @param beta              beta inputValue
     * @param z                 numbers of teeth inputValue
     * @return normal module
     */
    private double mn02(double diameterReference, double beta, double z) {
        try {
            double moduleNormal = (diameterReference * TrigonometricUtil.cos(beta)) / z;
            this.calculationSucceed = true;
            //logger.info("mn02 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn02 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate normal module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleTransverse transverse module inputValue
     * @param gamma            gamma inputValue
     * @param beta             beta inputValue
     * @return normal module
     */
    private double mn03(double moduleTransverse, double gamma, double beta) {
        try {
            double moduleNormal = (TrigonometricUtil.cos(gamma) / TrigonometricUtil.tan(beta)) * moduleTransverse;
            this.calculationSucceed = true;
            //logger.info("mn03 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal module  according to equation 19 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterBase base diameter inputValue
     * @param beta         beta inputValue
     * @param z            numbers of teeth inputValue
     * @param alphaT       pressure angle inputValue
     * @return normal module
     */
    private double mn04(double diameterBase, double beta, int z, double alphaT) {
        try {
            double moduleNormal = (diameterBase * TrigonometricUtil.cos(beta)) / (z * TrigonometricUtil.cos(alphaT));
            this.calculationSucceed = true;
            //logger.info("mn04 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn04 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal module  according to equation 19 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterBase base diameter inputValue
     * @param alphaN       normal pressure angle inputValue
     * @param beta         beta inputValue
     * @param z            numbers of teeth inputValue
     * @return normal module
     */
    private double mn05(double diameterBase, double alphaN, double beta, int z) {
        try {
            double moduleNormal = (diameterBase * Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaN), 2) + Math.pow(TrigonometricUtil.cos(beta), 2))) / z;
            this.calculationSucceed = true;
            //logger.info("mn05 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn05 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal module according to equation 19 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleBasic basic module inputValue
     * @param alphaN      normal pressure angle inputValue
     * @param beta        beta inputValue
     * @return normal module
     */
    private double mn06(double moduleBasic, double alphaN, double beta) {
        try {
            double moduleNormal = moduleBasic * Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaN), 2) + Math.pow(TrigonometricUtil.cos(beta), 2));
            this.calculationSucceed = true;
            //logger.info("mn06 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn06 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleAxial axial module inputValue
     * @param gamma       gamma inputValue
     * @return normal module
     */
    private double mn07(double moduleAxial, double gamma) {
        try {
            double moduleNormal = moduleAxial * TrigonometricUtil.cos(gamma);
            this.calculationSucceed = true;
            //logger.info("mn07 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn07 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal module according to equation 3 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleAxial axial module inputValue
     * @param beta        beta inputValue
     * @return normal module
     */
    private double mn08(double moduleAxial, double beta) {
        try {
            double moduleNormal = moduleAxial * TrigonometricUtil.sin(beta);
            this.calculationSucceed = true;
            //logger.info("mn08 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn08 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal module  according to equation 19 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param diameterBase base diameter inputValue
     * @param beta         beta angle inputValue
     * @param alphaT       pressure angle inputValue
     * @param z            numbers of teeth inputValue
     * @return normal module
     */
    private double mn09(double diameterBase, double beta, double alphaT, int z) {
        try {
            double moduleNormal = (diameterBase * TrigonometricUtil.cos(beta) * Math.sqrt(Math.pow(TrigonometricUtil.tan(alphaT), 2) + 1)) / z;
            this.calculationSucceed = true;
            //logger.info("mn09 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn09 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate normal module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleBasic basic module inputValue
     * @param beta        beta inputValue
     * @param alphaT      pressure angle inputValue
     * @return normal module
     */
    private double mn10(double moduleBasic, double beta, double alphaT) {
        try {
            double moduleNormal = (moduleBasic * TrigonometricUtil.cos(beta)) / TrigonometricUtil.cos(alphaT);
            this.calculationSucceed = true;
            //logger.info("mn10 calculate success return value: " + moduleNormal);
            return moduleNormal;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mn10 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }
}
