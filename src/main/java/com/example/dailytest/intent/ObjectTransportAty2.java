package com.example.dailytest.intent;

import android.os.Bundle;
import android.util.Log;

import com.example.dailytest.dailytest.BaseAty;

/**
 * Created by Gypsophila on 2016/3/17.
 */
public class ObjectTransportAty2 extends BaseAty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Person p1 = (Person) getIntent().getSerializableExtra("person_data");
        Log.i("person_data", p1.toString());

        Book book = getIntent().getParcelableExtra("book_data");
        Log.i("book_data", book.toString());
    }
}
