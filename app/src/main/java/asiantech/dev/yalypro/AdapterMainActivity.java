package asiantech.dev.yalypro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import asiantech.dev.yalypro.fragment.AuthorFragment;
import asiantech.dev.yalypro.fragment.PostFragment;
import asiantech.dev.yalypro.fragment.TopFragment;

/**
 * Created by PhuQuy on 4/13/15.
 */
public class AdapterMainActivity extends FragmentStatePagerAdapter {

    public static int count = 3;
    //tab fragment
    private TopFragment topFragment;
    private PostFragment postFragment;
    private AuthorFragment authorFragment;


    public AdapterMainActivity(FragmentManager fragmentManager) {
        super(fragmentManager);
        topFragment = new TopFragment();
        postFragment = new PostFragment();
        authorFragment = new AuthorFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return topFragment;
            case 1:
                return postFragment;
            case 2:
                return authorFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return count;
    }


}
