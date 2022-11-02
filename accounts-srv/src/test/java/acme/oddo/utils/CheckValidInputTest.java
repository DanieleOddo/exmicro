package acme.oddo.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CheckValidInputTest {
    
    @Test
    void isStringNumeric() {
        assertThat(CheckValidInput.isStringNumeric("22")).isTrue();
    }

    @Test
    void isStringNotNumeric() {
        assertThat(CheckValidInput.isStringNumeric("a")).isFalse();

    }
}
