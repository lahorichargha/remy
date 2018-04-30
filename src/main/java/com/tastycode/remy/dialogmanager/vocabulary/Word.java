package com.tastycode.remy.dialogmanager.vocabulary;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class Word
{
    private String word; 

    public Word(String word)
    {
        this.word = word;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public String getStemmed()
    {
        return word;
    }
    
    public boolean isAbstract()
    {
        return true;
    }

}
