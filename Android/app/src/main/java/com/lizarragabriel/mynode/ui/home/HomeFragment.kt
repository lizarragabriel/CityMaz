package com.lizarragabriel.mynode.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lizarragabriel.mynode.R
import com.lizarragabriel.mynode.api.City
import com.lizarragabriel.mynode.databinding.FragmentHomeBinding
import com.lizarragabriel.mynode.ui.home.adapter.CityAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var myAdapter: CityAdapter
    private val myModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            model = myModel
            lifecycleOwner = viewLifecycleOwner
            frag = this@HomeFragment
        }
        myAdapter = CityAdapter()
        binding.mRecyclerView.adapter = myAdapter

        myModel.employeeList.observe(this, { fruits ->
            fruits.forEach {
                println(it)
                myAdapter.setList(fruits)
            }
        })
        myAdapter.onItemClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            view.findNavController().navigate(action)
        }
        myAdapter.onItemDelete = {
            deleteDialog(it)
        }
        binding.mSwipe.setOnRefreshListener {
            lifecycleScope.launch {
                myModel.mReload()
                binding.mSwipe.isRefreshing = false
            }
        }
    }

    private fun deleteDialog(employee: City) {
        val alert = MaterialAlertDialogBuilder(context!!, R.style.AlertDialogTheme)
            .setTitle(getString(R.string.home_dialog_tittle))
            .setMessage("${getString(R.string.home_dialog_message)} ${employee.name}")
            .setPositiveButton(getString(R.string.home_dialog_yes)) { m1, m2 ->
                lifecycleScope.launch {
                    myModel.mDelete(employee._id!!)
                    myModel.mReload()
                }
            }
            .setNegativeButton(getString(R.string.home_dialog_no)) { m1, m2 -> }
            .show()
    }

    fun mNavigate() {
        view?.findNavController()?.navigate(R.id.action_homeFragment_to_addFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}