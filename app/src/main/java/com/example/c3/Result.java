package com.example.c3;

import java.math.BigDecimal;
import java.util.Stack;

public class Result {
    Stack<BigDecimal> numberStack = null;
    String postfixExpression = "";
    public Result(){
        numberStack = new Stack<>();
    }

    public void setPostfixExpression(String postfixExpression) {
        this.postfixExpression = postfixExpression;
    }

    public Stack<BigDecimal> getNumberStack() {
        return numberStack;
    }

    public void setNumberStack(Stack<BigDecimal> numberStack) {
        this.numberStack = numberStack;
    }

    public BigDecimal method(Stack<BigDecimal> numberStack)
    {
        BigDecimal bg=numberStack.peek();
        String string = bg.toString();
        if(string.indexOf('.')!=-1){
            bg.stripTrailingZeros();
        }
        return bg;
    }
}
