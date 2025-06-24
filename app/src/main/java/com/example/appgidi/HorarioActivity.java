package com.example.appgidi;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HorarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_horario);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutHorario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Activar comportamiento de selección para los botones de día
        setupDayButtons();
    }

    private void setupDayButtons() {
        Button[] dayButtons = {
                findViewById(R.id.btnLunes),
                findViewById(R.id.btnMartes),
                findViewById(R.id.btnMiercoles),
                findViewById(R.id.btnJueves),
                findViewById(R.id.btnViernes)
        };

        for (Button btn : dayButtons) {
            // Estilo base: fondo claro, texto oscuro
            btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.vista_horario)));
            btn.setTextColor(getResources().getColor(R.color.dark_gray_2));

            btn.setOnClickListener(v -> {
                for (Button b : dayButtons) {
                    b.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.vista_horario)));
                    b.setTextColor(getResources().getColor(R.color.dark_gray_2)); // Texto oscuro cuando no está seleccionado
                }

                // Estilo seleccionado
                btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_gray_2)));
                btn.setTextColor(getResources().getColor(R.color.white)); // Texto blanco cuando está seleccionado
            });
        }

        // Establecer por defecto el botón Lunes como seleccionado
        dayButtons[0].performClick();
    }
}
