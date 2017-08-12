package com.ptb.involutecalculator.involute;

/**
 * The type Angle.
 *
 * @description
 * @create 2017 -05-09 下午2:12
 * @email spartajet.guo @gmail.com
 */
public class Angle implements Comparable<Angle> {
    /**
     * The Degree.
     */
    private int degree;
    /**
     * The Minute.
     */
    private int minute;
    /**
     * The Second.
     */
    private double second;

    /**
     * The Angle string.
     */
    private String angleString;

    public final static Angle ANGLE_MAX_VALUE = new Angle(Integer.MAX_VALUE, 0, 0);
    public final static Angle ANGLE_MIN_VALUE = new Angle(-Integer.MAX_VALUE, 0, 0);
    public final static Angle ANGLE_ZERO_VALUE = new Angle(0, 0, 0);

    /**
     * Instantiates a new Angle.
     *
     * @param degree the degree
     * @param minute the minute
     * @param second the second
     */
    public Angle(int degree, int minute, double second) {
        if (second >= 60.0) {
            this.minute = minute + 1;
            this.second = second - 60.0;
        } else {
            this.second = second;
        }
        if (this.minute >= 60) {
            this.degree = degree + 1;
            this.minute -= 60;
        } else {
            this.minute = minute;
            this.degree = degree;
        }
//        this.degree = degree;
//        this.minute = minute;
//        this.second = second;
    }

    public Angle(double value) {

        this.degree = (int) value;
        this.minute = (int) ((value - degree) * 60);
        this.second = ((value - degree) * 60 - minute) * 60;
    }

    public double toReal() {
        return this.degree + this.minute / 60.0 + this.second / 3600.0;
    }

    /**
     * Instantiates a new Angle.
     */
    public Angle() {
        this(0, 0, 0);
    }

    public Angle(Angle angle) {
        this(angle.degree, angle.minute, angle.second);
    }

    /**
     * Compare to int.
     *
     * @param angle the angle
     * @return the int
     */
    @Override
    public int compareTo(Angle angle) {
        if (this.degree == angle.degree && this.minute == angle.minute && this.second == angle.second) {
            return 0;
        }
        if (this.degree > angle.degree) {
            return 1;
        }
        if (this.degree == angle.degree && this.minute > angle.minute) {
            return 1;
        }
        if (this.degree == angle.degree && this.minute == angle.minute && this.second > angle.second) {
            return 1;
        }
        return -1;
    }

    public boolean bigger(Angle angle) {
        return this.compareTo(angle) == 1;
    }

    public boolean smaller(Angle angle) {
        return this.compareTo(angle) == -1;
    }

    public Angle minus(Angle angle) {
        return new Angle(this.toReal() - angle.toReal());
    }

    public Angle plus(Angle angle) {
        return new Angle(this.toReal() + angle.toReal());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Angle) {
            if (this.degree == ((Angle) obj).degree && this.minute == ((Angle) obj).minute && (this.second - ((Angle) obj).second) < 0.00000000000001) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear.
     */
    public void clear() {
        this.degree = 0;
        this.minute = 0;
        this.second = 0;
        this.angleString = "";
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return this.degree + "°" + this.minute + "'" + this.second + "\"";
    }


    /**
     * Gets degree.
     *
     * @return the degree
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Sets degree.
     *
     * @param degree the degree
     * @return the degree
     */
    public Angle setDegree(int degree) {
        this.degree = degree;
        return this;
    }

    /**
     * Gets minute.
     *
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets minute.
     *
     * @param minute the minute
     * @return the minute
     */
    public Angle setMinute(int minute) {
        this.minute = minute;
        return this;
    }

    /**
     * Gets second.
     *
     * @return the second
     */
    public double getSecond() {
        return second;
    }

    /**
     * Sets second.
     *
     * @param second the second
     * @return the second
     */
    public Angle setSecond(int second) {
        this.second = second;
        return this;
    }

    /**
     * Gets angle string.
     *
     * @return the angle string
     */
    public String getAngleString() {
        return angleString;
    }

    /**
     * Sets angle string.
     *
     * @param angleString the angle string
     * @return the angle string
     */
    public Angle setAngleString(String angleString) {
        this.angleString = angleString;
        return this;
    }
}
