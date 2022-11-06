package acme.oddo.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CheckValidInputTest {

    @Test
    void isStringNumeric() {
        assertThat(CheckValidInput.isStringNumeric("22.32")).isTrue();
    }


    @Test
    void isStringNotNumeric() {
        assertThat(CheckValidInput.isStringNumeric("a")).isFalse();

    }
    
}
