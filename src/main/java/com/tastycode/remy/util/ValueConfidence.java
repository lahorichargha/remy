package com.tastycode.remy.util;

/**
 *
 * represents a value and then a confidence in having that value
 */
public class ValueConfidence
{
    private double value;
    private double confidence;

    public ValueConfidence(double value, double confidence )
    {
        this.value = value;
        this.confidence = confidence;
    }
    public double getValue()
    {
        return value;
    }
    public double getConfidence()
    {
        return confidence;
    }
    /**
     * update the evidence using a cool evidence updating formula
     * @param evidence
     */
    public void addEvidence(double evidence)
    {
        
    }
    

}
