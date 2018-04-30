package com.tastycode.remy.gui;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tastycode.remy.Remy;
import com.tastycode.remy.dialogmanager.RemyDialog;
import com.tastycode.remy.dialogmanager.SpeakerAndListener;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class TestRemyModel
{
    private static SpeakerAndListener speaker;
    private static RemyDialog dialog;
    
    static
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
            "/context/Remy-context.xml"
        }, 
        Remy.class);

        speaker = (SpeakerAndListener) context.getBean("speaker");
        dialog = (RemyDialog) context.getBean("dialog");
    }

    @Test
    public void testSay()
    {
        RemyModel model = new RemyModel(dialog);
        
        
        String step1 = model.getCurrentStepDescription();
        assertThat("step not empty", step1.length(), greaterThan(0));
        
        model.say("What's next");
        
        String step2 = model.getCurrentStepDescription();
        assertThat(step2, notNullValue());
        
        assertThat("not the same step", step1, not(equalTo(step2)));
    }

    @Test
    public void testOther()
    {
        RemyModel model = new RemyModel(dialog);
        
        assertThat("get some description", 
            model.getTimersDescription().length(), greaterThan(0));
        
        assertThat("get some description", 
            model.getStepDescription().length(), greaterThan(0));
    }

}
