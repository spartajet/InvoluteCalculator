package com.ptb.length.involutecalculator.calculator;

import com.ptb.involutecalculator.involute.*;

/**
 * @description
 * @create 2017-08-12 下午6:35
 * @email spartajet.guo@gmail.com
 */

public class Parameters {
    /**
     * The Teeth number.
     */
    public static TeethNumber teethNumber = new TeethNumber(true, "", 1000, 1, 3);
    /**
     * The Normal module.
     */
    public static ModuleNormal moduleNormal = new ModuleNormal(true, 2, 4, 100, 0.001, "");
    /**
     * The Transverse module.
     */
    public static ModuleTransverse moduleTransverse = new ModuleTransverse(true, 2, 4, 100, 0.001, "");
    /**
     * The Axial module.
     */
    public static ModuleAxial moduleAxial = new ModuleAxial(true, 4, 4, 1000, 0.001, "");
    /**
     * The Base module.
     */
    public static ModuleBasic moduleBase = new ModuleBasic(true, 2, 4, 100, 0.001, "");
    /**
     * The Angle pressure normal real.
     */
    public static AnglePressureNormalReal anglePressureNormalReal = new AnglePressureNormalReal(true, 2, 4, 45, 0.001, "");
    /**
     * The Pressure angle.
     */
    public static AnglePressureReal anglePressureReal = new AnglePressureReal(true, 2, 4, 45, 0.001, "");
    /**
     * The Helix angle.
     */
    public static AngleHelixReal angleHelixReal = new AngleHelixReal(true, 2, 4, 90, 0.0, "");
    /**
     * The Lead angle.
     */
    public static AngleLeadReal angleLeadReal = new AngleLeadReal(true, 2, 4, 91, 0.0, "");
    /**
     * The Reference diameter.
     */
    public static DiameterReference diameterReference = new DiameterReference(true, 4, 5, 10000, 0.001, "");
    /**
     * The Base diameter.
     */
    public static DiameterBase diameterBase = new DiameterBase(true, 5, 4, 10000, 0.001, "");
}
