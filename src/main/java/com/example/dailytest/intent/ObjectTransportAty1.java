package com.example.dailytest.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dailytest.dailytest.BaseAty;
import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/17.
 */
public class ObjectTransportAty1 extends BaseAty {

    private Button transport1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.objecttransport);

        transport1 = (Button) findViewById(R.id.transport1);
        transport1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setAge(1);
                person.setName("哈哈");
                Book book = new Book();
                book.setBookName("呵呵");
                book.setBookPages(200);
                book.setAuthor("嘻嘻");

                Intent intent = new Intent(ObjectTransportAty1.this, ObjectTransportAty2.class);
                intent.putExtra("person_data", person);
                intent.putExtra("book_data", book);
                startActivity(intent);

            }
        });
    }
}
