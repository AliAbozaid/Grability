package com.aliabozaid.grabilitytask.contollers;

import com.aliabozaid.grabilitytask.models.ListOfProductsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ProductController {
    @GET("rss/{media}/limit={limit}/{format}")
    Call<ListOfProductsModel> getProducts(@Path("media") String media, @Path("limit") int limit, @Path("format") String format);
}
