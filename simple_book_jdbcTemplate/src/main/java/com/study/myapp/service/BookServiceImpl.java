package com.study.myapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.myapp.dao.BookDAO;
import com.study.myapp.dto.BookDTO;

@Service("service") // 객체 생성
public class BookServiceImpl implements BookService {
	
	@Autowired // 생성된 객체 주입
	private BookDAO dao; // => 객체 생성 필요
	
	@Override
	public List<BookDTO> getList() {
		return dao.select();
	}

	@Override
	public boolean bookInsert(BookDTO insertDTO) {
		return dao.insert(insertDTO) == 1 ? true : false;
	}

	@Override
	public boolean bookUpdate(int code, int price) {
		return dao.update(code, price) == 1 ? true : false;
	}

	@Override
	public boolean bookDelete(int code) {
		return dao.delete(code) == 1 ? true : false;
	}

}