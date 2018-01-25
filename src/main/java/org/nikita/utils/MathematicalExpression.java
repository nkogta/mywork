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
        Stack tempNum = new Stack();
        Stack <Character> tempOper = new Stack();

        for(int i=0; i < expression.length; i++){
            if(expression[i] == '+' || expression[i] == '-' || expression[i] == '*' || expression[i] == '/') {
                operator.push(expression[i]);
            }else{
                num.push(expression[i]);
            }
        }

        while(!num.isEmpty() || !operator.isEmpty()){
            tempNum.push(num.pop());
            tempOper.push(operator.pop());
            if(!operator.isEmpty()  && !tempOper.isEmpty()){
            while(isGreater(operator.peek(), tempOper.peek())) {
                tempNum.push(num.pop());
                tempOper.push(operator.pop());
                if(num.isEmpty() || operator.isEmpty())
                    break;
            }}
            result = calculate((Integer.parseInt(num.pop().toString())), tempOper.pop(), (Integer.parseInt(tempNum.pop().toString())));
            num.push(result);

            while(num.isEmpty() || operator.isEmpty()){
              if(!tempNum.isEmpty() && !tempOper.isEmpty()) {
                  result = calculate(Integer.parseInt(String.valueOf(num.pop())), tempOper.pop(), Integer.parseInt(String.valueOf(tempNum.pop())));
                  num.push(result);
              }else{
                  result = Integer.parseInt(num.pop().toString());
                  break;
              }

            }
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
//        System.out.println(expr + " = " + mathematicalExpression.evaluate(expr.toCharArray()));
//        expr = "2*3+4-5";
//        System.out.println(expr + " = " + mathematicalExpression.evaluate(expr.toCharArray()));
//        expr = "2/2+4*5";
//        System.out.println(expr + " = " + mathematicalExpression.evaluate(expr.toCharArray()));
        expr = "2/2+4*5-10";
        System.out.println(expr + " = " + mathematicalExpression.evaluate(expr.toCharArray()));
    }
}
