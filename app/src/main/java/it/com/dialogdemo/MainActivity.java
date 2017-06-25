package it.com.dialogdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.com.dialogdemo.dialog.AlterDialogFragment;
import it.com.dialogdemo.dialog.DoubleButtonDialog;
import it.com.dialogdemo.dialog.ShareDialog;

public class MainActivity extends Activity {
    public final static String ALERT_DIALOG_TAG = "ALERT_DIALOG_TAG";
    @BindView(R.id.ivor1_btn)
    Button mIvor1_btn;
    @BindView(R.id.ivor2_btn)
    Button mIvor2_btn;
    @BindView(R.id.ivor3_btn)
    Button mIvor3Btn;
    @BindView(R.id.ivor4_btn)
    Button mIvor4Btn;
    @BindView(R.id.ivor5_btn)
    Button mIvor5Btn;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivor1_btn, R.id.ivor2_btn, R.id.ivor3_btn, R.id.ivor4_btn, R.id.ivor5_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivor1_btn:
                AlterDialogFragment diglog = AlterDialogFragment.newInstance("Ivor", "Handsome Boy!");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                diglog.show(transaction, ALERT_DIALOG_TAG);
                break;
            case R.id.ivor2_btn:
                Dialog mDialog = new Dialog(this, R.style.Diglog);
                mDialog.setContentView(R.layout.messagetoastlayout);
                mDialog.show();
                break;
            case R.id.ivor3_btn:
                ShareDialog dialog = new ShareDialog(this, R.layout.sharelayout);
                dialog.getInstance();
                dialog.show();
                break;

            case R.id.ivor4_btn://自定义的dialog
                DoubleButtonDialog dialog1 = new DoubleButtonDialog(this, R.style.Diglog, R.layout.messagetoastlayout);
                dialog1.setTitle("你好");
                dialog1.setContent("我好");
                dialog1.show();
                break;

            case R.id.ivor5_btn://Alertdialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Diglog);
                builder.setTitle("世界杯");
                builder.setView(View.inflate(this, R.layout.alertdialog_content, null));
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                mAlertDialog = builder.create();
                mAlertDialog.setCanceledOnTouchOutside(false);

                //设置其在屏幕上的位置,alertdialog只能这样设置.
                Window window5 = mAlertDialog.getWindow();
                WindowManager.LayoutParams params5 = window5.getAttributes();
                params5.x = 0;
                params5.y = 0;
                params5.width = -1;
                params5.height = -2;
                mAlertDialog.onWindowAttributesChanged(params5);

                mAlertDialog.show();
                break;
            default:
                break;
        }
    }

    public void onDialogBack(String tag, boolean cancelled, CharSequence message) {
        if (cancelled) {
            String s = tag + " Was Cancelled By Ivor";
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }
        this.mIvor2_btn.setText("Good!");
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
