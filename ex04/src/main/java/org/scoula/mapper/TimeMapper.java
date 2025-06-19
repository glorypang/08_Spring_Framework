package org.scoula.mapper;
import org.apache.ibatis.annotations.Select;
public interface TimeMapper {
    @Select("SELECT sysdate()")
    public String getTime();

    /**
     * XML 매퍼에서 정의할 메서드
     * 구현체는 MyBatis가 자동으로 생성
     */
    public String getTime2();
}