package com.example.sampleapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnAllBooks, btnCurrentlyReading, btnFavouriteBooks, btnAlreadyRead, btnWantToReadBooks, btnAbout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);

            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);
                startActivity(intent);
            }

        });

        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,CurrentlyReadingActivity.class);
                startActivity(intent);

            }
        });

        btnWantToReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WantToReadActivity.class);
                startActivity(intent);
            }
        });

        btnFavouriteBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addToFavouriteActivity.class);
                startActivity(intent);
            }
        });


        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.app_name));
            builder.setMessage("Designed and Developed with Love by " +
                    "Abhay at Shorthills tech \n Check my website for more awesome applications:");
            builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                   intent.putExtra("url", "https://google.com/");

                    startActivity(intent);





                }
            });
            builder.setCancelable(true);
            builder.create().show();
            }
        });

        Utils.getInstance(this);



    }

    private void initViews() {
        btnAllBooks = (Button) findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = (Button) findViewById(R.id.btnCurrentlyReading);
        btnFavouriteBooks = (Button) findViewById(R.id.btnFavouriteBooks);
        btnAlreadyRead  = (Button) findViewById(R.id.btnAlreadyRead);
        btnWantToReadBooks = (Button) findViewById(R.id.btnWantToReadBooks);
        btnAbout = (Button) findViewById(R.id.btnAbout);
    }


}