package it.com.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class ShareDialog {
    private Context mContext;
    private int mLayoutId;
    private Dialog mDialog;

    public ShareDialog(Context context, int layoutId) {
        mContext = context;
        this.mLayoutId = layoutId;
    }

    public View getInstance() {
        View rootView = LayoutInflater.from(mContext).inflate(this.mLayoutId, null);
        mDialog = new Dialog(mContext, R.style.sharedialogWindowStyle);
        mDialog.setContentView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT));

        Window window = mDialog.getWindow();
        window.setWindowAnimations(R.style.main_menu_animstyle);//设置一个动画
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = window.getWindowManager().getDefaultDisplay().getHeight();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mDialog.onWindowAttributesChanged(params);
        mDialog.setCanceledOnTouchOutside(true);
        rootView.findViewById(R.id.cancelview).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cancel();
            }
        });
        return rootView;
    }

    public void cancel() {
        mDialog.cancel();
    }

    public void show() {
        mDialog.show();
    }
}