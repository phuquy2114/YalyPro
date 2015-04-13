package asiantech.dev.yalypro.cameraRoll;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import asiantech.dev.yalypro.R;


public class CameraRollActivity extends Activity {
    private static final String TAG = "CameraRollActivity";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    // use to get photos selected
    public static final String KEY_PARCEL_PHOTOS_SELECTED = "photo_selected";

    private static final String KEY_PARCEL_LIST_PHOTO_EXIST = "list_photo_exist";
    private static final String KEY_PARCEL_MAX_PHOTOS = "max_photos";

    // max photo be chosen
    private static final int MAX_PHOTO_DEFAULT = 1;
    private int mMaxPhotoBeChosen = MAX_PHOTO_DEFAULT;


    // Cursor
    private Cursor mCursor;

    // 1. Data
    private ArrayList<ImageOnPhone> mImageOnPhonesAll = new ArrayList<ImageOnPhone>();
    private ArrayList<ImageOnPhone> mImageOnPhonesSelected = new ArrayList<ImageOnPhone>();
    private ArrayList<ImageOnPhone> mImageOnPhonesFromPostingPhoto;
    private boolean mFirstTime = true;

    // 2. Adapter
    private CameraRollGridAdapter mGridAdapter;

    // 3. Grid View
    private GridView mGridView;

    // 4. Task
    private LoadingImageFromGalleryOnPhone mTask;

    // xml
    private ProgressBar mProgressBar;
    private ImageView mImgHeaderClose;
    private TextView mTxtHeaderRight;

    public static void startActivity(Activity activity, int requestCode,
                                     ArrayList<ImageOnPhone> list, int maxPhotos) {
        Intent intent = new Intent(activity, CameraRollActivity.class);
        intent.putParcelableArrayListExtra(KEY_PARCEL_LIST_PHOTO_EXIST, list);
        intent.putExtra(KEY_PARCEL_MAX_PHOTOS, maxPhotos);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivity(Fragment fragment, int requestCode,
                                     ArrayList<ImageOnPhone> list, int maxPhotos) {
        Intent intent = new Intent(fragment.getActivity(), CameraRollActivity.class);
        intent.putParcelableArrayListExtra(KEY_PARCEL_LIST_PHOTO_EXIST, list);
        intent.putExtra(KEY_PARCEL_MAX_PHOTOS, maxPhotos);
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_roll);
        initialize();
        initView();
        initValue();
        setListener();
        if (mFirstTime) {
            loadImages();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCursor != null) {
            mCursor.close();
        }

        if (mTask != null) {
            mTask.cancel(true);
        }
    }

    protected void initialize() {
        mImageOnPhonesFromPostingPhoto = getIntent().getParcelableArrayListExtra(KEY_PARCEL_LIST_PHOTO_EXIST);
        mMaxPhotoBeChosen = getIntent().getIntExtra(KEY_PARCEL_MAX_PHOTOS, MAX_PHOTO_DEFAULT);

        mGridAdapter = new CameraRollGridAdapter(this, mImageOnPhonesAll);

    }


    protected void initView() {
        mGridView = (GridView) findViewById(R.id.acr_grid_view);
        mGridView.setAdapter(mGridAdapter);

        mProgressBar = (ProgressBar) findViewById(R.id.acr_progress_bar);
        mImgHeaderClose = (ImageView) findViewById(R.id.img_left);
        mTxtHeaderRight = (TextView) findViewById(R.id.txt_right);
    }

    protected void initValue() {

    }

    protected void setListener() {
        mImgHeaderClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTxtHeaderRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onDoneClick(v);
            }
        });

        mGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ImageOnPhone imageOnPhone = mGridAdapter.getItem(position);

                // todo
                if (imageOnPhone != null) {
                    if (imageOnPhone.isSelected()) {
                        // to remove that photo into the list
                        imageOnPhone.setSelected(false);
                        mImageOnPhonesSelected.remove(imageOnPhone);
                    } else {
                        // to add that photo into the list
                        // but check the number in advanced
                        if (mImageOnPhonesSelected.size() < mMaxPhotoBeChosen) {
                            // to be allowed to choose a new photo
                            imageOnPhone.setSelected(true);
                            mImageOnPhonesSelected.add(imageOnPhone);
                        } else {
                            for (int i = 0; i < mImageOnPhonesSelected.size(); i++) {
                                mImageOnPhonesSelected.get(i).setSelected(false);
                            }
                            mImageOnPhonesSelected.clear();

                            imageOnPhone.setSelected(true);
                            mImageOnPhonesSelected.add(imageOnPhone);
                        }
                    }

                } else {
                    onTakePhotoClick();
                    // item take photo
//                    if (mImageOnPhonesSelected.size() < mMaxPhotoBeChosen) {
//                        onTakePhotoClick();
//                    } else {
//                        // show the notice
//                        Toast.makeText(getApplicationContext(),
//                                "You can't add more than " + mMaxPhotoBeChosen
//                                        + " photos", Toast.LENGTH_SHORT).show();
//                    }

                }

                mGridAdapter.notifyDataSetChanged();
            }
        });

    }

    private void loadImages() {
        mTask = new LoadingImageFromGalleryOnPhone();
        mTask.execute();
    }

    private class LoadingImageFromGalleryOnPhone extends AsyncTask<Void, ImageOnPhone, ArrayList<ImageOnPhone>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            displayCircleProgressBar(true);
        }


        @Override
        protected ArrayList<ImageOnPhone> doInBackground(Void... params) {
            ArrayList<ImageOnPhone> imageOnPhones = new ArrayList<ImageOnPhone>();

            // Columns
            String[] columns = {MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media._ID};

            // Order By
            String sortOrder = MediaStore.Images.Media._ID;

            // Cursor
            mCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    columns, null, null, sortOrder);

            // Count
            int count = mCursor.getCount();

            // ImageOnPhone
            ImageOnPhone imageOnPhone;

            // Data Index
            int idColumnIndex = mCursor.getColumnIndex(MediaStore.Images.Media._ID);
            int dataColumnIndex = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int id;
            String path;
//			Bitmap bitmap;

            for (int i = count - 1; i >= 0; i--) {
                if (isCancelled()) {
                    break;
                }

                mCursor.moveToPosition(i);

                id = mCursor.getInt(idColumnIndex);
                path = mCursor.getString(dataColumnIndex);

//				bitmap = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), 
//						id, MediaStore.Images.Thumbnails.MICRO_KIND, null);

                imageOnPhone = new ImageOnPhone();
                imageOnPhone.setId(id);
                imageOnPhone.setPath(path);
//				imageOnPhone.setBitmap(bitmap);
                imageOnPhone.setSelected(false);

                imageOnPhones.add(imageOnPhone);
            }

            return imageOnPhones;
        }


        @Override
        protected void onPostExecute(ArrayList<ImageOnPhone> result) {
            super.onPostExecute(result);
            if (isCancelled()) {
                result = null;
            }

            mImageOnPhonesAll.clear();

            if (result != null && !result.isEmpty()) {
                mImageOnPhonesAll.addAll(0, result);
            }

            if (mFirstTime) {
                markChosenPhotoFromPostingScreen();
            } else {
                mImageOnPhonesFromPostingPhoto.clear();
                mImageOnPhonesFromPostingPhoto.addAll(mImageOnPhonesSelected);
                mImageOnPhonesSelected.clear();
                markChosenPhotoFromPostingScreen();
                markNewPhoto();

                if (mImageOnPhonesSelected.size() == 1) {
                    onDoneClick(null);
                }
            }


            mGridAdapter.notifyDataSetChanged();
            displayCircleProgressBar(false);
            mFirstTime = false;
        }

    }

    private void markNewPhoto() {
        // mark the first photo after taking a photo
        if (!mFirstTime && mImageOnPhonesAll != null && !mImageOnPhonesAll.isEmpty()) {
            ImageOnPhone firstPhoto = mImageOnPhonesAll.get(0);
            firstPhoto.setSelected(true);
            mImageOnPhonesSelected.add(firstPhoto);
        }
    }

    private void markChosenPhotoFromPostingScreen() {
        if (mImageOnPhonesFromPostingPhoto != null && !mImageOnPhonesFromPostingPhoto.isEmpty()) {

            for (ImageOnPhone imageOnPhone : mImageOnPhonesAll) {
                for (ImageOnPhone childImageOnPhone : mImageOnPhonesFromPostingPhoto) {

                    if (imageOnPhone.getPath().equals(childImageOnPhone.getPath())) {
                        imageOnPhone.setSelected(true);
                        mImageOnPhonesSelected.add(imageOnPhone);
                    }
                }
            }
        }

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

    private void displayCircleProgressBar(boolean showCircleProgressBar) {
        mProgressBar.setVisibility(showCircleProgressBar ? View.VISIBLE : View.GONE);
    }

    private void onDoneClick(View v) {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(KEY_PARCEL_PHOTOS_SELECTED, mImageOnPhonesSelected);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void onTakePhotoClick() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Uri uri = data.getData();
                        if (uri != null) {
                            ImageOnPhone imageOnPhone = getImageOnPhoneFromUri(this, data.getData());
                            mImageOnPhonesAll.add(0, imageOnPhone);
                            mGridAdapter.notifyDataSetChanged();
                            mGridView.smoothScrollToPosition(0);

                            mImageOnPhonesSelected.add(imageOnPhone);
                            if (mImageOnPhonesSelected.size() == 0) {
                                onDoneClick(null);
                            }
                        } else {
                            mProgressBar.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loadImages();
                                }
                            }, 3000);
                        }
                    }
                }
                break;

            default:
                break;
        }

    }


}
