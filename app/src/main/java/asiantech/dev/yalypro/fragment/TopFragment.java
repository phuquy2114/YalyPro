package asiantech.dev.yalypro.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import asiantech.dev.yalypro.Helper.BaseFragment;
import asiantech.dev.yalypro.R;
import asiantech.dev.yalypro.adapter.AdapterTopFragment;
import asiantech.dev.yalypro.model.DataTDTO;

/**
 * Created by PhuQuy on 4/13/15.
 */
public class TopFragment extends BaseFragment {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private AdapterTopFragment mAdapter;
    private View mViewRoot;
    private ArrayList<DataTDTO> mData;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewRoot = inflater.inflate(R.layout.fragment_top, null, false);
//        initialize();
//        setValue();
//        setEvent();
        return mViewRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        setValue();
        setEvent();
    }

    public void initialize() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mViewRoot.findViewById(R.id.swiptorefresh);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.aqua),
                getResources().getColor(R.color.red),getResources().getColor(R.color.chartreu)
                ,getResources().getColor(R.color.blue));
        mListView = (ListView) mViewRoot.findViewById(R.id.lv_information);

    }

    public void setValue() {
        mData = new ArrayList<>();
        mAdapter = new AdapterTopFragment(getActivity(), mData);

    }


    public void setEvent() {
        mListView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
                        mAdapter = new AdapterTopFragment(getActivity(), mData);
                        mListView.setAdapter(mAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                }, 5000);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
