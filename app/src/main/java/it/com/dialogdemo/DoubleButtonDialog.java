package it.com.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.TextView;

/**
 * Created by tony on 2017/5/30.
 */

public class DoubleButtonDialog extends Dialog implements View.OnClickListener {
    private int mLayout = -1;
    private Context mContext;
    private TextView mContentView;
    private TextView mTitleView;
    private View mCancel;
    private View mOk;
    private View mView;
    private View mRootContentView;

    public DoubleButtonDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        init();
    }

    public DoubleButtonDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mContext = context;
        init();
    }

    public DoubleButtonDialog(@NonNull Context context, @StyleRes int themeResId, int layout) {
        super(context, themeResId);
        this.mLayout = layout;
        mContext = context;
        init();
    }

    protected DoubleButtonDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题
        setCanceledOnTouchOutside(false);//外部不可点击
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
        Window window = getWindow();
        mRootContentView = window.getDecorView().findViewById(android.R.id.content);

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
    }

    @Override
    public void show() {
        super.show();
        AnimationSet animatorIn = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.photo_dialog_in_anim);
        mRootContentView.startAnimation(animatorIn);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        AnimationSet animatorIn = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.photo_dialog_out_anim);
        mRootContentView.startAnimation(animatorIn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }

    private String mTitle;
    private String mContent;

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
