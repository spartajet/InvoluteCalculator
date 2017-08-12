package com.ptb.involutecalculator.io.check;

/**
 * The type Input check real result.
 *
 * @description
 * @create 2017 -03-30 上午9:34
 * @email spartajet.guo @gmail.com
 */
public class InputCheckResultReal extends InputCheckResult {
    /**
     * The Number.
     */
    private double value;

    /**
     * Instantiates a new Input check result.
     */
    public InputCheckResultReal() {
        this(ERROR_CODE_NO_ERROR, ERROR_MESSAGE_NO_ERROR);
    }

    /**
     * Instantiates a new Input check result real.
     *
     * @param code        the code
     * @param message     the message
     */
    public InputCheckResultReal(int code, String message) {
        super(code, message, "");
        this.value = 0.0;
    }

    /**
     * Gets inputValue.
     *
     * @return the inputValue
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets inputValue.
     *
     * @param value the inputValue
     *
     * @return the inputValue
     */
    public InputCheckResultReal setValue(double value) {
        this.value = value;
        return this;
    }
}
