package com.ps.citizen3.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.citizen3.App;
import com.ps.citizen3.R;
import com.ps.citizen3.data.api.GoogleCivicModel.RepResult;
import com.ps.citizen3.data.api.GoogleCivicService;
import com.ps.citizen3.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class RepFragment extends Fragment {
    @Inject GoogleCivicService googleCivicService;

    public static RepFragment newInstance() {
        RepFragment fragment = new RepFragment();
        return fragment;
    }

    public RepFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_layout, container, false);
        ((App)getActivity().getApplication()).getComponent().inject(this);

        final RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.fragment_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        googleCivicService.getReps("2310+Talc+Trail,+Madison,+WI")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RepResult>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error", e);
                    }

                    @Override
                    public void onNext(RepResult result) {
                        RepAdapter repAdapter = new RepAdapter(result);
                        recyclerView.setAdapter(repAdapter);
                    }
                });

        return rootView;
    }
}
