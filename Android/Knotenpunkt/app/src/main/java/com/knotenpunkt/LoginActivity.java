package com.knotenpunkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hideNavigation();
        checkConnection();
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.white));
        autoLogin();
    }

    private void autoLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String emailDetail = sharedPreferences.getString("email", "");
        String passwordDetail = sharedPreferences.getString("password", "");
        if (!emailDetail.equals("") && !passwordDetail.equals("")) {
            finish();
        }
    }

    // geeksforgeeks.org/how-to-post-data-to-api-using-volley-in-android/
    public void clickLogin(View v) {
        Intent intent = getIntent();
        EditText inputEmailLogin = findViewById(R.id.EmailLoginText);
        String emailLogin = inputEmailLogin.getText().toString();
        EditText inputPasswordLogin = findViewById(R.id.PasswordLoginText);
        String passwordLogin = inputPasswordLogin.getText().toString();
        String Url = ("https://script.google.com/macros/s/AKfycbw9O-c05FMgYDmEbkBQwrc4gFABN_9qrbia-Imp8FOOlqHUNrRXKTuzFLpydsfZlauJYA/exec?&action=login&email=" + emailLogin + "&password=" + passwordLogin);
        if (emailLogin.equals("") && passwordLogin.equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                    .setIcon(R.drawable.ic_error)
                    .setTitle("Fehler")
                    .setMessage("Gib deine E-Mail-Adresse und dein Passwort ein.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what would happen when positive button is clicked
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        }
        if (emailLogin.equals("") && !passwordLogin.equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                    .setIcon(R.drawable.ic_error)
                    .setTitle("Fehler")
                    .setMessage("Gib deine E-Mail-Adresse ein.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what would happen when positive button is clicked
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        }
        if (!emailLogin.equals("") && passwordLogin.equals("")) {
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                    .setIcon(R.drawable.ic_error)
                    .setTitle("Fehler")
                    .setMessage("Gib dein Passwort ein.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what would happen when positive button is clicked
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        }
        if (!emailLogin.equals("") && !passwordLogin.equals("")) {
            if (isValidEmailId(emailLogin.toString().trim())) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("itemsUser");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                email = jsonObject.getString("Email");
                                password = jsonObject.getString("Password");
                                firstname = jsonObject.getString("Firstname");
                                lastname = jsonObject.getString("Lastname");
                            }
                            if (email.equals(emailLogin) && password.equals(passwordLogin)) {
                                try {
                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("email", emailLogin);
                                    editor.putString("password", password);
                                    editor.apply();
                                    Thread.sleep(3000);
                                    finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                                .setIcon(R.drawable.ic_error)
                                .setTitle("Fehler")
                                .setMessage("Deine Zugangsdaten sind leider unvollständig oder fehlerhaft, bitte überprüfe deine Angaben.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //set what would happen when positive button is clicked
                                        dialogInterface.cancel();
                                    }
                                })
                                .show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(jsonObjectRequest);
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                        .setIcon(R.drawable.ic_error)
                        .setTitle("Fehler")
                        .setMessage("Deine E-Mail-Adresse ist ungültig.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        }
    }

    public void clickLoginBack(View v) {
        finish();
    }

    // tackoverflow.com/questions/12947620/email-address-validation-in-android-on-edittext
    private boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigation();
        checkConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // call method
        checkConnection();
    }

    private void hideNavigation() {
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    // Check if network is connected
    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void checkConnection() {
        if (!checkInternet()) {
            Intent intent = new Intent(LoginActivity.this, NoInternetActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
