package asiantech.dev.yalypro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import asiantech.dev.yalypro.Helper.BaseFragment;
import asiantech.dev.yalypro.R;
import asiantech.dev.yalypro.dialog.DialogCameraRoll;


/**
 * Created by PhuQuy on 4/13/15.
 */
public class PostFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mImgThumnai;
    private EditText mEdtName;
    private EditText mNumber;
    private RadioGroup mRadioGroup;
    private View mView;
    private TextView mBtnPost;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_post, null, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        setValue();
        setEvents();
    }

    public void initialize() {
        mImgThumnai = (ImageView) mView.findViewById(R.id.img_avatar);
        mEdtName = (EditText) mView.findViewById(R.id.edit_name);
        mNumber = (EditText) mView.findViewById(R.id.edit_number);
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.group_radio);
        mBtnPost = (TextView) mView.findViewById(R.id.btn_post);
    }

    public void setValue() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_square) {
                    Toast.makeText(getActivity(), "SQUARE", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_round) {
                    Toast.makeText(getActivity(), "ROUND", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_pointed) {
                    Toast.makeText(getActivity(), "POINTED", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_tu) {
                    Toast.makeText(getActivity(), "TU", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setEvents() {
        mImgThumnai.setOnClickListener(this);
        mBtnPost.setOnClickListener(this);
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
            case R.id.img_avatar:
                DialogCameraRoll dialogCameraRoll = new DialogCameraRoll(getActivity());
                dialogCameraRoll.show();
                break;
            case R.id.btn_post:
                Toast.makeText(getActivity(), "Post Success ", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
