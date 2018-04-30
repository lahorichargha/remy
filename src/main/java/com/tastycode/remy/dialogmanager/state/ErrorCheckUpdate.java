package com.tastycode.remy.dialogmanager.state;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class ErrorCheckUpdate
{
    enum ErrorType 
    {
        DELETION, INSERT, SUBSTITUTION
    }

    public void explainError()
    {
     checkForErrors();
     checkForGroundingErrors();
    }

    public void checkForErrors()
    {
     articulationPatterns();
     isNoise();
    }

    private void articulationPatterns()
    {
        // TODO Auto-generated method stub
        
    }

    private void isNoise()
    {
        // TODO Auto-generated method stub
        
    }

    public void checkForGroundingErrors()
    {


    }

    public void checkFromSameTask(Object task)
    {
//     if can't do that
//     response=ask for clarification, clarification++
    }

    public void detectErrorSpiral()
    {
        isHyperArticulate();
        isMoreVeboseThanLastTime();
    }

    private void isMoreVeboseThanLastTime()
    {
        //1 said same thing
        // but said it with more words
    }

    private void isHyperArticulate()
    {
        //slower, higher pitched?
        
    }
}
