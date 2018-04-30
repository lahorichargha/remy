package com.tastycode.remy.output;

import java.io.PrintStream;

import com.tastycode.remy.commonobjects.dialog.WhatToSay;

public class TextOutput implements Output {
	private PrintStream out;
	
	public TextOutput() {
		this.out = System.out;
	}
	
	public void say(WhatToSay utterance) {
		out.println(utterance.getText());
	}

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}
}
