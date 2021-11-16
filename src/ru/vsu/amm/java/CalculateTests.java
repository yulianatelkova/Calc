package ru.vsu.amm.java;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

/**
 * @author  Telkova Yuliana
 */
public class CalculateTests {

    @Test
    public void testSummation() {
        assertEquals(Calculate.calculateExpression("2+2"), 4);
    }

    @Test
    public void testSubtraction() {
        assertEquals(Calculate.calculateExpression("2-2"), 0);
    }

    @Test
    public void testMultiplication() {
        assertEquals(Calculate.calculateExpression("2*2"), 4);
    }

    @Test
    public void testDivision() {
       assertEquals(Calculate.calculateExpression("2/2"), 1);
    }

    @Test
    public void testExponentiation() {
        assertEquals(Calculate.calculateExpression("2^2"), 4);
    }

    @Test
    public void testFunction() {
        assertEquals(Calculate.calculateExpression("sqrt(4)"), 2);
        assertEquals(Calculate.calculateExpression("sin(0)"), 0);
        assertEquals(Calculate.calculateExpression("cos(0)"), 1);
    }

    @Test
    public void testBracket() {
        try {
            Calculate.calculateExpression("((2+2)");
        } catch (ArithmeticException expected) {}
    }

    @Test
    public void testSign() {
        try {
            Calculate.calculateExpression("2_3");
        } catch (ArithmeticException expected) {}
    }

    @Test
    public void testNull() {
        try {
            Calculate.calculateExpression("2/0");
        } catch (ArithmeticException expected) {}
    }

}