package com.ptb.involutecalculator.io.result;

/**
 * The type Calculate result.
 *
 * @description
 * @create 2017 -07-06 下午8:02
 * @email spartajet.guo @gmail.com
 */
public class CalculateResult {
    /**
     * The Angle helix.
     */
    private String angleHelixInputValue;
    /**
     * The Angle helix result value.
     */
    private String angleHelixResultValue;
    /**
     * The Angle helix contradiction.
     */
    private String angleHelixContradiction;
    /**
     * The Angle helix fixed.
     */
    private boolean angleHelixFixed;
    /**
     * The Angle lead.
     */
    private String angleLeadInputValue;
    /**
     * The Angle lead result value.
     */
    private String angleLeadResultValue;
    /**
     * The Angle lead contradiction.
     */
    private String angleLeadContradiction;
    /**
     * The Angle lead fixed.
     */
    private boolean angleLeadFixed;
    /**
     * The Angle pressure.
     */
    private String anglePressureInputValue;
    /**
     * The Angle pressure result value.
     */
    private String anglePressureResultValue;
    /**
     * The Angle pressure contradiction.
     */
    private String anglePressureContradiction;
    /**
     * The Angle pressure fixed.
     */
    private boolean anglePressureFixed;
    /**
     * The Angle pressure normal.
     */
    private String anglePressureNormalInputValue;
    /**
     * The Angle pressure normal result value.
     */
    private String anglePressureNormalResultValue;
    /**
     * The Angle pressure normal contradiction.
     */
    private String anglePressureNormalContradiction;
    /**
     * The Angle pressure normal fixed.
     */
    private boolean anglePressureNormalFixed;
    /**
     * The Diameter base.
     */
    private String diameterBaseInputValue;
    /**
     * The Diameter base result value.
     */
    private String diameterBaseResultValue;
    /**
     * The Diameter base contradiction.
     */
    private String diameterBaseContradiction;
    /**
     * The Diameter base fixed.
     */
    private boolean diameterBaseFixed;
    /**
     * The Diameter reference.
     */
    private String diameterReferenceInputValue;
    /**
     * The Diameter reference result value.
     */
    private String diameterReferenceResultValue;
    /**
     * The Diameter reference contradiction.
     */
    private String diameterReferenceContradiction;
    /**
     * The Diameter reference fixed.
     */
    private boolean diameterReferenceFixed;
    /**
     * The Module axial.
     */
    private String moduleAxialInputValue;
    /**
     * The Module axial result value.
     */
    private String moduleAxialResultValue;
    /**
     * The Module axial contradiction.
     */
    private String moduleAxialContradiction;
    /**
     * The Module axial fixed.
     */
    private boolean moduleAxialFixed;
    /**
     * The Module basic.
     */
    private String moduleBasicInputValue;
    /**
     * The Module basic result value.
     */
    private String moduleBasicResultValue;
    /**
     * The Module basic contradiction.
     */
    private String moduleBasicContradiction;
    /**
     * The Module basic fixed.
     */
    private boolean moduleBasicFixed;
    /**
     * The Module normal.
     */
    private String moduleNormalInputValue;
    /**
     * The Module normal result value.
     */
    private String moduleNormalResultValue;
    /**
     * The Module normal contradiction.
     */
    private String moduleNormalContradiction;
    /**
     * The Module normal fixed.
     */
    private boolean moduleNormalFixed;
    /**
     * The Module transverse.
     */
    private String moduleTransverseInputValue;
    /**
     * The Module transverse result value.
     */
    private String moduleTransverseResultValue;
    /**
     * The Module transverse contradiction.
     */
    private String moduleTransverseContradiction;
    /**
     * The Module transverse fixed.
     */
    private boolean moduleTransverseFixed;
    /**
     * The Teeth number.
     */
    private String teethNumberInputValue;
    /**
     * The Teeth number result value.
     */
    private String teethNumberResultValue;

    /**
     * The Teeth number contradiction.
     */
    private String teethNumberContradiction;
    /**
     * The Teeth number fixed.
     */
    private boolean teethNumberFixed;

    /**
     * Instantiates a new Calculate result.
     */
    public CalculateResult() {
    }

    /**
     * Instantiates a new Calculate result.
     *
     * @param angleHelixInputValue             the angle helix input value
     * @param angleHelixResultValue           the angle heilx result vallue
     * @param angleHelixContradiction          the angle helix contradiction
     * @param angleHelixFixed                  the angle helix fixed
     * @param angleLeadInputValue              the angle lead input value
     * @param angleLeadResultValue             the angle lead result value
     * @param angleLeadContradiction           the angle lead contradiction
     * @param angleLeadFixed                   the angle lead fixed
     * @param anglePressureInputValue          the angle pressure input value
     * @param anglePressureResultValue         the angle pressure result value
     * @param anglePressureContradiction       the angle pressure contradiction
     * @param anglePressureFixed               the angle pressure fixed
     * @param anglePressureNormalInputValue    the angle pressure normal input value
     * @param anglePressureNormalResultValue   the angle pressure normal result value
     * @param anglePressureNormalContradiction the angle pressure normal contradiction
     * @param anglePressureNormalFixed         the angle pressure normal fixed
     * @param diameterBaseInputValue           the diameter base input value
     * @param diameterBaseResultValue          the diameter base result value
     * @param diameterBaseContradiction        the diameter base contradiction
     * @param diameterBaseFixed                the diameter base fixed
     * @param diameterReferenceInputValue      the diameter reference input value
     * @param diameterReferenceResultValue     the diameter reference result value
     * @param diameterReferenceContradiction   the diameter reference contradiction
     * @param diameterReferenceFixed           the diameter reference fixed
     * @param moduleAxialInputValue            the module axial input value
     * @param moduleAxialResultValue           the module axial result value
     * @param moduleAxialContradiction         the module axial contradiction
     * @param moduleAxialFixed                 the module axial fixed
     * @param moduleBasicInputValue            the module basic input value
     * @param moduleBasicResultValue           the module basic result value
     * @param moduleBasicContradiction         the module basic contradiction
     * @param moduleBasicFixed                 the module basic fixed
     * @param moduleNormalInputValue           the module normal input value
     * @param moduleNormalResultValue          the module normal result value
     * @param moduleNormalContradiction        the module normal contradiction
     * @param moduleNormalFixed                the module normal fixed
     * @param moduleTransverseInputValue       the module transverse input value
     * @param moduleTransverseResultValue      the module transverse result value
     * @param moduleTransverseContradiction    the module transverse contradiction
     * @param moduleTransverseFixed            the module transverse fixed
     * @param teethNumberInputValue            the teeth number input value
     * @param teethNumberResultValue           the teeth number result value
     * @param teethNumberContradiction         the teeth number contradiction
     * @param teethNumberFixed                 the teeth number fixed
     */
    public CalculateResult(String angleHelixInputValue, String angleHelixResultValue, String angleHelixContradiction, boolean angleHelixFixed, String angleLeadInputValue, String angleLeadResultValue, String angleLeadContradiction, boolean angleLeadFixed, String anglePressureInputValue, String anglePressureResultValue, String anglePressureContradiction, boolean anglePressureFixed, String anglePressureNormalInputValue, String anglePressureNormalResultValue, String anglePressureNormalContradiction, boolean anglePressureNormalFixed, String diameterBaseInputValue, String diameterBaseResultValue, String diameterBaseContradiction, boolean diameterBaseFixed, String diameterReferenceInputValue, String diameterReferenceResultValue, String diameterReferenceContradiction, boolean diameterReferenceFixed, String moduleAxialInputValue, String moduleAxialResultValue, String moduleAxialContradiction, boolean moduleAxialFixed, String moduleBasicInputValue, String moduleBasicResultValue, String moduleBasicContradiction, boolean moduleBasicFixed, String moduleNormalInputValue, String moduleNormalResultValue, String moduleNormalContradiction, boolean moduleNormalFixed, String moduleTransverseInputValue, String moduleTransverseResultValue, String moduleTransverseContradiction, boolean moduleTransverseFixed, String teethNumberInputValue, String teethNumberResultValue, String teethNumberContradiction, boolean teethNumberFixed) {
        this.angleHelixInputValue = angleHelixInputValue;
        this.angleHelixResultValue = angleHelixResultValue;
        this.angleHelixContradiction = angleHelixContradiction;
        this.angleHelixFixed = angleHelixFixed;
        this.angleLeadInputValue = angleLeadInputValue;
        this.angleLeadResultValue = angleLeadResultValue;
        this.angleLeadContradiction = angleLeadContradiction;
        this.angleLeadFixed = angleLeadFixed;
        this.anglePressureInputValue = anglePressureInputValue;
        this.anglePressureResultValue = anglePressureResultValue;
        this.anglePressureContradiction = anglePressureContradiction;
        this.anglePressureFixed = anglePressureFixed;
        this.anglePressureNormalInputValue = anglePressureNormalInputValue;
        this.anglePressureNormalResultValue = anglePressureNormalResultValue;
        this.anglePressureNormalContradiction = anglePressureNormalContradiction;
        this.anglePressureNormalFixed = anglePressureNormalFixed;
        this.diameterBaseInputValue = diameterBaseInputValue;
        this.diameterBaseResultValue = diameterBaseResultValue;
        this.diameterBaseContradiction = diameterBaseContradiction;
        this.diameterBaseFixed = diameterBaseFixed;
        this.diameterReferenceInputValue = diameterReferenceInputValue;
        this.diameterReferenceResultValue = diameterReferenceResultValue;
        this.diameterReferenceContradiction = diameterReferenceContradiction;
        this.diameterReferenceFixed = diameterReferenceFixed;
        this.moduleAxialInputValue = moduleAxialInputValue;
        this.moduleAxialResultValue = moduleAxialResultValue;
        this.moduleAxialContradiction = moduleAxialContradiction;
        this.moduleAxialFixed = moduleAxialFixed;
        this.moduleBasicInputValue = moduleBasicInputValue;
        this.moduleBasicResultValue = moduleBasicResultValue;
        this.moduleBasicContradiction = moduleBasicContradiction;
        this.moduleBasicFixed = moduleBasicFixed;
        this.moduleNormalInputValue = moduleNormalInputValue;
        this.moduleNormalResultValue = moduleNormalResultValue;
        this.moduleNormalContradiction = moduleNormalContradiction;
        this.moduleNormalFixed = moduleNormalFixed;
        this.moduleTransverseInputValue = moduleTransverseInputValue;
        this.moduleTransverseResultValue = moduleTransverseResultValue;
        this.moduleTransverseContradiction = moduleTransverseContradiction;
        this.moduleTransverseFixed = moduleTransverseFixed;
        this.teethNumberInputValue = teethNumberInputValue;
        this.teethNumberResultValue = teethNumberResultValue;
        this.teethNumberContradiction = teethNumberContradiction;
        this.teethNumberFixed = teethNumberFixed;
    }

    /**
     * Gets angle helix input value.
     *
     * @return the angle helix input value
     */
    public String getAngleHelixInputValue() {
        return angleHelixInputValue;
    }

    /**
     * Sets angle helix input value.
     *
     * @param angleHelixInputValue the angle helix input value
     */
    public void setAngleHelixInputValue(String angleHelixInputValue) {
        this.angleHelixInputValue = angleHelixInputValue;
    }

    /**
     * Gets angle heilx result vallue.
     *
     * @return the angle heilx result vallue
     */
    public String getAngleHelixResultValue() {
        return angleHelixResultValue;
    }

    /**
     * Sets angle heilx result vallue.
     *
     * @param angleHelixResultValue the angle heilx result vallue
     */
    public void setAngleHelixResultValue(String angleHelixResultValue) {
        this.angleHelixResultValue = angleHelixResultValue;
    }

    /**
     * Gets angle helix contradiction.
     *
     * @return the angle helix contradiction
     */
    public String getAngleHelixContradiction() {
        return angleHelixContradiction;
    }

    /**
     * Sets angle helix contradiction.
     *
     * @param angleHelixContradiction the angle helix contradiction
     */
    public void setAngleHelixContradiction(String angleHelixContradiction) {
        this.angleHelixContradiction = angleHelixContradiction;
    }

    /**
     * Is angle helix fixed boolean.
     *
     * @return the boolean
     */
    public boolean isAngleHelixFixed() {
        return angleHelixFixed;
    }

    /**
     * Sets angle helix fixed.
     *
     * @param angleHelixFixed the angle helix fixed
     */
    public void setAngleHelixFixed(boolean angleHelixFixed) {
        this.angleHelixFixed = angleHelixFixed;
    }

    /**
     * Gets angle lead input value.
     *
     * @return the angle lead input value
     */
    public String getAngleLeadInputValue() {
        return angleLeadInputValue;
    }

    /**
     * Sets angle lead input value.
     *
     * @param angleLeadInputValue the angle lead input value
     */
    public void setAngleLeadInputValue(String angleLeadInputValue) {
        this.angleLeadInputValue = angleLeadInputValue;
    }

    /**
     * Gets angle lead result value.
     *
     * @return the angle lead result value
     */
    public String getAngleLeadResultValue() {
        return angleLeadResultValue;
    }

    /**
     * Sets angle lead result value.
     *
     * @param angleLeadResultValue the angle lead result value
     */
    public void setAngleLeadResultValue(String angleLeadResultValue) {
        this.angleLeadResultValue = angleLeadResultValue;
    }

    /**
     * Gets angle lead contradiction.
     *
     * @return the angle lead contradiction
     */
    public String getAngleLeadContradiction() {
        return angleLeadContradiction;
    }

    /**
     * Sets angle lead contradiction.
     *
     * @param angleLeadContradiction the angle lead contradiction
     */
    public void setAngleLeadContradiction(String angleLeadContradiction) {
        this.angleLeadContradiction = angleLeadContradiction;
    }

    /**
     * Is angle lead fixed boolean.
     *
     * @return the boolean
     */
    public boolean isAngleLeadFixed() {
        return angleLeadFixed;
    }

    /**
     * Sets angle lead fixed.
     *
     * @param angleLeadFixed the angle lead fixed
     */
    public void setAngleLeadFixed(boolean angleLeadFixed) {
        this.angleLeadFixed = angleLeadFixed;
    }

    /**
     * Gets angle pressure input value.
     *
     * @return the angle pressure input value
     */
    public String getAnglePressureInputValue() {
        return anglePressureInputValue;
    }

    /**
     * Sets angle pressure input value.
     *
     * @param anglePressureInputValue the angle pressure input value
     */
    public void setAnglePressureInputValue(String anglePressureInputValue) {
        this.anglePressureInputValue = anglePressureInputValue;
    }

    /**
     * Gets angle pressure result value.
     *
     * @return the angle pressure result value
     */
    public String getAnglePressureResultValue() {
        return anglePressureResultValue;
    }

    /**
     * Sets angle pressure result value.
     *
     * @param anglePressureResultValue the angle pressure result value
     */
    public void setAnglePressureResultValue(String anglePressureResultValue) {
        this.anglePressureResultValue = anglePressureResultValue;
    }

    /**
     * Gets angle pressure contradiction.
     *
     * @return the angle pressure contradiction
     */
    public String getAnglePressureContradiction() {
        return anglePressureContradiction;
    }

    /**
     * Sets angle pressure contradiction.
     *
     * @param anglePressureContradiction the angle pressure contradiction
     */
    public void setAnglePressureContradiction(String anglePressureContradiction) {
        this.anglePressureContradiction = anglePressureContradiction;
    }

    /**
     * Is angle pressure fixed boolean.
     *
     * @return the boolean
     */
    public boolean isAnglePressureFixed() {
        return anglePressureFixed;
    }

    /**
     * Sets angle pressure fixed.
     *
     * @param anglePressureFixed the angle pressure fixed
     */
    public void setAnglePressureFixed(boolean anglePressureFixed) {
        this.anglePressureFixed = anglePressureFixed;
    }

    /**
     * Gets angle pressure normal input value.
     *
     * @return the angle pressure normal input value
     */
    public String getAnglePressureNormalInputValue() {
        return anglePressureNormalInputValue;
    }

    /**
     * Sets angle pressure normal input value.
     *
     * @param anglePressureNormalInputValue the angle pressure normal input value
     */
    public void setAnglePressureNormalInputValue(String anglePressureNormalInputValue) {
        this.anglePressureNormalInputValue = anglePressureNormalInputValue;
    }

    /**
     * Gets angle pressure normal result value.
     *
     * @return the angle pressure normal result value
     */
    public String getAnglePressureNormalResultValue() {
        return anglePressureNormalResultValue;
    }

    /**
     * Sets angle pressure normal result value.
     *
     * @param anglePressureNormalResultValue the angle pressure normal result value
     */
    public void setAnglePressureNormalResultValue(String anglePressureNormalResultValue) {
        this.anglePressureNormalResultValue = anglePressureNormalResultValue;
    }

    /**
     * Gets angle pressure normal contradiction.
     *
     * @return the angle pressure normal contradiction
     */
    public String getAnglePressureNormalContradiction() {
        return anglePressureNormalContradiction;
    }

    /**
     * Sets angle pressure normal contradiction.
     *
     * @param anglePressureNormalContradiction the angle pressure normal contradiction
     */
    public void setAnglePressureNormalContradiction(String anglePressureNormalContradiction) {
        this.anglePressureNormalContradiction = anglePressureNormalContradiction;
    }

    /**
     * Is angle pressure normal fixed boolean.
     *
     * @return the boolean
     */
    public boolean isAnglePressureNormalFixed() {
        return anglePressureNormalFixed;
    }

    /**
     * Sets angle pressure normal fixed.
     *
     * @param anglePressureNormalFixed the angle pressure normal fixed
     */
    public void setAnglePressureNormalFixed(boolean anglePressureNormalFixed) {
        this.anglePressureNormalFixed = anglePressureNormalFixed;
    }

    /**
     * Gets diameter base input value.
     *
     * @return the diameter base input value
     */
    public String getDiameterBaseInputValue() {
        return diameterBaseInputValue;
    }

    /**
     * Sets diameter base input value.
     *
     * @param diameterBaseInputValue the diameter base input value
     */
    public void setDiameterBaseInputValue(String diameterBaseInputValue) {
        this.diameterBaseInputValue = diameterBaseInputValue;
    }

    /**
     * Gets diameter base result value.
     *
     * @return the diameter base result value
     */
    public String getDiameterBaseResultValue() {
        return diameterBaseResultValue;
    }

    /**
     * Sets diameter base result value.
     *
     * @param diameterBaseResultValue the diameter base result value
     */
    public void setDiameterBaseResultValue(String diameterBaseResultValue) {
        this.diameterBaseResultValue = diameterBaseResultValue;
    }

    /**
     * Gets diameter base contradiction.
     *
     * @return the diameter base contradiction
     */
    public String getDiameterBaseContradiction() {
        return diameterBaseContradiction;
    }

    /**
     * Sets diameter base contradiction.
     *
     * @param diameterBaseContradiction the diameter base contradiction
     */
    public void setDiameterBaseContradiction(String diameterBaseContradiction) {
        this.diameterBaseContradiction = diameterBaseContradiction;
    }

    /**
     * Is diameter base fixed boolean.
     *
     * @return the boolean
     */
    public boolean isDiameterBaseFixed() {
        return diameterBaseFixed;
    }

    /**
     * Sets diameter base fixed.
     *
     * @param diameterBaseFixed the diameter base fixed
     */
    public void setDiameterBaseFixed(boolean diameterBaseFixed) {
        this.diameterBaseFixed = diameterBaseFixed;
    }

    /**
     * Gets diameter reference input value.
     *
     * @return the diameter reference input value
     */
    public String getDiameterReferenceInputValue() {
        return diameterReferenceInputValue;
    }

    /**
     * Sets diameter reference input value.
     *
     * @param diameterReferenceInputValue the diameter reference input value
     */
    public void setDiameterReferenceInputValue(String diameterReferenceInputValue) {
        this.diameterReferenceInputValue = diameterReferenceInputValue;
    }

    /**
     * Gets diameter reference result value.
     *
     * @return the diameter reference result value
     */
    public String getDiameterReferenceResultValue() {
        return diameterReferenceResultValue;
    }

    /**
     * Sets diameter reference result value.
     *
     * @param diameterReferenceResultValue the diameter reference result value
     */
    public void setDiameterReferenceResultValue(String diameterReferenceResultValue) {
        this.diameterReferenceResultValue = diameterReferenceResultValue;
    }

    /**
     * Gets diameter reference contradiction.
     *
     * @return the diameter reference contradiction
     */
    public String getDiameterReferenceContradiction() {
        return diameterReferenceContradiction;
    }

    /**
     * Sets diameter reference contradiction.
     *
     * @param diameterReferenceContradiction the diameter reference contradiction
     */
    public void setDiameterReferenceContradiction(String diameterReferenceContradiction) {
        this.diameterReferenceContradiction = diameterReferenceContradiction;
    }

    /**
     * Is diameter reference fixed boolean.
     *
     * @return the boolean
     */
    public boolean isDiameterReferenceFixed() {
        return diameterReferenceFixed;
    }

    /**
     * Sets diameter reference fixed.
     *
     * @param diameterReferenceFixed the diameter reference fixed
     */
    public void setDiameterReferenceFixed(boolean diameterReferenceFixed) {
        this.diameterReferenceFixed = diameterReferenceFixed;
    }

    /**
     * Gets module axial input value.
     *
     * @return the module axial input value
     */
    public String getModuleAxialInputValue() {
        return moduleAxialInputValue;
    }

    /**
     * Sets module axial input value.
     *
     * @param moduleAxialInputValue the module axial input value
     */
    public void setModuleAxialInputValue(String moduleAxialInputValue) {
        this.moduleAxialInputValue = moduleAxialInputValue;
    }

    /**
     * Gets module axial result value.
     *
     * @return the module axial result value
     */
    public String getModuleAxialResultValue() {
        return moduleAxialResultValue;
    }

    /**
     * Sets module axial result value.
     *
     * @param moduleAxialResultValue the module axial result value
     */
    public void setModuleAxialResultValue(String moduleAxialResultValue) {
        this.moduleAxialResultValue = moduleAxialResultValue;
    }

    /**
     * Gets module axial contradiction.
     *
     * @return the module axial contradiction
     */
    public String getModuleAxialContradiction() {
        return moduleAxialContradiction;
    }

    /**
     * Sets module axial contradiction.
     *
     * @param moduleAxialContradiction the module axial contradiction
     */
    public void setModuleAxialContradiction(String moduleAxialContradiction) {
        this.moduleAxialContradiction = moduleAxialContradiction;
    }

    /**
     * Is module axial fixed boolean.
     *
     * @return the boolean
     */
    public boolean isModuleAxialFixed() {
        return moduleAxialFixed;
    }

    /**
     * Sets module axial fixed.
     *
     * @param moduleAxialFixed the module axial fixed
     */
    public void setModuleAxialFixed(boolean moduleAxialFixed) {
        this.moduleAxialFixed = moduleAxialFixed;
    }

    /**
     * Gets module basic input value.
     *
     * @return the module basic input value
     */
    public String getModuleBasicInputValue() {
        return moduleBasicInputValue;
    }

    /**
     * Sets module basic input value.
     *
     * @param moduleBasicInputValue the module basic input value
     */
    public void setModuleBasicInputValue(String moduleBasicInputValue) {
        this.moduleBasicInputValue = moduleBasicInputValue;
    }

    /**
     * Gets module basic result value.
     *
     * @return the module basic result value
     */
    public String getModuleBasicResultValue() {
        return moduleBasicResultValue;
    }

    /**
     * Sets module basic result value.
     *
     * @param moduleBasicResultValue the module basic result value
     */
    public void setModuleBasicResultValue(String moduleBasicResultValue) {
        this.moduleBasicResultValue = moduleBasicResultValue;
    }

    /**
     * Gets module basic contradiction.
     *
     * @return the module basic contradiction
     */
    public String getModuleBasicContradiction() {
        return moduleBasicContradiction;
    }

    /**
     * Sets module basic contradiction.
     *
     * @param moduleBasicContradiction the module basic contradiction
     */
    public void setModuleBasicContradiction(String moduleBasicContradiction) {
        this.moduleBasicContradiction = moduleBasicContradiction;
    }

    /**
     * Is module basic fixed boolean.
     *
     * @return the boolean
     */
    public boolean isModuleBasicFixed() {
        return moduleBasicFixed;
    }

    /**
     * Sets module basic fixed.
     *
     * @param moduleBasicFixed the module basic fixed
     */
    public void setModuleBasicFixed(boolean moduleBasicFixed) {
        this.moduleBasicFixed = moduleBasicFixed;
    }

    /**
     * Gets module normal input value.
     *
     * @return the module normal input value
     */
    public String getModuleNormalInputValue() {
        return moduleNormalInputValue;
    }

    /**
     * Sets module normal input value.
     *
     * @param moduleNormalInputValue the module normal input value
     */
    public void setModuleNormalInputValue(String moduleNormalInputValue) {
        this.moduleNormalInputValue = moduleNormalInputValue;
    }

    /**
     * Gets module normal result value.
     *
     * @return the module normal result value
     */
    public String getModuleNormalResultValue() {
        return moduleNormalResultValue;
    }

    /**
     * Sets module normal result value.
     *
     * @param moduleNormalResultValue the module normal result value
     */
    public void setModuleNormalResultValue(String moduleNormalResultValue) {
        this.moduleNormalResultValue = moduleNormalResultValue;
    }

    /**
     * Gets module normal contradiction.
     *
     * @return the module normal contradiction
     */
    public String getModuleNormalContradiction() {
        return moduleNormalContradiction;
    }

    /**
     * Sets module normal contradiction.
     *
     * @param moduleNormalContradiction the module normal contradiction
     */
    public void setModuleNormalContradiction(String moduleNormalContradiction) {
        this.moduleNormalContradiction = moduleNormalContradiction;
    }

    /**
     * Is module normal fixed boolean.
     *
     * @return the boolean
     */
    public boolean isModuleNormalFixed() {
        return moduleNormalFixed;
    }

    /**
     * Sets module normal fixed.
     *
     * @param moduleNormalFixed the module normal fixed
     */
    public void setModuleNormalFixed(boolean moduleNormalFixed) {
        this.moduleNormalFixed = moduleNormalFixed;
    }

    /**
     * Gets module transverse input value.
     *
     * @return the module transverse input value
     */
    public String getModuleTransverseInputValue() {
        return moduleTransverseInputValue;
    }

    /**
     * Sets module transverse input value.
     *
     * @param moduleTransverseInputValue the module transverse input value
     */
    public void setModuleTransverseInputValue(String moduleTransverseInputValue) {
        this.moduleTransverseInputValue = moduleTransverseInputValue;
    }

    /**
     * Gets module transverse result value.
     *
     * @return the module transverse result value
     */
    public String getModuleTransverseResultValue() {
        return moduleTransverseResultValue;
    }

    /**
     * Sets module transverse result value.
     *
     * @param moduleTransverseResultValue the module transverse result value
     */
    public void setModuleTransverseResultValue(String moduleTransverseResultValue) {
        this.moduleTransverseResultValue = moduleTransverseResultValue;
    }

    /**
     * Gets module transverse contradiction.
     *
     * @return the module transverse contradiction
     */
    public String getModuleTransverseContradiction() {
        return moduleTransverseContradiction;
    }

    /**
     * Sets module transverse contradiction.
     *
     * @param moduleTransverseContradiction the module transverse contradiction
     */
    public void setModuleTransverseContradiction(String moduleTransverseContradiction) {
        this.moduleTransverseContradiction = moduleTransverseContradiction;
    }

    /**
     * Is module transverse fixed boolean.
     *
     * @return the boolean
     */
    public boolean isModuleTransverseFixed() {
        return moduleTransverseFixed;
    }

    /**
     * Sets module transverse fixed.
     *
     * @param moduleTransverseFixed the module transverse fixed
     */
    public void setModuleTransverseFixed(boolean moduleTransverseFixed) {
        this.moduleTransverseFixed = moduleTransverseFixed;
    }

    /**
     * Gets teeth number input value.
     *
     * @return the teeth number input value
     */
    public String getTeethNumberInputValue() {
        return teethNumberInputValue;
    }

    /**
     * Sets teeth number input value.
     *
     * @param teethNumberInputValue the teeth number input value
     */
    public void setTeethNumberInputValue(String teethNumberInputValue) {
        this.teethNumberInputValue = teethNumberInputValue;
    }

    /**
     * Gets teeth number result value.
     *
     * @return the teeth number result value
     */
    public String getTeethNumberResultValue() {
        return teethNumberResultValue;
    }

    /**
     * Sets teeth number result value.
     *
     * @param teethNumberResultValue the teeth number result value
     */
    public void setTeethNumberResultValue(String teethNumberResultValue) {
        this.teethNumberResultValue = teethNumberResultValue;
    }

    /**
     * Gets teeth number contradiction.
     *
     * @return the teeth number contradiction
     */
    public String getTeethNumberContradiction() {
        return teethNumberContradiction;
    }

    /**
     * Sets teeth number contradiction.
     *
     * @param teethNumberContradiction the teeth number contradiction
     */
    public void setTeethNumberContradiction(String teethNumberContradiction) {
        this.teethNumberContradiction = teethNumberContradiction;
    }

    /**
     * Is teeth number fixed boolean.
     *
     * @return the boolean
     */
    public boolean isTeethNumberFixed() {
        return teethNumberFixed;
    }

    /**
     * Sets teeth number fixed.
     *
     * @param teethNumberFixed the teeth number fixed
     */
    public void setTeethNumberFixed(boolean teethNumberFixed) {
        this.teethNumberFixed = teethNumberFixed;
    }
}