package com.ptb.length.involutecalculator.calculator;

import android.util.Log;
import de.ptb.length.involute.*;


/**
 * @description
 * @create 2017-08-12 下午6:35
 * @email spartajet.guo@gmail.com
 */

public class Parameters {
    private static final String TAG = "Parameters";
    /**
     * The Teeth number.
     */
    public static TeethNumber teethNumber = new TeethNumber();
    /**
     * The Normal module.
     */
    public static ModuleNormal moduleNormal = new ModuleNormal();
    /**
     * The Transverse module.
     */
    public static ModuleTransverse moduleTransverse = new ModuleTransverse();
    /**
     * The Axial module.
     */
    public static ModuleAxial moduleAxial = new ModuleAxial();
    /**
     * The Base module.
     */
    public static ModuleBasic moduleBase = new ModuleBasic();
    /**
     * The Angle pressure normal real.
     */
    public static AnglePressureNormalReal anglePressureNormal = new AnglePressureNormalReal();
    /**
     * The Pressure angle.
     */
    public static AnglePressureTransverseReal anglePressure = new AnglePressureTransverseReal();
    /**
     * The Helix angle.
     */
    public static AngleHelixReal angleHelix = new AngleHelixReal();
    /**
     * The Lead angle.
     */
    public static AngleLeadReal angleLead = new AngleLeadReal();
    /**
     * The Reference diameter.
     */
    public static DiameterReference diameterReference = new DiameterReference();
    private static int calculateCount = 0;
    /**
     * The Base diameter.
     */
    public static DiameterBase diameterBase = new DiameterBase();

    public static void calculate() {
        refresh();
        Log.i(TAG, "calculate: start teeth number " + teethNumber.getResultValue());
        Log.i(TAG, "calculate: start normal module " + moduleNormal.getResultValue());
        Log.i(TAG, "calculate: start transverse module " + moduleTransverse.getResultValue());
        Log.i(TAG, "calculate: start axial module " + moduleAxial.getResultValue());
        Log.i(TAG, "calculate: start base module " + moduleBase.getResultValue());
        Log.i(TAG, "calculate: start angle press normal " + anglePressureNormal.getResultValue());
        Log.i(TAG, "calculate: start angle press " + anglePressure.getResultValue());
        Log.i(TAG, "calculate: start angle helix " + angleHelix.getResultValue());
        Log.i(TAG, "calculate: start angle lead " + angleLead.getResultValue());
        Log.i(TAG, "calculate: start diameter base " + diameterBase.getResultValue());
        Log.i(TAG, "calculate: start diameter reference " + diameterReference.getResultValue());
        for (int i = 0; i < 2; i++) {
            Para.setOnceMore(true);
            while (Para.isOnceMore()) {
                calculateCount++;
                Para.setOnceMore(false);
                moduleNormal.calculateValue(moduleTransverse, angleHelix, diameterReference, teethNumber, angleLead, anglePressure, diameterBase, anglePressureNormal, moduleBase, moduleAxial);
                moduleTransverse.calculateValue(moduleNormal, angleHelix, diameterReference, teethNumber, angleLead, anglePressure, diameterBase, anglePressureNormal, moduleBase, moduleAxial);
                angleHelix.calculateValue(diameterReference, moduleTransverse, moduleNormal, diameterBase, anglePressure, angleLead, moduleBase, anglePressureNormal, teethNumber);
                diameterReference.calculateValue(moduleTransverse, angleHelix, moduleNormal, teethNumber, diameterBase, anglePressure, anglePressureNormal, moduleBase);
                teethNumber.calculateValue(diameterReference, moduleTransverse, angleHelix, moduleNormal, diameterBase, anglePressure, angleLead, moduleBase, anglePressureNormal);
                angleLead.calculateValue(moduleTransverse, moduleNormal, moduleAxial, angleHelix);
                moduleAxial.calculateValue(moduleTransverse, moduleNormal, angleHelix, angleLead);
                moduleBase.calculateValue(moduleNormal, anglePressureNormal, angleHelix, diameterBase, teethNumber, anglePressure, moduleTransverse, diameterReference);
                anglePressureNormal.calculateValue(angleHelix, anglePressure, teethNumber, moduleNormal, diameterBase, moduleBase, moduleTransverse);
                anglePressure.calculateValue(anglePressureNormal, angleHelix, diameterReference, diameterBase, teethNumber, moduleTransverse, moduleNormal, moduleBase);
                diameterBase.calculateValue(diameterReference, anglePressure, teethNumber, moduleTransverse, moduleNormal, angleHelix, moduleBase);
            }
        }
        Log.i(TAG, "calculate times : " + calculateCount);
        CalculateResult.setIsSucceed(true);

        //calculate contradiction
        moduleNormal.calculateContradiction(moduleTransverse, angleHelix, diameterReference, teethNumber, angleLead, anglePressure, diameterBase, anglePressureNormal, moduleBase, moduleAxial);
        moduleTransverse.calculateContradiction(moduleNormal, angleHelix, diameterReference, teethNumber, angleLead, anglePressure, diameterBase, anglePressureNormal, moduleBase, moduleAxial);
        angleHelix.calculateContradiction(diameterReference, moduleTransverse, moduleNormal, diameterBase, anglePressure, angleLead, moduleBase, anglePressureNormal, teethNumber, moduleAxial);
        diameterReference.calculateContradiction(moduleTransverse, angleHelix, moduleNormal, teethNumber, diameterBase, anglePressure, anglePressureNormal, moduleBase);
        teethNumber.calculateContradiction(diameterReference, moduleTransverse, angleHelix, moduleNormal, diameterBase, anglePressure, angleLead, moduleBase, anglePressureNormal);
        angleLead.calculateContradiction(moduleTransverse, moduleNormal, moduleAxial, angleHelix);
        moduleAxial.calculateContradiction(moduleTransverse, moduleNormal, angleHelix, angleLead);
        moduleBase.calculateContradiction(moduleNormal, anglePressureNormal, angleHelix, diameterBase, teethNumber, anglePressure, moduleTransverse, diameterReference);
        anglePressureNormal.calculateContradiction(angleHelix, anglePressure, teethNumber, moduleNormal, diameterBase, moduleBase, moduleTransverse);
        anglePressure.calculateContradiction(anglePressureNormal, angleHelix, diameterReference, diameterBase, teethNumber, moduleTransverse, moduleNormal, moduleBase);
        diameterBase.calculateContradiction(diameterReference, anglePressure, teethNumber, moduleTransverse, moduleNormal, angleHelix, moduleBase);

        if (angleHelix.isCalculationSucceed() && angleHelix.roundValue()) {
            CalculateResult.setAngleHelixResultValue(String.valueOf(angleHelix.getRoundValueString()));
        } else {
            CalculateResult.setAngleHelixResultValue(angleHelix.getInputValueString());
        }
        if (angleLead.isCalculationSucceed() && angleLead.roundValue()) {
            CalculateResult.setAngleLeadResultValue(String.valueOf(angleLead.getRoundValueString()));
        } else {
            CalculateResult.setAngleLeadResultValue(angleLead.getInputValueString());
        }
        if (anglePressureNormal.isCalculationSucceed() && anglePressureNormal.roundValue()) {
            CalculateResult.setAnglePressureNormalResultValue(String.valueOf(anglePressureNormal.getRoundValueString()));
        } else {
            CalculateResult.setAnglePressureNormalResultValue(anglePressureNormal.getInputValueString());
        }
        if (anglePressure.isCalculationSucceed() && anglePressure.roundValue()) {
            CalculateResult.setAnglePressureResultValue(String.valueOf(anglePressure.getRoundValueString()));
        } else {
            CalculateResult.setAnglePressureResultValue(anglePressure.getInputValueString());
        }
        if (diameterBase.isCalculationSucceed() && diameterBase.roundValue()) {
            CalculateResult.setDiameterBaseResultValue(String.valueOf(diameterBase.getRoundValue()));
        } else {
            CalculateResult.setDiameterBaseResultValue(diameterBase.getInputValueString());
        }
        if (diameterReference.isCalculationSucceed() && diameterReference.roundValue()) {
            CalculateResult.setDiameterReferenceResultValue(String.valueOf(diameterReference.getRoundValue()));
        } else {
            CalculateResult.setDiameterReferenceResultValue(diameterReference.getInputValueString());
        }
        if (moduleAxial.isCalculationSucceed() && moduleAxial.roundValue()) {
            CalculateResult.setModuleAxialResultValue(String.valueOf(moduleAxial.getRoundValue()));
        } else {
            CalculateResult.setModuleAxialResultValue(moduleAxial.getInputValueString());
        }
        if (moduleBase.isCalculationSucceed() && moduleBase.roundValue()) {
            CalculateResult.setModuleBasicResultValue(String.valueOf(moduleBase.getRoundValue()));
        } else {
            CalculateResult.setModuleBasicResultValue(moduleBase.getInputValueString());
        }
        if (moduleNormal.isCalculationSucceed() && moduleNormal.roundValue()) {
            CalculateResult.setModuleNormalResultValue(String.valueOf(moduleNormal.getRoundValue()));
        } else {
            CalculateResult.setModuleNormalResultValue(moduleNormal.getInputValueString());
        }
        if (moduleTransverse.isCalculationSucceed() && moduleTransverse.roundValue()) {
            CalculateResult.setModuleTransverseResultValue(String.valueOf(moduleTransverse.getRoundValue()));
        } else {
            CalculateResult.setModuleTransverseResultValue(moduleTransverse.getInputValueString());
        }
        if (teethNumber.isCalculationSucceed() && teethNumber.roundValue()) {
            CalculateResult.setTeethNumberResultValue(String.valueOf(teethNumber.getRoundValue()));
        } else {
            CalculateResult.setTeethNumberResultValue(teethNumber.getInputValueString());
        }

        if (angleHelix.isCalculationSucceed() && angleHelix.isKnown() && angleHelix.roundContradiction()) {
            CalculateResult.setAngleHelixContradiction(String.valueOf(angleHelix.getRoundContradictionString()));
        } else {
            CalculateResult.setAngleHelixContradiction("no solution");
        }
        if (angleLead.isCalculationSucceed() && angleLead.isKnown() && angleLead.roundContradiction()) {
            CalculateResult.setAngleLeadContradiction(String.valueOf(angleLead.getRoundContradictionString()));
        } else {
            CalculateResult.setAngleLeadContradiction("no solution");
        }
        if (anglePressureNormal.isCalculationSucceed() && anglePressureNormal.isKnown() && anglePressureNormal.roundContradiction()) {
            CalculateResult.setAnglePressureNormalContradiction(String.valueOf(anglePressureNormal.getRoundContradictionString()));
        } else {
            CalculateResult.setAnglePressureNormalContradiction("no solution");
        }
        if (anglePressure.isCalculationSucceed() && anglePressure.isKnown() && anglePressure.roundContradiction()) {
            CalculateResult.setAnglePressureContradiction(String.valueOf(anglePressure.getRoundContradictionString()));
        } else {
            CalculateResult.setAnglePressureContradiction("no solution");
        }
        if (diameterBase.isCalculationSucceed() && diameterBase.isKnown() && diameterBase.roundContradiction()) {
            CalculateResult.setDiameterBaseContradiction(String.valueOf(diameterBase.getRoundContradiction()));
        } else {
            CalculateResult.setDiameterBaseContradiction("no solution");
        }
        if (diameterReference.isCalculationSucceed() && diameterReference.isKnown() && diameterReference.roundContradiction()) {
            CalculateResult.setDiameterReferenceContradiction(String.valueOf(diameterReference.getRoundContradiction()));
        } else {
            CalculateResult.setDiameterReferenceContradiction("no solution");
        }
        if (moduleAxial.isCalculationSucceed() && moduleAxial.isKnown() && moduleAxial.roundContradiction()) {
            CalculateResult.setModuleAxialContradiction(String.valueOf(moduleAxial.getRoundContradiction()));
        } else {
            CalculateResult.setModuleAxialContradiction("no solution");
        }
        if (moduleBase.isCalculationSucceed() && moduleBase.isKnown() && moduleBase.roundContradiction()) {
            CalculateResult.setModuleBasicContradiction(String.valueOf(moduleBase.getRoundContradiction()));
        } else {
            CalculateResult.setModuleBasicContradiction("no solution");
        }
        if (moduleNormal.isCalculationSucceed() && moduleNormal.isKnown() && moduleNormal.roundContradiction()) {
            CalculateResult.setModuleNormalContradiction(String.valueOf(moduleNormal.getRoundContradiction()));
        } else {
            CalculateResult.setModuleNormalContradiction("no solution");
        }
        if (moduleTransverse.isCalculationSucceed() && moduleTransverse.isKnown() && moduleTransverse.roundContradiction()) {
            CalculateResult.setModuleTransverseContradiction(String.valueOf(moduleTransverse.getRoundContradiction()));
        } else {
            CalculateResult.setModuleTransverseContradiction("no solution");
        }
        if (teethNumber.isCalculationSucceed() && teethNumber.isKnown() && teethNumber.roundContradiction()) {
            CalculateResult.setTeethNumberContradiction(String.valueOf(teethNumber.getRoundContradiction()));
        } else {
            CalculateResult.setTeethNumberContradiction("no solution");
        }
    }

    private static void refresh() {
        calculateCount = 0;
        angleHelix.refresh();
        angleLead.refresh();
        anglePressureNormal.refresh();
        anglePressure.refresh();
        diameterBase.refresh();
        diameterReference.refresh();
        moduleAxial.refresh();
        moduleBase.refresh();
        moduleNormal.refresh();
        moduleTransverse.refresh();
        teethNumber.refresh();
    }

    public static void clear() {
        angleHelix.clear();
        angleLead.clear();
        anglePressureNormal.clear();
        anglePressure.clear();
        diameterBase.clear();
        diameterReference.clear();
        moduleAxial.clear();
        moduleBase.clear();
        moduleNormal.clear();
        moduleTransverse.clear();
        teethNumber.clear();
    }
}
