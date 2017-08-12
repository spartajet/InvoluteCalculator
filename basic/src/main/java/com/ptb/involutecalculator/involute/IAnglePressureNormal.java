package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.ICheckable;

/**
 * @description
 * @create 2017-06-15 上午10:25
 * @email spartajet.guo@gmail.com
 */
public interface IAnglePressureNormal extends IAngle, ICheckable {
    void calculateValue(IAngle angleHelix, IAngle anglePressure, TeethNumber teethNumber, ModuleNormal moduleNormal, DiameterBase diameterBase, ModuleBasic moduleBasic, ModuleTransverse moduleTransverse);

    void calculateContradiction(IAngle angleHelix, IAngle anglePressure, TeethNumber teethNumber, ModuleNormal moduleNormal, DiameterBase diameterBase, ModuleBasic moduleBasic, ModuleTransverse moduleTransverse);
}
