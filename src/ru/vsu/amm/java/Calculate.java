package ru.vsu.amm.java;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.lang.Exception;

/**
 * Выражение может содержать числа, знаки операций, скобки.
 * В случае, если выражение записано корректно, вычисляет
 * значение, в противном случае — выводит сообщение об ошибке.
 * @author  Telkova Yuliana
 */
public class Calculate {

    private Calculate() {}

    /**
     * Список допустимых чисел
     */
    private static List<String> digits = new ArrayList<String>(
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")
    );

    /**
     * Список операций
     */
    private static List<String> simpleFunctions = new ArrayList<String>(
            Arrays.asList("^", "/", "*", "+", "-")
    );

    /**
     * Список функций
     */
    private static List<String> Functions = new ArrayList<String>(
            Arrays.asList("sqrt", "sin", "cos")
    );

    /**
     * Список скобок
     */
    private static List<String> brackets = new ArrayList<String>(
            Arrays.asList("(", ")")
    );


    /**
     * Character.toString() — возвращает строковый объект (String) и представляет указанное char-значение как одну символьную строку
     * @param c - аргумент
     */
    private static String getStringSymbol(char c) {
        return Character.toString(c);
    }

    /**
     * Делает из выражения массив символов
     * @param expression - выражение
     */
    private static ArrayList<String> toStringArray(String expression) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            // метод charAt() возвращает значение char по указанному индексу
            arrayList.add(getStringSymbol(expression.charAt(i)));
        }
        return arrayList;
    }

    /**
     * Основная функция, которая вычисляет значение выражения
     * @param expr - выражение
     * @throws Exception Ошибка ввода выражения
     * @return operands.pop()
     */
    public static double calculateExpression(String expr) throws Exception {

        ArrayList<String> expression = toStringArray("(" + expr + ")");

        // стек для операндов (чисел)
        Stack<Double> operands = new Stack<>();
        // стек для операций
        Stack<String> functions = new Stack<>();

        // Мы будем идти слева на право, добавляя операнды в один стек,
        // а операции в другой. При каждом добавлении новой операции мы
        // будем пытаться вытолкнуть из стека старые, руководствуясь
        // приоритетами операций.
        int pos = 0;
        String token;
        String prevToken = "";
        String number = "";
        String function = "";
        do {
            token = expression.get(pos);

            // унарный минус и плюс
            if ((prevToken.equals("(") && token.equals("-")) || (prevToken.equals("(") && token.equals("+"))) {
                operands.push(0.0);
            }
            //contains - метод, позволяющий проверить, содержит ли string другую подстроку или нет
            if (digits.contains(token)) {
                number += token;
            }
            else if (simpleFunctions.contains(token) || brackets.contains(token)) {
                if (!number.equals("")) {
                    // Double.parseDouble метод, возвращающий новое значение double,
                    // инициализированное значением, представленным указанной строкой
                    operands.push(Double.parseDouble(number));
                    number = "";
                }

                if (token.equals(")")) {
                    // peek() находит значение поля для строки
                    while (functions.size() > 0 && !functions.peek().equals("("))
                    //  pop() удаляет последний элемент из массива и возвращает его значение
                        popFunction(operands, functions);
                    functions.pop();
                }
                else {
                    while (canPop(token, functions))
                        popFunction(operands, functions);
                    // элемент помещается в начало стека
                    functions.push(token);
                }
            }
            else {
                function += token;
                if (Functions.contains(function)) {
                    int functionBracketStart = pos + 1;//начало функциональной скобки
                    int functionBracketEnd = 0;//конец функциональной скобки
                    int bracketsSum = 1;

                    for (int i = functionBracketStart + 1; i < expression.size(); i++) {
                        //представляющий сравнение равенства
                        if (expression.get(i).equals("(")) bracketsSum++;
                        if (expression.get(i).equals(")")) bracketsSum--;
                        if (bracketsSum == 0) {
                            functionBracketEnd = i;
                            pos = functionBracketEnd;
                            break;
                        }
                    }
                    //substring() возвращает подстроку строки между двумя индексами или от одного индекса и до конца строки
                    String functionParameter = expr.substring(functionBracketStart, functionBracketEnd-1);

                    if (function.equals("sqrt")) {
                        if (Integer.parseInt (functionParameter) >= 0) {
                            double res = Math.sqrt(calculateExpression( functionParameter ));
                        operands.add(res);}
                        else throw new Exception();
                    }

                    if (function.equals("sin")) {
                        double res = Math.sin(calculateExpression( functionParameter ));
                        operands.add(res);
                    }

                    if (function.equals("cos")) {
                        double res = Math.cos(calculateExpression( functionParameter ));
                        operands.add(res);
                    }
                } else if (!Character.isLetter(token.charAt(0))) throw new Exception();
            }
            pos++;
            prevToken = token;
        }
        while (token != null && pos + 1 <= expression.size());
        if (operands.size() > 1 || functions.size() > 0)
            throw new Exception();
        return operands.pop();
    }

    /**
     * Функция, которая выталкивает из стека два операнда, выполняет операцию над ними,
     * кладет результат в стек с операндами
     * @param operands - операнды
     * @param functions - выражение
     */
    private static void popFunction(Stack<Double> operands, Stack<String> functions) throws Exception{
        double b = operands.pop();
        double a = operands.pop();
        switch (functions.pop()) {
            case "+" :
                operands.push(a + b);
                break;
            case "-" :
                operands.push(a - b);
                break;
            case "*" :
                operands.push(a * b);
                break;
            case "/" :
                if (b!=0){
                operands.push(a / b);
                break;}
                else throw new Exception();
            case "^":
                operands.push(Math.pow(a, b));
        }
    }

    /**
     * Расставляем приоритет операции
     * @param op1 - операции
     * @param functions - функции
     * @throws Exception Недопустимая операция
     */
    private static boolean canPop(String op1, Stack<String> functions) throws Exception {
        if (functions.size() == 0)
            return false;
        int p1 = getPriotity(op1);
        int p2 = getPriotity(functions.peek());
        return p1 >= 0 && p2 >= 0 && p1 >= p2;
    }

    /**
     * Получаем приоритет операции
     * @param op - операции
     * @throws Exception Недопустимая операция
     */
    private static int getPriotity(String op) throws Exception {
        switch (op) {
            case "(":
                return -1;
            case "^":
                return 0;
            case "*":
                return 1;
            case "/":
                return 1;
            case "+":
                return 2;
            case "-":
                return 2;
            default:
                throw new Exception();
        }
    }
}