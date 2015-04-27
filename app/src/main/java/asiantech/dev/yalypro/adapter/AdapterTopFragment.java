package asiantech.dev.yalypro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asiantech.dev.yalypro.R;
import asiantech.dev.yalypro.model.DataTDTO;

/**
 * Created by PhuQuy on 4/13/15.
 */
public class AdapterTopFragment extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<DataTDTO> mData;

    public AdapterTopFragment(Context mContext, ArrayList<DataTDTO> mData) {

        this.mContext = mContext;
        this.mData = mData;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public DataTDTO getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        private ImageView imageView;
        private TextView mTxtName;
        private TextView mTxtNumber;
        private ImageView mImgPointed;
        private ImageView mImgRound;
        private ImageView mImgSquare;
        private ImageView mImgTu;
        private Button mBtnSetting;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_topframgent, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_thumnai);
            viewHolder.mTxtName = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.mTxtNumber = (TextView) convertView.findViewById(R.id.txt_number_page);

            //image
            viewHolder.mImgSquare = (ImageView) convertView.findViewById(R.id.btn_img_vuong);
            viewHolder.mImgPointed = (ImageView) convertView.findViewById(R.id.btn_img_nhon);
            viewHolder.mImgRound = (ImageView) convertView.findViewById(R.id.btn_img_tron);
            viewHolder.mImgTu = (ImageView) convertView.findViewById(R.id.btn_img_tu);

            //Button
            viewHolder.mBtnSetting = (Button) convertView.findViewById(R.id.btn_setting);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load("http://ndlapi.somee.com/Content/Upload/" + getItem(position).getImage())
                .resize(300, 300)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(viewHolder.imageView);

        viewHolder.mTxtName.setText("" + getItem(position).getName());
        viewHolder.mTxtNumber.setText("" + getItem(position).getNumber());

        if (getItem(position).getType().equals("square")) {
            setDefaultImageCheckbox(viewHolder, position);
            viewHolder.mImgSquare.setImageResource(R.drawable.ic_on_check);
        } else if (getItem(position).getType().equals("round")) {
            setDefaultImageCheckbox(viewHolder, position);
            viewHolder.mImgPointed.setImageResource(R.drawable.ic_on_check);
        } else if (getItem(position).getType().equals("pointed")) {
            setDefaultImageCheckbox(viewHolder, position);
            viewHolder.mImgPointed.setImageResource(R.drawable.ic_on_check);
        } else if (getItem(position).getType().equals("tu")) {
            setDefaultImageCheckbox(viewHolder, position);
            viewHolder.mImgTu.setImageResource(R.drawable.ic_on_check);
        } else {
            setDefaultImageCheckbox(viewHolder, position);
        }

        return convertView;
    }

    public void setDefaultImageCheckbox(ViewHolder viewHolder, int position) {
        viewHolder.mImgSquare.setImageResource(R.drawable.ic_off_check);
        viewHolder.mImgRound.setImageResource(R.drawable.ic_off_check);
        viewHolder.mImgPointed.setImageResource(R.drawable.ic_off_check);
        viewHolder.mImgTu.setImageResource(R.drawable.ic_off_check);
    }

}
