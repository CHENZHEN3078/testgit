package com.example.caculator01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/*
bug:8+10*9=18*9=162
bug:4^-1=3 */
public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    // 第一个操作数
    private String firstNum = "0";
    // 运算符
    private String operator = "";
    // 第二个操作数
    private String secondNum = "";
    // 当前的计算结果
    private String result = "";
    // 显示的文本内容
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        // 从布局文件中获取名叫tv_result的文本视图
        tv_result = findViewById(R.id.tv_result);
        // 下面给每个按钮控件都注册了点击监听器
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this); // “除法”按钮
        findViewById(R.id.btn_multiply).setOnClickListener(this); // “乘法”按钮
        findViewById(R.id.btn_clear).setOnClickListener(this); // “清除”按钮

        findViewById(R.id.btn_sin).setOnClickListener(this);//sin
        findViewById(R.id.btn_cos).setOnClickListener(this); // cos
        findViewById(R.id.btn_tan).setOnClickListener(this); // tan
        findViewById(R.id.btn_power).setOnClickListener(this); // power

        findViewById(R.id.btn_seven).setOnClickListener(this); // 数字7
        findViewById(R.id.btn_eight).setOnClickListener(this); // 数字8
        findViewById(R.id.btn_nine).setOnClickListener(this); // 数字9
        findViewById(R.id.btn_plus).setOnClickListener(this); // “加法”按钮
        findViewById(R.id.btn_four).setOnClickListener(this); // 数字4
        findViewById(R.id.btn_five).setOnClickListener(this); // 数字5
        findViewById(R.id.btn_six).setOnClickListener(this); // 数字6
        findViewById(R.id.btn_minus).setOnClickListener(this); // “减法”按钮
        findViewById(R.id.btn_one).setOnClickListener(this); // 数字1
        findViewById(R.id.btn_two).setOnClickListener(this); // 数字2
        findViewById(R.id.btn_three).setOnClickListener(this); // 数字3
        findViewById(R.id.btn_reciprocal).setOnClickListener(this); // 求倒数按钮
        findViewById(R.id.btn_zero).setOnClickListener(this); // 数字0
        findViewById(R.id.btn_dot).setOnClickListener(this); // “小数点”按钮
        findViewById(R.id.btn_equal).setOnClickListener(this); // “等号”按钮
        findViewById(R.id.ib_sqrt).setOnClickListener(this); // “开平方”按钮
    }

    @Override
    public void onClick(View v) {
        String inputText;
        // 如果是开根号按钮
        if (v.getId() == R.id.ib_sqrt) {
            inputText = "√";
        } else {
            // 除了开根号之外的其他按钮
            inputText = ((TextView) v).getText().toString();
        }
        int id = v.getId();// 点击了清除按钮
        if (id == R.id.btn_clear) {
            clear();
            // 点击了取消按钮
        } else if (id == R.id.btn_cancel) {
            if (secondNum.length() != 0) {
                secondNum = secondNum.substring(0, secondNum.length() - 1);
            } else if (operator.length() != 0) {
                operator = "";
            } else if (firstNum.length() != 0) {
                firstNum = firstNum.substring(0, firstNum.length() - 1);
            } else {}
            refreshText(showText.substring(0, secondNum.length() - 1));

            // 点击了加、减、乘、除按钮
        } else if (id == R.id.btn_plus || id == R.id.btn_minus || id == R.id.btn_multiply || id == R.id.btn_divide) {
            operator = inputText; // 运算符
            refreshText(showText + operator);
            // 点击了等号按钮
        } else if (id == R.id.btn_equal) {// 运算
            double calculate_result = calculate();
            refreshOperate(String.valueOf(calculate_result));
            refreshText(showText + "=" + result);
            // 点击了开根号按钮
        } else if (id == R.id.ib_sqrt) {
            double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
            refreshOperate(String.valueOf(sqrt_result));
            refreshText(showText + "√=" + result);
            // 点击了求倒数按钮
        } else if (id == R.id.btn_reciprocal) {
            double reciprocal_result = 1.0 / Double.parseDouble(firstNum);
            refreshOperate(String.valueOf(reciprocal_result));
            refreshText(showText + "/=" + result);


            //sin
        } else if (id == R.id.btn_sin) {
            operator = inputText; // 运算符
            refreshText(showText + operator);
            //cos
        } else if (id == R.id.btn_cos) {
            operator = inputText; // 运算符
            refreshText(showText + operator);
            //tan
        } else if (id == R.id.btn_tan) {
            operator = inputText; // 运算符
            refreshText(showText + operator);
            //power
        } else if (id == R.id.btn_power) {
            operator = inputText; // 运算符
            refreshText(showText + operator);

            // 点击了其他按钮，包括数字和小数点
        } else {// 上次的运算结果已经出来了
            if (result.length() > 0 && operator.equals("")) {
                clear();
            }

            // 无运算符，则继续拼接第一个操作数
            if (operator.equals("")) {
                firstNum = firstNum + inputText;
            } else {
                // 有运算符，则继续拼接第二个操作数
                secondNum = secondNum + inputText;
            }
            // 整数不需要前面的0
            if (showText.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                refreshText(showText + inputText);
            }
        }

    }

    // 运算返回计算结果
    private double calculate() {
        switch (operator) {
            case "＋":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "－":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            case "÷":
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
            case "sin":
                return Math.sin(Double.parseDouble(secondNum)/180.0*Math.PI);
            case "cos":
                return Math.cos(Double.parseDouble(secondNum)/180.0*Math.PI);
            case "tan":
                return Math.tan(Double.parseDouble(secondNum)/180.0*Math.PI);
            case "^":
                return Math.pow(Double.parseDouble(firstNum),Double.parseDouble(secondNum));
            default:
                return 0;

        }
    }

    // 清空并初始化
    private void clear() {
        refreshOperate("");
        refreshText("");
    }

    // 刷新运算结果
    private void refreshOperate(String new_result) {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    // 刷新文本显示
    private void refreshText(String text) {
        showText = text;
        tv_result.setText(showText);
    }
}
