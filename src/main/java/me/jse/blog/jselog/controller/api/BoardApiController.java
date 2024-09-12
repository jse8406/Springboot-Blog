package me.jse.blog.jselog.controller.api;


import me.jse.blog.jselog.config.auth.PrincipalDetail;
import me.jse.blog.jselog.dto.ResponseDto;
import me.jse.blog.jselog.model.Board;
import me.jse.blog.jselog.model.Member;
import me.jse.blog.jselog.service.BoardService;
import me.jse.blog.jselog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board") // saveForm.js 에서 버튼 이벤트에 의해 title과 content를 이 api로 날림
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.save(board, principal.getMember()); //현재 세션의 아이디를 가져옴
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.update(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
