package com.cos.blog_0909.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog_0909.config.auth.PrincipalDetails;
import com.cos.blog_0909.dto.ResponseDto;
import com.cos.blog_0909.model.Board;
import com.cos.blog_0909.model.RoleType;
import com.cos.blog_0909.model.User;
import com.cos.blog_0909.repository.UserRepository;
import com.cos.blog_0909.service.BoardService;
import com.cos.blog_0909.service.UserService;

@RestController 
public class BoardApiController {

	@Autowired 
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetails principal){ 
		boardService.save(board,principal.getUser());
 		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
