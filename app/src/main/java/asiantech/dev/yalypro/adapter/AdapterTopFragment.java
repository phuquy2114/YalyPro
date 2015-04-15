package asiantech.dev.yalypro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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
        return 20;
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
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_topframgent,parent,false);
        }
        return convertView;
    }

}
