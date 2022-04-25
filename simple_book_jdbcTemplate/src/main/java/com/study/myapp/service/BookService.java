package com.study.myapp.service;

import java.util.*;
import com.study.myapp.dto.BookDTO;

public interface BookService {
	public List<BookDTO> getList();
	public boolean bookInsert(BookDTO insertDTO);
	public boolean bookUpdate(int code, int price);
	public boolean bookDelete(int code);
}