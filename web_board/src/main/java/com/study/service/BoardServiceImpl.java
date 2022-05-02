package com.study.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.dto.BoardDTO;
import com.study.dto.Criteria;
import com.study.mapper.BoardMapper;

@Service("service")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardDTO> getList(Criteria criteria) {
		return mapper.getList(criteria);
	}

	@Override
	public boolean register(BoardDTO registerDTO) {
		return mapper.register(registerDTO) == 1 ? true : false;
	}

	@Override
	public boolean modify(int bno, String title, String content) {
		return mapper.modify(bno, title, content) == 1 ? true : false;
	}

	@Override
	public BoardDTO read(int bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean remove(int bno) {
		return mapper.remove(bno) == 1 ? true : false;
	}

	@Override
	public int getTotalCnt(Criteria criteria) {
		return mapper.totalCnt(criteria);
	}
}