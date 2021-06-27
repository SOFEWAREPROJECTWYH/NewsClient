package com.wyh.newsclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.wyh.newsclient.R;
import adapter.LeftMenuListAdapter;


public class LeftMenuFragment extends BaseFragment {

    private ListView lv;

    private List<String> titles;
    private LeftMenuListAdapter adapter;

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.from(getActivity()).inflate(R.layout.left_menu_fragment_main, null);

        lv = (ListView) rootView.findViewById(R.id.lv);

        return rootView;
    }

    @Override
    public void initDatas() {
        titles = new ArrayList<>();
        adapter = new LeftMenuListAdapter(titles);

        lv.setAdapter(adapter);
    }

    @Override
    public void initListeners() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                System.out.println("-------->点击的是: " + position);

                adapter.setCurrentPosition(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void processClick(View view) {

    }


    public void addTitles(List<String> titles) {
        this.titles.addAll(titles);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

}