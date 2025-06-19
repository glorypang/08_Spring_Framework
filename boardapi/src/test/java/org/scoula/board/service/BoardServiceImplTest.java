package org.scoula.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class} )
@Log4j2
class BoardServiceImplTest {

    @Autowired
    private BoardService service;

    // 목록 조회 테스트
    @Test
    public void getList() {
        List<BoardDTO> list = service.getList();

        assertFalse(list.isEmpty());           // 목록이 비어있지 않은지 확인

        for(BoardDTO board : list) {
            log.info("게시글: {}", board);
            assertNotNull(board.getNo());      // 게시글 번호 존재 확인
            assertNotNull(board.getTitle());   // 제목 존재 확인
        }
    }

    // 단일 조회 테스트
    @Test
    void get() {
        Long testNo = 1L;
        BoardDTO board = service.get(testNo);

        assertNotNull(board);                          // 조회 결과 존재 확인
        assertEquals(testNo, board.getNo());           // 번호 일치 확인

        log.info("조회된 게시글: {}", board);
    }

    // 게시글 등록 테스트
    @Test
    public void create() {
        BoardDTO board = BoardDTO.builder()
                .title("서비스 테스트 제목")
                .content("서비스 테스트 내용")
                .writer("testuser")
                .build();

        service.create(board);

        assertNotNull(board.getNo());              // PK가 설정되었는지 확인
        log.info("생성된 게시물 번호: {}", board.getNo());
    }

    // 게시글 수정 테스트
    @Test
    public void update() {
        // 기존 게시글 조회
        BoardDTO board = service.get(1L);

        // 데이터 수정
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");

        // 수정 실행 및 결과 확인
        BoardDTO updated = service.update(board);

        assertNotNull(updated);                           // 반환 객체가 null 아님
        assertEquals("수정된 제목", updated.getTitle());  // 제목이 수정된 내용과 일치하는지 확인
        assertEquals("수정된 내용", updated.getContent()); // 내용도 일치하는지 확인

        log.info("수정된 게시물: {}", updated);
    }



    // 게시글 삭제 테스트
    @Test
    public void delete() {
        Long testNo = 10L;

        // 삭제 전 조회로 존재 확인
        BoardDTO existing = service.get(testNo);
        assertNotNull(existing);

        // 삭제 실행
        BoardDTO deleted = service.delete(testNo);
        assertNotNull(deleted);   // 삭제된 객체가 반환되는지 확인
        assertEquals(testNo, deleted.getNo());

        log.info("삭제된 게시물: {}", deleted);

        // 삭제 후 다시 조회 시 null 또는 예외 발생 예상
        assertThrows(NoSuchElementException.class, () -> {
            BoardDTO afterDelete = service.get(testNo);
            if (afterDelete == null) throw new NoSuchElementException();
        });
    }

}