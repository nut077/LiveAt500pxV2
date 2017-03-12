package com.example.nutfreedom.liveat500pxv2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.example.nutfreedom.liveat500pxv2.R;
import com.example.nutfreedom.liveat500pxv2.dao.PhotoItemDao;
import com.example.nutfreedom.liveat500pxv2.databinding.FragmentMoreInfoBinding;

public class MoreInfoFragment extends Fragment {
    private FragmentMoreInfoBinding binding;
    private PhotoItemDao dao;

    public MoreInfoFragment() {
        super();
    }

    public static MoreInfoFragment newInstance(PhotoItemDao dao) {
        MoreInfoFragment fragment = new MoreInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMoreInfoBinding.inflate(inflater, container, false);
        initInstances(savedInstanceState);
        return binding.getRoot();
    }

    private void init(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    private void initInstances(Bundle savedInstanceState) {
        binding.viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return PhotoSummaryFragment.newInstance(dao);
                    case 1:
                        return PhotoInfoFragment.newInstance(dao);
                    case 2:
                        return PhotoTagsFragment.newInstance(dao);
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Summary";
                    case 1:
                        return "Info";
                    case 2:
                        return "Tags";
                    default:
                        return "";
                }
            }
        });

        binding.slidingTabLayout.setDistributeEvenly(true);
        binding.slidingTabLayout.setViewPager(binding.viewPager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_more_info, menu);

        MenuItem menuItem = menu.findItem(R.id.actionShare);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider.setShareIntent(getShareIntent());
    }

    private Intent getShareIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Extra Text");
        return intent;
    }

}
