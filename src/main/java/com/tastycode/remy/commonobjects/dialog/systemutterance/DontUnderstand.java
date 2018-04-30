package com.tastycode.remy.commonobjects.dialog.systemutterance;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class DontUnderstand implements SystemUtterance
{
    public static DontUnderstand DONT_UNDERSTAND = new DontUnderstand();
    
    //singleton class
    private DontUnderstand()
    {
        
    }
    
    public String getWhatToSay()
    {
        return "I don't understand";
    }   
    
    @Override
    public String toString() {
        return getClass().getSimpleName() 
            + "[text='" + getWhatToSay() +"']";
    }

}
