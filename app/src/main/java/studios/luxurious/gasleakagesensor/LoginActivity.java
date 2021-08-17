package studios.luxurious.gasleakagesensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;

import java.util.Objects;

import studios.luxurious.gasleakagesensor.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();


        loginBinding.cardViewProgress.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();

        loginBinding.textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        loginBinding.textViewForgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgotActivity.class));
            }
        });

        loginBinding.outlinedButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = loginBinding.editTextEmailLogin.getText().toString();
                String password = loginBinding.editTextPassword.getText().toString();

                if (email.isEmpty()){
                    loginBinding.filledTextField.setError("Email Required");
                }
                else if (password.isEmpty()){
                    loginBinding.filledTextFieldpassword.setError("Password Required");
                }
                else {
                    loginUser(email, password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        loginBinding.cardViewProgress.setVisibility(View.VISIBLE);
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                loginBinding.cardViewProgress.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login failed! Check if Credentials are Correct", Toast.LENGTH_LONG).show();
                    //email.length(0);
                    loginBinding.cardViewProgress.setVisibility(View.GONE);
                }
            }
        });

        //Toast.makeText(getApplicationContext(), "Implemented Soon", Toast.LENGTH_LONG).show();
    }
}