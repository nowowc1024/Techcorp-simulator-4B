package com.example.techcorp;

public class Operators {

    public static void main(String[] args) {

        // 3.1 Addition
        int sum = 7 + 5;
        double total = 2.5 + 1.5;
        System.out.println("sum = " + sum);
        System.out.println("total = " + total);

        // 3.2 Subtraction
        int difference = 10 - 4;
        double balance = 100.0 - 35.5;
        System.out.println("difference = " + difference);
        System.out.println("balance = " + balance);

        // 3.3 Multiplication
        int product = 3 * 4;
        double scaled = 2.5 * 3;
        System.out.println("product = " + product);
        System.out.println("scaled = " + scaled);

        // 3.4 Division
        int result1 = 7 / 2;
        double result2 = 7.0 / 2;
        System.out.println("result1 (int division) = " + result1);
        System.out.println("result2 (double division) = " + result2);

        // 3.5 Remainder
        int remainder = 7 % 2;
        System.out.println("remainder = " + remainder);

        // 4.1 Unary Plus
        int x1 = +5;
        System.out.println("x1 = " + x1);

        // 4.2 Unary Minus
        int x2 = -5;
        System.out.println("x2 = " + x2);

        // 4.3 Logical Negation
        boolean valid = false;
        boolean negResult = !valid;
        System.out.println("!valid = " + negResult);

        // 4.4 Bitwise Complement
        int x3 = 5;
        int y = ~x3;
        System.out.println("~5 = " + y);

        // 5.1 Prefix vs postfix increment
        int a5 = 5;
        int b5 = ++a5;
        System.out.println("prefix ++a5: a5=" + a5 + ", b5=" + b5);

        int c5 = 5;
        int d5 = c5++;
        System.out.println("postfix c5++: c5=" + c5 + ", d5=" + d5);

        // 5.2 Decrement
        int x5 = 5;
        x5--;
        System.out.println("x5-- = " + x5);

        // 6. Relational Operators
        int a6 = 5;
        System.out.println("a6 == 5: " + (a6 == 5));
        System.out.println("a6 != 3: " + (a6 != 3));
        System.out.println("10 > 3: " + (10 > 3));
        System.out.println("2 < 9: " + (2 < 9));
        int score = 75;
        System.out.println("score >= 50: " + (score >= 50));
        int age = 16;
        System.out.println("age <= 18: " + (age <= 18));

        // 7. Logical Operators
        int cash = 1500;
        int employees = 3;
        boolean finished = false;
        int progress = 80;
        int requiredWork = 100;
        System.out.println("cash>1000 && employees>0: " + ((cash > 1000) && (employees > 0)));
        System.out.println("finished || progress>=requiredWork: " + (finished || (progress >= requiredWork)));
        System.out.println("!finished: " + !finished);

        // 7.4 Non-short-circuit boolean operators
        boolean ba = true & false;
        boolean bb = true | false;
        boolean bc = true ^ false;
        System.out.println("true & false: " + ba);
        System.out.println("true | false: " + bb);
        System.out.println("true ^ false: " + bc);

        // 8. Bitwise Operators
        System.out.println("6 & 3 = " + (6 & 3));
        System.out.println("6 | 3 = " + (6 | 3));
        System.out.println("6 ^ 3 = " + (6 ^ 3));

        // 9. Shift Operators
        System.out.println("3 << 1 = " + (3 << 1));
        System.out.println("8 >> 1 = " + (8 >> 1));
        System.out.println("8 >>> 1 = " + (8 >>> 1));

        // 10. Assignment Operators
        int x10 = 10;
        x10 += 5;  System.out.println("x10 += 5: " + x10);
        x10 -= 2;  System.out.println("x10 -= 2: " + x10);
        x10 *= 3;  System.out.println("x10 *= 3: " + x10);
        x10 /= 2;  System.out.println("x10 /= 2: " + x10);
        x10 %= 3;  System.out.println("x10 %= 3: " + x10);

        // 11. Ternary Operator
        int ta = 7, tb = 9;
        int max = (ta > tb) ? ta : tb;
        System.out.println("max of 7,9: " + max);

        // 12. instanceof
        Object obj = "Hello";
        System.out.println("obj instanceof String: " + (obj instanceof String));

        // 13. Cast Operator
        double castX = 9.7;
        int castY = (int) castX;
        System.out.println("(int) 9.7 = " + castY);

        // 14. Object-Related Operators
        StringBuilder sb = new StringBuilder("Tech");
        sb.append("Corp");
        System.out.println("new + . operator: " + sb.toString());
        int[] numbers = {1, 2, 3};
        int first = numbers[0];
        System.out.println("numbers[0] = " + first);
        int grouped = (2 + 3) * 4;
        System.out.println("(2+3)*4 = " + grouped);
    }
}
