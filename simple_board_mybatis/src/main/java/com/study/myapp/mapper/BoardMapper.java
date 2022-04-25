package com.study.myapp.mapper;

import java.util.List;
import com.study.myapp.dto.BoardDTO;

public interface BoardMapper {
	public int insert(BoardDTO insertDTO);
	public List<BoardDTO> select();
	public int update(BoardDTO updateDTO);
	public int delete(int bno);
	public BoardDTO selectOne(int bno);
}