package me.jse.blog.jselog.test;

import me.jse.blog.jselog.model.Member;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {
    //http 요청은 객체를 인수로 전달 받을 떄 @RequestParam을 사용할 필요 없이 보낸 text 데이터가 필드에 알아서 mapping이 된다.

    //Get요청은 text로 데이터를 전달받을 때 @RequestParam을 사용해야 한다.
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "getTest"+ m.getId() +","+ m.getUsername() + m.getPassword();
    }
    //Post요청은 text/plain으로 데이터를 전달받을 때 @RequestBody를 사용해야 한다. param형식으로 url로 정보를 전달할 수 없다.
    //그렇지만 json으로 보내야 정보를 파싱하기가 더 편하기 때문에 json으로 보내는 것이 일반적이다.
    //json형식으로 보냈는데 이를 text/plain으로 보내면 member에 매핑이 되지 않아서 인식을 올바르게 할 수 없다.
    // 그리고 이 json형식을 mapping하게 해주는 것은 스프링의 messageConverter이 해준다.(위에서 url query string 또한 messageConverter가 해준다.)
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return m.getUsername() + m.getPassword() + m.getId();
    }
}
