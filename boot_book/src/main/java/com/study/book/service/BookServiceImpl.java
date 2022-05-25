package com.study.book.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.book.dto.BookDTO;
import com.study.book.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper mapper;

	@Override
	public List<BookDTO> list() {
		return mapper.getList();
	}

	@Override
	public boolean insert(BookDTO insertDTO) {
		return mapper.insert(insertDTO) == 1 ? true : false;
	}

	@Override
	public boolean update(int code, int price) {
		return mapper.update(code, price) == 1 ? true : false;
	}

	@Override
	public boolean delete(int code) {
		return mapper.delete(code) == 1 ? true : false;
	}

	@Override
	public List<BookDTO> search(String criteria, String keyword) {
		return mapper.search(criteria, keyword);
	}

}