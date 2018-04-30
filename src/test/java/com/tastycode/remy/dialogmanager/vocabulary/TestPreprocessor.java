package com.tastycode.remy.dialogmanager.vocabulary;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestPreprocessor
{
    @Test
    public void testSome()
    {
        assertThat(TextPreprocessor.preprocess("What do I do with the carrots?"),
            equalTo("what carrot"));
    }

}
