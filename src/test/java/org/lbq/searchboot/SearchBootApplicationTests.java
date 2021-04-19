package org.lbq.searchboot;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("org.lbq.searchboot.mapper")
class SearchBootApplicationTests {

    @Test
    void contextLoads() {
    }

}
