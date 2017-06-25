package it.com.dialogdemo.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import it.com.dialogdemo.R;
import it.com.dialogdemo.base.BaseDialog;

/**
 * Created by admin on 2017/6/25.
 */

public class CreateDialogFragment extends BaseDialog {
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
        builder.setView(view);
        return super.onCreateDialog(savedInstanceState);
    }
}
