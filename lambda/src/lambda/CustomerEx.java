package lambda;

import java.util.ArrayList;
import java.util.List;

public class CustomerEx {
	public static void main(String[] args) {
		// 고객 명단 출력해 총 여행 경비 계산 후 20세 이상인 고객의 이름 정렬 및 출력
		
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("꼬물이", 18, 100));
		customerList.add(new Customer("곰탱이", 28, 200));
		customerList.add(new Customer("찬료리", 8, 50));
		customerList.add(new Customer("아죠씨", 40, 300));
		
		customerList.stream().map(c -> c.getName()).forEach(c -> System.out.print(c + "\t"));
		
		System.out.println();
		
		long sum = customerList.stream().mapToInt(c -> c.getPrice()).sum();
		System.out.println("총 여행 경비 : " + sum);
		
		customerList.stream().filter(c -> c.getAge() >= 20).map(c -> c.getName()).sorted().forEach(c -> System.out.print(c + "\t"));
	}
}
