package com.thiagohenrique.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String list) {
//		String[] s = list.split(",");
//		return Arrays.asList(vet).stream().map(obj->  Integer.parseInt(obj)).collect(Collectors.toList());
		return Arrays.asList(list
				.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}
