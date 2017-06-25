package it.com.dialogdemo.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import it.com.dialogdemo.R;
import it.com.dialogdemo.util.ConvertData;

/**
 * dialog的基类.
 */
public class BaseDialogFragment extends DialogFragment {
    private static final String TAG = "BaseDialogFragment";
    protected Context mContext;
    protected FrameLayout rootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.v(TAG, "onAttach");
        mContext = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        //设置样式和主题
        setStyle(STYLE_NO_TITLE, ConvertData.getIdOfStyle(mContext, "theme_dialog_no_title"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        Window window = getDialog().getWindow();
        rootView = new FrameLayout(mContext);
        if (window != null) {
            window.setGravity(Gravity.FILL);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        if (rootView != null) {
            rootView.setBackgroundColor(0x99000000);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            dismiss();
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置动画。
        getDialog().getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        if (view == null) {
            Log.e(TAG, "view==null");
            dismiss();
        } else {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            ViewGroup.LayoutParams params = rootView.getLayoutParams();
            params.width = dm.widthPixels;
            params.height = dm.heightPixels;
            rootView.setLayoutParams(params);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog() != null ? getDialog().getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.gravity = Gravity.CENTER;  //设置子view在自己父view中是居中显示的.
            windowParams.dimAmount = 0.1f;          //设置窗口变暗的程度
            window.setAttributes(windowParams);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
