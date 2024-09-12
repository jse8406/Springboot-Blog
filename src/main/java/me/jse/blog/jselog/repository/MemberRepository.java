package me.jse.blog.jselog.repository;

import me.jse.blog.jselog.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//DAO라고 볼 수 있다. 자동으로 bean 등록이 된다.

//japrepository를 상속받아 자동으로 영속성 컨텍스트에 저장할 수 있다.
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
}
