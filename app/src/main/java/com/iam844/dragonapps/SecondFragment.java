package com.iam844.dragonapps;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    List<String> allInstalledApps = new ArrayList<>();
    Context context;
    View mView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=view.getContext();
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerViewLayoutManager = new GridLayoutManager(view.getContext(), 1);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        allInstalledApps= new ApkInfoExtractor(view.getContext()).GetAllInstalledApkInfo();
        adapter = new AppsAdapter(view.getContext(), allInstalledApps);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mView = view;

    }
    @Override
    public void onResume()
    {
        super.onResume();
        allInstalledApps=new ApkInfoExtractor(context).GetAllInstalledApkInfo();

        if (allInstalledApps.isEmpty()) {
            LinearLayout iv = mView.findViewById(R.id.fragment_background);
            iv.setVisibility(View.VISIBLE);
        }

        adapter = new AppsAdapter(context, allInstalledApps);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}