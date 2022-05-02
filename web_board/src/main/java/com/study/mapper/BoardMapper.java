package com.study.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.study.dto.*;

public interface BoardMapper {
	public List<BoardDTO> getList(Criteria criteria);
	public int register(BoardDTO registerDTO);
	public int modify(@Param("bno")int bno, @Param("title")String title, @Param("content")String content);
	public BoardDTO read(int bno);
	public int remove(int bno);
	public int totalCnt(Criteria criteria);
}