package com.example.c3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.channels.AlreadyBoundException;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView input;
    Character dig = '0';
    private boolean flag = false;
    Button zero, one, two, three, four, five, six, seven, eight, nine, close, open, del, sub, add, mul, cls, spot, equator, div;

    private Boolean eq = false;
    private Boolean eq1 = false;
    private Boolean su = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        input = (TextView) findViewById(R.id.input);
        input.setMovementMethod(ScrollingMovementMethod.getInstance());
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        close = (Button) findViewById(R.id.close);
        open = (Button) findViewById(R.id.open);
        del = (Button) findViewById(R.id.del);
        sub = (Button) findViewById(R.id.sub);
        add = (Button) findViewById(R.id.add);
        mul = (Button) findViewById(R.id.mul);
        cls = (Button) findViewById(R.id.cls);
        spot = (Button) findViewById(R.id.point);
        equator = (Button) findViewById(R.id.equator);
        div = (Button) findViewById(R.id.div);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        close.setOnClickListener(this);
        open.setOnClickListener(this);
        del.setOnClickListener(this);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
        mul.setOnClickListener(this);
        cls.setOnClickListener(this);
        spot.setOnClickListener(this);
        equator.setOnClickListener(this);
        div.setOnClickListener(this);
    }

    public Stack<Character> parStack = new Stack<>();

    @Override
    public void onClick(View v) {
        Character dig = '0';
        String textviewContent = input.getText().toString();
        if(textviewContent.equals("错误")){
            textviewContent="";
        }
        switch (v.getId()) {
            case R.id.open:
                su = true;
                eq = true;
                if (eq1) {
                    textviewContent = "";
                    eq1 = false;
                }
                if (textviewContent.length() != 0 && (
                        (textviewContent.charAt(textviewContent.length() - 1) >= '0'
                                && textviewContent.charAt(textviewContent.length() - 1) <= '9')
                                || textviewContent.charAt(textviewContent.length() - 1) == ')')) {
                    parStack.push('(');
                    flag = false;
                    textviewContent += "*(";
                    input.setText(textviewContent);
                    break;
                }
                textviewContent += '(';
                parStack.push('(');
                input.setText(textviewContent);
                flag = false;
                break;
            case R.id.close:
                if (textviewContent.length() != 0 && (textviewContent.charAt(textviewContent.length() - 1) == '(')) {
                    break;
                }
                if (parStack.isEmpty()) {
                    break;
                }
                if (textviewContent.equals("")) {
                    break;
                }
                char previousChar2 = textviewContent.charAt(textviewContent.length() - 1);
                if (previousChar2 == '+' || previousChar2 == '-' || previousChar2 == '*' || previousChar2 == '/') {
                    break;
                }
                parStack.pop();
                input.setText(textviewContent + ')');
                break;
            case R.id.zero:
                su = true;
                if (eq1) {
                    textviewContent = "";
                    eq1 = false;
                }
                eq = true;
                if (textviewContent == "") {
                    textviewContent += "0";
                    input.setText(textviewContent);
                    break;
                }
                if (textviewContent.length() == 1 && textviewContent.charAt(0) == '0') {
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == ')') {
                    textviewContent += "*0";
                    flag = true;
                    break;
                }
                textviewContent += '0';
                input.setText(textviewContent);
                break;
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
                su = true;
                if (eq1) {
                    textviewContent = "";
                    eq1 = false;
                }
                eq = true;
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == ')') {
                    input.setText(textviewContent + ((Button) v).getText());
                    break;
                }
                if (textviewContent.equals("错误")) {
                    textviewContent = "";
                }
                input.setText(textviewContent + ((Button) v).getText());
                flag = true;
                break;
            case R.id.point:
                su = true;
                eq1 = false;
                if (textviewContent.length() > 0 && !dig.isDigit(textviewContent.charAt(textviewContent.length() - 1))) {
                    break;
                }
                if (textviewContent.length() == 0) {
                    textviewContent += "0.";
                } else {
                    int len = textviewContent.length() - 1;
                    while (len >= 0 && dig.isDigit(textviewContent.charAt(len))) {
                        len--;
                    }
                    if (len >= 0 && textviewContent.charAt(len) == '.') {
                        break;
                    } else {
                        textviewContent += '.';
                    }
                }
                input.setText(textviewContent);
                break;
            case R.id.add:
                su = true;
                eq1 = false;
                if (textviewContent == "") {
                    break;
                }
                if (flag == false) {
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '.') {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    textviewContent += "+";
                    flag = false;
                    input.setText(textviewContent);
                    break;
                }
                flag = false;
                textviewContent += "+";
                input.setText(textviewContent);
                break;
            case R.id.div:
                su = true;
                eq1 = false;
                if (textviewContent == "") {
                    break;
                }
                if (flag == false) {
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '.') {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    textviewContent += "/";
                    flag = false;
                    input.setText(textviewContent);
                    break;
                }
                flag = false;
                textviewContent += "/";
                input.setText(textviewContent);
                break;
            case R.id.mul:
                su = true;
                eq1 = false;
                if (textviewContent == "") {
                    break;
                }
                if (flag == false) {
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '.') {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    textviewContent += "*";
                    flag = false;
                    input.setText(textviewContent);
                    break;
                }
                flag = false;
                textviewContent += "*";
                input.setText(textviewContent);
                break;
            case R.id.sub:
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '-') {
                    break;
                }
                eq1 = false;
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '(') {
                    textviewContent += "-";
                    su = true;
                    input.setText(textviewContent);
                    break;
                }
                if (textviewContent.length() != 0 &&
                        ((textviewContent.charAt(textviewContent.length() - 1) == '*')
                                || (textviewContent.charAt(textviewContent.length() - 1) == '/')
                                || textviewContent.charAt(textviewContent.length() - 1) == '+')) {
                    if (su) {
                        su = false;
                        textviewContent += "(-";
                        parStack.push('(');
                        input.setText(textviewContent);
                        break;
                    } else {
                        break;
                    }

                }
                if (textviewContent.length() != 0 && flag == false) {
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '.') {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    textviewContent += "-";
                    flag = false;
                    input.setText(textviewContent);
                    break;
                }

                textviewContent += "-";
                flag = false;
                input.setText(textviewContent);
                break;
            case R.id.del:
                if (textviewContent.length() != 0) {
                    if ((textviewContent.charAt(textviewContent.length() - 1) == '*')
                            || (textviewContent.charAt(textviewContent.length() - 1) == '-')
                            || (textviewContent.charAt(textviewContent.length() - 1) == '/')
                            || (textviewContent.charAt(textviewContent.length() - 1) == '+')) {
                        flag = true;
                    }
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    input.setText(textviewContent);
                }
                break;
            case R.id.cls:
                textviewContent = "";
                while (!parStack.isEmpty()) {
                    parStack.pop();
                }
                flag = false;
                input.setText(textviewContent);
                break;
            case R.id.equator:
                if (su == false) {
                    input.setText("错误");
                    break;
                }
                eq1 = true;
                if (eq == false) {
                    break;
                }
                if (textviewContent == "") {
                    break;
                }
                eq = false;
                if(textviewContent.length()!=0&&textviewContent.charAt(textviewContent.length()-1)=='('){
                    input.setText("错误");
                    break;
                }
                if (textviewContent.length() != 0 &&
                        (textviewContent.charAt(textviewContent.length() - 1) == '*')
                        || (textviewContent.charAt(textviewContent.length() - 1) == '-')
                        || (textviewContent.charAt(textviewContent.length() - 1) == '/')
                        || (textviewContent.charAt(textviewContent.length() - 1) == '+')) {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                }
                while (!parStack.isEmpty()) {
                    textviewContent += ")";
                    parStack.pop();
                }
                if (!textviewContent.equals("")) {
                    try {
                        ExpressionTool expressionTool = new ExpressionTool();
                        Result result = null;
                        result = expressionTool.SuffixesToSuffixes(textviewContent);

                        input.setText((result.getNumberStack()).peek().stripTrailingZeros().toPlainString());
                    } catch (CalException e) {
                        input.setText("错误");
                    } catch (ArithmeticException e) {
                        input.setText("错误");
                    }finally{

                    }
                }
                break;
        }
    }
}


