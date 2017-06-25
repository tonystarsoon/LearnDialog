package it.com.dialogdemo.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import it.com.dialogdemo.MainActivity;
import it.com.dialogdemo.R;

public class AlterDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static AlterDialogFragment newInstance(String title, String message) {
        AlterDialogFragment alterDialogFragment = new AlterDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        alterDialogFragment.setArguments(args);
        return alterDialogFragment;
    }

    private String getTitle() {
        return getArguments().getString("title");
    }

    private String getMessage() {
        return getArguments().getString("message");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Diglog)
                .setTitle(getTitle())
                .setMessage(getMessage())
                .setPositiveButton("OK", this)
                .setNegativeButton("Cancel", this);
        AlertDialog alertDialog = builder.create();
        WindowManager.LayoutParams attributes = alertDialog.getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER;
//        attributes.windowAnimations = R.style.
        return alertDialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        boolean isCancel = false;
        if (which == AlertDialog.BUTTON_NEGATIVE) {
            isCancel = true;
        }
        MainActivity act = (MainActivity) getActivity();
        act.onDialogBack(getTag(), isCancel, "Good Job!");
    }
}