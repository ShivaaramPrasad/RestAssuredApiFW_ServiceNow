package com.techurate.api.utilites;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {
	
	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
	IRetryAnalyzer retry = testAnnotation.getRetryAnalyzer();
	
	if(retry==null) {
		testAnnotation.setRetryAnalyzer(FailRetry.class); //pass the class name created in step -1 
	}

}
}