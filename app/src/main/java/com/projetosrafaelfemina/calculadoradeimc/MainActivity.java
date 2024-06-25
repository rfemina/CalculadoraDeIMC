package com.projetosrafaelfemina.calculadoradeimc;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.projetosrafaelfemina.calculadoradeimc.databinding.ActivityMainBinding;
import com.projetosrafaelfemina.calculadoradeimc.utils.NumberUtils;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String peso = binding.editWeight.getText().toString();
                String altura = binding.editHight.getText().toString();
                if (peso.isEmpty()) {
                    binding.editWeight.setError("Informe seu Peso!");
                } else if (altura.isEmpty()) {
                    binding.editHight.setError("Informe sua Altura!");
                } else {
                    calculateBMI();
                }
            }
        });
    }

    private void calculateBMI() {

        float weight = Float.parseFloat(binding.editWeight.getText().toString().replace(",", "."));
        float height = Float.parseFloat(binding.editHight.getText().toString().replace(",", "."));
        float bmi = NumberUtils.calculate(weight, height);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        if (bmi < 18.5) {
            binding.txtResult.setText("Seu IMC é de " + decimalFormat.format(bmi) + "\n" + "Peso Baixo");
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            binding.txtResult.setText("Seu IMC é de " + decimalFormat.format(bmi) + "\n" + "Peso Normal");
        } else if (bmi >= 25 && bmi <= 29.9) {
            binding.txtResult.setText("Seu IMC é de " + decimalFormat.format(bmi) + "\n" + "Sobrepeso");
        } else if (bmi >= 30 && bmi <= 34.9) {
            binding.txtResult.setText("Seu IMC é de " + decimalFormat.format(bmi) + "\n" + "Obesidade (Grau I)");
        } else if (bmi >= 35 && bmi <= 39.9) {
            binding.txtResult.setText("Seu IMC é de " + decimalFormat.format(bmi) + "\n" + "Obesidade Severa (Grau II)");
        } else {
            binding.txtResult.setText("Seu IMC é de " + decimalFormat.format(bmi) + "\n" + "Obesidade Mórbida (Grau III)");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();

        if (itemID == R.id.ic_clear) {
            binding.editWeight.setText("");
            binding.editHight.setText("");
            binding.txtResult.setText("");
        }

        return super.onOptionsItemSelected(item);
    }

}