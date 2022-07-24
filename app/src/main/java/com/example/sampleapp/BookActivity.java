package com.example.sampleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";


    private TextView textMysterious,textAbhay,textPage,textShortDescriptioin,textLongDesctiption;
    private Button btnCurrentReading, btnAddToFavourite,btnAlreadyReadBook;
    private ImageView bookImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

//        String textLongDesctiption = "a book or other written or printed work, regarded in terms of its content rather than its physical form." +
//                "\n" +
//                "he main body of a book or other piece of writing, as distinct from other material such as notes,"+
//                "\n" +
//                "appendices, and illustrations.";
//
//        //TODO: Get the incoming data from the Recycler View...
//        Book book = new Book(2, "Jal Sanarachana",
//                "Kumaor",
//                1500,
//                "https://www.gstatic.com/webp/gallery3/5.sm.png",
//                "This book is about the science of magnetism of water",
//                textLongDesctiption
//        );

        Intent intent = getIntent();

        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(null != incomingBook){
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }


            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInWantToReadBooks = false;
        for (Book b:wantToReadBooks
        ) {

            if(b.getId() == book.getId()){
                existInWantToReadBooks = true;
            }

        }

        if(existInWantToReadBooks){
            btnAddToFavourite.setEnabled(false);
        }else{

            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addWantToRead(book)){

                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        // ToDo : Navigate the user to already read books.
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something Wrong happened", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }


    }
    private void handleFavouriteBooks(Book book){
        ArrayList<Book> favouriteBook = Utils.getInstance(this).getFavoriteBooks();
        boolean existInfavouriteBook = false;
        for (Book b:favouriteBook
        ) {

            if(b.getId() == book.getId()){
                existInfavouriteBook = true;
            }

        }

        if(existInfavouriteBook){
            btnAddToFavourite.setEnabled(false);
        }else{

            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToFavouriteBook(book)){
                        Toast.makeText(BookActivity.this, "Book Add to Favourite ", Toast.LENGTH_SHORT).show();
                        // ToDo : Navigate the user to already read books.
                        Intent intent = new Intent(BookActivity.this, addToFavouriteActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something Wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }




    }

    private void handleCurrentlyReadingBooks(Book book) {

    }

    /**
     * Enable and Disable button .
     * Add the book to already read book Arraylist.
     * @param book
     */







    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;
        for (Book b:alreadyReadBooks
             ) {

            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
            
        }

        if(existInAlreadyReadBooks){
            btnAlreadyReadBook.setEnabled(false);
        }else{

            btnAlreadyReadBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){

                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        // ToDo : Navigate the user to already read books.
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something Wrong happened", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }




    }

    private void setData(Book book) {

        textMysterious.setText(book.getName());
        textAbhay.setText(book.getAuthor());
        textPage.setText(String.valueOf(book.getPages()));
        textShortDescriptioin.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);

    }

    private void initViews() {
        textMysterious = (TextView) findViewById(R.id.textMysterious);
        textAbhay = (TextView) findViewById(R.id.textAbhay);
        textPage = (TextView) findViewById(R.id.textPage);
        textShortDescriptioin = (TextView) findViewById(R.id.textShortDescriptioin);
        textLongDesctiption = (TextView) findViewById(R.id.textLongDesctiption);

        btnCurrentReading = (Button) findViewById(R.id.btnCurrentReading);
        btnAddToFavourite = (Button) findViewById(R.id.btnWantToRead);
        btnAlreadyReadBook = (Button) findViewById(R.id.btnAlreadyReadBook);
        btnAddToFavourite = (Button) findViewById(R.id.btnAddToFavourite);

        bookImage = (ImageView) findViewById(R.id.bookImage);





    }
}