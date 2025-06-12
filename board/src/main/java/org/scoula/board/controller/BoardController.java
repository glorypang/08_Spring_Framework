package org.scoula.board.controller;
import lombok.RequiredArgsConstructor;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2                           // 로깅을 위한 Lombok 어노테이션
@Controller                       // Spring MVC Controller 지정
@RequestMapping("/board")         // 기본 URL 패턴 설정
@RequiredArgsConstructor         // final 필드 생성자 자동 생성
public class BoardController {
    // 의존성 주입: BoardService를 통해 비즈니스 로직 처리
    final private BoardService service;

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", service.getList());
    }

    @GetMapping("/create")
    public void create() {
        log.info("create");
    }
    @PostMapping("/create")
    public String create(BoardDTO board) {
        log.info("create: " + board);
        service.create(board);
        return "redirect:/board/list";
    }

    @GetMapping({ "/get", "/update" })
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/update")
    public String update(BoardDTO board) {
        log.info("update:" + board);
        service.update(board);
        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {
        log.info("delete..." + no);
        service.delete(no);
        return "redirect:/board/list";
    }
}