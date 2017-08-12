package com.ptb.involutecalculator.io.check;

import java.util.ArrayList;
import java.util.List;

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
 * @author <a href="mailto:frank.haertig@ptb.de">Frank H&aumlrtig (Frank         Haertig)</a>
 * @author Cristiam Cometta Conde
 * @author Marcus Diego Pinto Freitas
 * @version 1.0 2010-03-02 (cc, hae, mf)          <p>          <p>          <p>          The type Input check real.
 * @description
 * @create 2017 -03-27 下午2:51
 * @email gxz04220427 @163.com
 */
public class InputCheckReal extends InputCheck {

    /**
     * The Length allowed dot before.
     */
    private int LengthAllowedDotBefore;
    /**
     * The Length allowed dot after.
     */
    private int LengthAllowedDotAfter;
    /**
     * The Unit.
     */
    private String unit;
    /**
     * The Value limit max.
     */
    private double ValueLimitMax;
    /**
     * The Value limit min.
     */
    private double ValueLimitMin;
    /**
     * The Zero flag.
     */
    private boolean zeroFlag;
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
     * The Integer list.
     */
    List<Character> integerList;
    /**
     * The Decimal list.
     */
    List<Character> decimalList;
    /**
     * The Result.
     */
    private InputCheckResultReal result;

    /**
     * Instantiates a new Input check real.
     *
     * @param lengthAllowedDotBefore the length allowed dot before
     * @param lengthAllowedDotAfter  the length allowed dot after
     * @param valueLimitMax          the inputValue limit max
     * @param valueLimitMin          the inputValue limit min
     */
    public InputCheckReal(int lengthAllowedDotBefore, int lengthAllowedDotAfter, double valueLimitMax, double valueLimitMin) {
        this.LengthAllowedDotBefore = lengthAllowedDotBefore;
        this.LengthAllowedDotAfter = lengthAllowedDotAfter;
        this.ValueLimitMax = valueLimitMax;
        this.ValueLimitMin = valueLimitMin;
        this.result = new InputCheckResultReal();
        this.inputCheckResult = this.result;
        this.result.setValue(0).setValueString("0").setCode(InputCheckResult.ERROR_CODE_NO_ERROR).setMessage(InputCheckResult.ERROR_MESSAGE_NO_ERROR);
        // initialize flags, cursor and the character store list
        this.zeroFlag = false;
        this.signalFlag = true;
        this.cursor = Cursor.CURSOR_START;
        this.integerList = new ArrayList<>();
        this.decimalList = new ArrayList<>();
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        super.clear();
        this.result.setValue(0).setValueString("0").setCode(InputCheckResult.ERROR_CODE_NO_ERROR).setMessage(InputCheckResult.ERROR_MESSAGE_NO_ERROR);
        // initialize flags, cursor and the character store list
        this.zeroFlag = false;
        this.signalFlag = true;
        this.cursor = Cursor.CURSOR_START;
        this.integerList.clear();
        this.decimalList.clear();
    }

    /**
     * Add char input check result.
     *
     * @param c the c
     * @return the input check result
     */
    @Override
    public InputCheckResult addChar(char c) {
        // at first no error
        this.result.setCode(InputCheckResult.ERROR_CODE_NO_ERROR).setMessage(InputCheckResult.ERROR_MESSAGE_NO_ERROR);
        if (c == 8) {
            // if the character typed is backspace
            this.backSpaceCheck();
        } else {
            switch (cursor) {
                case CURSOR_START:
                    this.firstCharCheck(c);
                    break;
                case CURSOR_SIGNAL:
                case CURSOR_INTEGER:
                    this.integerPartCheck(c);
                    break;
                case CURSOR_DOT:
                case CURSOR_DECIMAL:
                    this.decimalPartCheck(c);
                    break;
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
                this.zeroFlag = true;
                this.integerList.add('0');
                this.cursor = Cursor.CURSOR_INTEGER;
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
                this.integerList.add(c);
                this.cursor = Cursor.CURSOR_INTEGER;
                break;
            case '.':
                this.integerList.add('0');
                this.cursor = Cursor.CURSOR_DOT;
                break;
            case '+':
                break;
            case '-':
                this.signalFlag = false; // the number is negative
                this.cursor = Cursor.CURSOR_SIGNAL;
                break;
            default:
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_CHARACTER).setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_CHARACTER + c);
        }
    }

    /**
     * Integer part check.
     *
     * @param c the c
     */
    private void integerPartCheck(char c) {
        switch (c) {
            case '.':
                this.cursor = Cursor.CURSOR_DOT;
                this.zeroFlag = false;
                break;
            case '+':
                this.signalFlag = true;
                break;
            case '-':
                this.signalFlag = false;
                break;
            case '0':
                if (this.integerList.get(0) == '0') {
                    this.result.setCode(InputCheckResult.ERROR_CODE_ZERO_REAL_ERROR);
                    this.result.setMessage(InputCheckResult.ERROR_MESSAGE_ZERO_REAL_ERROR);
                    return;
                }
                if (this.checkTempNumberForInteger(c)) {
                    this.integerList.add(c);
                    this.cursor = Cursor.CURSOR_INTEGER;
                }
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
                if (this.integerList.get(0) == '0') {
                    this.integerList.remove(0);
                }
                if (this.checkTempNumberForInteger(c)) {
                    this.integerList.add(c);
                    this.cursor = Cursor.CURSOR_INTEGER;
                }
                break;
            default:
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_CHARACTER).setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_CHARACTER + c);
        }
    }

    /**
     * Check temp number for integer boolean.
     *
     * @param c the c
     * @return the boolean
     */
    private boolean checkTempNumberForInteger(char c) {
        if (this.integerList.size() >= this.LengthAllowedDotBefore) {
            this.result.setCode(InputCheckResult.ERROR_CODE_MORE_INTEGER_DIGITS);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_MORE_INTEGER_DIGITS);
            return false;
        }
        StringBuilder builder = new StringBuilder();
        if (!this.signalFlag) {
            builder.append('-');
        }
        this.integerList.forEach(builder::append);
        builder.append(c);
        int tempValue = Integer.parseInt(builder.toString());
        if (tempValue > this.ValueLimitMax) {
            this.result.setCode(InputCheckResult.ERROR_CODE_BIGGER);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_BIGGER + this.ValueLimitMax);
            return false;
        } else if (tempValue < this.ValueLimitMin) {
            this.result.setCode(InputCheckResult.ERROR_CODE_SMALLER);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_SMALLER + this.ValueLimitMin);
            return false;
        }
        return true;
    }


    /**
     * Decimal part check.
     *
     * @param c the c
     */
    private void decimalPartCheck(char c) {
        switch (c) {
            case '.':
                // two or more two . in a real is not allowed
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_DOT);
                this.result.setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_DOT);
                break;
            case '+':
                this.signalFlag = true;
                break;
            case '-':
                this.signalFlag = false;
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
                if (this.checkTempNumberForDecimal(c)) {
                    this.decimalList.add(c);
                    this.cursor = Cursor.CURSOR_DECIMAL;
                }
                break;
            default:
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_CHARACTER).setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_CHARACTER + c);
        }

    }

    private boolean checkTempNumberForDecimal(char c) {
        if (this.decimalList.size() >= LengthAllowedDotAfter) {
            this.result.setCode(InputCheckResult.ERROR_CODE_MORE_DECIMAL_DIGITS);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_MORE_DECIMAL_DIGITS + this.LengthAllowedDotAfter);
            return false;
        }
        StringBuilder builder = new StringBuilder();
        if (!this.signalFlag) {
            builder.append('-');
        }
        this.integerList.forEach(builder::append);
        builder.append('.');
        this.decimalList.forEach(builder::append);
        builder.append(c);
        double tempValue = Double.parseDouble(builder.toString());
        if (tempValue > ValueLimitMax) {
            this.result.setCode(InputCheckResult.ERROR_CODE_BIGGER);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_BIGGER + this.ValueLimitMax);
        } else if (tempValue < ValueLimitMin) {
            this.result.setCode(InputCheckResult.ERROR_CODE_SMALLER);
            this.result.setMessage(InputCheckResult.ERROR_MESSAGE_SMALLER + this.ValueLimitMin);
        }
        return true;
    }

    /**
     * Back space check.
     */
    private void backSpaceCheck() {
        switch (cursor) {
            case CURSOR_START:
                break;
            case CURSOR_SIGNAL:
                this.signalFlag = true;
                this.cursor = Cursor.CURSOR_START;
                break;
            case CURSOR_INTEGER:
                this.integerList.remove(this.integerList.size() - 1);
                if (this.integerList.size() == 0) {
                    if (this.signalFlag) {
                        this.cursor = Cursor.CURSOR_START;
                    } else {
                        this.cursor = Cursor.CURSOR_SIGNAL;
                    }
                }
                break;
            case CURSOR_DOT:
                this.cursor = Cursor.CURSOR_INTEGER;
                break;
            case CURSOR_DECIMAL:
                this.decimalList.remove(this.decimalList.size() - 1);
                if (this.decimalList.size() == 0) {
                    this.cursor = Cursor.CURSOR_DOT;
                }
                break;
        }
    }

    /**
     * Generate result.
     */
    private void generateResultValue() {
        StringBuilder builder = new StringBuilder();
        switch (this.cursor) {
            case CURSOR_START:
            case CURSOR_SIGNAL:
                this.result.setValue(0);
                return;
            case CURSOR_INTEGER:
            case CURSOR_DOT:
                if (!this.signalFlag) {
                    builder.append('-');
                }
                this.integerList.forEach(builder::append);
                break;
            case CURSOR_DECIMAL:
                if (!this.signalFlag) {
                    builder.append('-');
                }
                this.integerList.forEach(builder::append);
                builder.append('.');
                this.decimalList.forEach(builder::append);
                break;
        }
        this.result.setValue(Double.parseDouble(builder.toString()));
    }

    private void generateResultValueString() {
        StringBuilder builder = new StringBuilder();
        switch (this.cursor) {
            case CURSOR_START:
                this.result.setValueString("");
                return;
            case CURSOR_SIGNAL:
                this.result.setValueString("-");
                return;
            case CURSOR_INTEGER:
                for (int i = this.integerList.size() - 1; i >= 0; i--) {
                    int index = this.integerList.size() - 1 - i;
                    if (index > 0 && index % 3 == 0) {
                        builder.insert(0, ',');
                    }
                    builder.insert(0, this.integerList.get(i));
                }
                if (!this.signalFlag) {
                    builder.insert(0, '-');
                }
                break;
            case CURSOR_DOT:
                for (int i = this.integerList.size() - 1; i >= 0; i--) {
                    int index = this.integerList.size() - 1 - i;
                    if (index > 0 && index % 3 == 0) {
                        builder.insert(0, ',');
                    }
                    builder.insert(0, this.integerList.get(i));
                }
                if (!this.signalFlag) {
                    builder.insert(0, '-');
                }
                builder.append('.');
                break;
            case CURSOR_DECIMAL:
                for (int i = this.integerList.size() - 1; i >= 0; i--) {
                    int index = this.integerList.size() - 1 - i;
                    if (index > 0 && index % 3 == 0) {
                        builder.insert(0, ',');
                    }
                    builder.insert(0, this.integerList.get(i));
                }
                if (!this.signalFlag) {
                    builder.insert(0, '-');
                }
                builder.append('.');
                this.decimalList.forEach(builder::append);
                break;
        }
        this.result.setValueString(builder.toString());
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public InputCheckResultReal getResult() {
        return result;
    }

}
