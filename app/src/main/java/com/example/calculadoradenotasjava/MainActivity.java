package com.example.calculadoradenotasjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculadoradenotasjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        binding.btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nota1 = binding.Nota1.getText().toString();
                String nota2 = binding.Nota2.getText().toString();
                String nota3 = binding.Nota3.getText().toString();
                String nota4 = binding.Nota4.getText().toString();
                String numeroFaltas = binding.NumeroFaltas.getText().toString();
                TextView resultado = binding.txtResultado;

                if (nota1.isEmpty() || nota2.isEmpty() || nota3.isEmpty() || nota4.isEmpty()
                    || numeroFaltas.isEmpty()){

                    Toast.makeText(MainActivity.this, "Preencha os campos!!", Toast.LENGTH_SHORT).show();

                }else {

                    calculoNotas(nota1, nota2, nota3, nota4, numeroFaltas, resultado);

                }
            }
        });

    }

public void calculoNotas(String nota1,String nota2,String nota3, String nota4,
                         String numeroFaltas, TextView resultado){

    Double n1 = Double.parseDouble(nota1);
    Double n2 = Double.parseDouble(nota2);
    Double n3 = Double.parseDouble(nota3);
    Double n4 = Double.parseDouble(nota4);
    int numDeFaltas = Integer.parseInt(numeroFaltas);

    Double media = (n1 + n2 + n3 + n4) /4;
    String mediaFinal = String.format("%.2f", media);

    if (media >= 6 && numDeFaltas <= 20){

        resultado.setText("Aluno foi Aprovado!! \n" + "Média Final: " + mediaFinal);
        resultado.setTextColor(getColor(R.color.green));

    } else if(media < 6 && numDeFaltas > 20){

        resultado.setText("Aluno reprovou por Nota e Faltas \n" + "Média Final: " + mediaFinal);
        resultado.setTextColor(getColor(R.color.red));

    } else if (media < 6 && numDeFaltas <= 20) {

        resultado.setText("Aluno reprovou por Nota \n" + "Média Final: " + mediaFinal);
        resultado.setTextColor(getColor(R.color.red));

    }else {
        resultado.setText("Aluno reprovou por Faltas \n" + "Média Final: " + mediaFinal);
        resultado.setTextColor(getColor(R.color.red));
    }

}


}