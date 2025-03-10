package me.jse.blog.jselog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jse.blog.jselog.model.KakaoProfile;
import me.jse.blog.jselog.model.OAuthToken;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


@Controller
public class UserController {
    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm") //인증이 필요없는 페이지에는 auth를 붙임 = 로그인을 안해도 들어갈 수 있음
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }

    @GetMapping("auth/kakao/callback")
    public @ResponseBody String kakaoCallback(@RequestParam String code){

        String client_id = "afd288e39a67101a3d6da66e9b32ecbc";
        String redirect_uri = "https://localhost:8080/auth/kakao/callback";
        // Post 방식으로 key = value 데이터를 요청 (kakao)
        RestTemplate rt = new RestTemplate();

        // http header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // http body 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client_id);
        params.add("redirect_url", redirect_uri);
        params.add("code", code);

        // header랑 body 붙여서 http entity 생성
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);


        // kakao token url에 http entity를 post 방식으로 요청해서 토큰 응답을 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        System.out.println(oauthToken.getAccess_token());


        /// User Profile info


        RestTemplate rtOAuth = new RestTemplate();

        // http header 생성
        HttpHeaders headersOAuth = new HttpHeaders();
        headersOAuth.add("Authorization", "Bearer "+ oauthToken.getAccess_token());
        headersOAuth.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // header랑 body 붙여서 http entity 생성
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headersOAuth);

        ResponseEntity<String> profileResponse = rtOAuth.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper KakaoObjectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = KakaoObjectMapper.readValue(profileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ID : " + kakaoProfile.getId());
        System.out.println("Nickname : " + kakaoProfile.getKakaoAccount().getProfile().getNickname());
        // profile response : response : {"id":3947791708,"connected_at":"2025-03-04T11:15:03Z","properties":{"nickname":"진승언","profile_image":"http://k.kakaocdn.net/dn/qcFkh/btsCguiE0XT/MHjfPYJMLmjcrJffQujEh1/img_640x640.jpg","thumbnail_image":"http://k.kakaocdn.net/dn/qcFkh/btsCguiE0XT/MHjfPYJMLmjcrJffQujEh1/img_110x110.jpg"},"kakao_account":{"profile_nickname_needs_agreement":false,"profile_image_needs_agreement":false,"profile":{"nickname":"진승언","thumbnail_image_url":"http://k.kakaocdn.net/dn/qcFkh/btsCguiE0XT/MHjfPYJMLmjcrJffQujEh1/img_110x110.jpg","profile_image_url":"http://k.kakaocdn.net/dn/qcFkh/btsCguiE0XT/MHjfPYJMLmjcrJffQujEh1/img_640x640.jpg","is_default_image":false,"is_default_nickname":false}}}
        return "response : " + profileResponse.getBody();

    }

    // content-type : application/x-www-form-urlencoded;charset=utf-8,
    // grant-type : authroization_code
    // client_id : rest api key
    // redirect_uri : https://localhost:8080/auth/kakao/callback
    // code : 인증이 완료된 code

//    @PostMapping("https://kauth.kakao.com/oauth/token") // post 방식은 query string이 아닌 http body에 담아서 보내야함



}
