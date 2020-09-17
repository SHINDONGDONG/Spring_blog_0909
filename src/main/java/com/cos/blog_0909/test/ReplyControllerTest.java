package com.cos.blog_0909.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog_0909.model.Board;
import com.cos.blog_0909.repository.BoardRepository;

@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;
	
	
	@GetMapping("/test/board/{id}")
	public  Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get();
	}
}
