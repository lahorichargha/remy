package com.tastycode.remy.commonobjects.dialog;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestUserUtterance
{
    @Test
    public void testTextPreprocess()
    {
        String input = "What do I do with the carrots?";
        String output = "what carrot";

        LiteralUserUtterance utterance = new 
            LiteralUserUtterance(input);
        
        assertThat(utterance.getProcessedText(), equalTo(output));
    }

}
