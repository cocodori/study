package com.coco.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
/*
 * @Aspect는 Aspect를 구현한 클래스라는 것을 명시한다.
 * @Component는 스프링 bean으로 인식하기 위해 선언한다.
 * 
 * */
public class LogAdvice {
	/*
	 * @Before는 BeforeAdvice를 구현한 메서드에 추가한다.
	 * 속성은 AspectJ의 표현식이다.
	 * 'execution의 경우, 접근제어자와 특정 클래스의 메서드를 지정할 수 있다.
	 * 맨 첫 문자 '*'는 접근 제어자를 의미한다. 맨뒤에 '*'는 클래스 이름과 메서드 이름을 의미한다.
	 * 어떤 위치에 Advice를 적용할 것인지를 결정하는 Pointcut이라고 할 수 있다.
	 * 설정시에 args를 이용하면 간단히 파라미터를 구할 수 있다.
	 * 
	 * */
	@Before("execution(* com.coco.service.SampleService*"
							+ ".doAdd(String,String)) && args(str1, str2)")
	public void logBefore(String str1, String str2) {
		log.info("--------@Before--------");
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	/*
	 * @AfterThrowing
	 * 
	 * */
	@AfterThrowing(pointcut = "execution(* com.coco.service.SampleService*.*(..))"
			,throwing = "exception")
	public void logException(Exception exception) {
		log.info("----------@AfterThrowing----------");
		log.info(exception);
	}
	
	/*
	 * @Around는 직접 대상 메서드를 실행할 수 있는 권한이 있다.
	 * 메서드 실행 전, 후에 처리가 가능하다.
	 * ProceedingJoinPoint와 결합해서 파라미터, 예외 등을 처리할 수 있다.
	 * 
	 * ProceedingJoinPoint는 AOP의 대상인 Target이나 파라미터를 파악하고
	 * 직접 실행을 결정할 수 있다. @Before등과 달리 @Around를 적용한 메서드는 리턴 타입이 void가 아닌 타입어야 한다.
	 * 또한, 메서드의 실행 결과 역시 직접 반환하는 형태로 작성해야 한다.
	 * 
	 * */
	@Around("execution(* com.coco.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint proceedingJoinPoint) {
		log.info("----------@Around & ProceedingJoinPoint----------");
		long start = System.currentTimeMillis();
		
		log.info("Target : " + proceedingJoinPoint.getTarget());
		log.info("Param : " + Arrays.toString(proceedingJoinPoint.getArgs()));
		
		//invoke method
		Object result = null;
		
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			log.info(e.getMessage());
		}
		
		long end = System.currentTimeMillis();
		
		log.info(" TIME : " + (end - start));
		
		
		return result;
	}

}
