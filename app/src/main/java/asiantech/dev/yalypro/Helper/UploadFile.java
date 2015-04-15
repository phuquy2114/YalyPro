package asiantech.dev.yalypro.Helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import asiantech.dev.yalypro.Common.Common;

/**
 * Created by PhuQuy on 4/15/15.
 */
public class UploadFile extends AsyncTask<Void, Void, JSONObject> {

    private ProgressDialog progressDialog;
    private String fileName;
    private HashMap<String, String> parameters = new HashMap<>();
    private Context mContext;
    private String Url = "";

    public UploadFile(Context context, String filePath, String fullName, String description,
                      String gender, String pub_gender, String birthday,  IPostAudioListener p) {

        this.fileName = filePath;
        mContext = context;

        Log.d("======mDay", birthday);

        mListener = p;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Update Profile...");
        progressDialog.setCanceledOnTouchOutside(false);

        parameters.put("full_name", fullName);
        parameters.put("description", description);
        parameters.put("gender", gender);
        parameters.put("pub_gender", pub_gender);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {

        JSONObject result = null;
        try {
            MultipartUtility multipartUtility = new MultipartUtility(mContext, Url);
            for (String name : parameters.keySet()) {
                multipartUtility.addFormField(name, parameters.get(name));
            }
            if (!fileName.equals("")) {
                // reduce size before post
//                fileName = Common.decodeFile(fileName, 200, 200);
                Bitmap bm = Picasso.with(mContext).load("file://" + fileName).centerCrop().resize(200, 200).get();
                fileName = Common.saveBitmapToFile(bm);
                multipartUtility.addFilePart("profile_image", new File(fileName));
            }
            result = multipartUtility.finish();

            if (result != null) {
                Log.d("qqq", result.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        progressDialog.dismiss();

        boolean success = false;

        if (jsonObject != null) {
            int ok = JsonUtil.getInt(JsonUtil.getJSONObject(jsonObject, "result"), "ok");
            if (ok == 1) {
                success = true;
            }
        }

        if (mListener != null) {
            mListener.onPostAudioFinish(success);
        }
    }


    private IPostAudioListener mListener;

    public interface IPostAudioListener {
        void onPostAudioFinish(boolean success);
    }
}
