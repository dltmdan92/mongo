package com.seungmoo.springboot.mongo.Account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest // slicing 테스트임, 테스트 시 mongoRepository 관련 bean들만 등록됨
// 이 클래스는 운영용 mongodb에 영향없이, de.flapdoodle.embed.mongo 테스트용 mongo 내장db를 쓴다.
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findByEmail() {
        // 시작할 때 안지워주니까 dup 에러 발생함
        accountRepository.deleteAll();

        Account account = new Account();
        account.setUsername("seungmoo");
        account.setEmail("dltmdan92@gmail.com");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        assertThat(byId).isNotEmpty();
        Optional<Account> byEmail = accountRepository.findByEmail(account.getEmail());
        assertThat(byEmail).isNotEmpty();
        assertThat(byEmail.get().getUsername()).isEqualTo("seungmoo");
    }

}