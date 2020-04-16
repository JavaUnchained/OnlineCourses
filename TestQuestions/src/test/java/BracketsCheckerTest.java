import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BracketsCheckerTest {
    BracketsChecker bracketsChecker = new BracketsChecker();

    @Test
    void correctExpression(){
        assertAll(
            "Expect is True value",
                () ->assertTrue(bracketsChecker.isCorrectBrackets("()[]{}")),
                () ->assertTrue(bracketsChecker.isCorrectBrackets("{(({{[]}}))}")),
                () ->assertTrue(bracketsChecker.isCorrectBrackets("{}"))

        );
    }
    @Test
    void incorrectExpression(){
        assertAll(
                "Expect is False value",
                () ->assertFalse(bracketsChecker.isCorrectBrackets("(")),
                () ->assertFalse(bracketsChecker.isCorrectBrackets(")")),
                () ->assertFalse(bracketsChecker.isCorrectBrackets(")(")),
                () ->assertFalse(bracketsChecker.isCorrectBrackets("{[(]}"))
        );
    }

    @Test
    void textableExpression(){
        assertAll(
                () -> assertTrue(bracketsChecker.isCorrectBrackets("(a^2 + b^2)")),
                () -> assertTrue(bracketsChecker.isCorrectBrackets("(a[2] + b[2])")),
                () -> assertTrue(bracketsChecker.isCorrectBrackets("(a[2sad]dsaqwepl{asd2})")));
    }

}
