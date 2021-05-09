package com.study.homework;

import java.lang.reflect.Method;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeworkApplication {
	private final static String CLASS_NAME = "Hello";
	
    public static void main( String[] args ) {
    	XclassLoader xclassLoader = new XclassLoader();
    	try {
			Class<?> helloClass = xclassLoader.findClass(CLASS_NAME);
			Object helloInstance = helloClass.newInstance();
			Method method = helloClass.getMethod("hello");
			method.invoke(helloInstance);
		} catch (Exception e) {
			System.out.println("Can't load or initial class: " + CLASS_NAME);
		}
    }

}
