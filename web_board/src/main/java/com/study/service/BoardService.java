package com.study.service;

import java.util.List;
import com.study.dto.*;

public interface BoardService {
	public List<BoardDTO> getList(Criteria criteria);
	public void register(BoardDTO registerDTO);
	public boolean modify(BoardDTO modifyDTO);
	public BoardDTO read(int bno);
	public boolean remove(int bno);
	public int getTotalCnt(Criteria criteria);
	
	public List<AttachDTO> attachList(int bno); // 첨부파일
}
