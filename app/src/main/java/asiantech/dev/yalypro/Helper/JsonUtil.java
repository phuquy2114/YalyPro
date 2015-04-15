
/*
 * Copyright (c) 2014 AsianTech company. All rights reserved.
 */
package asiantech.dev.yalypro.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * JsonUtil is a class contain some common method to support get data from
 * JSONObject
 *
 * @author thangtc
 * @version 1.0
 * @since 05/09/2014
 */
public class JsonUtil {

    /**
     * This method is used to get a string from jSONObject with a key
     *
     * @param jSONObject is a json data object that contain data
     * @param name       is a key to get data in jSONObject
     * @return return data in jSONObject with a matching key otherwise return a
     * string empty
     */
    public static String getString(JSONObject jSONObject, String name) {

        if (jSONObject == null) {
            return "";
        }

        if (!jSONObject.isNull(name)) {
            try {
                String value = jSONObject.getString(name);
                if (value == null) {
                    return "";
                } else {
                    return value;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static String getString(JSONArray jSONArray, int index) {

        if (jSONArray == null) {
            return "";
        }

        try {
            return jSONArray.getString(index);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * This method is used to get a int value from jSONObject with a key
     *
     * @param jSONObject is a json data object that contain data
     * @param name       is a key to get data in jSONObject
     * @return return data in jSONObject with a matching key otherwise return 0
     */
    public static int getInt(JSONObject jSONObject, String name) {

        if (jSONObject == null) {
            return 0;
        }

        if (!jSONObject.isNull(name)) {
            try {
                return jSONObject.getInt(name);
            } catch (JSONException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static long getLong(JSONObject jSONObject, String name) {

        if (jSONObject == null) {
            return 0;
        }

        if (!jSONObject.isNull(name)) {
            try {
                return jSONObject.getLong(name);
            } catch (JSONException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * This method is used to get a double value from jSONObject with a key
     *
     * @param jSONObject is a json data object that contain data
     * @param name       is a key to get data in jSONObject
     * @return return data in jSONObject with a matching key otherwise return 0
     */
    public static double getDouble(JSONObject jSONObject, String name) {

        if (jSONObject == null) {
            return 0;
        }

        if (!jSONObject.isNull(name)) {
            try {
                return jSONObject.getDouble(name);
            } catch (JSONException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * This method is used to get boolean value from jSONObject with a key
     *
     * @param jSONObject is a json data object that contain data
     * @param name       is a key to get data in jSONObject
     * @return return data in jSONObject with a matching key otherwise return
     * false
     */
    public static boolean getBoolean(JSONObject jSONObject, String name) {

        if (jSONObject == null) {
            return false;
        }

        if (!jSONObject.isNull(name)) {
            try {
                return jSONObject.getBoolean(name);
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This method is used to get a json object from jSONObject with a key
     *
     * @param jSONObject is a json data object that contain data
     * @param name       is a key to get data in jSONObject
     * @return return data in jSONObject with a matching key otherwise return
     * null
     */
    public static JSONObject getJSONObject(JSONObject jSONObject,
                                           String name) {

        if (jSONObject == null) {
            return null;
        }

        if (!jSONObject.isNull(name)) {
            try {
                return jSONObject.getJSONObject(name);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }


    /**
     * This method is used to get a json object from json array jSONArray with
     * position
     *
     * @param jSONArray is a array data of json object that contain data
     * @param position  is the position you want to get json object
     * @return return a json object otherwise return null
     */
    public static JSONObject getJSONObject(JSONArray jSONArray,
                                           int position) {

        if (jSONArray == null) {
            return null;
        }

        try {
            return jSONArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method is used to get value in json object
     *
     * @param jSONObject is json object you want to get value from
     * @return return object value
     */
    public static Object getValue(JSONObject jSONObject, String name) {

        if (jSONObject == null) {
            return null;
        }

        try {
            return jSONObject.get(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method is used to create a new JSONObject by copying mappings for
     * the listed names from the given object. Names that aren't present in
     * copyFrom will be skipped.
     *
     * @param copyFrom is json object you want to copy from
     * @param keys     is array key name
     * @return return json object with keys mapping with array keys
     */
    public static JSONObject getJSONObject(JSONObject copyFrom,
                                           String[] keys) {

        if ((copyFrom == null) || (keys == null)) {
            return null;
        }

        try {
            return new JSONObject(copyFrom, keys);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method is used to convert a string format json to json object
     *
     * @param json is string format json
     * @return return json object
     */
    public static JSONObject getJSONObject(String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method is used to get a json array from a json object
     *
     * @param jSONObject is a json data object that contain data
     * @param name       is a key to get data in jSONObject
     * @return return a json array otherwise return null
     */
    public static JSONArray getJSONArray(JSONObject jSONObject,
                                         String name) {

        if (jSONObject == null) {
            return null;
        }

        if (!jSONObject.isNull(name)) {
            try {
                return jSONObject.getJSONArray(name);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }






}