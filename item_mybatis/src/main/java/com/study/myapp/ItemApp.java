package com.study.myapp;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.study.dto.ItemDTO;
import com.study.service.ItemService;

public class ItemApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		ItemService service = (ItemService)context.getBean("service");
		
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		
		while(flag) {
			System.out.println("=================================");
			System.out.println("1. 아이템 추가");
			System.out.println("2. 아이템 검색");
			System.out.println("3. 아이템 삭제");
			System.out.println("4. 아이템 수정");
			System.out.println("5. 아이템 목록");
			System.out.println("0. 프로그램 종료");
			System.out.println("=================================");
			
			System.out.print("메뉴를 선택하세요 : ");
			int no = Integer.parseInt(scanner.nextLine());
			
			switch (no) {
			case 1:
				ItemDTO insertDTO = new ItemDTO();
				System.out.print("아이템 카테고리 : ");
				insertDTO.setCategory(scanner.nextLine());
				System.out.print("아이템 이름 : ");
				insertDTO.setName(scanner.nextLine());
				System.out.print("아이템 내용 : ");
				insertDTO.setContent(scanner.nextLine());
				System.out.print("아이템 사이즈 : ");
				insertDTO.setPsize(scanner.nextLine());
				System.out.print("아이템 가격 : ");
				insertDTO.setPrice(Integer.parseInt(scanner.nextLine()));
				System.out.println("=================================");
				if(service.insert(insertDTO)) {
					System.out.println("등록되었습니다.");
				}
				break;
			case 2:
				System.out.print("조회할 아이템 번호를 입력하세요 : ");
				service.select(Integer.parseInt(scanner.nextLine()));
				System.out.println("=================================");
				break;
			case 3:
				System.out.print("삭제할 아이템 번호를 입력하세요 : ");
				if(service.delete(Integer.parseInt(scanner.nextLine()))) {
					System.out.println("삭제되었습니다.");					
				}
				System.out.println("=================================");
				break;
			case 4:
				System.out.print("수정할 아이템 번호를 입력하세요 : ");
				int num = Integer.parseInt(scanner.nextLine());
				System.out.print("수정할 아이템 사이즈를 입력하세요 : ");
				String psize = scanner.nextLine();
				System.out.print("수정할 아이템 가격을 입력하세요 : ");
				int price = Integer.parseInt(scanner.nextLine());
				System.out.println("=================================");
				if(service.update(num, psize, price)) {
					System.out.println("수정되었습니다.");					
				}
				
				break;
			case 5:
				System.out.println("=================================");
				System.out.println("num\tcategory\tname\t\tcontent\t\tpsize\tprice");
				List<ItemDTO> list = service.selectAll();
				for(ItemDTO dto : list) {
					System.out.printf("%d\t%s\t\t%s\t%s\t%s\t%d\n", dto.getNum(), dto.getCategory(), dto.getName(), dto.getContent(), dto.getPsize(), dto.getPrice());
				}
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				flag = false;
				break;
			}
		}
		
		scanner.close();
	}
}
