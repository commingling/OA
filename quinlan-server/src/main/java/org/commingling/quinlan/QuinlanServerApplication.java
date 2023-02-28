package org.commingling.quinlan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ：Quinlan
 * @date ：2022/11/07 14:55
 */
@SpringBootApplication
public class QuinlanServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuinlanServerApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
