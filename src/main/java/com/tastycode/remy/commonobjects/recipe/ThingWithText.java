package com.tastycode.remy.commonobjects.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tastycode.remy.dialogmanager.vocabulary.TextPreprocessor;
import com.tastycode.remy.dialogmanager.vocabulary.Word;


/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public abstract class ThingWithText implements HasText
{
    private final String DELIMITER = " ";
    private String text;
    
    /**
     * @param text
     */
    public ThingWithText(String text)
    {
        this.text = text; //TextPreprocessor.preprocess(text);
    }

    public String getText()
    {
        return text;
    }

    public String getProcessedText()
    {
        return TextPreprocessor.preprocess(text);
    }

    public List<Word> getWords()
    {
        List<Word> words = new ArrayList<Word>(); 
     
        String processedText = getProcessedText();
        
        String [] splits = processedText.split(DELIMITER);
        for (String string : splits)
        {
            words.add(new Word(string));
        }
        
        return words;
    }
    
    public List<Word> getCookingWords()
    {
        //TODO: implement this for real
        return getWords();
    }

    /**
     * compares stemmed versions of the words
     * @param otherText
     * @return
     */
    public int numMatchingCookingWords(HasText otherText)
    {
        return numMatching(this.getCookingWords(), otherText.getCookingWords());
    }

    
    public int numMatchingWords(HasText otherText)
    {
        return numMatching(this.getCookingWords(), otherText.getCookingWords());
    }

    public List<Word> getDialogWords()
    {
        return getWords();
    }
    
    private static int numMatching(List<Word> words1, List<Word> words2)
    {
        int numMatch = 0;
        for (Word word1 : words1)
        {
            for (Word word2 : words2)
            {
                if (word1.getStemmed().equals(word2.getStemmed()))
                {
                    numMatch++;
                }
            }
        }
        return numMatch;
    }

    public int numMatch(HasText otherText)
    {
        int numMatch = 0;
        String otherString = otherText.getProcessedText();
        for (String wordIn : otherString.split(" "))
        {
            if (getText().contains(wordIn))
            {
                numMatch++;
            }
        }
        return numMatch;
    }
    
    public String toString()
    {
        return getText();
    }
}
