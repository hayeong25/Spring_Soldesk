package com.study.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.AttachDTO;
import com.study.dto.BoardDTO;
import com.study.dto.Criteria;
import com.study.dto.PageDTO;
import com.study.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	/* --------------------------------- list --------------------------------- */
	@GetMapping("/list") // ActionFactory 역할
	public void listGet(@ModelAttribute("criteria")Criteria criteria, Model model) {
		log.info("전체 목록 보여주기");
		List<BoardDTO> list = service.getList(criteria);
		
		// 전체 게시물 개수
		int total = service.getTotalCnt(criteria);
		
		model.addAttribute("pageDTO", new PageDTO(criteria, total));
		model.addAttribute("list", list);
	}
	
	/* --------------------------------- register --------------------------------- */
	@PreAuthorize("isAuthenticated()") // 로그인 정보가 있는지 확인
	@GetMapping("/register")
	public void registerGet() {
		log.info("register form 보여주기");
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String registerPost(BoardDTO registerDTO, @ModelAttribute("criteria")Criteria criteria, RedirectAttributes rttr) {
		log.info("register 요청" + registerDTO);
		service.register(registerDTO);
		
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
		rttr.addAttribute("type", criteria.getType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		rttr.addFlashAttribute("result", registerDTO.getBno());
		
		return "redirect:/board/list";
	}
	
	/* --------------------------------- read --------------------------------- */
	@GetMapping("/read")
	public void readGet(int bno, @ModelAttribute("criteria")Criteria criteria, Model model) {
		log.info("게시물 보여주기");
		BoardDTO dto = service.read(bno);
		model.addAttribute("dto", dto);
	}
	
	/* --------------------------------- 첨부파일 --------------------------------- */
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachDTO>> getAttachList(int bno) {
		log.info("첨부파일 가져오기");
		return new ResponseEntity<List<AttachDTO>>(service.attachList(bno), HttpStatus.OK);
	}
	
	private void deleteFiles(List<AttachDTO> attachList) {
		log.info("폴더 내 첨부파일 삭제");
		if(attachList == null || attachList.size() <= 0) {
			return;
		}
		for(AttachDTO attach : attachList) {
			// 파일이 존재하는 경로 생성
			Path path = Paths.get("c:\\Users\\hayeo\\upload\\", attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());
			try {
				// 일반 파일, 원본 이미지 삭제
				Files.deleteIfExists(path);
				
				// Files.probeContentType(경로) : 확장자를 통해서 mime 타입 판단			
				if(Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("c:\\Users\\hayeo\\upload\\", attach.getUploadPath() + "\\s_" + attach.getUuid() + "_" + attach.getFileName());
					// 썸네일 이미지 삭제
					Files.delete(thumb);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* --------------------------------- modify --------------------------------- */
	@GetMapping("/modify")
	public void modifyGet(int bno, @ModelAttribute("criteria")Criteria criteria, Model model) {
		log.info("modify form 보여주기");
		BoardDTO dto = service.read(bno); // jsp로 보내기
		model.addAttribute("dto", dto);
	}
	
	@PreAuthorize("principal.username == #modifyDTO.writer")
	@PostMapping("/modify")
	public String modifyPost(BoardDTO modifyDTO, @ModelAttribute("criteria")Criteria criteria, RedirectAttributes rttr) {
		log.info("modify 요청");
		if(service.modify(modifyDTO)) {
			rttr.addAttribute("bno", modifyDTO.getBno());
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			rttr.addAttribute("type", criteria.getType());
			rttr.addAttribute("keyword", criteria.getKeyword());
			return "redirect:/board/read";
		}
		return "redirect:/board/modify";
	}
	
	/* --------------------------------- remove --------------------------------- */
	@PreAuthorize("principal.username == #writer")
	@GetMapping("/remove")
	public String removePost(int bno, String writer, @ModelAttribute("criteria")Criteria criteria, RedirectAttributes rttr) {
		log.info("remove 요청");
		// bno에 해당하는 첨부파일 목록 가져오기
		List<AttachDTO> attachList = service.attachList(bno);
		
		// 첨부파일 삭제
		deleteFiles(attachList);
		
		// 게시글 삭제 + 첨부파일 삭제 + 댓글 삭제
		service.remove(bno);

		// URL에 같이 보내는 방식 - JSP로 가는 게 아니라 Controller로 다시 들어가서 주소줄에 딸려보내야 하기 때문
		rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
		rttr.addAttribute("type", criteria.getType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		
		// session을 이용하는 방식
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/board/list";
	}
}