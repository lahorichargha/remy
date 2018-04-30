package com.tastycode.remy.commonobjects.dialog.systemutterance;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class SayMultipleThings extends AbstractSystemUtterance
{
    List<SystemUtterance> utterancesToSay;
    
    public SayMultipleThings(SystemUtterance... utterances)
    {
        utterancesToSay = Arrays.asList(utterances);
    }
    
    @Override
    public String getWhatToSay()
    {
        StringBuffer sb = new StringBuffer();
        for (SystemUtterance utter : utterancesToSay)
        {
            sb.append(utter.getWhatToSay());
            sb.append(". ");
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("multiple utterances: ");
        for (SystemUtterance utter : utterancesToSay)
        {
            sb.append(utter.toString());
        }
        return sb.toString();
    }

}
