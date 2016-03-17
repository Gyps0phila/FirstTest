package com.example.dailytest.baseadapter;

/**
 * Created by Gypsophila on 2016/2/23.
 */
public class ItemBean {
    public int ItemImgResId;
    public String ItemTitle;
    public String ItemContent;

    public ItemBean(int itemImgResId, String itemTitle, String itemContent) {
        ItemImgResId = itemImgResId;
        ItemTitle = itemTitle;
        ItemContent = itemContent;
    }
}
