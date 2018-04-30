package com.tastycode.remy.dialogmanager;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tastycode.remy.Remy;
import com.tastycode.remy.commonobjects.dialog.WhatToSay;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.dialogmanager.testcases.UserSaysAndExpecteResponse;
import com.tastycode.remy.dialogmanager.testcases.ingredientquery.IngredientQuestionOatCookieParameter;
import com.tastycode.remy.dialogmanager.testcases.recipes.OatCookiesParameter;

/**
 *
 *
 */
@RunWith(Theories.class)
public class TextInputTests
{
    /* log4j logger instance */
    private static final Logger logger = Logger.getLogger(TextInputTests.class
                    .getName());

    private static SpeakerAndListener speaker;
    private static RemyDialog dialog;
    
    static
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
            "/context/Remy-context.xml",
        	"/context/Oat-context.xml"
        }, 
        Remy.class);

        speaker = (SpeakerAndListener) context.getBean("speaker");
        dialog = (RemyDialog) context.getBean("dialog");
    }

    @Theory
    public void testRecipeQuestions(
        @IngredientQuestionOatCookieParameter UserSaysAndExpecteResponse requestAndResponse,
        @OatCookiesParameter Recipe recipe
        )
    {
        String userSays = requestAndResponse.getUserSays();
        String systemSaysContaining = requestAndResponse.getSystemShouldSaySomethingContaining();
        
        logger.debug("user says: " + userSays);
        logger.debug("expected answer containing: " + systemSaysContaining);

        WhatToSay next = dialog.getWhatToSayNext(userSays);

        logger.debug("user says: " + userSays);
        logger.debug("expected answer containing: " + systemSaysContaining);
        logger.debug("system said: " + next.toString());

        assertTrue(next.toString().contains(systemSaysContaining));
    }
    
    private static Matcher<WhatToSay> systemReplyToContains(WhatWasSaid wasSaid, String contained) {
    	return new SystemReplyTo(wasSaid, contained);
    }
    
    private static class SystemReplyTo extends TypeSafeMatcher<WhatToSay> {
    	private WhatWasSaid wasSaid;
		private String contained;

		public SystemReplyTo(WhatWasSaid wasSaid, String contained) {
    		this.wasSaid = wasSaid;
    		this.contained = contained;
    	}
    	
		@Override
		public boolean matchesSafely(WhatToSay item) {
			return item.getText().contains(contained);
		}

		public void describeTo(Description description) {
	        description.appendText("when the user says: ")
	        			.appendValue(wasSaid.getText())
	        			.appendText(" the system should reply with something containing: ")
	        			.appendValue(contained);
		}
    	
    }
}
