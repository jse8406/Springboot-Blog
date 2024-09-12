package me.jse.blog.jselog.controller.api;


import jakarta.servlet.http.HttpSession;
import me.jse.blog.jselog.config.auth.PrincipalDetail;
import me.jse.blog.jselog.dto.ResponseDto;
import me.jse.blog.jselog.model.Member;
import me.jse.blog.jselog.repository.MemberRepository;
import me.jse.blog.jselog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserApiController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;



    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody Member member){

        memberService.save(member); // 회원가입
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody Member member){
        memberService.update(member);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
