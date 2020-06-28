package com.iam844.finddragonapps;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShareApp = new Intent(android.content.Intent.ACTION_SEND);
                intentShareApp.setType("text/plain");
                String shareBody = "Delete Chinese apps and support your country. Aatmanirbhar Bharat.\n" + "https://play.google.com/store/apps/details?id=com.iam844.finddragonapps";
                intentShareApp.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                intentShareApp.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intentShareApp, "Share via"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_rate) {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
            return true;
        }

        if (id == R.id.action_contact) {
            Intent intentContact = new Intent(Intent.ACTION_SENDTO);
            intentContact.setData(Uri.parse("mailto:deviam844@gmail.com"));
            if (intentContact.resolveActivity(getPackageManager()) != null) {
                startActivity(intentContact);
            }
            return true;
        }

        if(id == R.id.action_privacy){

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://remove-dragon-0.flycricket.io/privacy.html"));
            try {
                startActivity(browserIntent);
            } catch (Exception e) {
                throw e;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}