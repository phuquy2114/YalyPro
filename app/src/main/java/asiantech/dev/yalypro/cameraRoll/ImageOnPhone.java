package asiantech.dev.yalypro.cameraRoll;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageOnPhone implements Parcelable {

	private int id;
	private String path;
	private boolean isSelected;
	private Bitmap bitmap;
	private String stringBase64;
	
	public ImageOnPhone() {
		
	}
	
	public ImageOnPhone(String path, Bitmap bitmap, boolean isSelected) {
		this.bitmap = bitmap;
		this.path = path;
		this.isSelected = isSelected;
	}
	
	public ImageOnPhone(Parcel in) {
		id = in.readInt();
		path = in.readString();
		isSelected = (in.readByte() != 0);
		bitmap = in.readParcelable(Bitmap.class.getClassLoader());
		stringBase64 = in.readString();
	}
	
	public static final Creator<ImageOnPhone> CREATOR = new Creator<ImageOnPhone>() {
		
		@Override
		public ImageOnPhone[] newArray(int size) {
			return new ImageOnPhone[size];
		}
		
		@Override
		public ImageOnPhone createFromParcel(Parcel source) {
			return new ImageOnPhone(source);
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(path);
		dest.writeByte((byte) (isSelected ? 1 : 0));
		dest.writeParcelable(bitmap, flags);
		dest.writeString(stringBase64);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getStringBase64() {
		return stringBase64;
	}

	public void setStringBase64(String stringBase64) {
		this.stringBase64 = stringBase64;
	}
	
}
