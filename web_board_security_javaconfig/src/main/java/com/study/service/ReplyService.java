package com.study.service;

import com.study.dto.*;

public interface ReplyService {
	public ReplyPageDTO getList(Criteria criteria, int bno);
	public ReplyDTO readReply(int rno);
	public boolean insertReply(ReplyDTO insertDTO);
	public boolean updateReply(ReplyDTO updateDTO);
	public boolean deleteReply(int rno);
}