package com.akeno.testingapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akeno.testingapp.api.PhotosService
import com.akeno.testingapp.model.Photo
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {
    private val photosService = PhotosService()
    private val disposable = CompositeDisposable()
    val photos = MutableLiveData<List<Photo>>()

    fun fetchData() {
        disposable.add(
            photosService.getPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                DisposableSingleObserver<List<Photo>>() {
                    override fun onSuccess(value: List<Photo>?) {
                        photos.value = value
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("ERRORFETCHDATA","error$e")
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}