package ru.vsu.amm.java;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Telkova Yuliana
 */
public class Main {
    /**
     * ������� main
     * @param args - ���������
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("���� �������� �����, ������� 'e'\n");
        var expression = "";
        do{
            try
            {
                Scanner inn = new Scanner(System.in);
                expression = inn.nextLine();
                if (!expression.equals("e")){
                System.out.println(Calculate.calculateExpression(expression));}
            }
            catch (Exception expected)
            {
                    System.out.println("������ ���������");
            }
            System.out.println();
        } while (!Objects.equals(expression, "e"));
    }
}