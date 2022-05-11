package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamEx5 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("바둑", "바나나", "포도", "딸기", "바질", "강아지");
		
		// '바'로 시작하는 단어만 출력
		for(String s : list) {
			if(s.startsWith("바")) {
				System.out.print(s + "\t");
			}
		}
		
		System.out.println();
		
		// stream 사용 시
		list.stream().filter(s -> s.startsWith("바")).forEach(s -> System.out.print(s + "\t"));
		
		System.out.println();
		
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("곰탱이", 70));
		stuList.add(new Student("꼬물이", 75));
		stuList.add(new Student("찬료리", 88));
		stuList.add(new Student("공릉이", 92));
		stuList.add(new Student("군산이", 93));
		
		// 곰으로 시작하는 학생 이름 출력
		// stuList.stream().filter(n -> n.getName().startsWith("곰")).forEach(n -> System.out.println(n));
		stuList.stream().map(n -> n.getName()).filter(n -> n.startsWith("곰")).forEach(n -> System.out.print(n + "\t"));
		
		System.out.println();
		
		stuList.stream().map(n -> n.getScore()).filter(n -> n > 80).forEach(n -> System.out.print(n + "\t"));		
	}
}