package com.cos.blog_0909.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog_0909.dto.ReplySaveRequestDto;
import com.cos.blog_0909.model.Board;
import com.cos.blog_0909.model.User;
import com.cos.blog_0909.repository.BoardRepository;
import com.cos.blog_0909.repository.ReplyRepository;
import com.cos.blog_0909.repository.UserRepository;

@Service
public class BoardService {

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	@Transactional
	public void save(Board board, User user) { // title,content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional
	public void modify(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글찾기 실패");
		}); // 영속화시키기 . //먼저 어떤 보드id가 있는지 알아야지 수정할수있음 (findById)
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당함수 종료시 (service가 종료될때) 트랜잭션이 종료됨.
		// 더티체킹 - 자동업데이트
	}

	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board boardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("지정된 게시글이 없습니다 : " + id);
		});
	}

	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void replySave(ReplySaveRequestDto replySaveRequestDto) {
		/*User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
			return new IllegalArgumentException("댓글작성 실패 : 유저 id를 찾을 수 없습니다.");
		});// 영속화 완료
		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() -> {
			return new IllegalArgumentException("댓글작성 실패 : 게시글 id를 찾을 수 없습니다.");
		});// 영속화 완료
*/
		/*Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(replySaveRequestDto.getContent())
				.build();*/
		replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
	}

	@Transactional
	public void replyDelete(int replyId) {
			replyRepository.deleteById(replyId);
	}
}
