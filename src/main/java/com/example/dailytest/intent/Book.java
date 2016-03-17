package com.example.dailytest.intent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gypsophila on 2016/3/17.
 */
public class Book implements Parcelable {

    private String bookName;
    private String author;
    private int bookPages;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeInt(bookPages);
        dest.writeString(author);

    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>(){

        @Override
        public Book createFromParcel(Parcel source) {
            Book book = new Book();
            book.bookName = source.readString();
            book.bookPages = source.readInt();
            book.author = source.readString();

            return book;
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", bookPages=" + bookPages +
                '}';
    }
}
