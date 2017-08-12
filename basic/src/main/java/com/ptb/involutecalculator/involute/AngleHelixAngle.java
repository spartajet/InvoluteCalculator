package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.math.TrigonometricUtil;

/**
 * @description
 * @create 2017-06-15 上午10:13
 * @email spartajet.guo@gmail.com
 */
public class AngleHelixAngle extends ParaAngle implements IAngleHelix {

    /**
     * Instantiates a new Para angle.
     *
     * @param fixed                the fixed
     * @param unit                 the unit
     * @param valueLimitMax        the value limit max
     * @param valueLimitMin        the value limit min
     * @param digitsAfterDotSecond the digits after dot second
     */
    public AngleHelixAngle(boolean fixed, String unit, Angle valueLimitMax, Angle valueLimitMin, int digitsAfterDotSecond) {
        super(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void calculateValue(DiameterReference diameterReference, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, DiameterBase diameterBase, IAngle anglePressure, IAngle angleLead, ModuleBasic moduleBasic, IAngle anglePressureNormal, TeethNumber teethNumber) {
        double beta = Double.MAX_VALUE;
        if (!(diameterReference.isCalculationSucceed() && moduleTransverse.isCalculationSucceed() && moduleNormal.isCalculationSucceed() && diameterBase.isCalculationSucceed() && anglePressure.calculationSucceed() && angleLead.calculationSucceed() && moduleBasic.isCalculationSucceed() && anglePressureNormal.calculationSucceed() && teethNumber.isCalculationSucceed())) {
            this.calculateCount++;
            this.resultValue = new Angle(Angle.ANGLE_MAX_VALUE);
            this.resultValueMax = new Angle(Angle.ANGLE_MAX_VALUE);
            this.resultValueMin = new Angle(Angle.ANGLE_MAX_VALUE);
            this.calculationSucceed = false;
            if (this.calculation_08) {
                onceMore = true;
                this.calculation_08 = true;
            }
            return;
        }
        if (moduleNormal.isKnown() && moduleTransverse.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            beta = this.beta01(moduleTransverse.getResultValue(), moduleNormal.getResultValue());
            this.refreshValue(new Angle(beta));
            this.first = true;
            if (!this.calculation_01) {
                onceMore = true;
                this.calculation_01 = true;
            }
        }
        if (teethNumber.isKnown() && moduleNormal.isKnown() && diameterReference.isKnown() && this.calculationSucceed) {
            this.calculateCount++;
            beta = beta02(teethNumber.getResultValue(), moduleNormal.getResultValue(), diameterReference.getResultValue());
            this.refreshValue(new Angle(beta));
            this.first = true;
            if (!this.calculation_02) {
                onceMore = true;
                this.calculation_02 = true;
            }
        }
        if (angleLead.known() && this.calculationSucceed) {
            this.calculateCount++;
            beta = beta03(angleLead.resultValue());
            this.refreshValue(new Angle(beta));
            this.first = true;
            if (!this.calculation_03) {
                onceMore = true;
                this.calculation_03 = true;
            }
        }
        if (moduleTransverse.isKnown() && moduleNormal.isKnown() && angleLead.known() && this.calculationSucceed) {
            this.calculateCount++;
            beta = beta04(moduleTransverse.getResultValue(), moduleNormal.getResultValue(), angleLead.resultValue());
            this.refreshValue(new Angle(beta));
            this.first = true;
            if (!this.calculation_04) {
                onceMore = true;
                this.calculation_04 = true;
            }
        }
        if (anglePressureNormal.known() && anglePressure.known() && this.calculationSucceed) {
            this.calculateCount++;
            beta = beta05(anglePressureNormal.resultValue(), anglePressure.resultValue());
            this.refreshValue(new Angle(beta));
            this.first = true;
            if (!this.calculation_05) {
                onceMore = true;
                this.calculation_05 = true;
            }
        }
        if (moduleNormal.isKnown() && moduleBasic.isKnown() && anglePressureNormal.known() && this.calculationSucceed) {
            this.calculateCount++;
            beta = beta06(moduleNormal.getResultValue(), moduleBasic.getResultValue(), anglePressureNormal.resultValue());
            this.refreshValue(new Angle(beta));
            this.first = true;
            if (!this.calculation_06) {
                onceMore = true;
                this.calculation_06 = true;
            }
        }
        if (moduleNormal.isKnown() && moduleBasic.isKnown() && anglePressure.known()) {
            this.calculateCount++;
            beta = beta07(moduleNormal.getResultValue(), moduleBasic.getResultValue(), anglePressure.resultValue());
            this.refreshValue(new Angle(beta));
            this.first = true;
            if (!this.calculation_07) {
                onceMore = true;
                this.calculation_07 = true;
            }
        }
    }

    @Override
    public void calculateContradiction(DiameterReference diameterReference, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, DiameterBase diameterBase, IAngle anglePressure, IAngle angleLead, ModuleBasic moduleBasic, IAngle anglePressureNormal, TeethNumber teethNumber, ModuleAxial moduleAxial) {
        if (this.resultValue.equals(Angle.ANGLE_MAX_VALUE)) {
            this.contradiction = new Angle(Angle.ANGLE_MAX_VALUE);
            return;
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue;
            this.resultValueMax = this.inputValue;
            this.resultValueMin = this.inputValue;
        }
        if (this.fixed && moduleTransverse.isFixed() && moduleNormal.isFixed() && teethNumber.isFixed() && angleLead.fixed() && anglePressure.fixed() && diameterBase.isFixed() && anglePressureNormal.fixed() && moduleBasic.isFixed() && moduleAxial.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleTransverse.isFixed() && moduleNormal.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && teethNumber.isFixed() && moduleNormal.isFixed() && diameterReference.isFixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && angleLead.fixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleTransverse.isFixed() && moduleNormal.isFixed() && angleLead.fixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && anglePressureNormal.fixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleNormal.isFixed() && moduleBasic.isFixed() && anglePressureNormal.fixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else if (this.fixed && moduleNormal.isFixed() && moduleBasic.isFixed() && anglePressure.fixed()) {
            this.contradiction = this.resultValueMax.minus(this.resultValueMin);
        } else {
            this.contradiction = new Angle();
        }
    }


    /**
     * Calculate beta according to equation 2 from ISO 21771:2007 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param moduleTransverse transverse module inputValue
     * @param moduleNormal     normal module inputValue
     * @return beta calculated
     */
    private double beta01(double moduleTransverse, double moduleNormal) {

        double beta = Double.MAX_VALUE;
        try {
            beta = Math.acos(moduleNormal / moduleTransverse);
            this.calculationSucceed = true;
            double degree = TrigonometricUtil.Radian2Grad(beta);
            //logger.info("beta01 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("beta01 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate beta according to equation 23 from ISO 21771:2007 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param z                 numbers of teeth inputValue
     * @param moduleNormal      normal module inputValue
     * @param diameterReference reference diameter inputValue
     * @return beta calculated
     */
    private double beta02(int z, double moduleNormal, double diameterReference) {

        double beta = Double.MAX_VALUE;
        try {
            beta = Math.acos((z * moduleNormal) / diameterReference);
            double degree = TrigonometricUtil.Radian2Grad(beta);
            //logger.info("beta02 calculate success return value: " + degree);
            this.calculationSucceed = true;
            return degree;
        } catch (Exception e) {
            //logger.info("beta02 calculate fail, return double max");
            this.calculationSucceed = false;
            return Double.MAX_VALUE;
        }

    }

    /**
     * Calculate beta according to equation 4.3.2 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param gamma gamma  inputValue
     * @return gamma calculated
     */
    private double beta03(double gamma) {
        double beta;
        if (gamma <= 0 || gamma > 90) {
            this.calculationSucceed = false;
            //logger.info("beta03 calculate fail, return double max");
            return Double.MAX_VALUE;
        }
        beta = 90 - gamma;
        //logger.info("beta03 calculate success return value: " + beta);
        this.calculationSucceed = true;
        return beta;
    }

    /**
     * Calculate beta according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param moduleTransverse transverse module inputValue
     * @param moduleNormal     normal module inputValue
     * @param gamma            gamma inputValue
     * @return beta calculated
     */
    private double beta04(double moduleTransverse, double moduleNormal, double gamma) {

        double beta = Double.MAX_VALUE;
        try {
            beta = Math.atan((TrigonometricUtil.cos(gamma) * moduleTransverse) / moduleNormal);
            double degree = TrigonometricUtil.Radian2Grad(beta);
            //logger.info("beta04 calculate success return value: " + degree);
            this.calculationSucceed = true;
            return degree;
        } catch (Exception e) {
            //logger.info("beta04 calculate fail, return double max");
            this.calculationSucceed = false;
            return Double.MAX_VALUE;
        }

    }

    /**
     * Calculate beta according to equation 14 from ISO 21771:2007 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param alphaN normal pressure angle inputValue
     * @param alphaT pressure angle inputValue
     * @return beta calculated
     */
    private double beta05(double alphaN, double alphaT) {
        double beta = Double.MAX_VALUE;
        try {
            beta = Math.acos(TrigonometricUtil.tan(alphaN) / TrigonometricUtil.tan(alphaT));
            double degree = TrigonometricUtil.Radian2Grad(beta);
            this.calculationSucceed = true;
            //logger.info("beta05 calculate success return value: " + degree);
            return degree;
        } catch (Exception e) {
            //logger.info("beta05 calculate fail, return double max");
            this.calculationSucceed = false;
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate beta according to equation 2.3 from DIN 3960 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param moduleBasic  basic module inputValue
     * @param alphaN       normal pressure angle inputValue
     * @return beta calculated
     */
    private double beta06(double moduleNormal, double moduleBasic, double alphaN) {
        double beta;
        try {
            double temp = Math.sqrt((Math.pow(moduleNormal, 2) / Math.pow(moduleBasic, 2)) - Math.pow(TrigonometricUtil.tan(alphaN), 2));
            beta = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(beta);
            //logger.info("beta06 calculate success return value: " + degree);
            this.calculationSucceed = true;
            return degree;
        } catch (Exception e) {
            //logger.info("beta06 calculate fail, return double max");
            this.calculationSucceed = false;
            return Double.MAX_VALUE;
        }
    }

    /**
     * Calculate beta according to equation 2.5 from DIN 3960 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD
     *
     * @param moduleNormal normal module inputValue
     * @param moduleBasic  basic module inputValue
     * @param alphaT       pressure angle
     * @return beta calculated
     */
    private double beta07(double moduleNormal, double moduleBasic, double alphaT) {
        try {
            double temp = moduleNormal * TrigonometricUtil.cos(alphaT) / moduleBasic;
            double beta = Math.acos(temp);
            double degree = TrigonometricUtil.Radian2Grad(beta);
            //logger.info("beta07 calculate success return value: " + degree);
            this.calculationSucceed = true;
            return degree;
        } catch (Exception e) {
            this.calculationSucceed = false;
            //logger.info("beta07 calculate fail, return double max");
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
