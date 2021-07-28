package com.app.weather.presentation.search

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.app.weather.data.db.entity.CoordEntity
import com.app.weather.domain.usecase.SearchCitiesUseCase
import com.app.weather.domain.usecase.SetCurrentLatLngUseCase
import com.app.weather.presentation.core.BaseViewModel
import com.app.weather.presentation.core.Constants
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Fawzy
 */

@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val useCase: SearchCitiesUseCase,
    private val setCurrentLatLngUseCase: SetCurrentLatLngUseCase) : BaseViewModel() {

    private val cityNameData: MutableLiveData<String> = MutableLiveData()
    fun getSearchViewState() = searchViewState

    private val searchViewState: LiveData<SearchViewState> = cityNameData.switchMap { params ->
        useCase(params)
    }

    fun setSearchParams(cityName:String?) {
        if (cityNameData.value == cityName) {
            return
        }
        cityNameData.postValue(cityName)
    }

    @ExperimentalCoroutinesApi
    fun saveCoordsToSharedPref(coordEntity: CoordEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            setCurrentLatLngUseCase(LatLng(coordEntity.lat?.toDouble()?:0.0,coordEntity.lon?.toDouble()?:0.0))
        }
    }
}