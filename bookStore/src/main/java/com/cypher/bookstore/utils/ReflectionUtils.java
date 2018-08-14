package com.cypher.bookstore.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Cypher-Z
 * @date 2018/8/11 - 20:56
 */
public class ReflectionUtils {
	public static Class getSuperGenericType(Class aClass){
//		得到带泛型的baseDao类 对象
		Type superClass = aClass.getGenericSuperclass();
//		判断此类对象是否为带泛型的类对象
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			Type[] types = parameterizedType.getActualTypeArguments();
			if (types != null && types.length > 0) {
				if (types[0] instanceof Class) {
					return  (Class) types[0];
				}
			}
		}
		return null;
	}
}
