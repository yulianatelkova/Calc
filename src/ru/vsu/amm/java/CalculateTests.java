package ru.vsu.amm.java;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author  Telkova Yuliana
 */
public class CalculateTests {
    /**
     * @throws Exception Ошибка ввода выражения
     */
    @Test
    public void testSummation() throws Exception {
        Assert.assertEquals(Calculate.calculateExpression("2+2"), 4, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("1+2+3"), 6, 0.001);
    }
    /**
     * @throws Exception Ошибка ввода выражения
     */
    @Test
    public void testSubtraction() throws Exception {
        Assert.assertEquals(Calculate.calculateExpression("2-2"), 0, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("3-4-1"), -2, 0.001);
    }
    /**
     * @throws Exception Ошибка ввода выражения
     */
    @Test
    public void testMultiplication() throws Exception {
        Assert.assertEquals(Calculate.calculateExpression("2*2"), 4, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("1*2*3"), 6, 0.001);
    }
    /**
     * @throws Exception Ошибка ввода выражения
     */
    @Test
    public void testDivision() throws Exception {
        Assert.assertEquals(Calculate.calculateExpression("2/2"), 1, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("6/3/2"), 1, 0.001);
    }
    /**
     * @throws Exception Ошибка ввода выражения
     */
    @Test
    public void testExponentiation() throws Exception {
        Assert.assertEquals(Calculate.calculateExpression("2^2"), 4, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("4^3"), 64, 0.001);
    }
    /**
     * @throws Exception Ошибка ввода выражения
     */
    @Test
    public void testFunction() throws Exception {
        Assert.assertEquals(Calculate.calculateExpression("sqrt(4)"), 2, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("sin(0)"), 0, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("cos(0)"), 1, 0.001);
    }
    /**
     * @throws Exception Ошибка ввода выражения
     */
    @Test
    public void testExpressionWithSubexrpession() throws Exception {
        Assert.assertEquals(Calculate.calculateExpression("sqrt(sqrt(16))"), 2, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("sin(sin(0))"), 0, 0.001);
    }
    /**
     * @throws Exception Ошибка ввода выражения
     */
    @Test
    public void someTests() throws Exception {
        Assert.assertEquals(Calculate.calculateExpression("2+4*3+sqrt(9)"), 17, 0.001);
        Assert.assertEquals(Calculate.calculateExpression("(2+1)*4"), 12, 0.001);
    }
}