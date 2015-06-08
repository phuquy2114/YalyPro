package asiantech.dev.yalypro;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

import asiantech.dev.yalypro.Helper.BaseActivity;
import asiantech.dev.yalypro.Helper.LoopingViewPager;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 6/5/15.
 */
public class ViewPageActivity extends BaseActivity {


    private LoopingViewPager mViewPage;
    private CirclePageIndicator mIndicator;
    private AdapterMainActivity mAdapterActivity;
    private ImagePagerAdapter imagePagerAdapter;
    private Timer swipeTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        initialize();
        setValue();
        setEvent();
        continueTimer();
    }

    @Override
    protected void initialize() {
        mViewPage = (LoopingViewPager) findViewById(R.id.viewpage);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
    }

    @Override
    protected void setValue() {

        mAdapterActivity = new AdapterMainActivity(getSupportFragmentManager());
        imagePagerAdapter = new ImagePagerAdapter();

    }

    @Override
    protected void setEvent() {

        mViewPage.setAdapter(imagePagerAdapter);
        mViewPage.setOffscreenPageLimit(6);
        mIndicator.setViewPager(mViewPage,6);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mImages = new int[]{

                R.drawable.tutorial_1,
                R.drawable.tutorial_2,
                R.drawable.tutorial_3,
                R.drawable.tutorial_4,
                R.drawable.tutorial_5,
                R.drawable.tutorial_6,
        };

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = ViewPageActivity.this;
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageResource(mImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

    Handler handler = new Handler();
    Runnable Update = new Runnable() {
        public void run() {
            if (mViewPage.getCurrentItem() == 6) {
                mViewPage.setCurrentItem(0, true);
            } else {
                mViewPage.setCurrentItem(mViewPage.getCurrentItem() + 1, true);
            }
        }
    };
    public void continueTimer() {
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 5000);
    }


}
