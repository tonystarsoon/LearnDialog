package it.com.dialogdemo.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 *
 */
public class ConvertData {
    private static final String TAG = "ConvertData";
    public static boolean isPrint = false;
    public static boolean isPrintAngle = false;
    private static Typeface mTf;
    private static Typeface mTf2;

    public static int dimToPx(Context context, String idName) {
        int resId = context.getResources().getIdentifier(idName, "dimen", context.getPackageName());
        int size = context.getResources().getDimensionPixelSize(resId);
        if (isPrint)
            Log.v(TAG, idName + " size:" + size);
        return size;
    }

    public static int getcolor(Context context, String colorName) {
        int resId = context.getResources().getIdentifier(colorName, "color", context.getPackageName());
        int size = 0;
        try {
            size = context.getResources().getColor(resId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        if (isPrint)
            Log.v(TAG, colorName + " color:" + size);
        return size;
    }

    public static View getLayout(Context context, String layoutName) {
        int resId = context.getResources().getIdentifier(layoutName, "layout", context.getPackageName());
        if (resId == 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(resId, null);
    }

    public static int getId(Context context, String idName) {
        int resId = context.getResources().getIdentifier(idName, "id", context.getPackageName());
        return resId;
    }

    public static Bitmap getBitmap(Context context, String drawName) {
        int resId = context.getResources().getIdentifier(drawName, "drawable", context.getPackageName());
        if (resId == 0) {
            return null;
        }
        BitmapDrawable drawable = (BitmapDrawable) context.getResources().getDrawable(resId);
        return drawable.getBitmap();
    }

    public static Bitmap scalBitmap(Context context, Bitmap bitmap, float witdh, float height) {
        Matrix matrix = new Matrix();
        float rate = (float) witdh / bitmap.getWidth();
        float rate2 = (float) height / bitmap.getHeight();
        matrix.postScale(rate, rate);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
    }

    public static Bitmap rotateBitmap(Context context, Bitmap bitmap, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
    }

    public static float getAngleByPoint(Point point1, Point cPonit) {
//    point1.y*=-1;
        float size = 0;
//     size=(float) (Math.atan((float)(point1.y-cPonit.y)/(point1.x-cPonit.x))/Math.PI*180);
        size = (float) (Math.atan2(point1.y - cPonit.y, point1.x - cPonit.x) * 180 / Math.PI);
        if (isPrintAngle) {
            Log.v(TAG, "size:" + size + " point1:" + point1.toString() + " point2:" + cPonit.toString());
        }
        return size;
    }

    public static float getAngleByPoint(Point point) {
        return getAngleByPoint(point, new Point(0, 0));
    }

    public static Bitmap drawableToRoundBitmap(Bitmap bm, int rx, int ry) {
        final Rect rect = new Rect(0, 0, bm.getWidth(), bm.getHeight());

        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(new RectF(rect), rx, ry, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        final Rect src = new Rect(0, 0, bm.getWidth(), bm.getHeight());
        canvas.drawBitmap(bm, src, rect, paint);
        return bitmap;
    }

    public static Bitmap decodeSampledBitmapFromResource(Context context, String drawName, int reqWidth, int reqHeigt) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int resId = context.getResources().getIdentifier(drawName, "drawable", context.getPackageName());
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        options.inSampleSize = calculateInsampleSize(options, reqWidth, reqHeigt);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), resId, options);
    }

    public static int calculateInsampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeght = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeght / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Typeface getTypeface(Context context) {
    /*
    if(mTf == null){
        AssetManager mgr=context.getAssets();//得到AssetManager
        mTf=Typeface.createFromAsset(mgr, "fonts/roboto-condensed.ttf");//根据路径得到Typeface
    }
    */
        return mTf;
    }

    public static Typeface getTypeface2(Context context) {
    /*
    if(mTf2 == null){
        AssetManager mgr=context.getAssets();//得到AssetManager
        mTf2=Typeface.createFromAsset(mgr, "fonts/roboto-regular.ttf");//根据路径得到Typeface
    }
    */
        return mTf2;
    }

    public static String getString(Context context, String txt_set) {

        int resId = context.getResources().getIdentifier(txt_set, "string", context.getPackageName());
        try {
            if (resId != 0) {
                return context.getResources().getString(resId);
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "e:" + e.getLocalizedMessage());
            return null;
        }
        return null;
    }

    public static Animation getAnim(Context context, String animName) {
        int resId = context.getResources().getIdentifier(animName, "anim", context.getPackageName());
        if (resId == 0) {
            return null;
        }
        return AnimationUtils.loadAnimation(context, resId);
    }

    public static int getStatusBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static Drawable getDrawableByPkg(String pkg, Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(pkg, 0);
            return info.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap scalBitmap(Context context, Drawable drawable, float witdh, float height) {
        Matrix matrix = new Matrix();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        float rate = (float) witdh / bitmap.getWidth();
        float rate2 = (float) height / bitmap.getHeight();
        matrix.postScale(rate, rate);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
    }

    public static int getIdOfDrawable(Context context, String identifierName) {
        int resId = context.getResources().getIdentifier(identifierName, "drawable", context.getPackageName());
        if (resId == 0) {
            Log.e(TAG, " drawable is not exists:" + identifierName);
        }
        return resId;
    }

    public static int getIdOfColor(Context context, String identifierName) {
        int resId = context.getResources().getIdentifier(identifierName, "color", context.getPackageName());
        if (resId == 0) {
            Log.e(TAG, " color is not exists:" + identifierName);
        }
        return resId;
    }

    public static int getIdOfDimen(Context context, String identifierName) {
        int resId = context.getResources().getIdentifier(identifierName, "dimen", context.getPackageName());
        if (resId == 0) {
            Log.e(TAG, " dimen is not exists:" + identifierName);
        }
        return resId;
    }

    public static int getIdOfStyle(Context context, String identifierName) {
        int resId = context.getResources().getIdentifier(identifierName, "style", context.getPackageName());
        if (resId == 0) {
            Log.e(TAG, " style is not exists:" + identifierName);
        }
        return resId;
    }
}
