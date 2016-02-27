package com.aliabozaid.grabilitytask;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.aliabozaid.grabilitytask.adapters.TabAdapters;
import com.aliabozaid.grabilitytask.fragments.ListOfProductsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    String[] tabsArray;
    TabAdapters adapter;
    String[] titles = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.is_tablet))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpSpinner();
        setSupportActionBar(toolbar);
        adapter = new TabAdapters(getSupportFragmentManager());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String arrayName = parent.getItemAtPosition(position).toString().replace(" ", "").toLowerCase();
                int arrayId = getResources().getIdentifier(arrayName, "array", getPackageName());
                titles = getResources().getStringArray(arrayId);
                setUpTab(titles);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(toolbar.getContext(),
                R.array.categories, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setAdapter(adapter);
    }

    private void setUpTab(String[] tabsName) {
        tabLayout.removeAllTabs();
        adapter = new TabAdapters(getSupportFragmentManager());

        for (int i = 0; i < titles.length; i++) {
            adapter.addFrag(ListOfProductsFragment.newInstance(titles[i].replace(" ", "").toLowerCase()), titles[i]);
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
