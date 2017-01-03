package com.project.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.project.retrofit.adapter.ProductAdapter;
import com.project.retrofit.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private final static String BASE_URL = "http://172.16.211.240:3000/Product";
    private ListView listView;
    private EditText edtId;
    private Button btnFilter;
    private Retrofit retrofit;
    private EndPointInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        edtId = (EditText) findViewById(R.id.edt_id);
        btnFilter = (Button) findViewById(R.id.btn_filter);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(EndPointInterface.class);

        Call<List<Product>> call = service.getProductList();

        call.enqueue(new Callback<List<Product>>() {
           @Override
           public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
               try{
                   List<Product> productList = response.body();
                   ProductAdapter adapter = new ProductAdapter(getApplicationContext(), R.layout.list_item, productList);

                   listView.setAdapter(adapter);

                   adapter.notifyDataSetChanged();
               }catch(Exception e ){
                   Log.d("Exception", e.getMessage());
               }
           }

           @Override
           public void onFailure(Throwable t) {
               Log.d("onFailure", t.toString());
           }
       });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Product> call = service.getProductById(edtId.getText().toString());

                call.enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Response<Product> response, Retrofit retrofit) {
                        try{
                            List<Product> list = new ArrayList<Product>();
                            Product productList = response.body();

                            list.add(productList);
                            Log.d("Name: ", productList.getName());
                            Log.d("Description", productList.getDescription());

                            ProductAdapter adapter = new ProductAdapter(getApplicationContext(), R.layout.list_item, list);

                            listView.setAdapter(adapter);

                            adapter.notifyDataSetChanged();

                        }catch(Exception e ){
                            Log.d("Exception", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("onFailure", t.toString());
                    }
                });
            }
        });


    }
}
