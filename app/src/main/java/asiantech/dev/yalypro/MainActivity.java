package asiantech.dev.yalypro;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import asiantech.dev.yalypro.Helper.BaseActivity;
import asiantech.dev.yalypro.Helper.HackyViewPager;
import asiantech.dev.yalypro.Helper.TabHost;

public class MainActivity extends BaseActivity implements TabHost.onItemTabhost {

    private HackyViewPager mViewPage;
    private TabHost mTabhost;
    private AdapterMainActivity mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        setValue();
        setEvent();
    }

    @Override
    protected void initialize() {
        mViewPage = (HackyViewPager) findViewById(R.id.viewpage);
        mTabhost = (TabHost) findViewById(R.id.tab_host);

    }

    @Override
    protected void setValue() {
       mAdapter = new AdapterMainActivity(getSupportFragmentManager());
    }

    @Override
    protected void setEvent() {
        //View page
//        mViewPage.setEnabled(true);
        mViewPage.setAdapter(mAdapter);
        mViewPage.setOffscreenPageLimit(3);
        mViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabhost.onItemClick(position);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mTabhost.setOnClickItemTabHost(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setonClickItemClick(int position) {
        mViewPage.setCurrentItem(position);
    }

}
