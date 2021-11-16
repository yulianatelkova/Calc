package ru.vsu.amm.java;
import java.util.Scanner;

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
            System.out.println( "1. Нажмите '1' и введите математическое выражение\n" +
                    "2. Нажмите '2' для выхода\n");
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
                default: if (command != 2) System.out.println("Ошибка ввода меню");
            }
        } while (command != 2);
}
}