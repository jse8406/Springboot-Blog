package me.jse.blog.jselog.test;


import jakarta.transaction.Transactional;
import me.jse.blog.jselog.model.Member;
import me.jse.blog.jselog.model.RoleType;
import me.jse.blog.jselog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DummyControllerTest {
    @Autowired //DummyController가 메모리에 뜰 때, memberRepository도 같이 메모리에 뜬다.
    private MemberRepository memberRepository;

    @PostMapping("/dummy/join")
    public String join(@RequestBody Member member) {
        System.out.println("id: " + member.getId());
        System.out.println("username: " + member.getUsername());
        System.out.println("password: " + member.getPassword());
        System.out.println("email: " + member.getEmail());
        System.out.println("createDate: " + member.getCreateDate());

        //어노테이션 너무 많이 사용하는 것 보다 join 시 user로 기본 세팅
        member.setRole(RoleType.USER);
        memberRepository.save(member);
        return "회원가입이 완료되었습니다.";
    }

    @GetMapping("/dummy/members")
    public List<Member> list() {
        return memberRepository.findAll();
    }

    @GetMapping("/dummy/members/page")
    public List<Member> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Member> pagingMembers = memberRepository.findAll(pageable);
        return pagingMembers.getContent();
    }

    @GetMapping("/dummy/member/{id}")
    public Member detail(@PathVariable int id){
        //그냥 .get은 예외처리가 잘 안되어 있다.
        return memberRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
        });
    }
    @Transactional
    @PutMapping("/dummy/member/{id}")
    public Member update(@PathVariable int id, @RequestBody Member requestMember){
        Member member = memberRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        member.setPassword(requestMember.getPassword());
        member.setEmail(requestMember.getEmail());
        return null;
    }

    @DeleteMapping("/dummy/member/{id}")
    public String delete(@PathVariable int id){
        if (!memberRepository.existsById(id)) {
            return "삭제에 실패하였습니다. 해당 id는 없습니다.";
        }
        memberRepository.deleteById(id);
        return "삭제되었습니다. id: "+id;
    }
}
