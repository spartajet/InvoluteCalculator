package com.ptb.involutecalculator.involute;


import com.ptb.involutecalculator.io.check.ICheckable;

/**
 * @description
 * @create 2017-06-15 上午10:24
 * @email spartajet.guo@gmail.com
 */
public interface IAngleLead extends IAngle,ICheckable {
    void calculateContradiction(ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleAxial moduleAxial, IAngle angleHelix);

    void calculateValue(ModuleTransverse moduleTransverse, ModuleNormal moduleNormal, ModuleAxial moduleAxial, IAngle angleHelix);
}
