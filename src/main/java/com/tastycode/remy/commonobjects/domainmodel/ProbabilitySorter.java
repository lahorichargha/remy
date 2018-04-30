package com.tastycode.remy.commonobjects.domainmodel;

import java.util.Comparator;

/**
 * sorts the higher values higher
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class ProbabilitySorter 
    implements Comparator<ThingWithProbability<?>>
{
    public int compare(ThingWithProbability<?> prob0,
        ThingWithProbability<?> prob1)
    {
        if (prob0.getProbability() > prob1.getProbability())
        {
            return -1;
        }
        else if (prob0.getProbability() < prob1.getProbability())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
