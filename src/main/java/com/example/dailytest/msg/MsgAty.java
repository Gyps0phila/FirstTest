package com.example.dailytest.msg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dailytest.dailytest.BaseAty;
import com.example.weather.dailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/3/5.
 */
public class MsgAty extends BaseAty {

    private ListView msgListView;
    private EditText editText;
    private Button btn_send;
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<Msg>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg);
        initMsg();
        msgListView = (ListView) findViewById(R.id.msg_lv);
        editText = (EditText) findViewById(R.id.et_msg);
        btn_send = (Button) findViewById(R.id.btn_send);
        adapter = new MsgAdapter(this, R.layout.msg_item, msgList);
        msgListView.setAdapter(adapter);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();//当有消息时候，刷新listview显示
                    msgListView.setSelection(msgList.size());//将listview定位到最后一行
                    editText.setText("");//清空输入框
                }

            }
        });
    }

    public void initMsg() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }


}
