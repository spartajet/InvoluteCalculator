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
 * The type Module axial.
 *
 * @description
 * @create 2017 -03-27 下午2:36
 * @email gxz04220427 @163.com
 */
public class ModuleAxial extends ParaReal {

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
    public ModuleAxial(boolean fixed, int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin, String unit) {
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

    public void calculateValue(ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, IAngleHelix angleHelix, IAngleLead angleLead) {
        double mx = Double.MAX_VALUE;
        if (!(moduleTransverse.isCalculationSucceed() && moduleNormal.isCalculationSucceed() && angleHelix.calculationSucceed() && angleLead.calculationSucceed())) {
            this.calculateCount++;
            this.calculationSucceed = false;
            this.resultValue = Double.MAX_VALUE;
            this.resultValueMax = Double.MAX_VALUE;
            this.resultValueMin = -Double.MAX_VALUE;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
        if (moduleNormal.isKnown() && angleHelix.known() && this.calculationSucceed && angleHelix.resultValue() > 0) {

            this.calculateCount++;
            mx = this.mx01(moduleNormal.getResultValue(), angleHelix.resultValue());
            this.refreshValue(mx);
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (moduleNormal.isKnown() && angleLead.known() && this.calculationSucceed && angleLead.resultValue() < 90) {
            this.calculateCount++;
            mx = this.mx02(moduleNormal.getResultValue(), angleLead.resultValue());
            this.refreshValue(mx);
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (moduleTransverse.isKnown() && angleHelix.known() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            this.calculateCount++;
            mx = this.mx03(moduleTransverse.getResultValue(), angleHelix.resultValue());
            this.refreshValue(mx);
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
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

    public void calculateContradiction(ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, IAngleHelix angleHelix, IAngleLead angleLead) {
        if (this.resultValue == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE;
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && moduleTransverse.isFixed() && moduleNormal.isFixed() && angleHelix.fixed() && angleLead.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleNormal.isFixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleNormal.isFixed() && angleLead.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else if (this.fixed && moduleTransverse.isFixed() && angleHelix.fixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin;
        } else {
            this.contradiction = 0;
        }
    }

    /**
     * Calculate axial module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed axial module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param beta         beta inputValue
     * @return axial module
     */
    private double mx01(double moduleNormal, double beta) {
        try {
            double moduleAxial = moduleNormal / TrigonometricUtil.sin(beta);
            this.calculationSucceed = true;
            //logger.info("mx01 calculate success return value: " + moduleAxial);
            return moduleAxial;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mx01 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate axial module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed axial module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param gamma        gamma inputValue
     * @return axial module
     */

    private double mx02(double moduleNormal, double gamma) {
        try {
            double moduleAxial = moduleNormal / TrigonometricUtil.cos(gamma);
            this.calculationSucceed = true;
            //logger.info("mx02 calculate success return value: " + moduleAxial);
            return moduleAxial;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mx02 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }


    /**
     * Calculate axial module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed axial module is equal the calculation, otherwise is set as Const.NonSensD
     *
     * @param moduleTransverse transverse module inputValue
     * @param beta             beta inputValue
     * @return axial module
     */
    private double mx03(double moduleTransverse, double beta) {
        try {
            double moduleAxial = moduleTransverse / TrigonometricUtil.tan(beta);
            this.calculationSucceed = true;
            //logger.info("mx03 calculate success return value: " + moduleAxial);
            return moduleAxial;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("mx03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }
}
