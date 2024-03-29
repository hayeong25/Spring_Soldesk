package com.study.calc;

import org.springframework.stereotype.Component;

// 팩토리얼을 for문으로 구현

@Component("forc")
public class ForCalc  implements Calc{
	@Override
	public long factorial(long num) {
		long result = 1;
		
		for(int i = 1; i <= num; i++) {
			result *= i;
		}
		
		return result;
	}
}