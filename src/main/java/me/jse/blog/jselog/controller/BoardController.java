package me.jse.blog.jselog.controller;


import me.jse.blog.jselog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    //이제 spring security에 세션이 등록되었기 때문에 세션을 사용할 수 있다. (main 페이지는 인증없이
    // 올 수 있어야 함으로 AuthenticationPrincipal 삭제
    @GetMapping({ "/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable ){
        model.addAttribute("boards", boardService.getList(pageable)); //getList함수가 page로 리턴을 하기 때문에 jsp에서 boards.content로 받아야함
        return "index";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.getDetails(id));
        return  "board/detail";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.getDetails(id)); //수정히기위해 들어온 글의 상세 정보를 가져와서 뿌림
        return "board/updateForm";
    }
}

