package com.example.c3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class CalService {
    //检查数字（小数）
    void checkNumber(String str) throws CalException {
        int dotCount = 0;
        for(int i = 0 ; i < str.length(); i++){
            if(str.charAt(i) == '.')
                dotCount++;
        }
        if(dotCount > 1){
            throw new CalException("表达式输入有误！");
        }
    }

    //计算两个数的某种运算
    public void  calculate(Stack<BigDecimal> numberStack , char ch){
        BigDecimal num2 = numberStack.pop();
        BigDecimal num1 = numberStack.pop();
        BigDecimal res = calculate(num1, num2, ch);
        numberStack.push(res);
    }

    //具体计算
    public BigDecimal calculate(BigDecimal num1, BigDecimal num2, char c) throws ArithmeticException{
        BigDecimal res = new BigDecimal("0");
        switch (c){
            case '+': res = num1.add(num2);
                break;
            case '-': res = num1.subtract(num2);
                break;
            case '*': res = num1.multiply(num2);
                break;
            case '/':
                int a=0;
                if(num2.equals(0)){
                    throw (new ArithmeticException());
                }
                res = num1.divide(num2,16 ,BigDecimal.ROUND_HALF_UP);
                break;
        }

        return res;
    }
}