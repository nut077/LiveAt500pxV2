package com.example.nutfreedom.liveat500pxv2.manager;

import android.content.Context;
import android.os.Bundle;

import com.example.nutfreedom.liveat500pxv2.dao.PhotoItemCollectionDao;
import com.example.nutfreedom.liveat500pxv2.dao.PhotoItemDao;

import java.util.ArrayList;

public class PhotoListManager {

    private Context mContext;
    private PhotoItemCollectionDao dao;

    public PhotoListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public PhotoItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }

    public void insertDataAtTopPosition(PhotoItemCollectionDao newDao) {
        if (dao == null) {
            dao = new PhotoItemCollectionDao();
        }
        if (dao.getData() == null) {
            dao.setData(new ArrayList< PhotoItemDao>());
        }
        dao.getData().addAll(0, newDao.getData());
    }

    public void appendDaoAtBottomPosition(PhotoItemCollectionDao newDao) {
        if (dao == null) {
            dao = new PhotoItemCollectionDao();
        }
        if (dao.getData() == null) {
            dao.setData(new ArrayList< PhotoItemDao>());
        }
        dao.getData().addAll(dao.getData().size(), newDao.getData());
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
}
