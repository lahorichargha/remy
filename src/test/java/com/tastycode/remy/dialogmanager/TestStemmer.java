package com.tastycode.remy.dialogmanager;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.tastycode.remy.commonobjects.domainmodel.Stemmer;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestStemmer
{
    @Test
    public void runStemmer()
    {
        assertThat(Stemmer.stem("baking"), equalTo("bake"));
        assertThat(Stemmer.stem("carrots"), equalTo("carrot"));
        assertThat(Stemmer.stem("cutting"), equalTo("cut"));
        assertThat(Stemmer.stem("cups"), equalTo("cup"));
    }

    @Test
    public void runStemmerDoesntChange()
    {
        assertThat(Stemmer.stem("water"), equalTo("water"));
        assertThat(Stemmer.stem("cut"), equalTo("cut"));
        assertThat(Stemmer.stem("baker"), equalTo("baker"));
        assertThat(Stemmer.stem("broke"), equalTo("broke"));
    }

}
