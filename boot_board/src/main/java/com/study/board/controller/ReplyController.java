package com.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.study.board.dto.*;
import com.study.board.service.ReplyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	/* ------------------------- SELECT : /replies/pages/{bno}/{page} + GET + body(JSON) ------------------------- */
	@GetMapping(path="/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> show(@PathVariable("bno")int bno, @PathVariable("page")int page) {
		log.info("댓글 전체 목록 요청");
		Criteria criteria = new Criteria(page, 10);
		return new ResponseEntity<ReplyPageDTO>(service.getList(criteria, bno), HttpStatus.OK);
	}
	
	/* ------------------------- SELECT : /replies/{rno} + GET + body(JSON) ------------------------- */
	@GetMapping(path="/{rno}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyDTO> get(@PathVariable("rno")int rno) {
		log.info("댓글 조회 요청");
		return new ResponseEntity<ReplyDTO>(service.readReply(rno), HttpStatus.OK);
	}
	
	/* ------------------------- INSERT : /replies/new + POST + body(JSON) ------------------------- */
	// consumes : 받아서 처리할 content 타입
	@PreAuthorize("isAuthenticated()")
	@PostMapping(path="/new", consumes="application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyDTO insertDTO) {
		log.info("댓글 입력 요청");
		return service.insertReply(insertDTO) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/* ------------------------- UPDATE : /replies/{rno} + PUT(PATCH) + body(JSON) ------------------------- */
	// @RequestMapping(path="/{rno}", method=RequestMethod.PUT)
	@PreAuthorize("principal.username == #updateDTO.replyer")
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@PathVariable("rno")int rno, @RequestBody ReplyDTO updateDTO) {
		log.info("댓글 수정 요청");
		updateDTO.setRno(rno);
		return service.updateReply(updateDTO) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	/* ------------------------- DELETE : /replies/{rno} + DELETE + body(JSON) ------------------------- */
	@PreAuthorize("principal.username == #dto.replyer")
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno")int rno, @RequestBody ReplyDTO dto) {
		log.info("댓글 삭제 요청");
		return service.deleteReply(rno) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
