package it.com.dialogdemo.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import it.com.dialogdemo.base.BaseDialogFragment;
import it.com.dialogdemo.util.ConvertData;


/**
 * 就显示一个dialog
 */
public class ShowPhotoDialog extends BaseDialogFragment {
    private static final String TAG = "ShowPhotoDialog";
    public static final String DOWNLOAD_QR_CODE = "messagetoast_man";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = new ImageView(mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        imageView.setLayoutParams(layoutParams);

        int idOfDrawable = ConvertData.getIdOfDrawable(mContext, DOWNLOAD_QR_CODE);

        if (idOfDrawable != 0) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(idOfDrawable));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "image is clicked!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.e(TAG, DOWNLOAD_QR_CODE + " is not exist");
        }
        if (rootView != null) {
            rootView.addView(imageView);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }
}
