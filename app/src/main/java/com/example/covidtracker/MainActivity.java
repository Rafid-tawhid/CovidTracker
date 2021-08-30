package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView cases,totalDeath, recovered,critical,active,todayCases,todayDeath,affected,todayRecovered;

    SimpleArcLoader loaders;
    ScrollView scrollView;
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cases=findViewById(R.id.tvCases);
        recovered=findViewById(R.id.tvRecovered);
        active=findViewById(R.id.tvActive);
        totalDeath=findViewById(R.id.tvDeath);
        todayDeath=findViewById(R.id.tvTodaysDeath);
        affected=findViewById(R.id.tvTotalContrys);
        todayRecovered=findViewById(R.id.todayrecovered);
        critical=findViewById(R.id.tvCritical);
        todayRecovered=findViewById(R.id.todayrecovered);
        todayCases=findViewById(R.id.todayCases);
        loaders=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrollSts);
        pieChart=findViewById(R.id.piechart);

        fetchData();

    }

    private void fetchData() {

        String url="https://corona.lmao.ninja/v2/all";
        loaders.start();
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response.toString());


                    cases.setText(jsonObject.getString("cases"));
                    recovered.setText(jsonObject.getString("recovered"));
                    critical.setText(jsonObject.getString("critical"));
                    todayCases.setText(jsonObject.getString("todayCases"));
                    active.setText(jsonObject.getString("active"));
                    todayDeath.setText(jsonObject.getString("todayDeaths"));
                    totalDeath.setText(jsonObject.getString("deaths"));
                    todayRecovered.setText(jsonObject.getString("todayRecovered"));
                    affected.setText(jsonObject.getString("affectedCountries"));


                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(cases.getText().toString()), Color.parseColor("#FFC107")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#8BC34A")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(totalDeath.getText().toString()), Color.parseColor("#FF0707")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()), Color.parseColor("#2196F3")));

                    pieChart.startAnimation();

                    loaders.stop();
                    loaders.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    loaders.stop();
                    loaders.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loaders.stop();
                loaders.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void goTrackCountries(View view) {
        startActivity(new Intent(MainActivity.this,AffectedCountries.class));
    }
}