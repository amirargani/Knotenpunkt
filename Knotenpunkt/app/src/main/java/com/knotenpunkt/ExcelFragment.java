package com.knotenpunkt;

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

public class ExcelFragment extends Fragment {
    private View webpagesave;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<GodiplanItem> mgodiplanItemList;
    private boolean showExcelFragment = true;

    public ExcelFragment() {
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
        View webpage = inflater.inflate(R.layout.fragment_excel , container, false);
        if (showExcelFragment != false) {
            recyclerView = (RecyclerView) webpage.findViewById(R.id.recyclerViewExcel);
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
            showExcelFragment = false;
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
                    GodiplanAdapter adapter = new GodiplanAdapter(mgodiplanItemList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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
}
