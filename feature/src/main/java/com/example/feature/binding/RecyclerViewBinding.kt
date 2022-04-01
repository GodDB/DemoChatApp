package com.example.feature.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
internal fun RecyclerView.setAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.adapter = adapter
}

@BindingAdapter("itemAnimator")
internal fun RecyclerView.setItemAnimator(itemAnimator: RecyclerView.ItemAnimator?) {
    this.itemAnimator = itemAnimator
}

@BindingAdapter("itemDecoration")
internal fun RecyclerView.addItemDecorator(itemDecoration: RecyclerView.ItemDecoration) {
    this.addItemDecoration(itemDecoration)
}

@BindingAdapter("submitList")
internal fun <T> RecyclerView.submitList(data: List<T>?) {
    check(adapter is ListAdapter<*, *>) { "submitList는 listAdapter이여야 합니다" }

    data?.let {
        (adapter as ListAdapter<T, *>).submitList(data)
    }
}

@BindingAdapter("concat_0_submitList", "concat_1_submitList", requireAll = true)
internal fun <T, U> RecyclerView.concatAdapterSubmitList(data_0: List<T>?, data_1 : List<U>?) {
    check(adapter is ConcatAdapter) {
        "concat_0_submitList, concat_1_submitList는 concat adapter 이여야만 합니다"
    }
    check((adapter as ConcatAdapter).adapters[0] is ListAdapter<*, *>) {
        "concatAdapter의 0번째 어댑터는 ListAdapter이여야 합니다"
    }
    check((adapter as ConcatAdapter).adapters[1] is ListAdapter<*, *>) {
        "concatAdapter의 1번째 어댑터는 ListAdapter이여야 합니다"
    }

    data_0?.let {
        ((adapter as ConcatAdapter).adapters[0] as ListAdapter<T, *>).submitList(it)
    }
    data_1?.let {
        ((adapter as ConcatAdapter).adapters[1] as ListAdapter<U, *>).submitList(it)
    }
}