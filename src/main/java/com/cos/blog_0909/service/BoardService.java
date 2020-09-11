package com.cos.blog_0909.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog_0909.model.Board;
import com.cos.blog_0909.model.RoleType;
import com.cos.blog_0909.model.User;
import com.cos.blog_0909.repository.BoardRepository;
import com.cos.blog_0909.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	
	@Transactional
	public void save(Board board, User user) { //title,content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	} 
	
	public Page<Board> boardList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	public Board boardDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
			return new IllegalArgumentException("지정된 게시글이 없습니다 : "+id);
		});
	}
}
