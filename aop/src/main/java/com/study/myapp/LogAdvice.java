package com.study.myapp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect // Advice + pointcut = Aspect
@Component("log")
public class LogAdvice {
	
	// Before : 메소드 실행 전
	@Before(value="execution(* com.study.myapp.Product.getInfo()")
	public void beforeLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");
	}
	
	// After : 예외가 있든 없든 메소드 실행 후
	@After(value="execution(* com.study.myapp.Product.getInfo()")
	public void afterLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출");
	}
	
	// AfterReturning : 메소드 실행 후 호출이 되지만 예외 발생 시 출력 X
	@AfterReturning(value="execution(* com.study.myapp.Product.getInfo()")
	public void afterReturnLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 후 Exception 없이 실행 후 호출");
	}
	
	// After Throwing : 메소드 실행 시 Exception 발생한 경우 예외 발생 후 호출
	@AfterThrowing(value="execution(* com.study.myapp.Product.getInfo()")
	public void afterThrowLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 시 Exception 발생 후 호출");
	}
	
	// Around : 메소드 실행 전 후 모두
	@Around(value="execution(* com.study.myapp.Product.getInfo()")
	public void aroundLog(ProceedingJoinPoint pjp) {
		System.out.println("[공통로그] 비즈니스 로직 수행 전");
		
		// 비즈니스 메소드 호출
		try {
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("[공통로그] 비즈니스 로직 수행 후");
	}
}