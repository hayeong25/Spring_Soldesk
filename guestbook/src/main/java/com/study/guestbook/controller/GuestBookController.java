package com.study.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.guestbook.dto.GuestbookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.service.GuestbookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestbookService service;
	
	/* --------------------------- list --------------------------- */
	@GetMapping({"/", "/list"})
	public String list(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("[GetMapping] ============= list 요청 =============");
		log.info("requestDTO : " + requestDTO);
		
		// 목록 리스트 생성 - PageResultDTO<GuestbookDTO, Guestbook>
		model.addAttribute("result", service.getList(requestDTO));
		
		return "/guestbook/list";
	}
	
	/* --------------------------- register --------------------------- */
	@GetMapping("/register")
	public void registerGet() {
		log.info("[GetMapping] ============= register form 요청 =============");
	}
	
	@PostMapping("/register")
	public String registerPost(GuestbookDTO insertDTO, RedirectAttributes rttr) {
		log.info("[PostMapping] ============= register 요청 : " + insertDTO + " =============");
		Long gno = service.register(insertDTO);
		rttr.addFlashAttribute("message", gno);
		return "redirect:/guestbook/list";
	}
	
	/* --------------------------- read / modify --------------------------- */
	@GetMapping({"/read", "/modify"})
	public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("[GetMapping] ============= read / modify form 요청 =============");
		GuestbookDTO dto = service.read(gno);
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/modify")
	public String modify(GuestbookDTO updateDTO, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes rttr) {
		log.info("[GetMapping] ============= modify 요청 =============");
		service.modify(updateDTO);
		rttr.addAttribute("gno", updateDTO.getGno());
		rttr.addAttribute("page", requestDTO.getPage());
		rttr.addAttribute("type", requestDTO.getType());
		rttr.addAttribute("keyword", requestDTO.getKeyword());
		return "redirect:/guestbook/read";
	}
	
	/* --------------------------- remove --------------------------- */
	@PostMapping("/remove")
	public String remove(long gno, RedirectAttributes rttr) {
		log.info("[PostMapping] ============= remove 요청 =============");
		service.remove(gno);
		rttr.addFlashAttribute("message", gno);
		return "redirect:/guestbook/list";
	}
}