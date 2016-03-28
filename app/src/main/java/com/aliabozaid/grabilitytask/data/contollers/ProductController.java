package com.aliabozaid.grabilitytask.data.contollers;

import com.aliabozaid.grabilitytask.data.models.ListOfProductsModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface ProductController {
    @GET("rss/{media}/limit={limit}/{format}")
    Observable<ListOfProductsModel> getProducts(@Path("media") String media, @Path("limit") int limit, @Path("format") String format);
}
