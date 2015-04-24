package asiantech.dev.yalypro.Helper;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by PhuQuy on March 18th 2015.
 */
public class ConnectingNetwork {
    public static final String TAG = ConnectingNetwork.class.getSimpleName();
    public static final int READ_TIMEOUT = 10 * 1000;
    public static final int CONNECT_TIMEOUT = 30 * 1000;
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String GET = "GET";
    public static int resultresponse = 200;
    private static ConnectingNetwork mInstance;

    private ConnectingNetwork() {
    }

    public static final ConnectingNetwork getInstance() {
        if (mInstance == null) {
            mInstance = new ConnectingNetwork();
        }

        return mInstance;
    }

    /**
     *
     * @param conn
     */

    private void addHeader(HttpURLConnection conn) {
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Language", "en-US");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
    }

    /**
     *
     * @param urlString
     * @param params
     * @return
     * @throws IOException
     * @throws JSONException
     */

    public String executePostReturnString(String urlString, ArrayList<NameValuePair> params) throws IOException, JSONException {
        String result = null;
        InputStream is = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // add header
            addHeader(conn);

            // add content body
            writeStream(conn.getOutputStream(), params);

            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            resultresponse = response;
            if (response < 400) {
                is = conn.getInputStream();
                result = readStream(is);

            } else {
                is = conn.getErrorStream();
                result = readStream(is);
            }

        } finally {
            if (is != null) {
                is.close();
            }

            if (conn != null) {
                conn.disconnect();
            }
        }

        return result;
    }

    /**
     *
     * @param urlString
     * @param params
     * @return
     * @throws IOException
     * @throws JSONException
     */

    public JSONObject executePostReturnJSONObject(String urlString, ArrayList<NameValuePair> params) throws IOException, JSONException {
        String result = executePostReturnString(urlString, params);
        return result == null ? null : new JSONObject(result);
    }

    /**
     *
     * @param urlString
     * @param params
     * @return
     * @throws IOException
     * @throws JSONException
     */

    public JSONArray executePostReturnJSONArray(String urlString, ArrayList<NameValuePair> params) throws IOException, JSONException {
        String result = executePostReturnString(urlString, params);
        return result == null ? null : new JSONArray(result);
    }

    /**
     *
     * @param Method
     * @param urlString
     * @return
     * @throws IOException
     * @throws JSONException
     */

    public String excuteGetResultString(String Method, String urlString) throws IOException, JSONException {

        String result = null;
        InputStream is = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestMethod(Method);
            conn.setUseCaches(false);
            conn.setDoInput(true);

            // add header
            addHeader(conn);

            // Starts the query
            conn.connect();

            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            result = readStream(is);

        } finally {
            if (is != null) {
                is.close();
            }

            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    /**
     *
     * @param Method
     * @param urlString
     * @return
     * @throws IOException
     * @throws JSONException
     */

    public JSONObject executeGetResultJSONObject(String Method, String urlString) throws IOException, JSONException {
        String result = excuteGetResultString(Method,urlString );
        return result == null ? null : new JSONObject(result);
    }

    /**
     * TAG GET JSONArray
     * @param Method
     * @param urlString
     * @return
     * @throws IOException
     * @throws JSONException
     */

    public JSONArray executeGetResultJSONArray(String Method, String urlString) throws IOException, JSONException {
        String result = null;
        InputStream is = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestMethod(Method);
            conn.setUseCaches(false);
            conn.setDoInput(true);

            // add header
            addHeader(conn);

            // Starts the query
            conn.connect();

            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            result = readStream(is);

        } finally {
            if (is != null) {
                is.close();
            }

            if (conn != null) {
                conn.disconnect();
            }
        }

        return result == null ? null : new JSONArray(result);
    }

    /**
     * TAG UPDATE
     * @param method
     * @param urlString
     * @param params
     * @return
     * @throws IOException
     * @throws JSONException
     */

    public JSONObject execute(String method, String urlString, ArrayList<NameValuePair> params) throws IOException, JSONException {
        String result = null;
        InputStream is = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestMethod(method);
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // add header
            addHeader(conn);

            // add content body
            writeStream(conn.getOutputStream(), params);

            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            if (response < 400) {
                is = conn.getInputStream();
                result = readStream(is);

            } else {
                is = conn.getErrorStream();
                result = readStream(is);
            }

        } finally {
            if (is != null) {
                is.close();
            }

            if (conn != null) {
                conn.disconnect();
            }
        }

        return result == null ? null : new JSONObject(result);
    }

    /**
     *
     * @param out
     * @param params
     * @throws IOException
     */

    private void writeStream(OutputStream out, ArrayList<NameValuePair> params) throws IOException {
        DataOutputStream writer = new DataOutputStream(out);
        writer.writeBytes(getQuery(params));
        writer.flush();
        writer.close();
    }

    /**
     *
     * @param is
     * @return
     * @throws IOException
     */

    private String readStream(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
            total.append("\r");
        }

        return total.toString();
    }

    /**
     *
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}