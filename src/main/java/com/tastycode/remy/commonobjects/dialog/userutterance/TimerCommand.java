package com.tastycode.remy.commonobjects.dialog.userutterance;

import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;


public class TimerCommand extends AbstractUserUtterance {
	public TimerCommand(WhatWasSaid text) {
		super(text);
	}

	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
