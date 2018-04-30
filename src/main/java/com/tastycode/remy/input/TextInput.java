package com.tastycode.remy.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.tastycode.remy.commonobjects.dialog.WhatWasSaid;
import com.tastycode.remy.commonobjects.dialog.WhatWasSaidLiterally;

public class TextInput implements Input {
	private BufferedReader in; 
	
	public WhatWasSaid parseUtterance(String utterance) {
		return new WhatWasSaidLiterally(utterance);
	}
	
	public WhatWasSaid recordUtterance() {
		String utterance = null;
		try {
			utterance = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parseUtterance(utterance);
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader reader) {
		this.in = reader;
	}
	
	public void setIn(InputStream inputStream) {
		this.setIn(new InputStreamReader(inputStream));
	}

	public void setIn(Reader reader) {
		setIn(new BufferedReader(reader));
	}

}
