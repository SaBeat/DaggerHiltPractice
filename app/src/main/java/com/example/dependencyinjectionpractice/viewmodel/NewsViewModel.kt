package com.example.dependencyinjectionpractice.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependencyinjectionpractice.model.Article
import com.example.dependencyinjectionpractice.model.NewsResponse
import com.example.dependencyinjectionpractice.repository.NewsRepository
import com.example.dependencyinjectionpractice.util.Utils.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel(),
    LifecycleObserver {

    private val list: MutableLiveData<NewsResponse> = MutableLiveData()

    init {
        getViewModel()
    }

    fun listObserver(): MutableLiveData<NewsResponse> {
        return list
    }

    fun getViewModel() = viewModelScope.launch {
        repository.getApi(API_KEY, "us", "business").let {
            if (it.isSuccessful) {
                list.postValue(it.body())
            } else {
                list.postValue(null)
            }
        }
    }

}