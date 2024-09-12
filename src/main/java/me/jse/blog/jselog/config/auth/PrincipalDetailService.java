package me.jse.blog.jselog.config.auth;


import me.jse.blog.jselog.model.Member;
import me.jse.blog.jselog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// 시큐리티가 어느 DB에 유저 정보가 있는지 알려주는 설정. 이렇게 DB를 알려줘야 로그인 요청 시 알아서 해줄 수 있다.
@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    // loadUserByUsername 함수를 override 안해주면 처음 시작시 나오는 일회용 비번과 아이디도 기본으로 주는 user만 사용이 가능하다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = memberRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
                });
        return new PrincipalDetail(principal);
    }
}
