/**
 * Types.java \version 2018. 7. 17.
 * Copyright 2018 NAVER Corp. All rights Reserved.
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.or.connect.util;

/**
 * @author Created by Ilsik Kim on 2018. 7. 17.
 */
public enum TodoTypes {
	TODO, DOING, DONE;
	
	public static TodoTypes next(String type) {
		TodoTypes enumType = TodoTypes.valueOf(type);
		
	     return enumType.ordinal() < TodoTypes.values().length - 1
	         ? TodoTypes.values()[enumType.ordinal() + 1]
	         : TodoTypes.values()[TodoTypes.values().length - 1];
	   }
}
