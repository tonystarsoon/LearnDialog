package it.com.dialogdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.com.dialogdemo.base.MyBaseDialogFragment;
import it.com.dialogdemo.dialog.CreateDialogFragment;
import it.com.dialogdemo.dialog.DoubleButtonDialog;
import it.com.dialogdemo.dialog.ShareDialog;
import it.com.dialogdemo.dialog.ShowPhotoDialog;
import it.com.dialogdemo.dialog.TwoButtonDialog;

public class MainActivity extends FragmentActivity {
    public final static String ALERT_DIALOG_TAG = "ALERT_DIALOG_TAG";
    @BindView(R.id.share_dialog_button)
    Button mIvor1_btn;
    @BindView(R.id.ivor2_btn)
    Button mIvor2_btn;
    @BindView(R.id.ivor3_btn)
    Button mIvor3Btn;
    @BindView(R.id.ivor4_btn)
    Button mIvor4Btn;
    @BindView(R.id.dialogButton_5)
    Button mIvor5Btn;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.share_dialog_button, R.id.ivor2_btn, R.id.ivor3_btn, R.id.ivor4_btn,
            R.id.dialogButton_5, R.id.show_photo_dialog, R.id.show_ok_and_cancel_dialog, R.id.show_dialog_by_onCreateDialog})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_dialog_button:
                //常用的从屏幕底部弹出一个对话框
                ShareDialog dialog = new ShareDialog(this, R.layout.sharelayout);
                dialog.getInstance();
                dialog.show();
                break;
            case R.id.ivor2_btn:
                //继承自Dialog的dialog
                DoubleButtonDialog dialog1 = new DoubleButtonDialog(this, R.style.Diglog, R.layout.messagetoastlayout);
                dialog1.setTitle("你好");
                dialog1.setContent("我好");
                dialog1.show();
                break;
            case R.id.show_photo_dialog:
                //显示一张图片
                ShowPhotoDialog showPhotoDialog = new ShowPhotoDialog();
                showPhotoDialog.show(getSupportFragmentManager(), "messagetoast_man");
                break;

            case R.id.show_ok_and_cancel_dialog:
                //确定、取消按钮的dialog,继承自DialogFragment
                new TwoButtonDialog(new TwoButtonDialog.ClickCallBack() {
                    @Override
                    public void positiveClick() {
                        Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void negativeClick() {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                }).show(getSupportFragmentManager(), "fuck2");
                break;

            case R.id.show_dialog_by_onCreateDialog:
                //通过onCreateDialog中创建dialog实现对话框;
                new CreateDialogFragment(new MyBaseDialogFragment.ClickCallBack() {
                    @Override
                    public void positiveClick() {
                        Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void negativeClick() {
                        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                    }
                }).show(getSupportFragmentManager(), "CreateDialogFragment");
                break;

            case R.id.dialogButton_5:
                //Alertdialog非常不建议使用这种方式,建议直接使用整个布局都是自定义的而不是默认的确定、取消按钮.
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.theme_dialog_no_title);
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
                /*WindowManager.LayoutParams params5 = window5.getAttributes();
                params5.x = 0;
                params5.y = 0;
                params5.width = -1;
                params5.height = -2;
                mAlertDialog.onWindowAttributesChanged(params5);*/
                window5.getAttributes().gravity = Gravity.CENTER;
                window5.getAttributes().windowAnimations = R.style.dialogAnim;
                mAlertDialog.show();
                break;
            case R.id.ivor3_btn:
                break;
            case R.id.ivor4_btn:
                break;
            default:
                break;
        }
    }
}
