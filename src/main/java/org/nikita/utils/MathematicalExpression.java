package org.nikita.utils;

import java.util.Stack;
/*
I/P - 2 * 3 + 4
O/P - 10
 */
public class MathematicalExpression {

    public int evaluate(char [] expression){
        int result = 0;
        Stack num = new Stack();
        Stack <Character> operator = new Stack();
        int leftside = 0;
        int rightside = 0;
        char curOper = ' ';

        for(int i=0; i < expression.length; i++){
            if(expression[i] == '+' || expression[i] == '-' || expression[i] == '*' || expression[i] == '/') {
                if (!operator.isEmpty() && (isGreater(operator.peek(), expression[i]))) {
                    rightside = Integer.parseInt(num.pop().toString());
                    leftside = Integer.parseInt(num.pop().toString());
                    curOper = operator.pop();
                    result = calculate(leftside, curOper, rightside);
                    num.push(result);
                    operator.push(expression[i]);
                } else {
                    operator.push(expression[i]);
                }
            }else{
                num.push(expression[i]);
            }
        }

        while(!operator.isEmpty()){
            rightside = Integer.parseInt(num.pop().toString());
            leftside = Integer.parseInt(num.pop().toString());
            curOper = operator.pop();
            result = calculate(leftside, curOper, rightside);
            num.push(result);
        }
        return result;
    }

    public int calculate(int left, char operator, int right){
        if(operator == '*'){
            return left * right;
        }else if(operator == '/'){
            return left / right;
        }else if(operator == '+'){
            return left + right;
        }else if(operator == '-'){
            return left - right;
        }else
            return 0;
    }

    public boolean isGreater(Character s1, Character s2){
        if(s1 == '*')
            return true;
        else if(s2 == '*')
            return false;
        else if(s1 == '/')
            return true;
        else if(s2 == '/')
            return false;
        else
            return true;
    }

    public static void main(String [] args){
        MathematicalExpression mathematicalExpression = new MathematicalExpression();
        String expr = "2+3*4-5";
        expr = "2*3+4-5";
        expr = "2/2+4*5";
        expr = "2/2+4*5-10";
        System.out.println(expr + " = " + mathematicalExpression.evaluate(expr.toCharArray()));
    }
}
