package lambda;

import java.util.*;
import java.util.stream.IntStream;

public class StreamEx10 {
	public static void main(String[] args) {
		IntStream stream = Arrays.stream(new int[] {1, 2, 3, 4, 5});
		
		// 2의 배수 개수
//		long count = stream.filter(i -> i % 2 == 0).count();
//		System.out.println("2의 배수의 개수는 " + count + "개입니다.");
		
		// 2의 배수 합
//		long sum = stream.filter(i -> i % 2 == 0).sum();
//		System.out.println("2의 배수의 합은 " + sum + "입니다.");
		
		// 2의 배수 평균
//		OptionalDouble avg = stream.filter(i -> i % 2 == 0).average();
//		System.out.println("2의 배수의 평균은 " + avg + "입니다.");
		
		// 2의 배수 최대값
//		OptionalInt max = stream.filter(i -> i % 2 == 0).max();
//		System.out.println("2의 배수의 최대값은 " + max + "입니다.");
		
		// 2의 배수 최소값
//		OptionalInt min = stream.filter(i -> i % 2 == 0).min();
//		System.out.println("2의 배수의 최대값은 " + min + "입니다.");
		
		// 2의 배수 첫 번째 값
		OptionalInt first = stream.filter(i -> i % 2 == 0).findFirst();
		System.out.println("2의 배수의 최대값은 " + first + "입니다.");
	}
}
