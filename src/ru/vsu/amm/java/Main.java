package ru.vsu.amm.java;

import java.util.Scanner;
import java.util.EmptyStackException;
/**
 * @author  Telkova Yuliana
 */
public class Main {
    /**
     * функция main
     * @param args - аргументы
     * @throws Exception Ошибка ввода выражения
     */
    public static void main(String[] args) throws Exception {

        int command = 1;
        do{
            Scanner in = new Scanner(System.in);
            System.out.println( "1. Нажмите '1' для проверки калькулятора\n" +
                    "2. Нажмите '2' для проверки логических выражений\n" +
                    "3. Нажмите '5' для выхода\n");
            command = in.nextInt();
            switch (command) {
                case 1:
                    try
                    {
                        Scanner inn = new Scanner(System.in);
                        String expression = inn.nextLine();
                        System.out.println();
                        System.out.println(Calculate.calculateExpression(expression));
                    }
                    catch (Exception expected)
                    {
                        System.out.println("Ошибка выражения");
                    }
                    break;
                case 2:
                    try
                    {
                        System.out.print("Наш калькулятор ");
                        System.out.println(Calculate.calculateExpression("cos(0)"));
                        System.out.print("Встроенный косинус ");
                        System.out.println(Math.cos(0));
                        System.out.print("Наш калькулятор ");
                        System.out.println(Calculate.calculateExpression("sin(0)"));
                        System.out.print("Встроенный синус ");
                        System.out.println(Math.sin(0));
                        System.out.print("Наш калькулятор ");
                        System.out.println(Calculate.calculateExpression("sqrt(4)"));
                        System.out.print("Встроенный корень ");
                        System.out.println(Math.sqrt(4));
                        System.out.println();
                    }
                    catch (Exception expected2)
                    {
                        System.out.println("Ошибка выражения");
                    }
                    break;
            }
        } while (command != 5);
}
}