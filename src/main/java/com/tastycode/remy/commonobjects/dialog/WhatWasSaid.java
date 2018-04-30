package com.tastycode.remy.commonobjects.dialog;

import com.tastycode.remy.commonobjects.recipe.HasText;

/**
 * Literal text or speech input by user. 
 * 
 * @author broberts
 */
public interface WhatWasSaid extends HasText {
	String getTags();
}
