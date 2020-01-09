package com.biswash.hamrobazzar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.biswash.hamrobazzar.Bll.LoginBll;
import com.biswash.hamrobazzar.strictmode.StrictModeClass;

public class logindialogActivity extends AppCompatDialogFragment {
    private EditText etEmail, etPassword;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_login, null);


        builder.setView(view)
                .setTitle("Login")

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        getContext().startActivity(intent);


                    }
                })

                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (TextUtils.isEmpty(etEmail.getText())) {
                            etEmail.setError("Enter email address");
                            etEmail.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(etPassword.getText())) {
                            etPassword.setError("Enter password");
                            etPassword.requestFocus();
                            return;
                        }

                        String email = etEmail.getText().toString();
                        String pwd = etPassword.getText().toString();

                        LoginBll loginBLL = new LoginBll();
                        StrictModeClass.StrictMode();

                        if (loginBLL.checkUser(email, pwd)) {
                           /* SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", etEmail.getText().toString());
                            editor.putString("password", etPassword.getText().toString());
                            editor.commit();*/
                            Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), DashboardActivity.class);
                            getContext().startActivity(intent);

                        } else {
                            Toast.makeText(getActivity(), "Email or password do not match" + email + pwd, Toast.LENGTH_SHORT).show();
                        }


                    }
                })

                .setNeutralButton(" Register here", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), RegisterActivity.class);
                        getContext().startActivity(intent);

                    }
                });


        etEmail = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);

        return builder.create();

    }
}
