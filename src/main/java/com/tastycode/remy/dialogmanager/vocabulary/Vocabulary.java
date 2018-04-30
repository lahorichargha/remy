/** Copyright(c) 2008 BAE SYSTEMS - AIT, Inc. Burlington, MA 01803 ALL RIGHTS RESERVED. */
package com.tastycode.remy.dialogmanager.vocabulary;

import java.util.List;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class Vocabulary
{

    public void extractVocab(List<Object> initialWords)
    {
//     corpus.getSyn(initialWords);
//     corpus.getCoOccur(initialWords);
//    //recipe vocab words hopefully words we don't know about: IE ALL ingredients, All measures, All types of measures.
//
//     weightPriors() //add prob of something happening regardless of how much the recognition happens.. it's like useful to break ties.
    }
    
    public void setLikelyWords()
    {
        
    }

    
    public void expandVocabulary(UserUtterance utterance)
    {
        Word w = null;
        if (w.isAbstract())
        {
            addInSpecificCategoriesOf(w);
        }
    }

    private void addInSpecificCategoriesOf(Word w)
    {
        // TODO Auto-generated method stub
        
    }


}
