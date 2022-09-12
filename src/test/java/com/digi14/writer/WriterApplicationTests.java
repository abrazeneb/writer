package com.digi14.writer;

import com.digi14.writer.service.writers.CustomStringWriter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WriterApplicationTests {

    @Test
    void contextLoads() {


    }

    @Test
    public void testScenario() {

    }

    @Test
    void removeDuplicatesTest() {
        CustomStringWriter stringWriter = new CustomStringWriter("Hello hello stupid world world program");
        stringWriter.removeDuplicateWords().removeStupid();
        Assertions.assertThat(stringWriter.getContent().equals("Hello s***** world program"));
        System.out.println(stringWriter.getContent());
    }

    @Test
    public void solution() {
        String S = "abbb";
        char[] chars = S.toCharArray();
        if(S.indexOf('a') != -1 && S.indexOf('b') != -1
           && S.lastIndexOf('a') < S.indexOf('b') ) {

            System.out.println(true);

        } else if(S.indexOf('a') == -1 || S.indexOf('b') == -1) {

            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }

}
