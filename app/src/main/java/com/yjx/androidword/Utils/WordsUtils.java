package com.yjx.androidword.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WordsUtils {

    private static List<WordsBean> sList;
    private static List<WordsBean> sList_Return;
    private static DictionaryHelper sMSQHelper;
    private static Cursor sCursor;
    private static SQLiteDatabase sSQLiteDatabase;
    private static WordsBean sGetWordBean;
    private static Random sRandom = new Random();
    private static Set<Integer> sSetReturn;
    private static int sI;
    private static Object[] sArray;

    /**
     * 选择模式专用
     * 返回四组数据（单词 + 中文）
     */
    public static List<WordsBean> getChoose(Context context) {
        sList = new ArrayList<>();
        sSetReturn = new HashSet<>();
        sList_Return = new ArrayList<>();
        sMSQHelper = new DictionaryHelper(context);
        sSQLiteDatabase = sMSQHelper.getWritableDatabase();
        sCursor = sSQLiteDatabase.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);

        //获取总词库
        while (sCursor.moveToNext()) {
            sGetWordBean = new WordsBean();
            String english = sCursor.getString(0);
            String chinese = sCursor.getString(1);
            sGetWordBean.setEnglish(english);
            sGetWordBean.setChinses(chinese);
            sList.add(sGetWordBean);
        }

        sSetReturn.clear();
        //四个随机数
        while (sSetReturn.size() <= 4) {
            sI = sRandom.nextInt(sList.size()) % (sList.size());
            sSetReturn.add(sI);
        }

        //随机的四组数据传入返回的数组中
        sArray = sSetReturn.toArray();
        for (int i = 0; i < sArray.length; i++) {
            sGetWordBean = new WordsBean();
            sGetWordBean.setEnglish(sList.get((Integer) sArray[i]).getEnglish());
            sGetWordBean.setChinses(sList.get((Integer) sArray[i]).getChinses());
            sList_Return.add(sGetWordBean);
        }

        return sList_Return;
    }

    /**
     * 填空模式专用
     * 返回单组数据（单词 + 中文）
     */
    public static List<WordsBean> getFill(Context context) {
        sList = new ArrayList<>();
        sList_Return = new ArrayList<>();
        sMSQHelper = new DictionaryHelper(context);
        sSQLiteDatabase = sMSQHelper.getWritableDatabase();
        sCursor = sSQLiteDatabase.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);

        //获取总词库
        while (sCursor.moveToNext()) {
            sGetWordBean = new WordsBean();
            String english = sCursor.getString(0);
            String chinese = sCursor.getString(1);
            sGetWordBean.setEnglish(english);
            sGetWordBean.setChinses(chinese);
            sList.add(sGetWordBean);
        }

        //随机数
        sI = sRandom.nextInt(sList.size()) % (sList.size());
        //随机的数据传入返回的数组中
        sGetWordBean = new WordsBean();
        sGetWordBean.setEnglish(sList.get(sI).getEnglish());
        sGetWordBean.setChinses(sList.get(sI).getChinses());
        sList_Return.add(sGetWordBean);

        return sList_Return;
    }

}
