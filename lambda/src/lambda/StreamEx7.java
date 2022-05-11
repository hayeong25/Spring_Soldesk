package lambda;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx7 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("사과", "딸기", "배", "바나나", "수박", "참외", "바나나");
		list.stream().distinct().forEach(s -> System.out.print(s + "\t"));
		
		Stream<File> stream = Stream.of(new File("file1.txt"), new File("file2.txt"), new File("file3.txt"), new File("file2.txt"), new File("file2"));
		
		// 파일명을 모아서 확장자가 없는 파일명은 제외하고 확장자만 모아서 중복제거 후 출력
		stream.map(f -> f.getName()).filter(f -> f.indexOf(".") > -1).map(f -> f.substring(f.lastIndexOf(".")+1)).distinct().forEach(f -> System.out.print(f + "\t"));
	}
}
