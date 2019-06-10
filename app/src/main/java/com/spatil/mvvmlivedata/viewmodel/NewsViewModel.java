package com.spatil.mvvmlivedata.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spatil.mvvmlivedata.model.NewsResponse;
import com.spatil.mvvmlivedata.repository.NewsRepository;

import static com.spatil.mvvmlivedata.utils.Constants.API_KEY;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsRepository newsRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getNews("google-news", API_KEY);

    }

    public LiveData<NewsResponse> getNewsRepository() {
        return mutableLiveData;
    }


}
