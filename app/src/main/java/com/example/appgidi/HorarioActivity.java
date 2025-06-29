package com.example.appgidi;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        setupBottomNav(R.id.nav_horarios);

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

    private void setupBottomNav(int selectedItemId) {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(selectedItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == selectedItemId) return true;

            if (id == R.id.nav_horarios) {
                startActivity(new Intent(this, HorarioActivity.class));
            } else if (id == R.id.nav_asistencias) {
                startActivity(new Intent(this, AsistenciaActivity.class));
            } else if (id == R.id.nav_calificaciones) {
                startActivity(new Intent(this, CalificacionesActivity.class));
            }

            finish(); // Finaliza la actual para evitar stack de Activities
            return true;
        });
    }



}
