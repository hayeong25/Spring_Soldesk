package lambda;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
	Stream 중간 연산
	1. map(), filter(), distinct(), skip(), limit(), ...
	
	map() : stream 요소에 저장된 값 중에서 원하는 필드만 추출하거나, 특정 형태로 변환 시 사용
	
	Stream 최종 연산
	1. forEach(), count(), sum(), reduce(), ...
*/

public class StreamEx3 {
	public static void main(String[] args) {
		// 기존 방식
		List<File> fileList = new ArrayList<File>();
		fileList.add(new File("file1.txt"));
		fileList.add(new File("file2.txt"));
		fileList.add(new File("file3.txt"));
		fileList.add(new File("file4.txt"));
		
		// 파일명 추출
		for(File f : fileList) {
			// 파일명만 수집
			System.out.println(f.getName());
		}
		
		// stream 생성
		Stream<File> stream = Stream.of(new File("file1.txt"), new File("file2.txt"), new File("file3.txt"), new File("file4.txt"));
		
		// 중간 연산				  // 최종 연산
		stream.map(File::getName).forEach(f -> System.out.println(f)); // File::getName => 클래스명::메소드명
	}
}
