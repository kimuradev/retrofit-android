package com.project.retrofit;

import com.project.retrofit.model.Product;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Leandro on 02/01/2017.
 */

public interface EndPointInterface {

    @GET("/Product")
    Call<List<Product>> getProductList();

    @GET("/Product/{id}")
    Call<List<Product>> getProductById(@Path("id") int id);

}
