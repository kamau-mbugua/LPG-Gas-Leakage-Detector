package studios.luxurious.gasleakagesensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;

import java.util.Objects;

import studios.luxurious.gasleakagesensor.databinding.ActivityForgotBinding;

public class ForgotActivity extends AppCompatActivity {


    ActivityForgotBinding forgortBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        forgortBinding = ActivityForgotBinding.inflate(getLayoutInflater());
        View view = forgortBinding.getRoot();
        setContentView(view);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        forgortBinding.cardViewProgress.setVisibility(View.GONE);
        forgortBinding.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        forgortBinding.outlinedButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgortBinding.cardViewProgress.setVisibility(View.GONE);

                String resetEmail =forgortBinding.editTextEmailReset.getText().toString();

                if (resetEmail.isEmpty()){
                    forgortBinding.filledTextField.setError("Email is Required to Reset Password");
                }
                else {
                    resetPassword(resetEmail);
                }

            }
        });
    }

    private void resetPassword(String resetEmail) {
        forgortBinding.cardViewProgress.setVisibility(View.VISIBLE);
        //navController.navigate(R.id.action_forgotPasswordFragment_to_loginFragment);
        FirebaseAuth.getInstance().sendPasswordResetEmail(resetEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                forgortBinding.cardViewProgress.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Success, Check your Email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed to Reset Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
               /* .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });*/
    }
}