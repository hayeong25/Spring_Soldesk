package com.study.myapp.service;

import java.util.List;
import com.study.myapp.dto.BoardDTO;

public interface BoardService {
	// CRUD 작업
	public List<BoardDTO> getList();
	public BoardDTO getRow(int bno);
	public boolean boardInsert(BoardDTO insertDTO);
	public boolean boardUpdate(BoardDTO updateDTO);
	public boolean boardDelete(int bno);
}