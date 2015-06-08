package asiantech.dev.yalypro.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import asiantech.dev.yalypro.Helper.JsonUtil;

/**
 * Created by PhuQuy on 4/13/15.
 */
public class DataTDTO implements Parcelable {

    private String name;
    private String number;
    private String type;
    private String image;


    @Override
    public int describeContents() {
        return 0;
    }

    public DataTDTO(Parcel in) {
        name = in.readString();
        number = in.readString();
        type = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getName());
        out.writeString(getNumber());
        out.writeString(getType());
        out.writeString(getImage());
    }


    public String getName() {
        String ecode = null;
        try {
            ecode = name.getBytes("ISO-8859-1").toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ecode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public DataTDTO(JSONObject jsonObject) {

        if (jsonObject != null) {
            name = JsonUtil.getString(jsonObject,"measurer");
            number = JsonUtil.getString(jsonObject,"size");
            type = JsonUtil.getString(jsonObject,"shoe_type");
            image = JsonUtil.getString(jsonObject,"img");
        }
    }
}
