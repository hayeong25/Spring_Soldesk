package com.study.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.study.dto.SampleDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // 데이터 그 자체를 return
public class BasicController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
	
	@GetMapping(path="/send.xml", produces=MediaType.APPLICATION_XML_VALUE)
	public SampleDTO sendDTO() {
		SampleDTO dto = new SampleDTO();
		dto.setName("kim");
		dto.setBno("123");
		dto.setAddress("서울");
		
		return dto;
	}
	
	@GetMapping(path="/send.json", produces=MediaType.APPLICATION_JSON_VALUE)
	public SampleDTO sendDTOjson() {
		SampleDTO dto = new SampleDTO();
		dto.setName("kim");
		dto.setBno("123");
		dto.setAddress("서울");
		
		return dto;
	}
	
	@GetMapping(path="/send_list", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SampleDTO> sendList() {
		List<SampleDTO> list = new ArrayList<SampleDTO>();
		for(int i = 0; i < 10; i++) {
			SampleDTO dto = new SampleDTO();
			dto.setName("kim" + i);
			dto.setBno("123" + i);
			dto.setAddress("서울" + i);
			list.add(dto);
		}
		return list;
	}
	
	@GetMapping(path="/send_map", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, SampleDTO> sendMap() {
		SampleDTO dto = new SampleDTO();
		dto.setName("kim");
		dto.setBno("123");
		dto.setAddress("서울");
		
		Map<String, SampleDTO> map = new HashMap<String, SampleDTO>();
		map.put("first", dto);
		
		return map;
	}
	
	// ResponseEntity 타입 : 상태 코드 + 데이터를 함께 보낼 수 있는 타입
	// 상태코드 : 404, 500, 406, 200(OK), ...
	@GetMapping(path="/check")
	public ResponseEntity<SampleDTO> check(Double height, Double weight) {
		SampleDTO dto = new SampleDTO("123", height+"", weight+"");
		ResponseEntity<SampleDTO> result = null;
		
		if(height < 150) {
			// result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			result = new ResponseEntity<>(dto, HttpStatus.BAD_GATEWAY);
		}else {
			// result = ResponseEntity.status(HttpStatus.OK).body(dto);
			result = new ResponseEntity<>(dto, HttpStatus.OK);
		}
		
		return result;
	}
	
	// @PathVariable : REST 방식은 URL이 가지고 있는 값을 정보로 사용하는 경우가 많기 때문에, URL parameter로 들어오는 값을 변수에 담을 수 있게 해줌
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat")String cat, @PathVariable("pid")String pid) {
		return new String[] {"category : " + cat, "productID : " + pid};
	}
	
	// @RequestBody : JSON 데이터를 서버로 가져올 때 원하는 타입의 객체로 변환
	@PostMapping("/test1")
	public void test1(@RequestBody SampleDTO dto) {
		log.info("JSON 데이터 가져오기 : " + dto);
	}
}
