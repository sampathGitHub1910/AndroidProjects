package com.example.uidesign_2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    TextView signUp;
    Button signIn;
    EditText emailEditText, passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signUp = findViewById(R.id.sign_up);
        signIn = findViewById(R.id.btn_sign_in);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                authenticateUser(email, password);
            }

            private void authenticateUser(String email, String password) {
                String url = "https://todo-api-9gll.onrender.com/api/users/login";

                JSONObject postData = new JSONObject();
                try {
                    postData.put("email", email);
                    postData.put("password", password);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData,
                        new Response.Listener<JSONObject>() {
                            /** @noinspection CallToPrintStackTrace*/
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String accessToken = response.getString("access_token");
                                    // Store the access token securely (e.g., in SharedPreferences)
                                    saveAccessToken(accessToken);
                                    Toast.makeText(SignInActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    // Navigate to the profile activity
                                    Intent intent = new Intent(SignInActivity.this, ProfileActivity.class);
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(SignInActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                VolleySingleton.getInstance(this).addToRequestQueue(request);
            }

            private void saveAccessToken(String accessToken) {
                SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
                editor.putString("access_token", accessToken);
                editor.apply();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}