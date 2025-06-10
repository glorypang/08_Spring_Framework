package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 🏠 홈(메인) 페이지 컨트롤러
 * - 애플리케이션의 메인 페이지와 기본 요청을 처리하는 컨트롤러
 *
 * 📋 주요 기능:
 * - 루트 경로("/") 요청 처리
 * - 홈페이지 렌더링
 * - 기본 진입점 역할
 *
 * @Controller
 * - @ComponentScan 어노테이션으로 스캔된 경우 자동으로 Bean 등록되는
 *   @Component 어노테이션의 하위 어노테이션
 * - Spring MVC 컨트롤러임을 명시
 *
 * @Log4j2
 * - Lombok을 이용해 log 관련 필드를 생성하는 어노테이션
 * - private static final org.apache.logging.log4j.Logger log
 *   = org.apache.logging.log4j.LogManager.getLogger(HomeController.class)
 */
@Controller // Spring MVC 컨트롤러로 등록
//@Log4j2
@Slf4j
public class HomeController {

    /**
     * 🌐 홈페이지 요청 처리 메서드
     * @GetMapping("/") - HTTP GET 요청, 루트 경로 매핑
     *
     * 🔄 처리 흐름:
     * 1. 사용자가 "/" 경로로 접근
     * 2. 로그 기록 (접근 추적용)
     * 3. "index" 뷰 이름 반환
     * 4. ViewResolver가 /WEB-INF/views/index.jsp로 변환
     * 5. 클라이언트의 요청을 index.jsp로 위임(Forward)
     * 6. JSP 페이지 렌더링 후 클라이언트에게 응답
     *
     * @return "index" - 논리적 뷰 이름 (index.jsp)
     */

    @GetMapping("/")
    public String home(Model model) {
        log.info("===========> HomeController /");
        model.addAttribute("title", "Spring 2일차"); // View로 전달할 데이터 Model에 추가
        return "index";		// View의 이름
    }

    /**
     * 컨트롤러 메서드에 필요한 객체를 매개 변수로 작성해두면
     * Spring Container가 Argument Resolver를 이용해서
     * 1) 해당 Bean 존재하면 주입
     * 2) 없으면 새로운 Bean을 생성해서 주입
     */
}
