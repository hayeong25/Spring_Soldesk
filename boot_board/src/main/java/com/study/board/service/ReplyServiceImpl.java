package com.study.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.study.board.dto.*;
import com.study.board.mapper.*;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public ReplyPageDTO getList(Criteria criteria, int bno) {
		return new ReplyPageDTO(mapper.getCountBno(bno), mapper.select(criteria, bno));
	}
	
	@Override
	public ReplyDTO readReply(int rno) {
		return mapper.read(rno);
	}
	
	@Transactional
	@Override
	public boolean insertReply(ReplyDTO insertDTO) {
		// 원본글의 댓글 수 추가
		boardMapper.updateReplyCnt(insertDTO.getBno(), 1);
		
		return mapper.insert(insertDTO) == 1 ? true : false;
	}

	@Override
	public boolean updateReply(ReplyDTO updateDTO) {
		return mapper.update(updateDTO) == 1 ? true : false;
	}

	@Transactional
	@Override
	public boolean deleteReply(int rno) {
		// bno 가져오기
		ReplyDTO dto = mapper.read(rno);
		
		// 원본글의 댓글 수 감소
		boardMapper.updateReplyCnt(dto.getBno(), -1);
		
		return mapper.delete(rno) == 1 ? true : false;
	}
}