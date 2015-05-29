package asiantech.dev.yalypro.Helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import asiantech.dev.yalypro.R;

/**
 * Created by PhuQuy on 4/13/15.
 */
public class TabHost extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private View mView;

    private LinearLayout mViewTabOne;
    private LinearLayout mViewTabTwo;
    private LinearLayout mViewTabThree;

    private TextView mTxtTabOne;
    private TextView mTxtTabTwo;
    private TextView mTxtTabThree;
    private onItemTabhost mListenner;


    public TabHost(Context context) {
        super(context);
        init(context);
    }

    public TabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TabHost(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.tabhost, this, false);
        addView(mView);

        mViewTabOne = (LinearLayout) mView.findViewById(R.id.view_tab1);
        mViewTabTwo = (LinearLayout) mView.findViewById(R.id.view_tab2);
        mViewTabThree = (LinearLayout) mView.findViewById(R.id.view_tab3);

        mTxtTabOne = (TextView) mView.findViewById(R.id.txt_tab_one);
        mTxtTabTwo = (TextView) mView.findViewById(R.id.txt_tab_two);
        mTxtTabThree = (TextView) mView.findViewById(R.id.txt_tab_three);

        setEvent();
        onItemClick(0);

    }


    public void setEvent() {

        mViewTabOne.setOnClickListener(this);
        mViewTabTwo.setOnClickListener(this);
        mViewTabThree.setOnClickListener(this);
    }

    public void setDefault() {
        //Tab one
        mTxtTabOne.setTextColor(0xff6fca7b);
        mViewTabOne.setBackgroundColor(0xFFA9A9A9);
        //Tab Two
        mTxtTabTwo.setTextColor(0xff6fca7b);
        mViewTabTwo.setBackgroundColor(0xFFA9A9A9);
        //Tab Three
        mTxtTabThree.setTextColor(0xff6fca7b);
        mViewTabThree.setBackgroundColor(0xFFA9A9A9);

    }

    public void onItemClick(int position) {
        setDefault();
        if (position == 0) {
            mTxtTabOne.setTextColor(0xFF333300);
            mViewTabOne.setBackgroundColor(0xFFF0FFFF);
        } else if (position == 1) {
            mTxtTabTwo.setTextColor(0xFF333300);
            mViewTabTwo.setBackgroundColor(0xFFF0FFFF);
        } else if (position == 2) {
            mTxtTabThree.setTextColor(0xFF333300);
            mViewTabThree.setBackgroundColor(0xFFF0FFFF);
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.view_tab1:
                if (mListenner != null) {
                    mListenner.setonClickItemClick(0);
                }
                break;
            case R.id.view_tab2:
                if (mListenner != null) {
                    mListenner.setonClickItemClick(1);
                }
                break;
            case R.id.view_tab3:
                if (mListenner != null) {
                    mListenner.setonClickItemClick(2);
                }
                break;
            default:
                break;
        }

    }

    public interface onItemTabhost {

        public void setonClickItemClick(int position);

    }

    public void setOnClickItemTabHost(onItemTabhost l) {
        this.mListenner = l;
    }
}
