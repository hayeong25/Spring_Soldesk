package com.study.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.mapper.BookMapper;
import com.study.dto.BookDTO;

@Service("service")
public class BookServiceImpl implements BookService {
	
	@Autowired // 생성된 객체 주입
	private BookMapper mapper; // => 객체 생성 필요
	
	@Override
	public List<BookDTO> getList() {
		return mapper.select();
	}
	
	@Override
	public BookDTO getRow(int code) {
		return mapper.get(code);
	}

	@Override
	public boolean bookInsert(BookDTO insertDTO) {
		return mapper.insert(insertDTO) == 1 ? true : false;
	}

	@Override
	public boolean bookUpdate(int code, int price) {
		return mapper.update(code, price) == 1 ? true : false;
	}

	@Override
	public boolean bookDelete(int code) {
		return mapper.delete(code) == 1 ? true : false;
	}

	@Override
	public List<BookDTO> searchList(String criteria, String keyword) {
		return mapper.search(criteria, keyword);
	}

}