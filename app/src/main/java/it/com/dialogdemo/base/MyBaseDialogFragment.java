package it.com.dialogdemo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;

import it.com.dialogdemo.R;
import it.com.dialogdemo.util.ConvertData;


/**
 * Created by admin on 2017/6/25.
 */

public class MyBaseDialogFragment extends DialogFragment implements View.OnClickListener{
    private static final String BASE_DIALOG = "MyBaseDialogFragment";
    protected Context mContext;
    protected MyBaseDialogFragment.ClickCallBack mCallBack;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(BASE_DIALOG, "onAttach--------------");
        mContext = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(BASE_DIALOG, "onCreate----------");
        //设置样式和主题
        setStyle(STYLE_NO_TITLE, ConvertData.getIdOfStyle(mContext, "theme_dialog_no_title2"));
    }

    /**
     * 确定和取消按钮的点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.negative:
                if (mCallBack != null) {
                    mCallBack.negativeClick();
                }
                dismiss();
                break;
            case R.id.positive:
                if (mCallBack != null) {
                    mCallBack.positiveClick();
                }
                dismiss();
                break;
        }
    }

    public interface ClickCallBack {
        void positiveClick();

        void negativeClick();
    }
}
