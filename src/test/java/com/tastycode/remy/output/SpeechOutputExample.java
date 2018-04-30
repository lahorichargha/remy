package com.tastycode.remy.output;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tastycode.remy.dialogmanager.WhatToSayLiterally;

/**
 * Example of using the speech output.
 * Not a testcase, so that computer doesn't speak during each
 * test run.
 * 
 * @author broberts
 */
public class SpeechOutputExample {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpeechOutputExample-context.xml", SpeechOutputExample.class);
		Output output = (Output) context.getBean("output");
		output.say(new WhatToSayLiterally("Oh what a goose I am."));
		output.say(new WhatToSayLiterally("oh-wa tagooe siam"));
        output.say(new WhatToSayLiterally("please pass the salt"));
        output.say(new WhatToSayLiterally("Set the oven to 450 degrees"));
        context.close();
	}
}
