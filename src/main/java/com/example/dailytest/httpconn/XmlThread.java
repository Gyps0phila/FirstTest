package com.example.dailytest.httpconn;

import android.os.Handler;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/3/1.
 */
public class XmlThread extends Thread {

    private String mUrl;
    private TextView mTextView;
    private Handler mHandler;

    public XmlThread(String mUrl, TextView mTextView, Handler mHandler) {
        this.mUrl = mUrl;
        this.mTextView = mTextView;
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                InputStream is = conn.getInputStream();
                parser.setInput(is, "UTF-8");
                //xml的读取是由事件驱动
                int eventType = parser.getEventType();
                final List<Girl> girls = new ArrayList<Girl>();
                Girl girl = null;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String data = parser.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG: {
                            if ("girl".equals(data)) {
                                girl = new Girl();
                            }
                            if ("name".equals(data)) {
                                girl.setName(parser.nextText());
                            }
                            if ("age".equals(data)) {
                                girl.setAge(Integer.parseInt(parser.nextText()));
                            }
                            if ("school".equals(data)) {
                                girl.setSchool(parser.nextText());
                            }
                            break;
                        }
                        case XmlPullParser.END_TAG: {
                            if ("girl".equals(data)) {
                                girls.add(girl);
                            }
                            break;
                        }

                    }
                    eventType = parser.next();
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(girls.toString());
                    }
                });
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
