package com.nsl.trainning.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CustomUtil {

	/**
	 * get a list of properties with null values of an object
	 * 
	 * @param source
	 * @return list of 'source' object's properties with null values
	 */
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**
	 * copy the non-null properties of 'src' to 'target'
	 * 
	 * @param src
	 * @param target
	 */
	public static void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

}
