package com.example.demopugspring.helper;

import java.util.List;

public class PugHelper {
	public String join(String delimiter, List<String> text) {
		return String.join(delimiter, text);
	}
}
