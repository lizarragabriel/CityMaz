package com.lizarragabriel.mynode.utils

import androidx.recyclerview.widget.DiffUtil
import com.lizarragabriel.mynode.api.City

class MyDiffUtil(
    private val oldList: List<City>,
    private val newList: List<City>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]._id == newList[newItemPosition]._id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition]._id != newList[newItemPosition]._id -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].image != newList[newItemPosition].image -> {
                false
            }
            oldList[oldItemPosition].description != newList[newItemPosition].description -> {
                false
            }
            else -> true
        }
    }
}