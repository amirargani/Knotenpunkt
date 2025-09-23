package com.knotenpunkt;

import static com.knotenpunkt.ExcelTableFragment.Extra_Datum;
import static com.knotenpunkt.ExcelTableFragment.Extra_Fr_Sabbat;
import static com.knotenpunkt.ExcelTableFragment.Extra_Gespreachsltng;
import static com.knotenpunkt.ExcelTableFragment.Extra_KidsGo;
import static com.knotenpunkt.ExcelTableFragment.Extra_Kindermoment;
import static com.knotenpunkt.ExcelTableFragment.Extra_Moderation;
//import static com.knotenpunkt.ExcelTableFragment.Extra_Musik;
import static com.knotenpunkt.ExcelTableFragment.Extra_Ort;
import static com.knotenpunkt.ExcelTableFragment.Extra_Predigt;
import static com.knotenpunkt.ExcelTableFragment.Extra_Putzdienst;
import static com.knotenpunkt.ExcelTableFragment.Extra_Zeit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        hideNavigation();
        checkConnection();
        getWindow().setStatusBarColor(ContextCompat.getColor(DetailActivity.this,R.color.white));

        Intent intent = getIntent();
        String datum = intent.getStringExtra(Extra_Datum);
        String fr_Sabbat = intent.getStringExtra(Extra_Fr_Sabbat);
        String moderation = intent.getStringExtra(Extra_Moderation);
        String kidsGo = intent.getStringExtra(Extra_KidsGo);
        //String musik = intent.getStringExtra(Extra_Musik);
        String gespreachsltng = intent.getStringExtra(Extra_Gespreachsltng);
        String predigt = intent.getStringExtra(Extra_Predigt);
        String kindermoment = intent.getStringExtra(Extra_Kindermoment);
        String zeit = intent.getStringExtra(Extra_Zeit);
        String ort = intent.getStringExtra(Extra_Ort);
        String putzdienst = intent.getStringExtra(Extra_Putzdienst);

        TextView textViewDatum = findViewById(R.id.DatumDetailLbl);
        EditText editTextFr_Sabbat = findViewById(R.id.Fr_SabbatDetailText);
        EditText editTextModeration = findViewById(R.id.ModerationDetailText);
        EditText editTextKidsGo = findViewById(R.id.KidsGoDetailText);
        //EditText editTextMusik = findViewById(R.id.MusikDetailText);
        EditText editTextGespreachsltng = findViewById(R.id.GespreachsltngDetailText);
        EditText editTextPredigt = findViewById(R.id.PredigtDetailText);
        EditText editTextKKindermoment = findViewById(R.id.KindermomentDetailText);
        TextView textViewZeit = findViewById(R.id.ZeitDetailLbl);
        TextView textViewOrt = findViewById(R.id.OrtDetailLbl);
        EditText editTextPutzdienst = findViewById(R.id.PutzdienstDetailText);

        textViewDatum.setText(datum);
        editTextFr_Sabbat.setText(fr_Sabbat);
        editTextModeration.setText(moderation);
        editTextKidsGo.setText(kidsGo);
        //editTextMusik.setText(musik);
        editTextGespreachsltng.setText(gespreachsltng);
        editTextPredigt.setText(predigt);
        editTextKKindermoment.setText(kindermoment);
        textViewZeit.setText(zeit);
        textViewOrt.setText(ort);
        editTextPutzdienst.setText(putzdienst);
    }

    // geeksforgeeks.org/how-to-post-data-to-api-using-volley-in-android/
    public void clickDetailUpdate(View v) {
        Intent intent = getIntent();
        String datum = intent.getStringExtra(Extra_Datum).toString();
        EditText inputFr_Sabbat = findViewById(R.id.Fr_SabbatDetailText);
        String fr_Sabbat = inputFr_Sabbat.getText().toString();
        EditText inputModeration = findViewById(R.id.ModerationDetailText);
        String moderation = inputModeration.getText().toString();
        EditText inputKidsGo = findViewById(R.id.KidsGoDetailText);
        String kidsGo = inputKidsGo.getText().toString();
        //EditText inputMusik = findViewById(R.id.MusikDetailText);
        //String musik = inputMusik.getText().toString();
        EditText inputGespreachsltng = findViewById(R.id.GespreachsltngDetailText);
        String gespreachsltng = inputGespreachsltng.getText().toString();
        EditText inputPredigt = findViewById(R.id.PredigtDetailText);
        String predigt = inputPredigt.getText().toString();
        EditText inputKindermoment = findViewById(R.id.KindermomentDetailText);
        String kindermoment = inputKindermoment.getText().toString();
        EditText inputPutzdienst = findViewById(R.id.PutzdienstDetailText);
        String putzdienst = inputPutzdienst.getText().toString();
        //+ "&Musik=" + musik
        String Url = ("https://script.google.com/macros/s/AKfycbzvUp5OGpQwcwIZb8ACtVGFokLQSiTQpGl7fD471pUiWI7Lqz63kNTEkJlJdkNzEwuy/exec?action=update&Datum=" + datum +"&Fr_Sabbat="+ fr_Sabbat + "&Moderation=" + moderation + "&KidsGo=" + kidsGo + "&Gespreachsltng=" + gespreachsltng + "&Predigt=" + predigt + "&Kindermoment=" + kindermoment + "&Putzdienst=" + putzdienst).replace(" ","%20");
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(DetailActivity.this);
        StringRequest request = new StringRequest(Request.Method.GET, Url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // on below line we are displaying a success toast message.
                if (response.equals("The update was successful.")) {
                    // inloop.github.io/svg2android/
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailActivity.this)
                            .setIcon(R.drawable.ic_check)
                            .setTitle("Erfolg")
                            .setMessage("Die Datenverarbeitung wurde erfolgreich durchgeführt.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //set what would happen when positive button is clicked
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                }
                if (response.equals("Update failed.")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailActivity.this)
                            .setIcon(R.drawable.ic_error)
                            .setTitle("Fehler")
                            .setMessage("Beim Senden von Daten ist ein Fehler aufgetreten.")
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
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
            }
        });
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    public void clickDetailBack(View v) {
        finish();
        // Back
        //onBackPressed();
        // Exit
        //moveTaskToBack(true);
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(1);
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
            Intent intent = new Intent(DetailActivity.this,NoInternetActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
