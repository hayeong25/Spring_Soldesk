package com.study.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.study.dto.*;

public interface BoardService {
	public List<BoardDTO> getList(Criteria criteria);
	public boolean register(BoardDTO registerDTO);
	public boolean modify(@Param("bno")int bno, @Param("title")String title, @Param("content")String content);
	public BoardDTO read(int bno);
	public boolean remove(int bno);
	public int getTotalCnt(Criteria criteria);
}
