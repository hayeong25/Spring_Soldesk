package com.study.calc;

import org.springframework.stereotype.Component;

// 팩토리얼을 재귀호출로 구현. 재귀호출을 반드시 기본 단계와 재귀 단계로 나뉘어야 함

@Component("rec")
public class RecCalc  implements Calc{
	@Override
	public long factorial(long num) {
		if(num == 0) {
			return 1;
		}else {
			return num * factorial(num-1);
		}
	}
}