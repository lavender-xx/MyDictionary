package com.yjx.androidword.Activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.R;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;
import com.yjx.androidword.Utils.ToastUtils;

public class AddWordsActivity extends BaseActivity {
    private android.widget.EditText mEditEnglish;
    private android.widget.EditText mEditChinese;
    private android.widget.Button mBtnAdd;

    DictionaryHelper mSQHelper;
    SQLiteDatabase mSQLiteDatabase;
    ContentValues mContentValues;

    @Override
    protected void initData() {

        mSQHelper = new DictionaryHelper(mContext);
        mSQLiteDatabase = mSQHelper.getWritableDatabase();

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String english = mEditEnglish.getText().toString();
                String chinese = mEditChinese.getText().toString();

                if (!TextUtils.isEmpty(english) && !TextUtils.isEmpty(chinese)) {
                    initSQLiteData(english, chinese);
                } else {
                    ToastUtils.show(mContext, "单词或翻译都不能为空噢！");
                }

            }
        });

    }

    private void initSQLiteData(String english, String chinese) {
        mContentValues = new ContentValues();
        mContentValues.put(DictionaryHelper.ENGLISH, english);
        mContentValues.put(DictionaryHelper.CHINESE, chinese);
        if (mSQLiteDatabase.insert(DictionaryHelper.TABLE_NAME, null, mContentValues) == -1) {
            Toast.makeText(mContext, "添加失败！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "添加成功！", Toast.LENGTH_SHORT).show();
            mEditEnglish.setText("");
            mEditChinese.setText("");
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_addwords;
    }

    @Override
    protected void initView() {
        mEditEnglish = findViewById(R.id.edit_english);
        mEditChinese = findViewById(R.id.edit_chinese);
        mBtnAdd = findViewById(R.id.btn_add);
    }
}