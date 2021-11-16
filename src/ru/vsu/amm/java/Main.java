package ru.vsu.amm.java;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Telkova Yuliana
 */
public class Main {
    /**
     * функция main
     * @param args - аргументы
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("Если захотите выйти, нажмите 'e'\n");
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
                    System.out.println("Ошибка выражения");
            }
            System.out.println();
        } while (!Objects.equals(expression, "e"));
    }
}