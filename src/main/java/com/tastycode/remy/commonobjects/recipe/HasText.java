package com.tastycode.remy.commonobjects.recipe;

import java.util.List;

import com.tastycode.remy.dialogmanager.vocabulary.Word;

/**
 *
 * has some text
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public interface HasText
{
    public String getText();

    public String getProcessedText();
    
    public int numMatch(HasText otherText);
    
    public int numMatchingWords(HasText otherText);

    public int numMatchingCookingWords(HasText otherText);

    /**
     * get all words, however, do not include stop 
     * words like "the" or "a"
     */
    public List<Word> getWords();

    /**
     * only includes ingredients, or tools, or cooking verbs
     */
    public List<Word> getCookingWords();

    /**
     * return the dialog words like, "what" or "after"
     */
    public List<Word> getDialogWords();
}
