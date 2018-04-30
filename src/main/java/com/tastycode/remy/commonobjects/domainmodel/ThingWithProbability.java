package com.tastycode.remy.commonobjects.domainmodel;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class ThingWithProbability<Thing>
{
    private Thing thing;
    private double probability;
    
    public ThingWithProbability(Thing thing, double probability)
    {
        this.thing = thing;
        this.probability = probability;
    }

    public Thing getThing()
    {
        return thing;
    }

    public double getProbability()
    {
        return probability;
    }
    
    public void addProbability(double probabilityToAdd)
    {
        //TODO: don't allow a probability of greater than one somehow
        //but currently this will allow it
        setProbability(getProbability() + probabilityToAdd);
    }
    
    private void setProbability(double setTo)
    {
        probability = setTo;
    }
    

}
