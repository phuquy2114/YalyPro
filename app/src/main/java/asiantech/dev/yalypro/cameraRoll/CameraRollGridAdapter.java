package asiantech.dev.yalypro.cameraRoll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asiantech.dev.yalypro.Common.Common;
import asiantech.dev.yalypro.R;

public class CameraRollGridAdapter extends BaseAdapter {
	private static final int NUMBER_COLUMNS = 4;

	private Context mContext;

	private ArrayList<ImageOnPhone> mImageOnPhones;
	private final int mItemHeight;
	private int mActionBarHeight = 0;
	private int mSpacing = 0;
	private RelativeLayout.LayoutParams mImageViewLayoutParams;
	
	public CameraRollGridAdapter(Context context, ArrayList<ImageOnPhone> imageOnPhones) {
		mContext = context;
		mImageOnPhones = imageOnPhones;

		mSpacing = mContext.getResources()
				.getDimensionPixelSize(R.dimen.activity_horizontal_margin);

        int width = Common.getWidthScreen(mContext);
		
		mItemHeight = (width - (NUMBER_COLUMNS - 1) * mSpacing) / NUMBER_COLUMNS;

		// Height Action Bar
        mActionBarHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_bar_height) + mSpacing;

		mImageViewLayoutParams = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, mItemHeight);
		
	}
	

	public void setData(ArrayList<ImageOnPhone> imageOnPhones) {
		mImageOnPhones = imageOnPhones;
	}
	
	@Override
	public int getCount() {
        int count = mImageOnPhones.size() + NUMBER_COLUMNS + 1;

//        if (mImageOnPhones.size() > 0) {
//            count = mImageOnPhones.size() + NUMBER_COLUMNS + 1;
//        }

		return count;
	}

	@Override
	public ImageOnPhone getItem(int position) {
		return position < (NUMBER_COLUMNS + 1)
				? null : mImageOnPhones.get(position - NUMBER_COLUMNS - 1);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	@Override
	public int getItemViewType(int position) {
		return position < NUMBER_COLUMNS ? 0 : 1;
	}
	
	@Override
	public boolean hasStableIds() {
		return true;
	}
	
	private static class ViewHolder {
		ImageView imgImage;
		ImageView imgTick;
        ImageView imgCameraIcon;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        // First check if this is the top row
        if (position < NUMBER_COLUMNS) {
            if (convertView == null) {
                convertView = new View(mContext);
            }
            // Set empty view with height of ActionBar
            convertView.setLayoutParams(new AbsListView.LayoutParams(
                    LayoutParams.MATCH_PARENT, mActionBarHeight - mSpacing));
            return convertView;
        }
        
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext)
					.inflate(R.layout.item_grid_camera_roll, parent, false);
			holder = new ViewHolder();
			
			holder.imgImage = (ImageView) convertView.findViewById(R.id.igcr_img_image);
			holder.imgTick = (ImageView) convertView.findViewById(R.id.igcr_img_tick);
            holder.imgCameraIcon = (ImageView) convertView.findViewById(R.id.igcr_img_camera_icon);
            holder.imgCameraIcon.setVisibility(View.GONE);
			
			// setLayoutParams
			holder.imgImage.setLayoutParams(mImageViewLayoutParams);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		setValue(holder, position);
		
		return convertView;
	}

	private void setValue(final ViewHolder holder, final int position) {

		if (position == NUMBER_COLUMNS) {
            holder.imgCameraIcon.setVisibility(View.VISIBLE);
			holder.imgTick.setVisibility(View.GONE);

            holder.imgImage.setBackgroundColor(0x00FFFFFF);

            Picasso.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .into(holder.imgImage);

		} else {
            holder.imgCameraIcon.setVisibility(View.GONE);
			ImageOnPhone imageOnPhone = getItem(position);
			holder.imgTick.setVisibility(imageOnPhone.isSelected() ? View.VISIBLE : View.GONE);

            holder.imgImage.setBackgroundColor(0xFFCCCCCC);
			
			String uri = "file://" + imageOnPhone.getPath();
			Picasso.with(mContext)
					.load(uri)
					.resize(mItemHeight, mItemHeight)
                    .error(R.mipmap.ic_launcher)
					.centerCrop()
					.into(holder.imgImage);

		}
		
	}

}
