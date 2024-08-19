package com.edu.utn.frgp.tpn1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio1 extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ejercicio1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText1 = findViewById(R.id.txt_n1);
        editText2 = findViewById(R.id.txt_n2);
        tvResultado = findViewById(R.id.tv_resultado);
        tvResultado.setVisibility(View.GONE);
    }

    public void realizarSuma(View view) {
        String texto1 = editText1.getText().toString();
        String texto2 = editText2.getText().toString();

        if (texto1.isEmpty() || texto2.isEmpty()) {
            tvResultado.setVisibility(View.VISIBLE);
            tvResultado.setText("Debe ingresar ambos números");
        } else {
            try {
                Double n1 = Double.parseDouble(texto1);
                Double n2 = Double.parseDouble(texto2);
                Double resultado = n1 + n2;

                tvResultado.setVisibility(View.VISIBLE);
                if (resultado == resultado.intValue()) {
                    tvResultado.setText("Resultado: " + String.valueOf(resultado.intValue()));
                } else {
                    tvResultado.setText("Resultado: " + String.valueOf(resultado));
                }

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor ingresa solo números.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public double sumar(double n1, double n2) {
        return n1 + n2;
    }
}