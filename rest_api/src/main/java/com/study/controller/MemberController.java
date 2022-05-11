package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.study.dto.*;
import com.study.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // Controller가 보내는 모든 값은 데이터
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	/* -------------------- /member/new + POST + body(JSON 데이터) -------------------- */
	@PostMapping("/new")
	public ResponseEntity<String> insert(@RequestBody MemberDTO registerDTO) {
		log.info("회원가입 요청");
		return service.register(registerDTO) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	/* -------------------- /member/id + PUT + 수정 데이터(JSON) -------------------- */
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable("id")String userid, @RequestBody ChangeDTO changeDTO) {
		log.info("회원가입 요청");
		return service.changePassword(changeDTO) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}