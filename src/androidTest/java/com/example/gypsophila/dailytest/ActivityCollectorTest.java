package com.example.gypsophila.dailytest;

import android.test.AndroidTestCase;

import com.example.gypsophila.animation.AniListViewAty;

/**
 * Created by Gypsophila on 2016/3/10.
 */
public class ActivityCollectorTest extends AndroidTestCase {

    //所有测试用例执行前，这个方法会执行做初始化工作
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    //以test开头
    public void testAddActivity() {

        assertEquals(0, ActivityCollector.activities.size());
        AniListViewAty act = new AniListViewAty();
        ActivityCollector.addActivity(act);
        assertEquals(1, ActivityCollector.activities.size());


    }
    //所有测试用力执行后，这个方法执行进行释放资源
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
