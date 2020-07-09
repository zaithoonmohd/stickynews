//package com.stackroute.favouriteservice.aspect;
//import java.util.Date;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
///* Annotate this class with @Aspect and @Component */
//
//@Aspect
//@Component
//public class LoggingAspect {
//
//	/*
//	 * Write loggers for each of the methods of controller, any particular method
//	 * will have all the four aspectJ annotation
//	 * (@Before, @After, @AfterReturning, @AfterThrowing).
//	 */
//
//	@Before("execution(com.stackroute.favouriteservice.controller.*)")
//	public void logBefore() {
//		System.out.println("@Before:" + new Date());
//	}
//
//	@After("execution(com.stackroute.favouriteservice.controller.*)")
//	public void logAfter() {
//		System.out.println("@After:" + new Date());
//	}
//
//	@AfterReturning(pointcut = "execution(com.stackroute.favouriteservice.controller.*)", returning = "obj")
//	public void logAfterReturning(Object obj) {
//		System.out.println("@AfterReturning :" + obj.toString());
//	}
//
//	@AfterThrowing(pointcut = "execution(com.stackroute.favouriteservice.controller.*)", throwing = "exception")
//	public void logAfterThrowing(Exception exception) {
//		System.out.println("@AfterThrowing Exception:" + exception.getMessage());
//	}
//
//}
//
