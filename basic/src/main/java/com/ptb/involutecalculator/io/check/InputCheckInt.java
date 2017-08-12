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

import java.util.ArrayList;
import java.util.List;

/**
 * The type Input check int.
 *
 * @description
 * @create 2017 -03-27 下午2:51
 * @email gxz04220427 @163.com
 */
public class InputCheckInt extends InputCheck {

    /**
     * The Unit.
     */
    private String unit;
    /**
     * The Value limit max.
     */
    private int ValueLimitMax;
    /**
     * The Value limit min.
     */
    private int ValueLimitMin;

    /**
     * The Digits limit max.
     */
    private int digitsLimitMax;
    /**
     * The Signal flag.
     * true: positive;
     * false: negative
     */
    private boolean signalFlag;
    /**
     * The Cursor.
     */
    private Cursor cursor;
    /**
     * The Character list.
     */
    private List<Character> characterList;
    /**
     * The Result.
     */
    private InputCheckResultInt result;


    /**
     * Instantiates a new Input check int.
     *
     * @param valueLimitMax  the inputValue limit max
     * @param valueLimitMin  the inputValue limit min
     * @param digitsLimitMax the digits limit max
     */
    public InputCheckInt(int valueLimitMax, int valueLimitMin, int digitsLimitMax) {
        ValueLimitMax = valueLimitMax;
        ValueLimitMin = valueLimitMin;
        this.digitsLimitMax = digitsLimitMax;
        this.characterList = new ArrayList<>();
        this.unit = "mm";
        this.signalFlag = true;
        this.result = new InputCheckResultInt();
        this.inputCheckResult = this.result;
        this.cursor = Cursor.CURSOR_START;
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        super.clear();
        this.characterList.clear();
        this.signalFlag = true;
        this.cursor = Cursor.CURSOR_START;
        this.result.setCode(InputCheckResult.ERROR_CODE_NO_ERROR).setMessage(InputCheckResult.ERROR_MESSAGE_NO_ERROR).setValue(0).setValueString("");
    }

    /**
     * Add char input check result.
     *
     * @param c the c
     * @return the input check result
     */
    @Override
    public InputCheckResult addChar(char c) {
        this.result.setCode(InputCheckResult.ERROR_CODE_NO_ERROR).setMessage(InputCheckResult.ERROR_MESSAGE_NO_ERROR);
        if (c == 8) {
            this.backSpaceCheck();
        } else {
            switch (cursor) {
                case CURSOR_START:
                    this.firstCharCheck(c);
                    break;
                case CURSOR_SIGNAL:
                case CURSOR_INTEGER:
                    this.integerPartCheck(c);
            }
        }
        this.generateResultValue();
        this.generateResultValueString();
        return this.result;
    }

    /**
     * First char check.
     *
     * @param c the c
     */
    private void firstCharCheck(char c) {
        switch (c) {
            case '0':
                this.result.setCode(InputCheckResult.ERROR_CODE_ZERO_INT_PREFIX_ERROR).setMessage(InputCheckResult.ERROR_MESSAGE_ZERO_INT_PREFIX_ERROR).setValue(0).setValueString("");
                break;
            case '+':
                break;
            case '-':
                this.signalFlag = false;
                this.cursor = Cursor.CURSOR_SIGNAL;
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (this.checkTempNumber(c)) {
                    this.characterList.add(c);
                    this.cursor = Cursor.CURSOR_INTEGER;
                }
                break;
            default:
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_CHARACTER).setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_CHARACTER + c);
        }
    }

    /**
     * Check temp number boolean.
     *
     * @param c the c
     * @return the boolean
     */
    private boolean checkTempNumber(char c) {
        if (this.characterList.size() == digitsLimitMax) {
            this.result.setCode(InputCheckResult.ERROR_CODE_MORE_INTEGER_DIGITS);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_MORE_INTEGER_DIGITS);
            return false;
        }
        StringBuilder builder = new StringBuilder(this.characterList.size() + 1);
        this.characterList.forEach(builder::append);
        builder.append(c);
        int tempValue = Integer.parseInt(builder.toString());
        if (!this.signalFlag) {
            tempValue = -tempValue;
        }
        if (tempValue > ValueLimitMax) {
            this.result.setCode(InputCheckResult.ERROR_CODE_BIGGER);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_BIGGER + this.ValueLimitMax);
            return false;
        } else if (tempValue < ValueLimitMin) {
            this.result.setCode(InputCheckResult.ERROR_CODE_SMALLER);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_SMALLER);
            return false;
        }
        return true;
    }

    /**
     * Integer part check.
     *
     * @param c the c
     */
    private void integerPartCheck(char c) {
        switch (c) {
            case '+':
                if (this.checkTempNumber(c)) {
                    this.signalFlag = true;
                }
                break;
            case '-':
                if (this.checkTempNumber(c)) {
                    this.signalFlag = false;
                }
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (this.checkTempNumber(c)) {
                    this.characterList.add(c);
                }
                break;
        }
    }


    /**
     * Back space check.
     */
    private void backSpaceCheck() {
        switch (cursor) {
            case CURSOR_START:
                break;
            case CURSOR_SIGNAL:
                this.cursor = Cursor.CURSOR_START;
                this.signalFlag = true;
                break;
            case CURSOR_INTEGER:
                this.characterList.remove(this.characterList.size() - 1);
                if (this.characterList.size() == 0) {
                    if (!this.signalFlag) {
                        this.cursor = Cursor.CURSOR_SIGNAL;
                    } else {
                        this.cursor = Cursor.CURSOR_START;
                    }
                }
                break;
        }
    }

    /**
     * Generate result inputValue.
     */
    private void generateResultValue() {
        int tempValue = 0;
        if (this.cursor == Cursor.CURSOR_START || this.cursor == Cursor.CURSOR_SIGNAL) {
            this.result.setValue(0);
            return;
        }
        StringBuilder builder = new StringBuilder();
        if (!this.signalFlag) {
            builder.append('-');
        }
        this.characterList.forEach(builder::append);

        tempValue = Integer.parseInt(builder.toString());
        this.result.setValue(tempValue);
    }

    /**
     * Generate result value string.
     */
    private void generateResultValueString() {
        if (this.cursor == Cursor.CURSOR_START) {
            this.result.setValueString("");
            return;
        }
        if (this.cursor == Cursor.CURSOR_SIGNAL) {
            this.result.setValueString("-");
            return;
        }
        StringBuilder builder = new StringBuilder();

        for (int i = this.characterList.size() - 1; i >= 0; i--) {
            int index = this.characterList.size() - 1 - i;
            if (index > 0 && index % 3 == 0) {
                builder.insert(0, ',');
            }
            builder.insert(0, this.characterList.get(i));
        }
        if (!this.signalFlag) {
            builder.insert(0, '-');
        }
        this.result.setValueString(builder.toString());
    }
    /**
     * Gets result.
     *
     * @return the result
     */
    public InputCheckResultInt getResult() {
        return result;
    }

}
