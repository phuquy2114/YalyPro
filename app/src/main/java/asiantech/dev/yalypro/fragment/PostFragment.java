package asiantech.dev.yalypro.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;

import asiantech.dev.yalypro.Helper.BaseFragment;
import asiantech.dev.yalypro.Helper.ConnectingNetwork;
import asiantech.dev.yalypro.R;
import asiantech.dev.yalypro.cameraRoll.CameraRollActivity;
import asiantech.dev.yalypro.cameraRoll.ImageOnPhone;
import asiantech.dev.yalypro.dialog.DialogCameraRoll;


/**
 * Created by PhuQuy on 4/13/15.
 */
public class PostFragment extends BaseFragment implements View.OnClickListener, DialogCameraRoll.BaseDialogListener {

    private ImageView mImgThumnail;
    private EditText mEdtName;
    private EditText mEditNumber;
    private RadioGroup mRadioGroup;
    private View mView;
    private TextView mBtnPost;

    private EncodeBase64 mTaskEncode;
    private static final int CAMERA_REQUEST = 1888;
    private static final int GALLERY_REQUEST = 1999;

    private ArrayList<ImageOnPhone> mImageOnPhonesSelected = new ArrayList<>();
    private Bitmap photoUser;
    private String mAvatarPath = "";
    private String type = "";
    private String mAvatarbase ="";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_post, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImgThumnail = (ImageView) view.findViewById(R.id.img_avatar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        setValue();
        setEvents();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                photoUser = (Bitmap) data.getExtras().get("data");

                mImgThumnail.setImageBitmap(photoUser);
                Uri uri = data.getData();
                if (uri != null) {

                    mAvatarPath = getImageOnPhoneFromUri(getActivity(), uri).getPath();
                    Log.d("aaa>>>", "file://" + mAvatarPath);
                    Picasso.with(getActivity())
                            .load("file://" + mAvatarPath)
                            .resize(300, 300)
                            .error(R.mipmap.ic_launcher)
                            .centerCrop()
                            .into(mImgThumnail);
                  //  executeEncode64("file://" + mAvatarPath);
                } else {
                    Log.d("qqq", "uri is null");
                }
            }
        } else if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data.hasExtra(CameraRollActivity.KEY_PARCEL_PHOTOS_SELECTED)) {
                mImageOnPhonesSelected = data.getParcelableArrayListExtra(CameraRollActivity.KEY_PARCEL_PHOTOS_SELECTED);
                if (mImageOnPhonesSelected.size() > 0) {
                    mAvatarPath = mImageOnPhonesSelected.get(0).getPath();
                    Picasso.with(getActivity())
                            .load("file://" + mAvatarPath)
                            .resize(300, 300)
                            .error(R.mipmap.ic_launcher)
                            .centerCrop()
                            .into(mImgThumnail);
                 //   executeEncode64("file://" + mAvatarPath);
                }

            }
        }
    }

    public void initialize() {

        mEdtName = (EditText) mView.findViewById(R.id.edit_name);
        mEditNumber = (EditText) mView.findViewById(R.id.edit_number);
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.group_radio);
        mBtnPost = (TextView) mView.findViewById(R.id.btn_post);
    }

    public void setValue() {

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_square) {
                    type ="square";
                } else if (checkedId == R.id.radio_round) {
                    type ="round";
                } else if (checkedId == R.id.radio_pointed) {
                    type ="pointed";
                } else if (checkedId == R.id.radio_tu) {
                    type ="tu";
                }
            }
        });
    }

    public void setEvents() {
        mImgThumnail.setOnClickListener(this);
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
                DialogCameraRoll dialogCameraRoll = new DialogCameraRoll(getActivity(), this);
                dialogCameraRoll.show(getFragmentManager(), "");
                break;
            case R.id.btn_post:
                    new PostInfo().execute("http://ndlapi.somee.com/api/Measure/AddMeasure");
                break;
            default:
                break;
        }
    }

    @Override
    public void setTake() {
        Intent cameraRoll = new Intent(getActivity(), CameraRollActivity.class);
        startActivityForResult(cameraRoll, GALLERY_REQUEST);

    }

    @Override
    public void setTakePhoto() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public static ImageOnPhone getImageOnPhoneFromUri(Context context, Uri uri) {
        ImageOnPhone imageOnPhone = new ImageOnPhone();
        String path;
        Bitmap bitmap;

        Cursor cursor = null;
        final String[] projection = {
                MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID
        };

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                int idColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);

                path = cursor.getString(dataColumnIndex);
                int id = cursor.getInt(idColumnIndex);

                bitmap = MediaStore.Images.Thumbnails.getThumbnail(context
                                .getContentResolver(),
                        id, MediaStore.Images.Thumbnails.MICRO_KIND, null);

                imageOnPhone.setBitmap(bitmap);
                imageOnPhone.setPath(path);
                imageOnPhone.setSelected(true);

            }
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return imageOnPhone;

    }

    // encode base 64 su dung cho Volley
    private void executeEncode64(String urlPath) {
        if (mTaskEncode != null && mTaskEncode.getStatus() == AsyncTask.Status.RUNNING) {
            return;
        }
        mTaskEncode = new EncodeBase64();
        mTaskEncode.execute(urlPath);
    }

    public class EncodeBase64 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //  mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            result = encode(params[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mAvatarbase = s;
            //   mProgressBar.setVisibility(View.GONE);
        }

        public String encode(String path) {
            return getStringBase64FromBitmap(path);
        }


    }


    public class PostInfo extends AsyncTask<String , Void , JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray jsonArray = null;
            try {
                jsonArray = ConnectingNetwork.getInstance().executePostReturnJSONArray(params[0], getparams());
            } catch (Exception e){
                e.printStackTrace();

            }
            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            Log.d("qqq",""+jsonArray);
        }

        public ArrayList<NameValuePair> getparams (){
            ArrayList<NameValuePair> params = new ArrayList<>();

            String mAvatar = getStringBase64FromBitmap("file://" + mAvatarPath);
            params.add(new BasicNameValuePair("id","0"));
            params.add(new BasicNameValuePair("measurer",mEdtName.getText().toString().trim()));
            params.add(new BasicNameValuePair("size",mEditNumber.getText().toString().toString()));
            params.add(new BasicNameValuePair("shoe_type",type));
            params.add(new BasicNameValuePair("img",mAvatar));

            return params;
        }


    }
}
