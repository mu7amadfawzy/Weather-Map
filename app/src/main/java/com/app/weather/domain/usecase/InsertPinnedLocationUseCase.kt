package com.app.weather.domain.usecase

import com.app.weather.data.db.entity.LocationEntity
import com.app.weather.domain.repositories.PinnedLocationsRepository
import com.algolia.search.saas.AbstractQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


class InsertPinnedLocationUseCase@Inject constructor(private val repo: PinnedLocationsRepository) {
    @ExperimentalCoroutinesApi
     operator fun invoke(latLng: AbstractQuery.LatLng) = repo.insert(LocationEntity(latLng))
}