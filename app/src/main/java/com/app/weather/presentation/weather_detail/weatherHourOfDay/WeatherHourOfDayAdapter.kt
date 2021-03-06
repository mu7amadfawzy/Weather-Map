package com.app.weather.presentation.weather_detail.weatherHourOfDay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.app.weather.databinding.ItemWeatherHourOfDayBinding
import com.app.weather.domain.model.ListItem
import com.app.weather.presentation.core.BaseAdapter

/**
 * Created by Fawzy
 */

class WeatherHourOfDayAdapter(private val callBack: (ListItem) -> Unit? = {}) :
    BaseAdapter<ListItem, ItemWeatherHourOfDayBinding>(
        diffCallback
    ) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemWeatherHourOfDayBinding {
        val mBinding = ItemWeatherHourOfDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = WeatherHourOfDayItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ItemWeatherHourOfDayBinding, position: Int) {
        binding.viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.dtTxt == newItem.dtTxt
}
