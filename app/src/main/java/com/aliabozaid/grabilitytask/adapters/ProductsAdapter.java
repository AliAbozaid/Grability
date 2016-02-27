package com.aliabozaid.grabilitytask.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliabozaid.grabilitytask.BuildConfig;
import com.aliabozaid.grabilitytask.ProductDetailsActivity;
import com.aliabozaid.grabilitytask.models.ListOfProductsModel;
import com.aliabozaid.grabilitytask.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<ListOfProductsModel.Feed.Entry> productsItems;
    Gson gson;


    //constructor to initialize
    public ProductsAdapter(Activity context, ArrayList<ListOfProductsModel.Feed.Entry> productsItems) {
        this.context = context;
        this.productsItems = productsItems;
        gson = new Gson();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        final Holder holder = (Holder) viewHolder;
        holder.itemDescription.setText(productsItems.get(position).name.label);
        if (productsItems.get(position).price.attributes.amount == 0.0)
            holder.itemPrice.setVisibility(View.GONE);
        holder.itemPrice.setText(productsItems.get(position).price.attributes.amount + "");
        int imageHeight = 190;
        if (productsItems.get(position).image != null) {
            imageHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, productsItems.get(position).image.get(2).attributes.height, context.getResources().getDisplayMetrics());
        }
        //set image view size
        setImageHeight(imageHeight, holder.itemImage);
        if (productsItems.get(position).image != null)
            Picasso.with(context).load(productsItems.get(position).image.get(2).label).into(holder.itemImage);
        else
            Picasso.with(context).load(R.drawable.no_image_available2).into(holder.itemImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entry = gson.toJson(productsItems.get(position), ListOfProductsModel.Feed.Entry.class);
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("Entry", entry);
                if (productsItems.get(position).image != null)
                    intent.putExtra(BuildConfig.EXTRA_IMAGE, productsItems.get(position).image.get(2).label);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, holder.itemImage, BuildConfig.EXTRA_IMAGE);
                ActivityCompat.startActivity((Activity) context, intent, options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsItems.size();
    }

    void setImageHeight(int height, ImageView imageView) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        params.height = height;
        imageView.setLayoutParams(params);
    }

    //holder for item row
    public class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.card_view)
        CardView cardView;
        @Bind(R.id.item_image)
        ImageView itemImage;
        @Bind(R.id.item_price)
        TextView itemPrice;
        @Bind(R.id.item_description)
        TextView itemDescription;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    //holder for progressbar
    /*public class HolderProgress extends RecyclerView.ViewHolder {
        @Bind(R.id.progress_bar_last)
        ProgressBar progressBar;

        public HolderProgress(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }*/


}
