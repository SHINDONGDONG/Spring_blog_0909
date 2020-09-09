package com.cos.blog_0909.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data//Getter,Setter
@AllArgsConstructor //construct
@NoArgsConstructor//基本生成者
public class Member {

	private int id;
	private String username;
	private String password;
	private String email;
}
