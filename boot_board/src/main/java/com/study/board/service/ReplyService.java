package com.study.board.service;

import com.study.board.dto.*;

public interface ReplyService {
	public ReplyPageDTO getList(Criteria criteria, int bno);
	public ReplyDTO readReply(int rno);
	public boolean insertReply(ReplyDTO insertDTO);
	public boolean updateReply(ReplyDTO updateDTO);
	public boolean deleteReply(int rno);
}