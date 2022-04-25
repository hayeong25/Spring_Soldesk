package com.study.myapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.myapp.dao.BoardDAO;
import com.study.myapp.dto.BoardDTO;

@Service("service") // service 객체임을 알림
public class BoardServiceImpl implements BoardService {
	
	@Autowired // 생성된 객체 주입
	private BoardDAO dao;
	
	@Override
	public List<BoardDTO> getList() {
		return dao.select();
	}

	@Override
	public BoardDTO getRow(int bno) {
		return dao.selectOne(bno);
	}

	@Override
	public boolean boardInsert(BoardDTO insertDTO) {
		return dao.insert(insertDTO) == 1 ? true : false;
	}

	@Override
	public boolean boardUpdate(BoardDTO updateDTO) {
		return dao.update(updateDTO.getBno(), updateDTO.getTitle(), updateDTO.getContent(), updateDTO.getUpdatedate()) == 1 ? true : false;
	}

	@Override
	public boolean boardDelete(int bno) {
		return dao.delete(bno) == 1 ? true : false;
	}

}
