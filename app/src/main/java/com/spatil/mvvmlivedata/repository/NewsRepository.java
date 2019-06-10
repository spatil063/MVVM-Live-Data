package com.spatil.mvvmlivedata.repository;

import androidx.lifecycle.MutableLiveData;

import com.spatil.mvvmlivedata.model.NewsResponse;
import com.spatil.mvvmlivedata.network.ApiClient;
import com.spatil.mvvmlivedata.network.ApiInterfaces;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private ApiInterfaces apiInterfaces;

    public NewsRepository(){
        apiInterfaces = ApiClient.getClient().create(ApiInterfaces.class);
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key){
        MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        apiInterfaces.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
