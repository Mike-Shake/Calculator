package com.example.c3;

import java.math.BigDecimal;
import java.util.Stack;

public class ExpressionTool {
    CalService service = new CalService();
    public Result SuffixesToSuffixes(String expression) throws ArithmeticException, CalException {
        String resExpression = "";
        Stack<Character> stack = new Stack<>();
        Stack<BigDecimal> numberStack = new Stack<>();
        Result result = new Result();

        boolean isMinus = false; //假设不是负数
        for(int i = 0 ; i < expression.length();  ){
            String str1 = "";

            //判断是负数
            if(expression.charAt(i) == '-' && (i + 1) < expression.length() && isNumber(expression.charAt(i + 1))){
                if(i == 0 || isOperator(expression.charAt(i - 1))){
                    isMinus = true;
                    i++;
                    continue;
                }
            }

            //是数字组成部分
            while (i < expression.length() && isNumberOrDot(expression.charAt(i))) {
                str1 = str1 + expression.charAt(i++);
            }

            //数字内容处理
            if(!str1.equals("")){
                resExpression += str1;
                //检查当前数字是否合法
                service.checkNumber(str1);
                //将String转换为对应的BigDecimal对象
                BigDecimal bd = new BigDecimal(str1);
                //转换成负数
                if(isMinus){
                    BigDecimal bd1 = new BigDecimal("-1");
                    bd = bd.multiply(bd1);
                    isMinus = false;
                }
                //为了显式好看，处理double类型尾部是0的情况，直接转换成对应整数
//                if(bd.doubleValue() == bd.intValue()){
//                    bd = new BigDecimal(bd.intValue());
//                }else{
//                    bd = new BigDecimal(bd.doubleValue());
//                }
                //将BigDecimal对象压栈
                numberStack.push(bd);
                //其他字符处理
            }else{
                //1.栈为空，当前元素直接入栈；或者当前字符为"("，直接入栈
                if(stack.isEmpty() || expression.charAt(i) == '('){
                    stack.push(expression.charAt(i));
                    //2.遇到“）”，将栈里内容一一出栈，直到遇到"("
                }else if(expression.charAt(i) == ')'){
                    while (stack.peek() != '('){
                        char c = stack.pop();
                        resExpression += c;
                        service.calculate(numberStack, c);
                    }
                    //“（”出栈
                    stack.pop();
                }else{
                    //3.其他字符的情况，比较当前字符与栈顶字符的优先级，若当前元素的优先级大于栈顶元素的优先级，直接入栈；否则，一直出栈，
                    // 直到符合要求，或者遇到“（”
                    if(operatorPrecedent(expression.charAt(i)) > operatorPrecedent(stack.peek())){
                        stack.push(expression.charAt(i));
                    }else{
                        while(!stack.isEmpty() && stack.peek() != '('
                                && operatorPrecedent(expression.charAt(i)) <= operatorPrecedent(stack.peek())) {
                            char c = stack.pop();
                            resExpression += c;
                            service.calculate(numberStack, c);
                        }
                        stack.push(expression.charAt(i));
                    }
                }
                i++;
            }
        }
        while(!stack.isEmpty()){
            char c = stack.pop();
            resExpression += c ;
            service.calculate(numberStack, c);
        }

        result.setNumberStack(numberStack);
        result.setPostfixExpression(resExpression);
        return result;
    }


    //判断是否是操作符
    static boolean isOperator(char ch){
        if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch =='(')
            return true;
        return false;
    }

    //判断是否是数字
    static boolean isNumber(char c){
        if(c == '0' || c == '1' || c == '2' || c=='3' || c=='4'
                || c=='5' || c=='6' || c=='7' || c=='8' || c=='9'){
            return true;
        }
        return false;
    }

    int operatorPrecedent(char c){
        if(c == '*' || c == '/')
            return 2;
        if(c == '+' || c == '-')
            return 1;
        return -1;
    }

    //是数字或者'.'
    static boolean isNumberOrDot(char c){
        if(isNumber(c) || c == '.'){
            return true;
        }
        return false;
    }
}

