package com.study.book.service;

import java.util.List;

import com.study.book.dto.BookDTO;

public interface BookService {
	public List<BookDTO> list();
	public boolean insert(BookDTO insertDTO);
	public boolean update(int code, int price);
	public boolean delete(int code);
	public List<BookDTO> search(String criteria, String keyword);
}