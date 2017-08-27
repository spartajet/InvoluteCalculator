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

        if (angleHelix.roundValue()) {
            CalculateResult.setAngleHelixResultValue(String.valueOf(angleHelix.getRoundValueString()));
        } else {
            CalculateResult.setAngleHelixResultValue("");
        }
        if (angleLead.roundValue()) {
            CalculateResult.setAngleLeadResultValue(String.valueOf(angleLead.getRoundValueString()));
        } else {
            CalculateResult.setAngleLeadResultValue("");
        }
        if (anglePressureNormal.roundValue()) {
            CalculateResult.setAnglePressureNormalResultValue(String.valueOf(anglePressureNormal.getRoundValueString()));
        } else {
            CalculateResult.setAnglePressureNormalResultValue("");
        }
        if (anglePressure.roundValue()) {
            CalculateResult.setAnglePressureResultValue(String.valueOf(anglePressure.getRoundValueString()));
        } else {
            CalculateResult.setAnglePressureResultValue("");
        }
        if (diameterBase.roundValue()) {
            CalculateResult.setDiameterBaseResultValue(String.valueOf(diameterBase.getRoundValue()));
        } else {
            CalculateResult.setDiameterBaseResultValue("");
        }
        if (diameterReference.roundValue()) {
            CalculateResult.setDiameterReferenceResultValue(String.valueOf(diameterReference.getRoundValue()));
        } else {
            CalculateResult.setDiameterReferenceResultValue("");
        }
        if (moduleAxial.roundValue()) {
            CalculateResult.setModuleAxialResultValue(String.valueOf(moduleAxial.getRoundValue()));
        } else {
            CalculateResult.setModuleAxialResultValue("");
        }
        if (moduleBase.roundValue()) {
            CalculateResult.setModuleBasicResultValue(String.valueOf(moduleBase.getRoundValue()));
        } else {
            CalculateResult.setModuleBasicResultValue("");
        }
        if (moduleNormal.roundValue()) {
            CalculateResult.setModuleNormalResultValue(String.valueOf(moduleNormal.getRoundValue()));
        } else {
            CalculateResult.setModuleNormalResultValue("");
        }
        if (moduleTransverse.roundValue()) {
            CalculateResult.setModuleTransverseResultValue(String.valueOf(moduleTransverse.getRoundValue()));
        } else {
            CalculateResult.setModuleTransverseResultValue("");
        }
        if (teethNumber.roundValue()) {
            CalculateResult.setTeethNumberResultValue(String.valueOf(teethNumber.getRoundValue()));
        } else {
            CalculateResult.setTeethNumberResultValue("");
        }

        if (angleHelix.roundContradiction()) {
            CalculateResult.setAngleHelixContradiction(String.valueOf(angleHelix.getRoundContradictionString()));
        } else {
            CalculateResult.setAngleHelixContradiction("");
        }
        if (angleLead.roundContradiction()) {
            CalculateResult.setAngleLeadContradiction(String.valueOf(angleLead.getRoundContradictionString()));
        } else {
            CalculateResult.setAngleLeadContradiction("");
        }
        if (anglePressureNormal.roundContradiction()) {
            CalculateResult.setAnglePressureNormalContradiction(String.valueOf(anglePressureNormal.getRoundContradictionString()));
        } else {
            CalculateResult.setAnglePressureNormalContradiction("");
        }
        if (anglePressure.roundContradiction()) {
            CalculateResult.setAnglePressureContradiction(String.valueOf(anglePressure.getRoundContradictionString()));
        } else {
            CalculateResult.setAnglePressureContradiction("");
        }
        if (diameterBase.roundContradiction()) {
            CalculateResult.setDiameterBaseContradiction(String.valueOf(diameterBase.getRoundContradiction()));
        } else {
            CalculateResult.setDiameterBaseContradiction("");
        }
        if (diameterReference.roundContradiction()) {
            CalculateResult.setDiameterReferenceContradiction(String.valueOf(diameterReference.getRoundContradiction()));
        } else {
            CalculateResult.setDiameterReferenceContradiction("");
        }
        if (moduleAxial.roundContradiction()) {
            CalculateResult.setModuleAxialContradiction(String.valueOf(moduleAxial.getRoundContradiction()));
        } else {
            CalculateResult.setModuleAxialContradiction("");
        }
        if (moduleBase.roundContradiction()) {
            CalculateResult.setModuleBasicContradiction(String.valueOf(moduleBase.getRoundContradiction()));
        } else {
            CalculateResult.setModuleBasicContradiction("");
        }
        if (moduleNormal.roundContradiction()) {
            CalculateResult.setModuleNormalContradiction(String.valueOf(moduleNormal.getRoundContradiction()));
        } else {
            CalculateResult.setModuleNormalContradiction("");
        }
        if (moduleTransverse.roundContradiction()) {
            CalculateResult.setModuleTransverseContradiction(String.valueOf(moduleTransverse.getRoundContradiction()));
        } else {
            CalculateResult.setModuleTransverseContradiction("");
        }
        if (teethNumber.roundContradiction()) {
            CalculateResult.setTeethNumberContradiction(String.valueOf(teethNumber.getRoundContradiction()));
        } else {
            CalculateResult.setTeethNumberContradiction("");
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
