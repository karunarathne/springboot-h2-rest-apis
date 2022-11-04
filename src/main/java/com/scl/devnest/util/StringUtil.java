package com.scl.devnest.util;

public class StringUtil {

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.isEmpty();
	}
	
	public static String startsWithText(String text) {
		return  text + "%";
	}
	
	public static String anywhereWithText(String text) {
		return "%" + text + "%";
	}
}
