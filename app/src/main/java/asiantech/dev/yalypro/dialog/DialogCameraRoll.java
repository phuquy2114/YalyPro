package asiantech.dev.yalypro.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import asiantech.dev.yalypro.R;

/**
 * Created by PhuQuy on 4/14/15.
 */
public class DialogCameraRoll extends DialogFragment implements View.OnClickListener {

    private View mView;
    private TextView mTxtTake;
    private TextView mTxtTakePhoto;
    private TextView mTxtCancel;
    private BaseDialogListener mListener;
    private Context mContext;


    public DialogCameraRoll (Context mContext , BaseDialogListener listener) {
        this.mContext = mContext;
        mListener = listener;
    }


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//
//        try {
//            baseDialogListener = (BaseDialogListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
//        }
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_cameraroll, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        setValues();
        setEvents();
    }

    public void initialize() {
        mTxtCancel = (TextView) mView.findViewById(R.id.txt_cancel);
        mTxtTake = (TextView) mView.findViewById(R.id.txt_take_loading);
        mTxtTakePhoto = (TextView) mView.findViewById(R.id.txt_take_photo);
    }

    public void setValues() {

    }

    public void setEvents() {
        mTxtTakePhoto.setOnClickListener(this);
        mTxtCancel.setOnClickListener(this);
        mTxtTake.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_cancel:
                dismiss();
                break;
            case R.id.txt_take_loading:
                if (mListener!=null){
                    mListener.setTake();
                    dismiss();
                }

                break;
            case R.id.txt_take_photo:
                if (mListener!=null){
                    mListener.setTakePhoto();
                    dismiss();
                }

                break;
            default:
                break;
        }
    }

    public interface BaseDialogListener {
        public void setTake();

        public void setTakePhoto();

    }

    public void setBaseDialogListener(BaseDialogListener l) {
        this.mListener = l;
    }
}
