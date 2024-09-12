package me.jse.blog.jselog.service;

import me.jse.blog.jselog.model.Board;
import me.jse.blog.jselog.model.Member;
import me.jse.blog.jselog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void save(Board board, Member member) { //title, content
        board.setCount(0);
        board.setMember(member);
        boardRepository.save(board);
    }

    public Page<Board> getList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board getDetails(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 게시글이 없습니다.");
                });
    }

    @Transactional()
    public void delete(int id) {
        System.out.println("delete");
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 게시글이 없습니다.");
                });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료. 이때 더티체킹 - 자동 업데이트가 됨. db flush
    }
}
