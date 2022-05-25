package com.study.board.dto;

import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
	private int replyCnt;			// 댓글 전체 개수
	private List<ReplyDTO> list;	// 댓글 목록
}
