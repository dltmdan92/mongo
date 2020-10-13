package com.seungmoo.springboot.mongo;

import com.seungmoo.springboot.mongo.Account.Account;
import com.seungmoo.springboot.mongo.Account.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MongoApplication {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger(MongoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Account account = new Account();
            account.setEmail("dltmdan92@naver.com");
            account.setUsername("seungmoo");
            // mongoTemplate으로 insert 하기
            mongoTemplate.insert(account);

            Account accountTemplate = new Account();
            accountTemplate.setEmail("dltmdan92@gmail.com");
            accountTemplate.setUsername("sam");
            // Repository로 insert 하기 (기능 똑같음)
            accountRepository.insert(accountTemplate);

            logger.info("finished");
        };
    }

}
