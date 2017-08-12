package com.ptb.involutecalculator.io.check;

/**
 * The type Input check result.
 *
 * @description
 * @create 2017 -03-29 下午2:30
 * @email gxz04220427 @163.com
 */
public abstract class InputCheckResult {

    /**
     * The constant ERROR_CODE_NO_ERROR.
     */
    public final static int ERROR_CODE_NO_ERROR = 0;
    /**
     * The constant ERROR_MESSAGE_NO_ERROR.
     */
    public final static String ERROR_MESSAGE_NO_ERROR = "success";
    /**
     * The constant ERROR_CODE_BIGGER.
     */
    public final static int ERROR_CODE_BIGGER = 2;
    /**
     * The constant ERROR_MESSAGE_BIGGER.
     */
    public final static String ERROR_MESSAGE_BIGGER = "bigger than the max limit: ";
    /**
     * The constant ERROR_CODE_SMALLER.
     */
    public final static int ERROR_CODE_SMALLER = 3;
    /**
     * The constant ERROR_MESSAGE_SMALLER.
     */
    public final static String ERROR_MESSAGE_SMALLER = "smaller than the min limit: ";
    /**
     * The constant ERROR_CODE_MORE_INTEGER_DIGITS.
     */
    public final static int ERROR_CODE_MORE_INTEGER_DIGITS = 4;
    /**
     * The constant ERROR_MESSAGE_MORE_INTEGER_DIGITS.
     */
    public final static String ERROR_MESSAGE_MORE_INTEGER_DIGITS = "more digits before dot: ";
    /**
     * The constant ERROR_CODE_MORE_DECIMAL_DIGITS.
     */
    public final static int ERROR_CODE_MORE_DECIMAL_DIGITS = 5;
    /**
     * The constant ERROR_MESSAGE_MORE_DECIMAL_DIGITS.
     */
    public final static String ERROR_MESSAGE_MORE_DECIMAL_DIGITS = "more digits after dot: ";

    /**
     * The constant ERROR_CODE_ILLEGAL_DOT.
     */
    public final static int ERROR_CODE_ILLEGAL_DOT = 6;
    /**
     * The constant ERROR_MESSAGE_ILLEGAL_DOT.
     */
    public final static String ERROR_MESSAGE_ILLEGAL_DOT = "Illegal dot here";
    /**
     * The constant ERROR_CODE_ZERO_REAL_ERROR.
     */
    public final static int ERROR_CODE_ZERO_REAL_ERROR = 7;
    /**
     * The constant ERROR_MESSAGE_ZERO_REAL_ERROR.
     */
    public final static String ERROR_MESSAGE_ZERO_REAL_ERROR = "more than one 0 in a real number";
    /**
     * The constant ERROR_CODE_ILLEGAL_SIGNAL_POSITION.
     */
    public final static int ERROR_CODE_ILLEGAL_SIGNAL_POSITION = 8;
    /**
     * The constant ERROR_MESSAGE_ILLEGAL_SIGNAL_POSITION.
     */
    public final static String ERROR_MESSAGE_ILLEGAL_SIGNAL_POSITION = "Illegal signal position here";

    /**
     * The constant ERROR_CODE_ZERO_INT_PREFIX_ERROR.
     */
    public final static int ERROR_CODE_ZERO_INT_PREFIX_ERROR = 9;
    /**
     * The constant ERROR_MESSAGE_ZERO_INT_PREFIX_ERROR.
     */
    public final static String ERROR_MESSAGE_ZERO_INT_PREFIX_ERROR = "Illegal 0 at the beginning of a integer number";

    /**
     * The constant ERROR_CODE_ILLEGAL_CHARACTER.
     */
    public final static int ERROR_CODE_ILLEGAL_CHARACTER = 10;
    /**
     * The constant ERROR_MESSAGE_ILLEGAL_CHARACTER.
     */
    public final static String ERROR_MESSAGE_ILLEGAL_CHARACTER = "Illegal character here: ";

    /**
     * The constant ERROR_CODE_FINISH_INPUT.
     */
    public final static int ERROR_CODE_FINISH_INPUT = 11;
    /**
     * The constant ERROR_MESSAGE_FINISH_INPUT.
     */
    public final static String ERROR_MESSAGE_FINISH_INPUT = "input is finished, you can not input any character more";
    /**
     * The constant ERROR_CODE_ANGLE_MINUTE_BIGGER_60.
     */
    public final static int ERROR_CODE_ANGLE_MINUTE_BIGGER_60 = 12;
    /**
     * The constant ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60.
     */
    public final static String ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60 = "minute part is bigger than 60";
    /**
     * The constant ERROR_CODE_ANGLE_MINUTE_BIGGER_60.
     */
    public final static int ERROR_CODE_ANGLE_SECOND_INTEGER_BIGGER_60 = 13;
    /**
     * The constant ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60.
     */
    public final static String ERROR_MESSGAE_ANGLE_SECOND_INTEGER_BIGGER_60 = "second part is bigger than 60";
    /**
     * The constant ERROR_CODE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS.
     */
    public final static int ERROR_CODE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS = 14;
    /**
     * The constant ERROR_MESSAGE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS.
     */
    public final static String ERROR_MESSAGE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS = "more angle second decimal digits";
    /**
     * The Error Code.
     */
    protected int code;
    /**
     * The Message.
     */
    protected String message;
    /**
     * The Value string.
     */
    protected String valueString;

    /**
     * Instantiates a new Input check result.
     */
    public InputCheckResult() {
    }

    /**
     * Instantiates a new Input check result.
     *
     * @param code        the code
     * @param message     the message
     * @param valueString the inputValue string
     */
    public InputCheckResult(int code, String message, String valueString) {
        this.code = code;
        this.message = message;
        this.valueString = valueString;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     * @return the code
     */
    public InputCheckResult setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     * @return the message
     */
    public InputCheckResult setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Gets inputValue string.
     *
     * @return the inputValue string
     */
    public String getValueString() {
        return valueString;
    }

    /**
     * Sets inputValue string.
     *
     * @param valueString the inputValue string
     * @return the inputValue string
     */
    public InputCheckResult setValueString(String valueString) {
        this.valueString = valueString;
        return this;
    }
}
