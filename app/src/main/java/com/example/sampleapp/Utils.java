package com.example.sampleapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static Utils instance ;
    private SharedPreferences sharedPreferences;
    private static final String ALL_BOOKS_KEY  = "all_books" ;
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books" ;
    private static final String FAVORITE_BOOKS = "favorite_books" ;


//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if(null == getAllBooks()){
           // allBooks = new ArrayList<>();
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();


        if(null == getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();

        }

        if(null == getWantToReadBooks()){
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getFavoriteBooks()){
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();        }


    }

    private void initData() {
        //TODO add initial data.
         ArrayList<Book> books = new ArrayList<>();



                books.add(new Book(1,
                        "Anant Arth",
                        "Abhay",
                        500,
                        "https://www.gstatic.com/webp/gallery/4.jpg",
                        "This book is about the character of A person",
                        "This book is about a person and it's behaviour towards society which make him weaker and weaker for life"
                                            ));

                books.add(new Book(
                        2,
                        "Jal Sanarachana",
                        "Kumaor",
                        1500,
                        "https://www.gstatic.com/webp/gallery3/5.sm.png",
                        "This book is about the science of magnetism of water",
                        "We can read so much about water through which it is in compulsory composition fo life."
                ));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
        //   String text = gson.toJson(books);



    }

    public static  Utils getInstance(Context context) {
        if(null != instance){
            return instance;
        }else{
            instance = new Utils(context);
            return instance;
        }

    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

            ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);

        return books;
    }

    public  ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);

        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);

        return books;
    }

    public  ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);

        return books;
    }

    public  ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS,null),type);

        return books;
    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();

        if (null != null){
            for (Book b:books
            ) {
                if(b.getId() == id){
                    return b;
                }
            }


        }

        return null;


    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }


    public boolean addToFavouriteBook(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;   }

   public boolean removeFromAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null != books){
            for (Book b : books
                 ) {
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                      Gson gson = new Gson();
                      SharedPreferences.Editor editor = sharedPreferences.edit();
                      editor.remove(ALREADY_READ_BOOKS);
                      editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                      editor.commit();
                      return true;

                    }
                }
                
            }

        }
        return false;


    }

    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if(null != books){
            for (Book b : books
            ) {
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }

            }

        }
        return false;
    }

    public  boolean removeFromFavorites (Book book ){
        ArrayList<Book> books = getFavoriteBooks();
        if(null != books){
            for (Book b : books
            ) {
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }

            }

        }
        return false;
    }

}
