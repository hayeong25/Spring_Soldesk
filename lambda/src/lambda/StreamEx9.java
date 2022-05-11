package lambda;

import java.util.*;
import java.util.stream.*;

/*
	Stream 최종 연산
	forEach()
	collect() : 요소를 그룹화하거나 분할한 결과를 컬렉션에 담아 변환하는 데 사용
	count()
	sum()
	average()
	max()
	min()
	findFirst()
*/

public class StreamEx9 {
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("곰탱이", 70));
		stuList.add(new Student("꼬물이", 75));
		stuList.add(new Student("찬료리", 88));
		stuList.add(new Student("공릉이", 92));
		stuList.add(new Student("군산이", 93));
		
		// 기존 방식
		List<Integer> stuNum = new ArrayList<Integer>();
		for(Student stu : stuList) {
			stuNum.add(stu.getScore());
		}
		
		// 학생들의 점수만 모아서 새로운 리스트로 생성
		Stream<Student> stream = stuList.stream();
		List<Integer> score = stream.map(n -> n.getScore()).collect(Collectors.toList());
	}
}