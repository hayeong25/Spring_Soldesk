package lambda;

// 함수형 인터페이스 : 단 하나의 추상 메소드만 정의되어야 함
// 둘 이상의 추상 메소드가 있으면 그냥 인터페이스 => 그냥 인터페이스도 추상 메소드가 하나만 있다면 함수형 인터페이스 사용 가능
// @FunctionalInterface : 안전하게 함수형 인터페이스를 구현할 수 있도록 도와줌

@FunctionalInterface
public interface Lambda2 {
	// public void method(int x); // 람다식으로 구현할 메소드 선언
	public int max(int num1, int num2);
}