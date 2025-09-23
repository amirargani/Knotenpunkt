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

public class CalendarFragment extends Fragment {
    private View webpagesave;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<CalendarItem> mcalendarItemList;
    private boolean showCalendarFragment = true;

    public CalendarFragment() {
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
        View webpage = inflater.inflate(R.layout.fragment_calendar, container, false);
        if (showCalendarFragment != false) {
            recyclerView = (RecyclerView) webpage.findViewById(R.id.recyclerviewCalendarl);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(webpage.getContext()));
            requestQueue = (RequestQueue) VolleySingleton.getmInstance(this.getContext()).getRequestQueue();
            mcalendarItemList = new ArrayList<>();
            Timer timerObj = new Timer();
            TimerTask timerTaskObj = new TimerTask() {
                public void run() {
                    fetchData();
                }
            };
            timerObj.schedule(timerTaskObj, 0, 300000); // 0 -> 0milliseconds /300000 -> 5minutes
            showCalendarFragment = false;
            webpagesave = webpage;
            return webpage;
        }
        else {
            return webpagesave;
        }
    }

    private void fetchData() {
        String Url = "https://script.googleusercontent.com/macros/echo?user_content_key=ir7nju2bodgdR4ZfVdPLqXEStFd8BZaRN5z_LLmxSRmYjkQ_tZ-r8iCCa1-WvlqTTgOKR0be7SrTbD1AhwduLa6Y6rk0EQCmOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHayInB7P1LJBgMPYRSfbHbrdF_XCV9QzsrQyJvlSBU8xyPhlKOpXbF_KTKoxaEsmUkGFGysKQQjsUbMHTgKUHcJzghnX8BuKMawXIuFltvcj9tQONWInhVK_ZzMThTeyPNB1l731hKZ3xIlReucCM-yeUAzWRtXlCMg&lib=McG-z9CZG2Hd3SuQT8NHRGXgDoiYn0zF_";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mcalendarItemList.clear();
                    JSONArray jsonArray = response.getJSONArray("itemsCalendar");
                    for (int i = 0; i < jsonArray.length(); i++) {
                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String datum = jsonObject.getString("Startdatum");
                        String startzeit = jsonObject.getString("Startzeit");
                        String endzeit = jsonObject.getString("Endzeit");
                        String titel = jsonObject.getString("Titel");
                        String beschreibung = jsonObject.getString("Beschreibung");
                        String ort = jsonObject.getString("Ort");
                       CalendarItem getList = new CalendarItem(datum,startzeit + " - " + endzeit,titel,beschreibung,ort);
                       mcalendarItemList.add(getList);
                    }
                    CalenderAdapter adapter = new CalenderAdapter(mcalendarItemList);
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
