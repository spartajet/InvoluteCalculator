package com.ptb.involutecalculator.math;

/**
 * The type Trigonometric util.
 *
 * @description
 * @create 2017 -05-19 上午8:46
 * @email spartajet.guo @gmail.com
 */
public class TrigonometricUtil {
    /**
     * Sin double.
     *
     * @param grad the grad
     *
     * @return the double
     */
    public static double sin(double grad) {
        return Math.sin(grad2Radian(grad));
    }

    /**
     * Cos double.
     *
     * @param grad the grad
     *
     * @return the double
     */
    public static double cos(double grad) {
        return Math.cos(grad2Radian(grad));
    }

    /**
     * Tan double.
     *
     * @param grad the grad
     *
     * @return the double
     */
    public static double tan(double grad) {
        return Math.tan(grad2Radian(grad));
    }

    /**
     * Radian 2 grad double.
     *
     * @param radian the radian
     *
     * @return the double
     */
    public static double Radian2Grad(double radian) {
        return Math.toDegrees(radian);
    }

    /**
     * Grad 2 radian double.
     *
     * @param grad the grad
     *
     * @return the double
     */
    public static double grad2Radian(double grad) {
        return Math.toRadians(grad);
    }
}
