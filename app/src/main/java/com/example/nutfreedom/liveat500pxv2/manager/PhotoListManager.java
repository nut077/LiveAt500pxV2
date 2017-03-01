package com.example.nutfreedom.liveat500pxv2.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.nutfreedom.liveat500pxv2.dao.PhotoItemCollectionDao;
import com.example.nutfreedom.liveat500pxv2.dao.PhotoItemDao;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PhotoListManager {

    private Context mContext;
    private PhotoItemCollectionDao dao;

    public PhotoListManager() {
        mContext = Contextor.getInstance().getContext();
        loadCache();
    }

    public PhotoItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
        saveCache();
    }

    public void insertDataAtTopPosition(PhotoItemCollectionDao newDao) {
        if (dao == null) {
            dao = new PhotoItemCollectionDao();
        }
        if (dao.getData() == null) {
            dao.setData(new ArrayList< PhotoItemDao>());
        }
        dao.getData().addAll(0, newDao.getData());
        saveCache();
    }

    public void appendDaoAtBottomPosition(PhotoItemCollectionDao newDao) {
        if (dao == null) {
            dao = new PhotoItemCollectionDao();
        }
        if (dao.getData() == null) {
            dao.setData(new ArrayList< PhotoItemDao>());
        }
        dao.getData().addAll(dao.getData().size(), newDao.getData());
        saveCache();
    }

    public int getMaximumId() {
        if (dao == null) {
            return 0;
        }
        if (dao.getData() == null) {
            return 0;
        }
        if (dao.getData().size() == 0) {
            return 0;
        }
        int maxId = dao.getData().get(0).getId();
        int size = dao.getData().size();
        for (int i = 1; i < size; i++) {
            maxId = Math.max(maxId, dao.getData().get(0).getId());
        }
        return maxId;
    }

    public int getMinimumId() {
        if (dao == null) {
            return 0;
        }
        if (dao.getData() == null) {
            return 0;
        }
        if (dao.getData().size() == 0) {
            return 0;
        }
        int minId = dao.getData().get(0).getId();
        int size = dao.getData().size();
        for (int i = 1; i < size; i++) {
            minId = Math.min(minId, dao.getData().get(0).getId());
        }
        return minId;
    }

    public int getCount() {
        if (dao == null) {
            return 0;
        }
        if (dao.getData() == null) {
            return 0;
        }
        return dao.getData().size();
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("dao", dao);
        return bundle;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        dao = savedInstanceState.getParcelable("dao");
    }

    private void saveCache() {
        PhotoItemCollectionDao cacheDao = new PhotoItemCollectionDao();
        if (dao != null && dao.getData() != null) {
            cacheDao.setData(dao.getData().subList(0, Math.min(20, dao.getData().size())));
        }
        String json = new Gson().toJson(cacheDao);
        SharedPreferences preferences = mContext.getSharedPreferences("photos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("json", json);
        editor.apply();

    }

    private void loadCache() {
        SharedPreferences preferences = mContext.getSharedPreferences("photos", Context.MODE_PRIVATE);
        String json = preferences.getString("photos", null);
        if (json == null) {
            return;
        }
        dao = new Gson().fromJson(json, PhotoItemCollectionDao.class);
    }
}
