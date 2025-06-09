package org.scoula.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
    private String name = "Ella";
    private Parrot parrot; // 의존성 필드

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrot() {
        return parrot;
    }

    // 매개 변수로 Bean 의존성 주입
    @Autowired
    public void setParrot(Parrot parrot) {
        this.parrot = parrot;
    }
}