package com.tastycode.remy.dialogmanager.vocabulary;

import com.tastycode.remy.commonobjects.domainmodel.Stemmer;

/**
 * run through all the steps to preprocess any text
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TextPreprocessor
{
    private final static String DELIMITER = " ";

    /**
     * to lowercase
     * remove stop words
     * stem all words
     */
    public static String preprocess(String inputString)
    {
        inputString = inputString.toLowerCase();
        inputString = StopWordRemoval.processString(inputString);

        String [] splits = inputString.split(DELIMITER);
        String finalString = "";
        for (String string : splits)
        {
            finalString = 
                finalString + Stemmer.stem(string) + DELIMITER;
        }
        
        return finalString.trim();
    }

}
