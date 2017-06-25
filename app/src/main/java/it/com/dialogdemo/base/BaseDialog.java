package it.com.dialogdemo.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import it.com.dialogdemo.R;

/**
 * Created by tony on 2016/6/26.
 * 继承dialog实现的对话框的dialog基类.
 */
public class BaseDialog extends Dialog implements View.OnClickListener {
    protected int mLayout = -1;
    protected Context mContext;
    protected TextView mContentView;
    protected TextView mTitleView;
    protected View mCancel;
    protected View mOk;
    protected View mView;
    protected View mRootContentView;
    protected String mTitle;
    protected String mContent;

    public BaseDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId, int layout) {
        super(context, themeResId);
        this.mLayout = layout;
        init(context);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题
        setCanceledOnTouchOutside(false);//外部不可点击
        Window window = getWindow();
        mRootContentView = window.getDecorView().findViewById(android.R.id.content);
//        getWindow().setWindowAnimations(R.style.dialogAnim);//设置动画
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {//返回键让对话框消失.
                    BaseDialog.this.dismiss();
                }
                return true;
            }
        });
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

    @Override
    public void show() {
        super.show();
        //设置动画
//        AnimationSet animatorIn = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.photo_dialog_in_anim);
        Animation animatorIn = AnimationUtils.loadAnimation(mContext, R.anim.photo_dialog_in_anim);
        mRootContentView.startAnimation(animatorIn);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //设置动画
//        AnimationSet animatorOut = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.photo_dialog_out_anim);
        Animation animatorOut = AnimationUtils.loadAnimation(mContext, R.anim.photo_dialog_out_anim);
        mRootContentView.startAnimation(animatorOut);
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
