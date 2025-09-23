package com.knotenpunkt;

import static android.content.Context.MODE_PRIVATE;
import static com.knotenpunkt.LoginActivity.SHARED_PREFS;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ExcelTableFragment extends Fragment implements GodiplanListAdapter.OnItemClickListener {
    private View webpagesave;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<GodiplanItem> mgodiplanItemList;
    private boolean showExcelTableFragment = true;

    public static final String Extra_Datum = "Datum";
    public static final String Extra_Fr_Sabbat = "Fr_Sabbat";
    public static final String Extra_Moderation = "Moderation";
    public static final String Extra_KidsGo = "KidsGo";
    //public static final String Extra_Musik = "Musik";
    public static final String Extra_Gespreachsltng = "Gespreachsltng";
    public static final String Extra_Predigt = "Predigt";
    public static final String Extra_Kindermoment = "Kindermoment";
    public static final String Extra_Zeit = "Zeit";
    public static final String Extra_Ort = "Ort";
    public static final String Extra_Putzdienst = "Putzdienst";

    public ExcelTableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View webpage = inflater.inflate(R.layout.fragment_excel_table , container, false);
        if (showExcelTableFragment != false) {
            recyclerView = (RecyclerView) webpage.findViewById(R.id.recyclerViewExcelTable);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(webpage.getContext()));
            requestQueue = (RequestQueue) VolleySingleton.getmInstance(this.getContext()).getRequestQueue();
            mgodiplanItemList = new ArrayList<>();
            Timer timerObj = new Timer();
            TimerTask timerTaskObj = new TimerTask() {
                public void run() {
                    fetchData();
                }
            };
            timerObj.schedule(timerTaskObj, 0, 60000); // 0 -> 0milliseconds /60000 -> 1minutes
            showExcelTableFragment = false;
            webpagesave = webpage;
            return webpage;
        }
        else {
            return webpagesave;
        }
    }

    private void fetchData() {
        String Url = "https://script.googleusercontent.com/macros/echo?user_content_key=pq0rnAYO1fi-4oW8zplQyKdyO08tQui41DzX3rf6y0nVoTINTQG9SqOXrnWA9rYhlv9f6vSOtmq73aRbPgqHe1ShI5yKpKr7OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHayZSCDAHUVSS3mVMVcRfiL6hN20gGJMisUYvTU_7v0teBLrBjhYScG4gLjS63dHeAmzu1bj1WdWPuTVbok-WcQwHddikmKHrEgtBHn44AOQPwcXmMXNw05t6IbBiDp_8zivdKcc0_27Xk2DCad7QGfiZYEw2G65thWE9_kKVCpQd&lib=MqKIGfITGTyTFj6oX42_urt25AROQb3Av";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mgodiplanItemList.clear();
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String datum = jsonObject.getString("Datum");
                        String fr_Sabbat = jsonObject.getString("Fr_Sabbat");
                        String moderation = jsonObject.getString("Moderation");
                        String kidsGo = jsonObject.getString("KidsGo");
                        //String musik = jsonObject.getString("Musik");
                        String gespreachsltng = jsonObject.getString("Gespreachsltng");
                        String predigt = jsonObject.getString("Predigt");
                        String kindermoment = jsonObject.getString("Kindermoment");
                        String zeit = jsonObject.getString("Zeit");
                        String ort = jsonObject.getString("Ort");
                        String putzdienst = jsonObject.getString("Putzdienst");
                        // musik,
                        GodiplanItem getList = new GodiplanItem(datum, fr_Sabbat, moderation, kidsGo, gespreachsltng, predigt,
                                kindermoment, zeit, ort, putzdienst);
                        mgodiplanItemList.add(getList);
                    }
                    GodiplanListAdapter adapter = new GodiplanListAdapter(mgodiplanItemList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    adapter.setOnItemClickListener(ExcelTableFragment.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(getActivity(),"" + position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity().getApplication(), DetailActivity.class);
        GodiplanItem clickgodiplanItem = mgodiplanItemList.get(position);
        intent.putExtra(Extra_Datum, clickgodiplanItem.getDatum());
        intent.putExtra(Extra_Fr_Sabbat, clickgodiplanItem.getFr_Sabbat());
        intent.putExtra(Extra_Moderation, clickgodiplanItem.getModeration());
        intent.putExtra(Extra_KidsGo, clickgodiplanItem.getKidsGo());
        //intent.putExtra(Extra_Musik, clickgodiplanItem.getMusik());
        intent.putExtra(Extra_Gespreachsltng, clickgodiplanItem.getGespreachsltng());
        intent.putExtra(Extra_Predigt, clickgodiplanItem.getPredigt());
        intent.putExtra(Extra_Kindermoment, clickgodiplanItem.getKindermoment());
        intent.putExtra(Extra_Zeit, clickgodiplanItem.getZeit());
        intent.putExtra(Extra_Ort, clickgodiplanItem.getOrt());
        intent.putExtra(Extra_Putzdienst, clickgodiplanItem.getPutzdienst());
        startActivity(intent);
    }

    private void autoLogin() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String emailDetail = sharedPreferences.getString("email", "");
        String passwordDetail = sharedPreferences.getString("password", "");
        if (emailDetail.equals("") && passwordDetail.equals("")) {
            startActivity(new Intent(getActivity().getApplication(), LoginActivity.class));
        }
    }

    public void timerData() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fetchData();
            }
        }, 0, 1000); // 1000 milliseconds = 1 second
    }

    @Override
    public void onResume() {
        super.onResume();
        autoLogin();
        timerData();
    }

    @Override
    public void onPause() {
        super.onPause();
        autoLogin();
        timerData();
    }
}
