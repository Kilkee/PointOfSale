package com.example.delaney.pointofsale;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mNameTextView, mQuantityTextView, mDateTextView;
    private Item mCurrentItem;
    private Item mClearedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameTextView = findViewById(R.id.name_text);
        mQuantityTextView = findViewById(R.id.quantity_text);
        mDateTextView = findViewById(R.id.date_text);


       // Boilerplate code, don't mess with it.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: Later make this actually an add button
                //This is just a test....
                // For now just practice showing an item on the screen.
                mCurrentItem = Item.getDefaultItem();
                showCurrentItem();

            }
        });
    }

    private void showCurrentItem() {
        mNameTextView.setText(mCurrentItem.getName());
        mQuantityTextView.setText(getString(R.string.quantity_format, mCurrentItem.getQuantity()));

        mDateTextView.setText(getString(R.string.date_format, mCurrentItem.getDeliveryDateString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Boilerplate code, don't mess with it. Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.action_reset:
               mClearedItem = mCurrentItem;
               mCurrentItem = new Item();
               showCurrentItem();

               //TODO: Use a snackbar to allow user to undo an action
               Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator_layout), "Item cleared", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mCurrentItem = mClearedItem;
                        showCurrentItem();
                        Snackbar.make(findViewById(R.id.coordinator_layout), "Item restored", Snackbar.LENGTH_SHORT).show();

                    }
                });
               snackbar.show();
            return true;
           case R.id.action_settings:
               // startActivity(new Intent(Settings.ACTION_SETTINGS));
               startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
               return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
