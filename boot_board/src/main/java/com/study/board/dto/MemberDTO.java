package com.study.board.dto;

import java.util.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
	private String userid;
	private String userpw;
	private String username;
	private Date regdate;
	private Date updatedate;
	private boolean enabled;
	
	private List<AuthDTO> authList;
}