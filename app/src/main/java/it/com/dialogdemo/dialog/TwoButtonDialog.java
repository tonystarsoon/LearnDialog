package it.com.dialogdemo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.com.dialogdemo.R;
import it.com.dialogdemo.util.ConvertData;


/**
 * 输入两行信息的dialog
 * 1. 两行输入信息，两个取消、确定按钮
 * 2. 弹出动画、收缩动画.
 * 3. 确定、取消事件的监听.
 * 4. 支持横竖屏切换
 */
public class TwoButtonDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "TwoButtonDialog";
    protected Context mContext;
    private ClickCallBack mCallBack;

    public TwoButtonDialog() {
    }

    public TwoButtonDialog(ClickCallBack clickCallBack) {
        super();
        Log.d(TAG, "TwoButtonDialog: ---------");
        this.mCallBack = clickCallBack;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach--------------");
        mContext = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate----------");
        //设置样式和主题
        setStyle(STYLE_NO_TITLE, ConvertData.getIdOfStyle(mContext, "theme_dialog_no_title2"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//q去掉dialog的标题
        Log.d(TAG, "onCreateView: -----------");
        View view = View.inflate(mContext, R.layout.dialog, null);
        view.findViewById(R.id.positive).setOnClickListener(this);
        view.findViewById(R.id.negative).setOnClickListener(this);
        return view;
    }

    //在此方法中设置了dialog的动画.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ------");
        getDialog().getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ------------");
        return super.onCreateDialog(savedInstanceState);
    }

    /**
     * 确定和取消按钮的点击事件
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
