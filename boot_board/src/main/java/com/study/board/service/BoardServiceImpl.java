package com.study.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.study.board.dto.*;
import com.study.board.mapper.*;

@Service("service")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public List<BoardDTO> getList(Criteria criteria) {
		return mapper.getList(criteria);
	}

	@Transactional
	@Override
	public void register(BoardDTO registerDTO) {
		// 새 글 등록
		mapper.register(registerDTO);
		
		// 첨부파일 삽입 - 첨부파일이 없다면 되돌려보내기
		if(registerDTO.getAttachList() == null || registerDTO.getAttachList().size() <= 0) {
			return;
		}
		
		// 첨부파일 개수만큼 루프 돌기
		registerDTO.getAttachList().forEach(attach -> {
			attach.setBno(registerDTO.getBno());
			attachMapper.insert(attach);
		});		
	}

	@Transactional
	@Override
	public boolean modify(BoardDTO modifyDTO) {
		// 기존 첨부파일 삭제
		attachMapper.deleteAll(modifyDTO.getBno());
		
		if(modifyDTO.getAttachList() != null && modifyDTO.getAttachList().size() > 0) {
			for(AttachDTO attach : modifyDTO.getAttachList()) {
				attach.setBno(modifyDTO.getBno());
				attachMapper.insert(attach);
			}
		}
		
		return mapper.modify(modifyDTO) == 1 ? true : false;
	}

	@Override
	public BoardDTO read(int bno) {
		return mapper.read(bno);
	}

	@Transactional
	@Override
	public boolean remove(int bno) { // 게시글 삭제
		// 첨부파일 삭제
		attachMapper.deleteAll(bno);
		
		// 댓글 삭제
		replyMapper.deleteAll(bno);
		
		return mapper.remove(bno) == 1 ? true : false;
	}

	@Override
	public int getTotalCnt(Criteria criteria) {
		return mapper.totalCnt(criteria);
	}

	@Override
	public List<AttachDTO> attachList(int bno) {
		return attachMapper.list(bno);
	}
}