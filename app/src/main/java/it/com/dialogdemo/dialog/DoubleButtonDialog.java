package it.com.dialogdemo.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import it.com.dialogdemo.R;
import it.com.dialogdemo.base.BaseDialog;

/**
 * 通过继承Dialog来创建对话框.
 */
public class DoubleButtonDialog extends BaseDialog{
    public DoubleButtonDialog(@NonNull Context context) {
        super(context);
    }

    public DoubleButtonDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public DoubleButtonDialog(@NonNull Context context, @StyleRes int themeResId, int layout) {
        super(context, themeResId, layout);
    }

    protected DoubleButtonDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mLayout != -1) {
            setContentView(mLayout);
        } else {
            mView = View.inflate(mContext, R.layout.messagetoastlayout, null);
            setContentView(mView);
        }
        mOk = findViewById(R.id.ok);
        mCancel = findViewById(R.id.cancel);

        mContentView = (TextView) findViewById(R.id.content);
        mTitleView = (TextView) findViewById(R.id.title);
        mContentView.setText(mContent);
        mTitleView.setText(mTitle);
        mOk.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    //如果还有其他点击事件直接扩展
    @Override
    public void onClick(View v) {
        super.onClick(v);


    }
}
