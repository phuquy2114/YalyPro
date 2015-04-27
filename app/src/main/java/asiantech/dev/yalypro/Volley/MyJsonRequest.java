package asiantech.dev.yalypro.Volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MyJsonRequest extends Request<JSONObject> {
    private Listener<JSONObject> listener;
    private Context mContext;

    public MyJsonRequest(String url, Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = reponseListener;
    }

    public MyJsonRequest(Context context, int method, String url, Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        mContext = context;
    }


    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        Map<String, String> responseHeaders = response.headers;
        Log.d("qqq", responseHeaders.get("Set-Cookie").toString());
        Log.d("qqq", response.toString());
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> params = new HashMap<String, String>();
//        if (new LoginUser(mContext).getDev_id() != null) {
//            params.put("Cookie", UrlAPI.SESSION_ID_KEY + "="+new LoginUser(mContext).getDev_id());
////            params.put("Cookie", "koebu_sid="+new LoginUser(mContext).getDev_id());
//        }
        return params;
    }
}

