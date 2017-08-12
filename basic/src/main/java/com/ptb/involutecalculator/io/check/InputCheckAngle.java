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


import com.ptb.involutecalculator.involute.Angle;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Input check angle.
 *
 * @description
 * @create 2017 -03-27 下午2:52
 * @email gxz04220427 @163.com
 */
public class InputCheckAngle extends InputCheck {

    /**
     * The Second allow digits.
     */
    private int secondAllowDigits;

    /**
     * The Cursor.
     */
    private Cursor cursor;
    /**
     * The Angle.
     */
    private Angle angle;
    /**
     * The Value.
     */
    private double value;
    /**
     * The Value string.
     */
    private String valueString;
    /**
     * The Character list.
     */
    private List<Character> characterList;
    /**
     * The Degree list.
     */
    private List<Character> degreeList;
    /**
     * The Minute list.
     */
    private List<Character> minuteList;
    /**
     * The Second integer list.
     */
    private List<Character> secondIntegerList;
    /**
     * The Second decimal list.
     */
    private List<Character> secondDecimalList;
    /**
     * The Result.
     */
    private InputCheckResultAngle result;
    /**
     * The Check limit max.
     */
    private Angle checkLimitMax;
    /**
     * The Check limit min.
     */
    private Angle checkLimitMin;


    /**
     * Instantiates a new Input check angle.
     *
     * @param secondAllowDigits the second allow digits
     * @param checkLimitMax     the check limit max
     * @param checkLimitMin     the check limit min
     */
    public InputCheckAngle(int secondAllowDigits, Angle checkLimitMax, Angle checkLimitMin) {
        this.secondAllowDigits = secondAllowDigits;
        this.checkLimitMax = checkLimitMax;
        this.checkLimitMin = checkLimitMin;
        this.cursor = Cursor.CURSOR_ANGLE_DEGREE_INTEGER;
//        this.angle = new Angle();
        this.characterList = new ArrayList<>();
        this.degreeList = new ArrayList<>();
        this.minuteList = new ArrayList<>();
        this.secondIntegerList = new ArrayList<>();
        this.secondDecimalList = new ArrayList<>();
        this.result = new InputCheckResultAngle();
        this.inputCheckResult = this.result;
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        super.clear();
        this.cursor = Cursor.CURSOR_START;
        this.degreeList.clear();
        this.minuteList.clear();
        this.secondIntegerList.clear();
        this.secondDecimalList.clear();
        this.value = 0;
        this.valueString = "";
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
            switch (this.cursor) {
                case CURSOR_START:
                    this.firstCharCheck(c);
                    break;
                case CURSOR_ANGLE_DEGREE_INTEGER:
                    this.angleDegreeIntegerCheck(c);
                    break;
                case CURSOR_ANGLE_DEGREE_SIGNAL:
                case CURSOR_ANGLE_MINUTS_INTEGER:
                    this.angleMinuteIntegerCheck(c);
                    break;
                case CURSOR_ANGLE_MINUTS_SIGNAL:
                case CURSOR_ANGLE_SECOND_INTEGER:
                    this.angleSecondIntegerCheck(c);
                    break;
                case CURSOR_ANGLE_SECONDE_DOT:
                case CURSOR_ANGLE_SECOND_DECIMAL:
                    this.angleSecondDecimalCheck(c);
                    break;
                case CURSOR_ANGLE_SECOND_SIGNAL:
                    this.result.setCode(InputCheckResult.ERROR_CODE_FINISH_INPUT).setMessage(InputCheckResult.ERROR_MESSAGE_FINISH_INPUT);
                    break;
            }
        }
        this.generateResultValueString();
        this.generateResultValue();
        this.result.setValue(new Angle(this.value));
        this.result.setValueString(this.valueString);
        return this.result;
    }

    /**
     * Back space check.
     */
    private void backSpaceCheck() {
        switch (cursor) {
            case CURSOR_START:
                break;
            case CURSOR_ANGLE_DEGREE_INTEGER:
                this.degreeList.remove(this.degreeList.size() - 1);
                if (degreeList.size() == 0) {
                    cursor = Cursor.CURSOR_START;
                }
                break;
            case CURSOR_ANGLE_DEGREE_SIGNAL:
                this.cursor = Cursor.CURSOR_ANGLE_DEGREE_INTEGER;
                break;
            case CURSOR_ANGLE_MINUTS_INTEGER:
                this.minuteList.remove(this.minuteList.size() - 1);
                if (minuteList.size() == 0) {
                    cursor = Cursor.CURSOR_ANGLE_DEGREE_SIGNAL;
                }
                break;
            case CURSOR_ANGLE_MINUTS_SIGNAL:
                this.cursor = Cursor.CURSOR_ANGLE_MINUTS_INTEGER;
                break;
            case CURSOR_ANGLE_SECOND_INTEGER:
                this.secondIntegerList.remove(this.secondIntegerList.size() - 1);
                if (this.secondIntegerList.size() == 0) {
                    this.cursor = Cursor.CURSOR_ANGLE_MINUTS_SIGNAL;
                }
                break;
            case CURSOR_ANGLE_SECONDE_DOT:
                this.cursor = Cursor.CURSOR_ANGLE_SECOND_INTEGER;
                break;
            case CURSOR_ANGLE_SECOND_DECIMAL:
                this.secondDecimalList.remove(this.secondDecimalList.size() - 1);
                if (this.secondDecimalList.size() == 0) {
                    this.cursor = Cursor.CURSOR_ANGLE_SECONDE_DOT;
                }
                break;
            case CURSOR_ANGLE_SECOND_SIGNAL:
                this.cursor = Cursor.CURSOR_ANGLE_SECOND_DECIMAL;
                break;
        }
    }

    /**
     * Generate result value.
     */
    private void generateResultValue() {
        int degree = 0;
        int minute = 0;
        int secondInteger = 0;
        int secondDecimal = 0;
        switch (cursor) {
            case CURSOR_START:
                this.value = 0;
                break;
            case CURSOR_ANGLE_DEGREE_INTEGER:
            case CURSOR_ANGLE_DEGREE_SIGNAL:
                this.value = this.getIntegerFromList(this.degreeList);
                break;
            case CURSOR_ANGLE_MINUTS_INTEGER:
            case CURSOR_ANGLE_MINUTS_SIGNAL:
                degree = this.getIntegerFromList(this.degreeList);
                minute = this.getIntegerFromList(this.minuteList);
                this.value = degree + minute / 60.0;
                break;
            case CURSOR_ANGLE_SECOND_INTEGER:
            case CURSOR_ANGLE_SECONDE_DOT:
                degree = this.getIntegerFromList(this.degreeList);
                minute = this.getIntegerFromList(this.minuteList);
                secondInteger = this.getIntegerFromList(this.secondIntegerList);
                this.value = degree + minute / 60.0 + secondInteger / 3600.0;
                break;
            case CURSOR_ANGLE_SECOND_DECIMAL:
            case CURSOR_ANGLE_SECOND_SIGNAL:
                degree = this.getIntegerFromList(this.degreeList);
                minute = this.getIntegerFromList(this.minuteList);
                secondInteger = this.getIntegerFromList(this.secondIntegerList);
                secondDecimal = this.getIntegerFromList(this.secondDecimalList);
                this.value = degree + minute / 60.0 + secondInteger / 3600.0 + Double.parseDouble("0." + secondDecimal) / 3600.0;
                break;
        }
    }


    /**
     * Generate result value string.
     */
    private void generateResultValueString() {
        StringBuilder builder = new StringBuilder();
        switch (this.cursor) {
            case CURSOR_START:
                this.valueString = "";
                break;
            case CURSOR_ANGLE_DEGREE_INTEGER:
                this.degreeList.forEach(builder::append);
                break;
            case CURSOR_ANGLE_DEGREE_SIGNAL:
                this.degreeList.forEach(builder::append);
                builder.append(ANGLE_DEGREE_SIGNAL);
                break;
            case CURSOR_ANGLE_MINUTS_INTEGER:
                this.degreeList.forEach(builder::append);
                builder.append(ANGLE_DEGREE_SIGNAL);
                this.minuteList.forEach(builder::append);
                break;
            case CURSOR_ANGLE_MINUTS_SIGNAL:
                this.degreeList.forEach(builder::append);
                builder.append(ANGLE_DEGREE_SIGNAL);
                this.minuteList.forEach(builder::append);
                builder.append(ANGLE_MINUTE_SIGNAL);
                break;
            case CURSOR_ANGLE_SECOND_INTEGER:
                this.degreeList.forEach(builder::append);
                builder.append(ANGLE_DEGREE_SIGNAL);
                this.minuteList.forEach(builder::append);
                builder.append(ANGLE_MINUTE_SIGNAL);
                this.secondIntegerList.forEach(builder::append);
                break;
            case CURSOR_ANGLE_SECONDE_DOT:
                this.degreeList.forEach(builder::append);
                builder.append(ANGLE_DEGREE_SIGNAL);
                this.minuteList.forEach(builder::append);
                builder.append(ANGLE_MINUTE_SIGNAL);
                this.secondIntegerList.forEach(builder::append);
                builder.append(DOT);
                break;
            case CURSOR_ANGLE_SECOND_DECIMAL:
                this.degreeList.forEach(builder::append);
                builder.append(ANGLE_DEGREE_SIGNAL);
                this.minuteList.forEach(builder::append);
                builder.append(ANGLE_MINUTE_SIGNAL);
                this.secondIntegerList.forEach(builder::append);
                builder.append(DOT);
                this.secondDecimalList.forEach(builder::append);
                break;
            case CURSOR_ANGLE_SECOND_SIGNAL:
                this.degreeList.forEach(builder::append);
                builder.append(ANGLE_DEGREE_SIGNAL);
                this.minuteList.forEach(builder::append);
                builder.append(ANGLE_MINUTE_SIGNAL);
                this.secondIntegerList.forEach(builder::append);
                builder.append(DOT);
                this.secondDecimalList.forEach(builder::append);
                builder.append(ANGLE_SECOND_SIGNAL);
                break;
        }
        this.valueString = builder.toString();
    }

    /**
     * Angle second decimal check.
     *
     * @param c the c
     */
    private void angleSecondDecimalCheck(char c) {
        switch (c) {
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
                if (this.secondDecimalList.size() == this.secondAllowDigits) {
                    this.result.setCode(InputCheckResult.ERROR_CODE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS).setMessage(InputCheckResult.ERROR_MESSAGE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS);
                }
                this.secondDecimalList.add(c);
                this.cursor = Cursor.CURSOR_ANGLE_SECOND_DECIMAL;
                break;
            case '″':
                if (this.secondDecimalList.size() == 0) {
                    this.secondDecimalList.add('0');
                }
                this.cursor = Cursor.CURSOR_ANGLE_SECOND_SIGNAL;
                break;
        }
    }

    /**
     * Angle second integer check.
     *
     * @param c the c
     */
    private void angleSecondIntegerCheck(char c) {
        switch (c) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (!this.checkTempSecondInteger(c)) {
                    this.result.setCode(InputCheckResult.ERROR_CODE_ANGLE_SECOND_INTEGER_BIGGER_60).setMessage(InputCheckResult.ERROR_MESSGAE_ANGLE_SECOND_INTEGER_BIGGER_60);
                    return;
                }
                this.secondIntegerList.add(c);
                this.cursor = Cursor.CURSOR_ANGLE_SECOND_INTEGER;
                break;
            case '0':
                if (!this.checkTempSecondInteger(c)) {
                    this.result.setCode(InputCheckResult.ERROR_CODE_ANGLE_SECOND_INTEGER_BIGGER_60).setMessage(InputCheckResult.ERROR_MESSGAE_ANGLE_SECOND_INTEGER_BIGGER_60);
                    return;
                }
                this.secondIntegerList.add(c);
                this.cursor = Cursor.CURSOR_ANGLE_SECONDE_DOT;
                break;
            case '.':
                if (this.secondIntegerList.size() == 0) {
                    this.secondIntegerList.add('0');
                }
                this.cursor = Cursor.CURSOR_ANGLE_SECONDE_DOT;
                break;
            case '″':
                if (this.secondIntegerList.size() == 0) {
                    this.secondIntegerList.add('0');
//                    this.secondDecimalList.add('0');
                }
//                } else if (this.secondIntegerList.get(this.secondIntegerList.size() - 1) == '.') {
//                    this.secondDecimalList.add('0');
//                }
//                this.secondIntegerList.add('0');
                this.secondDecimalList.add('0');
                this.cursor = Cursor.CURSOR_ANGLE_SECOND_SIGNAL;
                break;
            default:
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_CHARACTER).setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_CHARACTER + c);
                break;
        }
    }

    /**
     * Angle minute integer check.
     *
     * @param c the c
     */
    private void angleMinuteIntegerCheck(char c) {
        switch (c) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (!this.checkTempMinuteInteger(c)) {
                    this.result.setCode(InputCheckResult.ERROR_CODE_ANGLE_MINUTE_BIGGER_60).setMessage(InputCheckResult.ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60);
                    return;
                }
                this.minuteList.add(c);
                this.cursor = Cursor.CURSOR_ANGLE_MINUTS_INTEGER;
                break;
            case '0':
                if (!this.checkTempMinuteInteger(c)) {
                    this.result.setCode(InputCheckResult.ERROR_CODE_ANGLE_MINUTE_BIGGER_60).setMessage(InputCheckResult.ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60);
                    return;
                }
                this.minuteList.add(c);
//                this.minuteList.add('′');
                this.cursor = Cursor.CURSOR_ANGLE_MINUTS_SIGNAL;
                break;
            case '′':
                if (this.minuteList.size() == 0) {
                    this.minuteList.add('0');
                }
                this.cursor = Cursor.CURSOR_ANGLE_MINUTS_SIGNAL;
                break;
            default:
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_CHARACTER).setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_CHARACTER + c);
                break;
        }
    }


    /**
     * Angle degree integer check.
     *
     * @param c the c
     */
    private void angleDegreeIntegerCheck(char c) {
        switch (c) {
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
                if (!this.checkTempDegreeValue(c)) {
                    this.result.setCode(InputCheckResult.ERROR_CODE_BIGGER).setMessage(InputCheckResult.ERROR_MESSAGE_BIGGER);
                    return;
                }
                this.degreeList.add(c);
                this.cursor = Cursor.CURSOR_ANGLE_DEGREE_INTEGER;
                break;
            case '°':
//                this.degreeList.add('°');
                this.cursor = Cursor.CURSOR_ANGLE_DEGREE_SIGNAL;
                break;
            default:
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_CHARACTER).setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_CHARACTER + c);
                break;
        }
    }

    /**
     * First char check.
     *
     * @param c the c
     */
    private void firstCharCheck(char c) {
        switch (c) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (Character.getNumericValue(c) > checkLimitMax.getDegree()) {
                    this.result.setCode(InputCheckResult.ERROR_CODE_BIGGER).setMessage(InputCheckResult.ERROR_MESSAGE_BIGGER);
                    return;
                }
                this.degreeList.add(c);
                this.cursor = Cursor.CURSOR_ANGLE_DEGREE_INTEGER;
                break;
            case '0':
                this.degreeList.add(c);
//                this.degreeList.add('°');
                this.cursor = Cursor.CURSOR_ANGLE_DEGREE_SIGNAL;
                break;
            case '°':
                this.degreeList.add('0');
//                this.degreeList.add('°');
                this.cursor = Cursor.CURSOR_ANGLE_DEGREE_SIGNAL;
                break;
            default:
                this.result.setCode(InputCheckResult.ERROR_CODE_ILLEGAL_CHARACTER).setMessage(InputCheckResult.ERROR_MESSAGE_ILLEGAL_CHARACTER + c);
        }
    }


    /**
     * Generate whole list list.
     *
     * @return the list
     */
    private List<Character> generateWholeList() {
        List<Character> list = new ArrayList<>();
        if (this.degreeList.size() > 0) {
            list.addAll(degreeList);
            list.add('°');
        }
        if (this.minuteList.size() > 0) {
            list.addAll(minuteList);
            list.add('′');
        }
        if (this.secondIntegerList.size() > 0) {
            list.addAll(secondIntegerList);
            if (this.secondDecimalList.size() > 0) {
                list.add('.');
                list.addAll(secondDecimalList);
            }
            list.add('″');
        }
        return list;
    }

    /**
     * Check temp degree value boolean.
     *
     * @param c the c
     * @return the boolean
     */
    private boolean checkTempDegreeValue(char c) {
        StringBuilder builder = new StringBuilder(degreeList.size() + 1);
        this.degreeList.forEach(builder::append);
        builder.append(c);
        String tempString = builder.toString();
        int degree = Integer.parseInt(tempString);
        return degree >= this.checkLimitMin.getDegree() && degree < this.checkLimitMax.getDegree();
    }

    /**
     * Check temp minute integer boolean.
     *
     * @param c the c
     * @return the boolean
     */
    private boolean checkTempMinuteInteger(char c) {
        StringBuilder builder = new StringBuilder(this.minuteList.size() + 1);
        this.minuteList.forEach(builder::append);
        builder.append(c);
        int minute = Integer.parseInt(builder.toString());
        return minute < 60;
    }

    /**
     * Check temp second integer boolean.
     *
     * @param c the c
     * @return the boolean
     */
    private boolean checkTempSecondInteger(char c) {
        StringBuilder builder = new StringBuilder(this.secondIntegerList.size() + 1);
        this.secondIntegerList.forEach(builder::append);
        builder.append(c);
        int second = Integer.parseInt(builder.toString());
        return second < 60;
    }


    /**
     * Degree int.
     *
     * @return the int
     */
    private int degree() {
        if (this.degreeList.size() == 0) {
            return 0;
        } else {
            StringBuilder builder = new StringBuilder(this.degreeList.size());
            this.degreeList.forEach(builder::append);
            String tempString = builder.toString();
            return Integer.parseInt(tempString);
        }
    }

    /**
     * Gets integerform list.
     *
     * @param list the list
     * @return the integerform list
     */
    private int getIntegerFromList(List<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        list.forEach(builder::append);
        String tempString = builder.toString();
        return Integer.parseInt(tempString);
    }


}
