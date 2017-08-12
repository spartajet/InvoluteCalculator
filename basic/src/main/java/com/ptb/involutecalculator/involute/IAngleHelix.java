package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.ICheckable;

/**
 * @description
 * @create 2017-06-15 上午10:24
 * @email spartajet.guo@gmail.com
 */
public interface IAngleHelix extends IAngle, ICheckable {
    void calculateValue(DiameterReference diameterReference, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, DiameterBase diameterBase, IAngle anglePressure, IAngle angleLead, ModuleBasic moduleBasic, IAngle anglePressureNormal, TeethNumber teethNumber);

    void calculateContradiction(DiameterReference diameterReference, ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, DiameterBase diameterBase, IAngle anglePressure, IAngle angleLead, ModuleBasic moduleBasic, IAngle anglePressureNormal, TeethNumber teethNumber, ModuleAxial moduleAxial);
}
