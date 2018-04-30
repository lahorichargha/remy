package com.tastycode.remy.commonobjects.domainmodel;

import org.tartarus.snowball.ext.EnglishStemmer;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class Stemmer
{
    private static EnglishStemmer stemmer;
    
    static {
        stemmer = new EnglishStemmer();
    }

    /**
     * run the stemmer from Lucene
     * @param word
     * @return
     */
    public static String stem(String word)
    {
        stemmer.setCurrent(word);
        boolean result = stemmer.stem();
        if (!result) 
        {
            return word;
        }
        return stemmer.getCurrent(); 
    }

}
