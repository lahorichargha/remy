package com.tastycode.remy.dialogmanager.vocabulary;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestStopWord
{
    @Test
    public void testStopWord()
    {
        String result = StopWordRemoval.processString("wash the carrot");
        assertThat(result, containsString("carrot"));
        assertThat(result, containsString("wash"));
        assertThat(result, not(containsString("the")));
    }

    @Test
    public void testStopWord2()
    {
        String result = StopWordRemoval.processString("I am a great big fish");
        assertThat(result, containsString("fish"));
        assertThat(result, not(containsString("am")));
        assertThat(result, not(containsString(" a ")));
    }

}
