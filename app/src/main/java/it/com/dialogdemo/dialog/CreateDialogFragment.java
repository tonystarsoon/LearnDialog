package it.com.dialogdemo.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import it.com.dialogdemo.R;
import it.com.dialogdemo.base.MyBaseDialogFragment;

/**
 * Created by admin on 2016/6/25.
 * 通过AlertDialog来创建对话框.
 */
public class CreateDialogFragment extends MyBaseDialogFragment {
    private static final String TAG = "CreateDialogFragment";

    public CreateDialogFragment() {
    }

    public CreateDialogFragment(ClickCallBack clickCallBack) {
        mCallBack = clickCallBack;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = View.inflate(mContext, R.layout.dialog, null);
        view.findViewById(R.id.positive).setOnClickListener(this);
        view.findViewById(R.id.negative).setOnClickListener(this);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        return alertDialog;
    }
}
