package asiantech.dev.yalypro.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import asiantech.dev.yalypro.Helper.BaseFragment;
import asiantech.dev.yalypro.Helper.ConnectingNetwork;
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
        new GetInfor().execute();
        setValue();
        setEvent();
    }

    public void initialize() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mViewRoot.findViewById(R.id.swiptorefresh);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.aqua),
                getResources().getColor(R.color.red), getResources().getColor(R.color.chartreu)
                , getResources().getColor(R.color.blue));
        mListView = (ListView) mViewRoot.findViewById(R.id.lv_information);

    }

    public void setValue() {
        mData = new ArrayList<>();
        mAdapter = new AdapterTopFragment(getActivity(), mData);
        mListView.setAdapter(mAdapter);
    }


    public void setEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new GetInfor().execute();
                        mAdapter.notifyDataSetChanged();
                        mAdapter = new AdapterTopFragment(getActivity(), mData);
                        mListView.setAdapter(mAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                }, 3000);
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


    private class GetInfor extends AsyncTask<String, Void, JSONArray> {

        private String url = "http://ndlapi.somee.com/api/Measure/GetMeasure";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray jsonArray = null;
            try {
                jsonArray = ConnectingNetwork.getInstance().executeGetResultJSONArray("GET", url);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            DataTDTO tdto;
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    tdto = new DataTDTO(jsonObject);
                    mData.add(tdto);
                    mAdapter.notifyDataSetChanged();
                    mListView.setAdapter(mAdapter);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
