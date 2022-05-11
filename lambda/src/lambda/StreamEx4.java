package lambda;

import java.util.*;
import java.util.stream.Collectors;

public class StreamEx4 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("abc", "def", "apple", "melon", "text");
				
		// 대문자로 변경 후 다른 리스트에 담아 출력
		List<String> upperList = new ArrayList<String>();
		for(String s : list) {
			upperList.add(s.toUpperCase());
		}
		System.out.println(upperList);
		
		// stream 사용 시
//		Stream<String> stream = list.stream();
//		Stream<String> upper = stream.map(String::toUpperCase);
//		upper.forEach(s -> System.out.println(s));
		
		// collection 사용 시
		List<String> upperList2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(upperList2);
	}
}