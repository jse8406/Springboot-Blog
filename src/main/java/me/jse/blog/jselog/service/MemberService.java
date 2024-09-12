package me.jse.blog.jselog.service;

import me.jse.blog.jselog.model.Member;
import me.jse.blog.jselog.model.RoleType;
import me.jse.blog.jselog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Transactional
    public void save(Member member) {
        String rawPassword = member.getPassword();
        String encPassword  = encoder.encode(rawPassword);
        member.setPassword(encPassword);
        member.setRole(RoleType.USER);
        memberRepository.save(member);
    }

    @Transactional
    public void update(Member member){
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update 문을 날려줌
        System.out.println(member.getUsername());
        // js로 username정보는 전달해주지 않아서 member에.username에 null값이 들어가있음. 그래서 id를 통해 member 객체 가져와서 필요한 부분만 update하고 커밋
        Member persistence = memberRepository.findById(member.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });
        persistence.setPassword(encoder.encode(member.getPassword()));
        persistence.setEmail(member.getEmail());


        // 세션 등록 Token 파라미터로 persistence가 아닌 member 의 username 과 password 를 넣어주면 아직 db 상에서 업데이트가 일어나지 않았기 때문에 세션에 반영이 안됨
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(persistence.getUsername(), member.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //update 함수 종료시 서비스 종류가 되어 트랜잭션이 종료가 되고 commit이 자동으로 됨
        //영속화된 persistence 객체의 변화가 감지되면 더티체킹이 되어 변화된 것을 감지하여 update문을 날려줌

    }

//    @Transactional(readOnly = true)
//    public Member login(Member member) {
//        return memberRepository.findByUsernameAndPassword(member.getUsername(), member.getPassword());
//    } spring security의 로그인을 사용할 것이기 때문에 필요없음
}
