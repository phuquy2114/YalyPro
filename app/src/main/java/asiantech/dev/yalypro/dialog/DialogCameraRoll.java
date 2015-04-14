package asiantech.dev.yalypro.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import asiantech.dev.yalypro.R;

/**
 * Created by PhuQuy on 4/14/15.
 */
public class DialogCameraRoll extends Dialog {

    private Context mContext;
    private View mRootview;


    public DialogCameraRoll(Context context) {
        super(context);
    }

    public DialogCameraRoll(Context context, int theme) {
        super(context, theme);
    }

    protected DialogCameraRoll(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void init(Context mContext) {
        this.mContext = mContext;
        mRootview = LayoutInflater.from(mContext).inflate(R.layout.dialog_cameraroll, null, false);

    }

}
