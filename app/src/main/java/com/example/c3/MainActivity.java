package com.example.c3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView input;
    Character dig = '0';
    Boolean opened = false;
    Boolean add_min = false;
    private boolean flag=false;
    Button zero, one, two, three, four, five, six, seven, eight, nine, close, open, del, sub, add, mul, cls, spot, equator, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        input = (TextView) findViewById(R.id.input);
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
        switch (v.getId()) {
            case R.id.open: {
                if (textviewContent.equals("")) {
                    parStack.push('(');
                    input.setText(textviewContent + "(");
                    flag=false;
                    opened = true;
                    break;
                }
                char previousChar = textviewContent.charAt(textviewContent.length() - 1);
                if (previousChar == '+' || previousChar == '-' || previousChar == '*' || previousChar == '/') {
                    parStack.push('(');
                    input.setText(textviewContent + "(");
                    opened = true;
                }
            }
            break;
            case R.id.close: {
                if (textviewContent.equals("")) {
                    break;
                }
                char previousChar2 = textviewContent.charAt(textviewContent.length() - 1);
                if (previousChar2 == '+' || previousChar2 == '-' || previousChar2 == '*' || previousChar2 == '/') {
                    break;
                }
                parStack.pop();
                input.setText(textviewContent + ')');
            }
            break;
            case R.id.zero:
                if (textviewContent.length() == 1 && textviewContent.charAt(0) == '0') {
                    input.setText(textviewContent);
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == ')') {
                    textviewContent+="*0";
                    flag=true;
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
                input.setText(textviewContent + ((Button) v).getText());
                flag=true;
                break;
            case R.id.point:
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
                if(flag==false){
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '.') {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    textviewContent += "+";
                    flag=false;
                    input.setText(textviewContent);
                    break;
                }
                flag=false;
                textviewContent += "+";
                input.setText(textviewContent);
                break;
            case R.id.div:
                if(flag==false){
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '.') {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    textviewContent +=  "/";
                    flag=false;
                    input.setText(textviewContent);
                    break;
                }
                flag=false;
                textviewContent += "/";
                input.setText(textviewContent);
                break;
            case R.id.mul:
                if(flag==false){
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '.') {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    textviewContent += "*";
                    flag=false;
                    input.setText(textviewContent);
                    break;
                }
                flag=false;
                textviewContent += "*";
                input.setText(textviewContent);
                break;
            case R.id.sub:
                if(textviewContent.length()!=0&&flag==false){
                    break;
                }
                if (textviewContent.length() != 0 && textviewContent.charAt(textviewContent.length() - 1) == '.') {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    textviewContent += "-";
                    flag=false;
                    input.setText(textviewContent);
                    break;
                }
                textviewContent += "-";
                flag=false;
                input.setText(textviewContent);
                break;
            case R.id.del:
                if (!textviewContent.equals("")) {
                    textviewContent = textviewContent.substring(0, textviewContent.length() - 1);
                    input.setText(textviewContent);
                }
                break;
            case R.id.cls:
                textviewContent = "";
                input.setText("");
                break;
            case R.id.equator:
                if (parStack.isEmpty()) {
                    if (!textviewContent.equals("")) {
                        try {
                            ExpressionTool expressionTool = new ExpressionTool();
                            Result result = null;
                            result = expressionTool.SuffixesToSuffixes(textviewContent);
                            input.setText(String.valueOf((result.getNumberStack()).peek()));
                        } catch (CalException e) {
                            input.setText("错误");
                            break;
                        }
                    }
                } else {
                    input.setText("错误");
                }
                break;
        }
    }
}


