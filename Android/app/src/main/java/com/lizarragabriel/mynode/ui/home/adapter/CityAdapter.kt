package com.lizarragabriel.mynode.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lizarragabriel.mynode.api.City
import com.lizarragabriel.mynode.databinding.CityItemBinding
import com.lizarragabriel.mynode.utils.MyDiffUtil

class CityAdapter: RecyclerView.Adapter<CityAdapter.MyViewHolder>() {
    private var list: List<City> = emptyList()

    var onItemClick: (City) -> Unit = {}
    var onItemDelete: (City) -> Unit = {}

    fun setList(newList: List<City>) {
        val diffUtil = MyDiffUtil(list, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        list = newList
        diffResults.dispatchUpdatesTo(this)
    }

    inner class MyViewHolder(private val binding: CityItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(fruit: City) {
            binding.fruit = fruit

            binding.mVer.setOnClickListener {
                onItemClick(fruit)
            }
            binding.mEliminar.setOnClickListener {
                onItemDelete(fruit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.MyViewHolder {
        return MyViewHolder(CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CityAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}