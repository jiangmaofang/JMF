package cn.com.jmf.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jiangmaofang
 * @date 2019/07/12 9:32
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.*")
public class LearnApplication {
    public static void main(String[] args){
        SpringApplication.run(LearnApplication.class, args);
    }
}
