package br.com.softblue.bluefood.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {

	@SuppressWarnings("unchecked")
	public static <T> List<T> listOf(T... objs) {
		if (objs == null) {
			return Collections.emptyList();
		}
		
		// Forma Tradicional
//		List<T> list = new ArrayList<T>(objs.length);
//		for (T obj : objs) {
//			list.add(obj);
//		}	
//		return list;
		
		// Utilizando Stream API
		return Arrays.stream(objs).collect(Collectors.toList());
	}

}
