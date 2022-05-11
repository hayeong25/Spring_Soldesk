package com.study.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.study.dto.Criteria;
import com.study.dto.ReplyDTO;

public interface ReplyMapper {
	public List<ReplyDTO> select(@Param("criteria")Criteria criteria, @Param("bno")int bno);
	public int getCountBno(int bno);
	public ReplyDTO read(int rno);
	public int insert(ReplyDTO insertDTO);
	public int update(ReplyDTO updateDTO);
	public int delete(int rno);
	public int deleteAll(int bno);
}