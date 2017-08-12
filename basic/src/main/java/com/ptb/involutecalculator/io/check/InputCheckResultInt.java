package com.ptb.involutecalculator.io.check;

/**
 * @description
 * @create 2017-04-20 上午9:00
 * @email spartajet.guo@gmail.com
 */
public class InputCheckResultInt extends InputCheckResult {
    private int value;

    /**
     * Instantiates a new Input check result.
     */
    public InputCheckResultInt() {
        this(ERROR_CODE_NO_ERROR, ERROR_MESSAGE_NO_ERROR);

    }

    /**
     * Instantiates a new Input check result.
     *
     * @param code    the code
     * @param message the message
     */
    public InputCheckResultInt(int code, String message) {
        super(code, message, "");
    }

    /**
     * Instantiates a new Input check result.
     *
     * @param code        the code
     * @param message     the message
     * @param valueString the inputValue string
     */
    public InputCheckResultInt(int code, String message, String valueString, int value) {
        super(code, message, valueString);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public InputCheckResultInt setValue(int value) {
        this.value = value;
        return this;
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
     *
     * @return the code
     */
    public InputCheckResultInt setCode(int code) {
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
     *
     * @return the message
     */
    public InputCheckResultInt setMessage(String message) {
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
     *
     * @return the inputValue string
     */
    public InputCheckResult setValueString(String valueString) {
        this.valueString = valueString;
        return this;
    }
}
