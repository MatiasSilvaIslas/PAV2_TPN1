package com.edu.utn.frgp.tpn1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static java.lang.Double.NaN;

public class Ejercicio2 extends AppCompatActivity {
    private Button cero, one, two, three, four, five, six, seven, eight, nine;
    private Button add, sub, mul, div, equal, borrar;
    private TextView info, result;
    private double val1 = NaN;
    private double val2=0;
    private char accion;
    private boolean isCalculating = false;
    private boolean isResultDisplayed = false;
    private boolean negativo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ejercicio2ej), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupUIViews();
        setupButtonListeners();
    }

    private void setupUIViews() {
        one = findViewById(R.id.btn1);
        cero = findViewById(R.id.btn0);
        two = findViewById(R.id.btn2);
        three = findViewById(R.id.btn3);
        four = findViewById(R.id.btn4);
        five = findViewById(R.id.btn5);
        six = findViewById(R.id.btn6);
        seven = findViewById(R.id.btn7);
        eight = findViewById(R.id.btn8);
        nine = findViewById(R.id.btn9);
        add = findViewById(R.id.btnadd);
        sub = findViewById(R.id.btnsub);
        mul = findViewById(R.id.btnmul);
        div = findViewById(R.id.btndivide);
        equal = findViewById(R.id.btnequal);
        info = findViewById(R.id.tvControl);
        result = findViewById(R.id.tvResult);
        borrar = findViewById(R.id.btnborrar);
    }

    private void setupButtonListeners() {
        cero.setOnClickListener(v -> handleNumberInput("0"));
        one.setOnClickListener(v -> handleNumberInput("1"));
        two.setOnClickListener(v -> handleNumberInput("2"));
        three.setOnClickListener(v -> handleNumberInput("3"));
        four.setOnClickListener(v -> handleNumberInput("4"));
        five.setOnClickListener(v -> handleNumberInput("5"));
        six.setOnClickListener(v -> handleNumberInput("6"));
        seven.setOnClickListener(v -> handleNumberInput("7"));
        eight.setOnClickListener(v -> handleNumberInput("8"));
        nine.setOnClickListener(v -> handleNumberInput("9"));

        add.setOnClickListener(v -> handleOperatorInput('+'));
        sub.setOnClickListener(v -> handleOperatorInput('-'));
        mul.setOnClickListener(v -> handleOperatorInput('*'));
        div.setOnClickListener(v -> handleOperatorInput('/'));

        equal.setOnClickListener(v -> {
            if (!isResultDisplayed) {
                calculateResult();
            } else {
                Toast.makeText(Ejercicio2.this, "El resultado ya se ha mostrado. Ingrese una nueva operación.", Toast.LENGTH_SHORT).show();
            }
        });

        borrar.setOnClickListener(v -> {
           /* if (info.getText().length() > 0) {
                CharSequence name = info.getText().toString();
                info.setText(name.subSequence(0, name.length() - 1));
            } else { */
                resetCalculator();
           // }
        });
    }

    private void handleNumberInput(String number) {
        if (isResultDisplayed) {
            resetCalculator();
        }
        if (info.getText().toString().equals("0")) {
            info.setText(number);
        } else {

            info.setText(info.getText().toString() + number);
        }
    }

    private void handleOperatorInput(char operation) {
        if(Double.isNaN(val1)&& operation=='-' && !negativo && info.getText().toString().isEmpty()){
            negativo=true;
            info.setText("-");
        }else{
        if (isResultDisplayed) {
            Toast.makeText(Ejercicio2.this, "El resultado ya se ha mostrado. Ingrese una nueva operación.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!info.getText().toString().isEmpty()) {
            if (isCalculating) {
                calculateResult();
            } else {
                val1 = Double.parseDouble(info.getText().toString());
                isCalculating = true;
            }
            accion = operation;
            updateResult(String.valueOf(operation));
            info.setText("0");
        }
        }
    }

    private void calculateResult() {
        if (!info.getText().toString().isEmpty()) {
            val2 = Double.parseDouble(info.getText().toString());
            if (accion == '/' && val2 == 0) {
                Toast.makeText(Ejercicio2.this, "Error: División por 0 no permitida", Toast.LENGTH_LONG).show();
                resetCalculator();
                return;
            }



            switch (accion) {
                case '+':
                    val1 += val2;
                    break;
                case '-':
                    val1 -= val2;
                    break;
                case '*':
                    val1 *= val2;
                    break;
                case '/':
                    val1 /= val2;
                    break;
            }
            if (Double.isNaN(val1)){
                val1=val2;

            }
            String expression = result.getText().toString() + formatResult(val2) + "=";
            result.setText(expression);
            info.setText(formatResult(val1));
            isResultDisplayed = true;
            isCalculating = false;
            val1 = NaN;
            negativo= false;

        } else {
            Toast.makeText(Ejercicio2.this, "Por favor ingrese un valor antes de calcular.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateResult(String operation) {
        String currentResult = result.getText().toString();
        if (currentResult.isEmpty()) {
            result.setText(formatResult(val1) + operation);
        } else {
            if (currentResult.matches(".*[\\+\\-\\*\\/]$")) {
                currentResult = currentResult.substring(0, currentResult.length() - 1);
            }
            result.setText(currentResult + operation);
        }
    }

    private void resetCalculator() {
        val1 = NaN;
        val2 = 0;
        negativo= false;
        result.setText("");
        info.setText("");
        isCalculating = false;
        isResultDisplayed = false;
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%s", result);
        }
    }




}
