package asiantech.dev.yalypro.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PhuQuy on 4/15/15.
 */
public class Parse {

    private static final String TAG = Parse.class.getSimpleName();

    public static String getString(JSONObject jsonObject, String key) {
        String result = null;

        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                result = jsonObject.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static boolean getBoolean(JSONObject jsonObject, String key) {
        boolean result = false;

        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                result = jsonObject.getBoolean(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static int getInt(JSONObject jsonObject, String key) {
        int result = 0;

        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                result = jsonObject.getInt(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static double getDouble(JSONObject jsonObject, String key) {
        double result = 0;

        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                result = jsonObject.getDouble(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
        JSONObject result = null;

        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                result = jsonObject.getJSONObject(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
        JSONArray result = null;

        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                result = jsonObject.getJSONArray(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static JSONObject getItemJsonObject(JSONArray jsonArray, int position) {
        JSONObject result = null;

        if (jsonArray != null && jsonArray.length() > 0 && position >= 0 && position < jsonArray.length()) {
            try {
                result = jsonArray.getJSONObject(position);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


}

