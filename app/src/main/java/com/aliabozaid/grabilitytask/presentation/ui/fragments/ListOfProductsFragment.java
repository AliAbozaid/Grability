package com.aliabozaid.grabilitytask.presentation.ui.fragments;

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
import com.aliabozaid.grabilitytask.presentation.presenter.MainPresenter;
import com.aliabozaid.grabilitytask.presentation.presenter.impl.ProductsPresenterImpl;
import com.aliabozaid.grabilitytask.presentation.ui.adapters.ProductsAdapter;
import com.aliabozaid.grabilitytask.data.contollers.ProductController;
import com.aliabozaid.grabilitytask.data.models.ListOfProductsModel;
import com.aliabozaid.grabilitytask.MyApplication;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListOfProductsFragment extends Fragment implements MainPresenter.PresenterCallBack {
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
    MainPresenter mainPresenter;


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
        this.showLoading(true);

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
        //call presenter to get data
        mainPresenter = new ProductsPresenterImpl(retrofit, url, this);

        return rootView;
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


    @Override
    public void showLoading(boolean show) {
        if (show)
        {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showConnectionError(Throwable t) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(Object data) {
        ListOfProductsModel listOfProductsModel = (ListOfProductsModel) data;
        if (data != null) {
            products.addAll(listOfProductsModel.feed.entry);
            productsAdapter.notifyDataSetChanged();
        }
    }
}
