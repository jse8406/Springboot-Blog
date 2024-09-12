package me.jse.blog.jselog.repository;

import me.jse.blog.jselog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//DAO라고 볼 수 있다. 자동으로 bean 등록이 된다.
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
