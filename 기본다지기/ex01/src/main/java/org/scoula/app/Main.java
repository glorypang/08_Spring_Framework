package org.scoula.app;
import org.scoula.config.ProjectConfig;
import org.scoula.config.ProjectConfig3;
import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Parrot p = context.getBean(Parrot.class);
        System.out.println(p);
        System.out.println(p.getName());

        String s = context.getBean(String.class);
        System.out.println(s); // Hello

        Integer n = context.getBean(Integer.class);
        System.out.println(n); // 10
    }
}