# Andriod Studio Introduction Practice - Simple Calculator

# My programming specifications
## Indentation:
Use four spaces for indentation, not tabs.
## Variable Naming:
Use meaningful variable names following the camelCase convention.
Class member variables should start with a lowercase letter and can use prefixes to denote their type, e.g., strName.
Constants should be in all uppercase letters with underscores separating words, e.g., MAX_VALUE.
## Maximum Number of Characters per Line:
Each line of code should ideally not exceed 80-120 characters to improve readability. If it does, consider breaking the line.
## Function and Class Naming:
Class names should start with an uppercase letter and use camelCase, e.g., MyClass.
Method names should start with a lowercase letter and use camelCase, e.g., calculateTotal().
## Blank Line Rule:
Leave blank lines between functions and class definitions to improve code readability.
## Annotation Rules:
Use JavaDoc-style comments to document classes, methods, and members.
Use line comments (//) or block comments (/* ... */) inside methods to explain the purpose or special cases of the code.
## Space Before and After Operator:
Add spaces before and after binary operators, e.g., int sum = a + b;.
## Other Rules:
Avoid using "magic numbers" (unnamed constants); use meaningful constants or enumeration types.
Handle exceptions using try-catch blocks, ensuring that caught exceptions are appropriately handled or logged.
Adhere to SOLID principles and design patterns in Java programming to improve code maintainability and extensibility.


---
> A simple calculator based on Android, refer to the tutorial on Bilibili.
>  [2022 最新 Android 基础教程，从开发入门到项目实战，看它就够了，更新中](https://www.bilibili.com/video/BV19U4y1R7zV/?spm_id_from=333.337.search-card.all.click&vd_source=095be17af1e00a1a80fc1b087af57581)

 
|The Link Your Class  |https://bbs.csdn.net/forums/ssynkqtd-04  |
|--|--|
| The Link of Requirement of This Assignment  |  https://bbs.csdn.net/topics/617332156 |
|The Aim of This Assignment|Android Application - Calculator|
| MU STU ID and FZU STU ID|<21124825-832102119>|
| The Link of Code of this assignment of GitHub|https://github.com/CHENZHEN3078/testgit|


@[TOC](Catalog)

---

# PSP form
| **Personal Software Process Stages**    | Estimated Time（minutes） | Actual Time（minutes） |
| :-------------------------------------- | :------------------------ | :--------------------- |
| Planning                                |                           |                        |
| • Estimate                              |       30                |           60          |
| Development                             |                           |                        |
| • Analysis                              |          10                 |        10                |
| • Design Spec                           |       30                    |          30              |
| • Design Review                         |          10                 |         10              |
| • Coding Standard                       |       20                    |        20                |
| • Design                                |       60                    |      60                  |
| • Coding                                |      300                    |       240                 |
| • Code Review                           |       60                    |        90               |
| • Test                                  |            60               |           60             |
| Reporting                               |                          |                     |
| • Test Repor                            |         60                  |           60             |
| • Size Measurement                      |          20                 |         10               |
| • Postmortem & Process Improvement Plan |          20                 |         60               |
| **Sum**                                 |            680              |       710                 |
---


# Description of problem-solving ideas
The design of calculators is divided into interface and backend logic. I have not studied interface design before, but I can write the backend logic in Java. Based on my own skill range, I chose to use Android to develop calculators. The advantage is that Android development can be done using Java, so during the learning process, I can mainly focus on the implementation of software interfaces.

And I can find tutorials for Android development on platforms such as Bilibili and CSDN. After collecting information, I found Android basic tutorials that combine calculators on Bilibili.
 [2022 最新 Android 基础教程，从开发入门到项目实战，看它就够了，更新中](https://www.bilibili.com/video/BV19U4y1R7zV/?spm_id_from=333.337.search-card.all.click&vd_source=095be17af1e00a1a80fc1b087af57581)

---
# Design and implementation process
Basic requirement: Implement addition, subtraction, multiplication, division, and clear functions.
Advanced requirement: Implement functionality for exponentiation, trigonometric functions, and more.

## Interface Design
Firstly, to design the application interface, create a new layout file(layout_ calculator.xml), set to linear layout.
Place a text box (tv_ result) serves as the calculation interface, and four columns and n rows of buttons (btn_ xxx).
Set the properties of the buttons in sequence, and create colors.xml, dimensions.xml, strings.xml, and themes.xml.
Classify different properties into xml files and implement the calls for buttons, easy to uniformly modify attributes in the future.
 Finally, beautify the interface.
## Code Logic
To complete the backend logic, create a new activity (CaculatorActivity.java) in Java.

In protected void onCreate (Bundle savedInstanceState), obtain a text view (tv_result) from the layout file, and register click listeners for each button control.
Complete the specific calculation logic in public void onClick (View v).

The main logic of the calculator is as follows,
![在这里插入图片描述](https://img-blog.csdnimg.cn/ec4491cc6c974051beaf05245a39200e.png#pic_center)


---
# Code description

## Back-end code

`CaculatorActivity.java`

```java
package com.example.caculator01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/*
bug:8+10*9=18*9=162
bug:4^-1=3
 */
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
        } else if (id == R.id.btn_cancel) {// 点击了加、减、乘、除按钮
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

```

## Front-end code
I only display the most important layout file here.
`activity_caculator.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/bg1"
    android:orientation="vertical"
    android:padding="5dp">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:text="@string/simple_calculator"
                android:textStyle="bold|italic"
                android:textColor="#000000"
                android:background="#156200EE"
                android:textSize="30sp" />

            <TextView
                android:id="@+id/tv_result"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="@color/white"
                android:gravity="right|bottom"
                android:lines="3"
                android:text="0"
                android:textColor="@color/black"
                android:textSize="30sp" />

            <GridLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:columnCount="4"
                android:rowCount="6">

                <Button
                    android:id="@+id/btn_cancel"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/cancel"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />


                <Button
                    android:id="@+id/btn_divide"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/divide"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_multiply"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/multiply"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_clear"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/clear"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />




                <Button
                    android:id="@+id/btn_sin"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/sin"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_cos"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/cos"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_tan"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/tan"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_power"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/power"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />





                <Button
                    android:id="@+id/btn_seven"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/seven"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_eight"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/eight"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_nine"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/nine"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_plus"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/plus"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_four"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/four"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_five"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/five"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_six"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/six"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_minus"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/minus"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_one"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/one"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_two"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/two"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_three"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/three"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/ib_sqrt"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="√"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <!--<ImageButton
                    android:id="@+id/ib_sqrt"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:background="@color/white"
                    android:scaleType="centerInside"
                    android:src="@drawable/sqrt" />-->

                <Button
                    android:id="@+id/btn_reciprocal"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/reciprocal"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_zero"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/zero"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_dot"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/dot"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />

                <Button
                    android:id="@+id/btn_equal"
                    android:background="@color/white"
                    android:layout_width="0dp"
                    android:layout_height="@dimen/button_height"
                    android:layout_columnWeight="1"
                    android:gravity="center"
                    android:text="@string/equal"
                    android:textColor="@color/black"
                    android:textSize="@dimen/button_font_size" />


            </GridLayout>

        </LinearLayout>

    </ScrollView>

</LinearLayout>
```
---
# Result display

## Screenshot

![在这里插入图片描述](https://img-blog.csdnimg.cn/c4a1a6c0e6f241ad8a2c71be2cf50f4a.png)

## Screen recording

[video(video-Tog7ifuN-1696922837726)(type-csdn)(url-https://live.csdn.net/v/embed/333899)(image-https://video-community.csdnimg.cn/vod-84deb4/d62810c0673d71ee80540675b3ed0102/snapshots/fa7bdd9120ef46e49d72b366540bf73d-00003.jpg?auth_key=4850522596-0-0-3b8af57d56c75081d64d0b5507e89ff3)(title-Simple calculator demonstration)]

---
# Summarize

## Remaining issues

1.Unable to perform complex formula calculations, requires step-by-step calculations.
2.The fallback button cannot be used properly (I'm still checking).
3.And the functions are not comprehensive.

## Learning summary

I learned Android development for the first time and I didn't know much about it. I spent a lot of time studying it, and finally followed the tutorial to complete this assignment. 
I have mastered basic Android interface operations through learning and practical operation, and have also honed my Java programming skills through the design and improvement of caculation logic.

Here is also a summary  of the problems and solutions I encountered (which will continue to be updated) while learning Android through the Bilibili tutorial.
[Andriod Studio学习经验分享（更新中）](https://blog.csdn.net/weixin_46396714/article/details/133751341?csdn_share_tail=%7B%22type%22%3A%22blog%22%2C%22rType%22%3A%22article%22%2C%22rId%22%3A%22133751341%22%2C%22source%22%3A%22weixin_46396714%22%7D)

**Thank you for browsing！**
