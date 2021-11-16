package ru.vsu.amm.java;
import java.util.Scanner;

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
            System.out.println( "1. ������� '1' � ������� �������������� ���������\n" +
                    "2. ������� '2' ��� ������\n");
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
                default: if (command != 2) System.out.println("������ ����� ����");
            }
        } while (command != 2);
}
}