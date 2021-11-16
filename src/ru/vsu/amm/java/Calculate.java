package ru.vsu.amm.java;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.lang.Exception;

/**
 * ��������� ����� ��������� �����, ����� ��������, ������.
 * � ������, ���� ��������� �������� ���������, ���������
 * ��������, � ��������� ������ � ������� ��������� �� ������.
 * @author  Telkova Yuliana
 */
public class Calculate {

    /**
     * ������ ���������� �����
     */
    private static List<String> digits = new ArrayList<>(
            Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")
    );

    /**
     * ������ ��������
     */
    private static List<String> simpleFunctions = new ArrayList<>(
            Arrays.asList("^", "/", "*", "+", "-")
    );

    /**
     * ������ �������
     */
    private static List<String> Functions = new ArrayList<>(
            Arrays.asList("sqrt", "sin", "cos")
    );

    /**
     * ������ ������
     */
    private static List<String> brackets = new ArrayList<>(
            Arrays.asList("(", ")")
    );


    /**
     * Character.toString() � ���������� ��������� ������ (String) � ������������ ��������� char-�������� ��� ���� ���������� ������
     * @param c - ��������
     */
    private static String getStringSymbol(char c) {
        return Character.toString(c);
    }

    /**
     * ������ �� ��������� ������ ��������
     * @param expression - ���������
     */
    private static ArrayList<String> toStringArray(String expression) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            // ����� charAt() ���������� �������� char �� ���������� �������
            arrayList.add(getStringSymbol(expression.charAt(i)));
        }
        return arrayList;
    }

    /**
     * �������� �������, ������� ��������� �������� ���������
     * @param expr - ���������
     * @return operands.pop()
     */
    public static double calculateExpression(String expr) {

        ArrayList<String> expression = toStringArray("(" + expr + ")");

        // ���� ��� ��������� (�����)
        Stack<Double> operands = new Stack<>();
        // ���� ��� ��������
        Stack<String> functions = new Stack<>();

        // �� ����� ���� ����� �� �����, �������� �������� � ���� ����,
        // � �������� � ������. ��� ������ ���������� ����� �������� ��
        // ����� �������� ���������� �� ����� ������, ��������������
        // ������������ ��������.
        int pos = 0;
        String token;
        String prevToken = "";
        String number = "";
        String function = "";
        do {
            token = expression.get(pos);

            // ������� ����� � ����
            if ((prevToken.equals("(") && token.equals("-")) || (prevToken.equals("(") && token.equals("+"))) {
                operands.push(0.0);
            }
            //contains - �����, ����������� ���������, �������� �� string ������ ��������� ��� ���
            if (digits.contains(token)) {
                number += token;
            }
            else if (simpleFunctions.contains(token) || brackets.contains(token)) {
                if (!number.equals("")) {
                    // Double.parseDouble �����, ������������ ����� �������� double,
                    // ������������������ ���������, �������������� ��������� �������
                    operands.push(Double.parseDouble(number));
                    number = "";
                }

                if (token.equals(")")) {
                    // peek() ������� �������� ���� ��� ������
                    while (functions.size() > 0 && !functions.peek().equals("("))
                    //  pop() ������� ��������� ������� �� ������� � ���������� ��� ��������
                        popFunction(operands, functions);
                    functions.pop();
                }
                else {
                    while (canPop(token, functions))
                        popFunction(operands, functions);
                    // ������� ���������� � ������ �����
                    functions.push(token);
                }
            }
            else {
                function += token;
                if (Functions.contains(function)) {
                    int functionBracketStart = pos + 1;//������ �������������� ������
                    int functionBracketEnd = 0;//����� �������������� ������
                    int bracketsSum = 1;

                    for (int i = functionBracketStart + 1; i < expression.size(); i++) {
                        //�������������� ��������� ���������
                        if (expression.get(i).equals("(")) bracketsSum++;
                        if (expression.get(i).equals(")")) bracketsSum--;
                        if (bracketsSum == 0) {
                            functionBracketEnd = i;
                            pos = functionBracketEnd;
                            break;
                        }
                    }
                    //substring() ���������� ��������� ������ ����� ����� ��������� ��� �� ������ ������� � �� ����� ������
                    String functionParameter = expr.substring(functionBracketStart, functionBracketEnd-1);

                    if (function.equals("sqrt")) {
                        if (Integer.parseInt (functionParameter) >= 0) {
                            double res = Math.sqrt(calculateExpression( functionParameter ));
                        operands.add(res);}
                        else throw new ArithmeticException();
                    }

                    if (function.equals("sin")) {
                        double res = Math.sin(calculateExpression( functionParameter ));
                        operands.add(res);
                    }

                    if (function.equals("cos")) {
                        double res = Math.cos(calculateExpression( functionParameter ));
                        operands.add(res);
                    }
                } else if (!Character.isLetter(token.charAt(0))) throw new ArithmeticException();
            }
            pos++;
            prevToken = token;
        }
        while (token != null && pos + 1 <= expression.size());
        if (operands.size() > 1 || functions.size() > 0)
            throw new ArithmeticException();
        return operands.pop();
    }

    /**
     * �������, ������� ����������� �� ����� ��� ��������, ��������� �������� ��� ����,
     * ������ ��������� � ���� � ����������
     * @param operands - ��������
     * @param functions - ���������
     */
    private static void popFunction(Stack<Double> operands, Stack<String> functions) {
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
                else throw new ArithmeticException();
            case "^":
                operands.push(Math.pow(a, b));
        }
    }

    /**
     * ����������� ��������� ��������
     * @param op1 - ��������
     * @param functions - �������
     */
    private static boolean canPop(String op1, Stack<String> functions) {
        if (functions.size() == 0)
            return false;
        int p1 = getPriotity(op1);
        int p2 = getPriotity(functions.peek());
        return p1 >= 0 && p2 >= 0 && p1 >= p2;
    }

    /**
     * �������� ��������� ��������
     * @param op - ��������
     */
    private static int getPriotity(String op) {
        return switch (op) {
            case "(" -> -1;
            case "^" -> 0;
            case "*", "/" -> 1;
            case "+", "-" -> 2;
            default -> throw new ArithmeticException();
        };
    }
}