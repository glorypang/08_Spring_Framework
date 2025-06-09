package org.scoula.config;

import org.scoula.beans.Parrot;
import org.scoula.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public Parrot parrot() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    /**
     * Person Bean 등록 메서드
     * @param parrot Spring Container에 등록된 Bean 중 같은 타입을 자동으로 주입
     * @return
     */
    @Bean
    public Person person(Parrot parrot) { // 매개변수로 빈 주입
        Person p = new Person();
        p.setName("Ella");
        p.setParrot(parrot);
        return p;
    }
}
