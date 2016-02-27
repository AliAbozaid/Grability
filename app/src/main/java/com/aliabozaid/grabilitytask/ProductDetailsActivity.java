package com.aliabozaid.grabilitytask;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliabozaid.grabilitytask.models.ListOfProductsModel;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    @Bind(R.id.item_image)
    ImageView itemImage;
    @Bind(R.id.item_name)
    TextView itemName;
    @Bind(R.id.item_price)
    TextView itemPrice;
    @Bind(R.id.item_content_type)
    TextView itemContentType;
    @Bind(R.id.item_term)
    TextView itemTerm;
    @Bind(R.id.item_release_date)
    TextView itemReleaseDate;
    @Bind(R.id.item_title)
    TextView itemTitle;
    @Bind(R.id.item_summary)
    TextView itemSummary;
    @Bind(R.id.item_rights)
    TextView itemRights;
    @Bind(R.id.item_link)
    TextView itemLink;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    ListOfProductsModel.Feed.Entry entry;
    String entryString;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.is_tablet))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initActivityTransitions();
        setContentView(R.layout.activity_product_details);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), BuildConfig.EXTRA_IMAGE);
        supportPostponeEnterTransition();
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        gson = new Gson();
        entryString = getIntent().getExtras().getString("Entry");
        entry = gson.fromJson(entryString, ListOfProductsModel.Feed.Entry.class);
        if (entry != null) {
            bindData();
            getSupportActionBar().setTitle(entry.name.label);
        }
        itemLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = entry.id.label;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void bindData() {
        supportStartPostponedEnterTransition();
        if (entry.image != null) {
            Picasso.with(this).load(getIntent().getStringExtra(BuildConfig.EXTRA_IMAGE)).into(itemImage, new Callback() {
                @Override
                public void onSuccess() {
                    Bitmap bitmap = ((BitmapDrawable) itemImage.getDrawable()).getBitmap();
                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette palette) {
                        }
                    });
                }

                @Override
                public void onError() {

                }
            });
        } else Picasso.with(this).load(R.drawable.no_image_available2).into(itemImage);
        if (entry.name.label != null)
            itemName.setText("Name: " + entry.name.label);
        if (entry.price.attributes != null)
            itemPrice.setText("Price: " + entry.price.attributes.amount + " " + entry.price.attributes.currency);
        if (entry.contentType.attributes.term != null)
            itemContentType.setText("Content type: " + entry.contentType.attributes.term);
        if (entry.contentType.attributes.term != null)
            itemTerm.setText("Category: " + entry.category.attributes.term);
        if (entry.releaseDate.attributes.label != null)
            itemReleaseDate.setText("Release date: " + entry.releaseDate.attributes.label);
        if (entry.title.label != null)
            itemTitle.setText("Title: " + entry.title.label);
        if (entry.summary != null && entry.summary.label != null)
            itemSummary.setText("Summary: " + entry.summary.label);
        if (entry.rights != null && entry.rights.label != null)
            itemRights.setText("Owned by: " + entry.rights.label);


    }

}
