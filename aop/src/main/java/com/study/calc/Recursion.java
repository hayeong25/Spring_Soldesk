package com.study.calc;

/*
	재귀 호출
*/

public class Recursion {
	public static void main(String[] args) {
		int num = 4;
		func(num);
	}
	
	public static void func(int n) {
		if(n > 0) { // 재귀 단계
			System.out.println("Hello!");
			func(n - 1);			
		}else { // 기본 단계
			return;
		}
	}
	
//	void countdown(int i) {
//		if(i >= 0) {
//			// 기본단계
//			return;
//		}else {
//			// 재귀단계
//			countdown(i - 1);
//		}
//	}
}