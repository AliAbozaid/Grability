package com.aliabozaid.grabilitytask.fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aliabozaid.grabilitytask.BuildConfig;
import com.aliabozaid.grabilitytask.R;
import com.aliabozaid.grabilitytask.adapters.ProductsAdapter;
import com.aliabozaid.grabilitytask.contollers.ProductController;
import com.aliabozaid.grabilitytask.models.ListOfProductsModel;
import com.aliabozaid.grabilitytask.singleton.MyApplication;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListOfProductsFragment extends Fragment {
    ProductController productController;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    ProductsAdapter productsAdapter;

    ArrayList<ListOfProductsModel.Feed.Entry> products;
    private static final String ARG_PARAM1 = "param1";
    private String url;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager;


    @Inject
    Retrofit retrofit;

    public ListOfProductsFragment() {
        // Required empty public constructor
    }

    public static ListOfProductsFragment newInstance(String param1) {
        ListOfProductsFragment fragment = new ListOfProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_of_products, container, false);
        ButterKnife.bind(this, rootView);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        MyApplication app = (MyApplication) getActivity().getApplication();
        app.getApiComponent().inject(this);
        products = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        if (isTablet(getActivity())) {
            // use staggered layout manager
            gaggeredGridLayoutManager = new StaggeredGridLayoutManager(getNumberOfColumn(), StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        } else {
            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
        }


        productsAdapter = new ProductsAdapter(getActivity(), products);
        recyclerView.setAdapter(productsAdapter);


        productController = retrofit.create(ProductController.class);
        loadMore(0);

        return rootView;
    }

    private void loadMore(int from) {
        products.clear();
        Call<ListOfProductsModel> call = productController.getProducts(url, BuildConfig.LIMIT, "json");

        call.enqueue(new Callback<ListOfProductsModel>() {
            @Override
            public void onResponse(Response<ListOfProductsModel> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if (response.body() != null) {
                    products.addAll(response.body().feed.entry);
                    productsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("test", t.toString());
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean isTablet(Activity context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    //get number of column based on device aspects
    private int getNumberOfColumn() {
        return getResources().getInteger(R.integer.num_of_column);
    }


}
