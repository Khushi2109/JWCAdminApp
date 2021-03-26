package rishabh.example.jwcadminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private TextView tvShow;
    private RelativeLayout loginBtn;

    private String email, pass;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("isLogin", "false").equals("yes")) {
            openDash();
        }

        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        tvShow = findViewById(R.id.txt_show);
        loginBtn = findViewById(R.id.login_btn);

        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userPassword.getInputType() == 144) {
                    userPassword.setInputType(129);
                    tvShow.setText("Show");
                } else {
                    userPassword.setInputType(144);
                    tvShow.setText("Hide");
                }
                userPassword.setSelection(userPassword.getText().length());
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        email = userEmail.getText().toString();
        pass = userPassword.getText().toString();

        if (email.isEmpty()) {
            userEmail.setError("Required");
            userEmail.requestFocus();
        } else if (pass.isEmpty()) {
            userPassword.setError("Required");
            userPassword.requestFocus();
        } else if (email.equals("admin@jwc.edu") && pass.equals("12345")) {
            editor.putString("isLogin", "yes");
            editor.commit();
            openDash();
        } else {
            Toast toast = Toast.makeText(this, "Please check the credentials!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
    }

    private void openDash() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}