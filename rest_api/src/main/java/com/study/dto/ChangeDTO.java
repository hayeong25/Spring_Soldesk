package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeDTO {
	private String userid;
	private String password;
	private String new_password;
	private String confirm_password;
}
