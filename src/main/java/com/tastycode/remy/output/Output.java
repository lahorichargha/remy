/**
 * 
 */
package com.tastycode.remy.output;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;

/**
 * Responsible for presenting output to user.
 * 
 * @author broberts
 */
public interface Output {
	public void say(WhatToSay utterance);
}
