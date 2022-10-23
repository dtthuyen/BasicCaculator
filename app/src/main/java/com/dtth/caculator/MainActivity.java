package com.dtth.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_clear, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_del,
            btn_phantram, btn_point, btn_add, btn_minus, btn_muti, btn_divide, btn_equal;
    TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_clear = findViewById(R.id.clear);
        btn0 = findViewById(R.id.zero);
        btn1 = findViewById(R.id.one);
        btn2 = findViewById(R.id.two);
        btn3 = findViewById(R.id.three);
        btn4 = findViewById(R.id.four);
        btn5 = findViewById(R.id.five);
        btn6 = findViewById(R.id.six);
        btn7 = findViewById(R.id.seven);
        btn8 = findViewById(R.id.eight);
        btn9 = findViewById(R.id.nine);
        btn_phantram = findViewById(R.id.phantram);
        btn_del = findViewById(R.id.del);
        btn_point = findViewById(R.id.dot);
        btn_add = findViewById(R.id.add);
        btn_minus = findViewById(R.id.minus);
        btn_muti = findViewById(R.id.multiply);
        btn_divide = findViewById(R.id.divide);
        btn_equal = findViewById(R.id.equal);
        editText = findViewById(R.id.edit);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_phantram.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_muti.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_del.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = editText.getText().toString()+"";
        switch (v.getId()) {
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
                if (str.length()==0) {
                    editText.setText(((Button) v).getText().toString());
                }
                else if (str.charAt(0) == '0' && !str.contains(" ") && !str.contains(".")) {
                    editText.setText(((Button) v).getText());
                } else {
                    editText.setText(str + ((Button) v).getText());
                }

                break;
            case R.id.phantram:
                if (str.charAt(str.length()-1) <= '9' && str.charAt(str.length()-1) >= '0')
                    editText.setText(str + "%");
                else if (str.charAt(str.length()-1) <= '.')
                    editText.setText(str + "0%");
                break;
            case R.id.dot:
                if (str == "0") editText.setText("0" + ".");
                else if (str.charAt(str.length() - 1) == ' ' || str.charAt(str.length() - 1) == '.'
                || str.charAt(str.length() - 1) == '%')
                    editText.setText(str);
                else editText.setText(str + ".");

                break;
            case R.id.add:
            case R.id.minus:
            case R.id.multiply:
            case R.id.divide:
                if (str.charAt((str.length() - 1)) == ' ')
                    editText.setText(str.substring(0, str.length() - 3) + " " + ((Button) v).getText() + " ");
                else editText.setText(str + " " + ((Button) v).getText() + " ");

                break;
            case R.id.del:
                if (str.length() == 1) editText.setText("0");
                else editText.setText(str.substring(0, str.length() - 1));

                break;
            case R.id.clear:
                editText.setText("0");
                break;
            case R.id.equal:
                caculate();

                break;
            default:
                break;
        }
    }

    private void caculate() {
        double result = 0;
        String str = editText.getText().toString();
        if(!str.contains(" ")) {
            editText.setText(str);
        }
        else {
            String str1 = str.substring(0, str.indexOf(" "));
            String op = str.substring(str.indexOf(" ") + 1, str.indexOf(" ") + 2);
            String str2 = str.substring(str.indexOf(" ") + 3).trim();
            if (!str2.equals("")) {
                Double d1, d2;
                if(str1.charAt(str1.length()-1) == '%')  d1 = Double.parseDouble(str1.substring(0,str1.length()-1)) /100;
                else d1 = Double.parseDouble(str1);
                if(str2.charAt(str2.length()-1) == '%')  d2 = Double.parseDouble(str2.substring(0,str2.length()-1)) /100;
                else d2 = Double.parseDouble(str2);
                if (op.equals("+")) result = d1 + d2;
                else if (op.equals("-"))
                    result = d1 - d2;
                else if (op.equals("x"))
                    result = d1 * d2;
                else if (op.equals("÷")) {
                    if (d1 == 0 && d2 != 0) result = 0;
                    else if (d2 == 0) {
                        result = 0;
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    } else result = d1 / d2;
                }

                double temp = result - (int) result;
                if (temp > 0) {
                    String last = String.valueOf(result);
                    editText.setText(last);
                } else {
                    if(result > 999999999999999d){
                        editText.setText("999999999999999d");
                        Toast.makeText(MainActivity.this,"Over the field",Toast.LENGTH_SHORT).show();
                    }else {
                        String last = String.valueOf((int) result);
                        editText.setText(last);
                    }
                }

            }
        }
    }
}