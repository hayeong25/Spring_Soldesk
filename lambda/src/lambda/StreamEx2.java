package lambda;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class StreamEx2 {
	public static void main(String[] args) {
		// 디렉토리 지정
		Path path = Paths.get("c:\\Users\\hayeo\\upload\\2022\\05\\09");
		
		// stream 생성
		try {
			Stream<Path> stream = Files.list(path); // stream 사용 시 중간 연산 가능
			stream.forEach(file -> System.out.println(file.getFileName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
