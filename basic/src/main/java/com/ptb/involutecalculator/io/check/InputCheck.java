package com.ptb.involutecalculator.io.check;
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

/**
 * The type Input check.
 *
 * @description
 * @create 2017 -03-27 下午2:50
 * @email gxz04220427 @163.com
 */
public abstract class InputCheck {

    /**
     * The constant ANGLE_DEGREE_SIGNAL.
     */
    public static char ANGLE_DEGREE_SIGNAL = '°';
    /**
     * The constant ANGLE_MINUTE_SIGNAL.
     */
    public static char ANGLE_MINUTE_SIGNAL = '′';
    /**
     * The constant ANGLE_SECOND_SIGNAL.
     */
    public static char ANGLE_SECOND_SIGNAL = '″';
    /**
     * The constant DOT.
     */
    public static char DOT = '.';

    /**
     * The Input check result.
     */
    protected InputCheckResult inputCheckResult;

    /**
     * Add char input check result.
     *
     * @param c the c
     * @return the input check result
     */
    public abstract InputCheckResult addChar(char c);

    /**
     * Clear.
     */
    public void clear() {
    }


    /**
     * Gets input check result.
     *
     * @return the input check result
     */
    public InputCheckResult getInputCheckResult() {
        return inputCheckResult;
    }

    /**
     * Sets input check result.
     *
     * @param inputCheckResult the input check result
     */
    public void setInputCheckResult(InputCheckResult inputCheckResult) {
        this.inputCheckResult = inputCheckResult;
    }

    /**
     * The enum Cursor.
     */
    protected enum Cursor {
        /**
         * Cursor start cursor.
         */
        CURSOR_START,
        /**
         * Cursor signal cursor.
         */
        CURSOR_SIGNAL,
        /**
         * Cursor integer cursor.
         */
        CURSOR_INTEGER,
        /**
         * Cursor dot cursor.
         */
        CURSOR_DOT,
        /**
         * Cursor decimal cursor.
         */
        CURSOR_DECIMAL,
        /**
         * Cursor angle degree integer cursor.
         */
        CURSOR_ANGLE_DEGREE_INTEGER,
        /**
         * Cursor angle degree signal cursor.
         */
        CURSOR_ANGLE_DEGREE_SIGNAL,
        /**
         * Cursor angle minuts integer cursor.
         */
        CURSOR_ANGLE_MINUTS_INTEGER,
        /**
         * Cursor angle minuts signal cursor.
         */
        CURSOR_ANGLE_MINUTS_SIGNAL,
        /**
         * Cursor angle second integer cursor.
         */
        CURSOR_ANGLE_SECOND_INTEGER,
        /**
         * Cursor angle second dot cursor.
         */
        CURSOR_ANGLE_SECONDE_DOT,
        /**
         * Cursor angle second decimal cursor.
         */
        CURSOR_ANGLE_SECOND_DECIMAL,
        /**
         * Cursor angle second signal cursor.
         */
        CURSOR_ANGLE_SECOND_SIGNAL

    }
}
