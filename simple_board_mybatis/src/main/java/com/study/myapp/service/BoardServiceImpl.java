package com.study.myapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.myapp.dto.BoardDTO;
import com.study.myapp.mapper.BoardMapper;

@Service("service") // service 객체임을 알림
public class BoardServiceImpl implements BoardService {
	
	@Autowired // 생성된 객체 주입
	private BoardMapper mapper;
	
	@Override
	public List<BoardDTO> getList() {
		return mapper.select();
	}

	@Override
	public BoardDTO getRow(int bno) {
		return mapper.selectOne(bno);
	}

	@Override
	public boolean boardInsert(BoardDTO insertDTO) {
		return mapper.insert(insertDTO) == 1 ? true : false;
	}

	@Override
	public boolean boardUpdate(BoardDTO updateDTO) {
		return mapper.update(updateDTO) == 1 ? true : false;
	}

	@Override
	public boolean boardDelete(int bno) {
		return mapper.delete(bno) == 1 ? true : false;
	}

}
