package lambda;

/*
	함수형 프로그래밍
	
	람다식 (8버전부터 추가)
	- 메소드를 하나의 식으로 표현
	- 메소드를 람다식으로 표현하면 메소드의 이름과 값이 없어짐 => 익명함수
*/

public class Lambda1 {
	int max(int a, int b) {
		return a > b ? a : b;
	}
	
//	(int a, int b) -> {return a > b ? a : b;}
//	(int a, int b) -> a > b ? a : b
//	(a, b) -> a > b ? a : b
	
	void print(String name, int i) {
		System.out.println(name + "=" + i);
	}
	
//	(String name, int i) -> {System.out.println(name + "=" + i);}
//	(String name, int i) -> System.out.println(name + "=" + i);
//	(name, i) -> System.out.println(name + "=" + i);
	
	int roll() {
		return (int)(Math.random() * 6);
	}
	
//	() -> {return (int)(Math.random() * 6);}
//	() -> (int)(Math.random() * 6);
	
	int square(int x) {
		return x * x;
	}
	
//	(int x) -> {return x * x;}
//	(x) -> x * x;
//	x -> x * x;
}