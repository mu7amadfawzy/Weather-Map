package com.app.weather.presentation.dashboard.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.app.weather.presentation.core.BaseAdapter
import com.app.weather.databinding.ItemForecastBinding
import com.app.weather.domain.model.ListItem

/**
 * Created by Fawzy
 */

class ForecastAdapter(
    private val callBack: (ListItem) -> Unit
) : BaseAdapter<ListItem,ItemForecastBinding>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemForecastBinding {
        val mBinding = ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = ForecastItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.cardView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ItemForecastBinding, position: Int) {
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
