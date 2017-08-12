package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.ICheckable;

/**
 * @description
 * @create 2017-06-15 上午10:25
 * @email spartajet.guo@gmail.com
 */
public interface IAnglePressure extends IAngle,ICheckable {
    void calculateValue(IAngle anglePressureNormal, IAngle angleHelix, DiameterReference diameterReference, DiameterBase diameterBase, TeethNumber teethNumber, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleBasic moduleBasic);

    void calculateContradiction(IAngle anglePressureNormal, IAngle angleHelix, DiameterReference diameterReference, DiameterBase diameterBase, TeethNumber teethNumber, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleBasic moduleBasic);
}
