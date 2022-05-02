package com.study.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@GetMapping("/register")
	public void registerGet() {
		log.info("register form 보여주기");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardDTO registerDTO, @ModelAttribute("criteria")Criteria criteria, RedirectAttributes rttr) {
		log.info("register 요청");
		if(service.register(registerDTO)) {
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			rttr.addAttribute("type", criteria.getType());
			rttr.addAttribute("keyword", criteria.getKeyword());
			rttr.addFlashAttribute("result", registerDTO.getBno());
			return "redirect:/board/list";
		}
		return "redirect:/board/register";
	}
	
	/* --------------------------------- read --------------------------------- */
	@GetMapping("/read")
	public void readGet(int bno, @ModelAttribute("criteria")Criteria criteria, Model model) {
		log.info("게시물 보여주기");
		BoardDTO dto = service.read(bno);
		model.addAttribute("dto", dto);
	}
	
	/* --------------------------------- modify --------------------------------- */
	@GetMapping("/modify")
	public void modifyGet(int bno, @ModelAttribute("criteria")Criteria criteria, Model model) {
		log.info("modify form 보여주기");
		BoardDTO dto = service.read(bno); // jsp로 보내기
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/modify")
	public String modifyPost(int bno, String title, String content, @ModelAttribute("criteria")Criteria criteria, RedirectAttributes rttr) {
		log.info("modify 요청");
		if(service.modify(bno, title, content)) {
			rttr.addAttribute("bno", bno);
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			rttr.addAttribute("type", criteria.getType());
			rttr.addAttribute("keyword", criteria.getKeyword());
			return "redirect:/board/read";
		}
		return "redirect:/board/modify";
	}
	
	/* --------------------------------- remove --------------------------------- */
	@GetMapping("/remove")
	public String removePost(int bno, @ModelAttribute("criteria")Criteria criteria, RedirectAttributes rttr) {
		log.info("remove 요청");
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