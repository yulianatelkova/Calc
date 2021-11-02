package ru.vsu.amm.java;

import java.util.Scanner;
import java.util.EmptyStackException;
/**
 * @author  Telkova Yuliana
 */
public class Main {
    /**
     * ������� main
     * @param args - ���������
     * @throws Exception ������ ����� ���������
     */
    public static void main(String[] args) throws Exception {

        int command = 1;
        do{
            Scanner in = new Scanner(System.in);
            System.out.println( "1. ������� '1' ��� �������� ������������\n" +
                    "2. ������� '2' ��� �������� ���������� ���������\n" +
                    "3. ������� '5' ��� ������\n");
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
                        System.out.println("������ ���������");
                    }
                    break;
                case 2:
                    try
                    {
                        System.out.print("��� ����������� ");
                        System.out.println(Calculate.calculateExpression("cos(0)"));
                        System.out.print("���������� ������� ");
                        System.out.println(Math.cos(0));
                        System.out.print("��� ����������� ");
                        System.out.println(Calculate.calculateExpression("sin(0)"));
                        System.out.print("���������� ����� ");
                        System.out.println(Math.sin(0));
                        System.out.print("��� ����������� ");
                        System.out.println(Calculate.calculateExpression("sqrt(4)"));
                        System.out.print("���������� ������ ");
                        System.out.println(Math.sqrt(4));
                        System.out.println();
                    }
                    catch (Exception expected2)
                    {
                        System.out.println("������ ���������");
                    }
                    break;
            }
        } while (command != 5);
}
}