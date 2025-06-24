package com.example.appgidi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appgidi.HorarioActivity;
import com.example.appgidi.R;
import com.example.appgidi.models.User;
import com.example.appgidi.models.LoginResponse;
import com.example.appgidi.network.ApiClient;
import com.example.appgidi.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);

        apiService = ApiClient.getClient().create(ApiService.class);

        loginButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        User user = new User(email, password, "mobile");

        apiService.loginUser(user).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null &&
                        "success".equals(response.body().getStatus())) {

                    // Guardar token JWT
                    SharedPreferences prefs = getSharedPreferences("AppGidiPrefs", MODE_PRIVATE);
                    prefs.edit().putString("jwt_token", response.body().getData().getToken()).apply();

                    // Ir a siguiente pantalla
                    Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HorarioActivity.class));
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
