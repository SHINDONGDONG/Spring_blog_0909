package com.cos.blog_0909.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog_0909.config.auth.PrincipalDetails;
import com.cos.blog_0909.dto.ReplySaveRequestDto;
import com.cos.blog_0909.dto.ResponseDto;
import com.cos.blog_0909.model.Board;
import com.cos.blog_0909.service.BoardService;

@RestController 
public class BoardApiController {

	@Autowired 
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetails principal){ 
		boardService.save(board,principal.getUser());
 		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id){
		boardService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
		boardService.modify(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){ 
		boardService.replySave(replySaveRequestDto);
 		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId,@PathVariable int boardId,Model mv){ 
		mv.addAttribute("boardId",boardId);
		boardService.replyDelete(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
