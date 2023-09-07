package stack;

import java.util.HashMap;

public class LinkedListStack<T> {

    public static void main(String[] args) {
//        LinkedListStack<Integer> stack = new LinkedListStack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//
//        System.out.println(stack);
//        System.out.println(stack.pop());
//        System.out.println(stack.top());
//        System.out.println(stack);
//        System.out.println(stack.peek(0));
//
//
//        System.out.println(isBalancedParenthesis("()"));
//        System.out.println(isBalancedParenthesis("()[]{}"));
//        System.out.println(isBalancedParenthesis("(]"));
//        System.out.println(isBalancedParenthesis("([)]"));
//        System.out.println(isBalancedParenthesis("{[]}"));

        System.out.println(infixToPostfix("((a+b)*c)-d^e^f"));
        System.out.println(evaluateDigitsExpr("((2+3)*5)-6^2"));

    }

    public static boolean isBalancedParenthesis(String str) {
        LinkedListStack<Character> stack = new LinkedListStack<>();

        HashMap<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');


        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if (pairs.get(c) != top) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static int outsidePrecedence(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 3;
            case '^' -> 6;
            case '(' -> 7;
            case ')' -> 0;
            default -> -1;
        };
    }

    private static int insidePrecedence(char ch) {
        return switch (ch) {
            case '+', '-' -> 2;
            case '*', '/' -> 4;
            case '^' -> 5;
            case '(' -> 0;
            case ')' -> 7;
            default -> -1;
        };
    }


    public static String infixToPostfix(String str) {
        char[] chars = str.toCharArray();
        StringBuilder postfixExpr = new StringBuilder();
        LinkedListStack<Character> operators = new LinkedListStack<>();
        int i = 0;

        while (i < chars.length) {
            char ch = chars[i];
            if (Character.isLetterOrDigit(ch)) {
                postfixExpr.append(ch);
                i++;
                continue;
            }

            if (operators.isEmpty() || outsidePrecedence(ch) > insidePrecedence(operators.top())) {
                operators.push(ch);
                i++;
                continue;
            }

            if (outsidePrecedence(ch) == insidePrecedence(operators.top())) {
                operators.pop();
                i++;
                continue;
            }

            postfixExpr.append(operators.pop());
        }

        while (!operators.isEmpty()) {
            postfixExpr.append(operators.pop());
        }

        return postfixExpr.toString();
    }


    public static String evaluateDigitsExpr(String infixExpr) {
        String postfixExpr = infixToPostfix(infixExpr);
        char[] chars = postfixExpr.toCharArray();
        LinkedListStack<Double> operands = new LinkedListStack<>();
        int i = 0;

        while (i < chars.length) {
            char ch = chars[i];

            if (Character.isDigit(ch)) {
                operands.push((double) (ch - '0'));
                i++;
                continue;
            }

            double operand2 = operands.pop();
            double operand1 = operands.pop();

            operands.push(calculateExpr(ch, operand1, operand2));

            i++;
        }

        return String.valueOf(operands.pop());
    }

    private static double calculateExpr(char operator, double operand1, double operand2) {
        return switch (operator) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> operand1 / operand2;
            case '^' -> Math.pow(operand1, operand2);
            default -> 0;
        };
    }


    private Node<T> top;
    private int size;

    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    public void push(T value) {
        this.top = new Node<T>(value, this.top);
        this.size++;
    }

    public T pop() {
        this.validateIsEmpty();

        T poppedData = this.top.data;
        this.top = this.top.next;
        this.size--;

        return poppedData;
    }

    public T top() {
        this.validateIsEmpty();
        return this.top.data;
    }

    public T peek(int position) {
        this.validateIsEmpty();
        this.validatePosition(position);

        Node<T> ptr = this.top;

        for (int i = 0; i < this.size - position - 1; i++) {
            ptr = ptr.next;
        }

        return ptr.data;
    }

    private void validateIsEmpty() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
    }

    private void validatePosition(int position) {
        if (position < 0 || position >= this.size) {
            throw new RuntimeException("Position is out of range!");
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    private class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        Node<T> ptr = this.top;
        StringBuilder sb = new StringBuilder();

        while (ptr != null) {
            sb.append(ptr.data);
            sb.append(" ");
            ptr = ptr.next;
        }

        return sb.toString();
    }
}
