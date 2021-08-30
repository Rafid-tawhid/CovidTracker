package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {

    EditText editSearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<CountryModel> countryModelList=new ArrayList<>();
    CountryModel countryModel;
    MyCustomAdaper myCustomAdaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);

        editSearch=findViewById(R.id.countrySearch);
        listView=findViewById(R.id.listView);
        simpleArcLoader=findViewById(R.id.loader2);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        fetchData();

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                myCustomAdaper.getFilter().filter(charSequence);
                myCustomAdaper.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {

        String url="https://corona.lmao.ninja/v2/countries/";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);


                    for (int j=0;j<jsonArray.length();j++)
                    {
                      JSONObject jsonObject=jsonArray.getJSONObject(j);
                      String countryName=jsonObject.getString("country");

                      String cases=jsonObject.getString("cases");
                      String todayCases=jsonObject.getString("todayCases");

                      String death=jsonObject.getString("deaths");
                      String todayDeaths=jsonObject.getString("todayDeaths");
                      String recovered=jsonObject.getString("recovered");
                      String toDayrecovered=jsonObject.getString("todayRecovered");
                      String active=jsonObject.getString("active");
                      String critical=jsonObject.getString("critical");

                      JSONObject object=jsonObject.getJSONObject("countryInfo");

                      String flagUrl=object.getString("flag");

                      countryModel=new CountryModel(flagUrl,countryName,cases,death,recovered,critical,active,todayCases,todayDeaths,toDayrecovered);
                      countryModelList.add(countryModel);

                    }
                    myCustomAdaper=new MyCustomAdaper(AffectedCountries.this,countryModelList);
                    listView.setAdapter(myCustomAdaper);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AffectedCountries.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}