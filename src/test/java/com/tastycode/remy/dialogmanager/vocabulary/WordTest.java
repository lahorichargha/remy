package com.tastycode.remy.dialogmanager.vocabulary;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class WordTest
{
    @Test
    public void testWord()
    {
        Word word = new Word("Happy");
        assertThat(word.getWord(), containsString("Happy"));
    }
    
    @Ignore("currently doesn't work cuz I didn't implement the stemmer")
    @Test
    public void testStemWord()
    {
        Word word = new Word("carrots");
        assertThat(word.getWord(), not(containsString("carrots")));
    }

}
