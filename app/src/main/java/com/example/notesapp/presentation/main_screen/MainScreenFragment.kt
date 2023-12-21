package com.example.newprojeckt.presentation.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newprojeckt.presentation.adapter.FoodsAdapter
import com.example.newprojeckt.presentation.adapter.FoodsItemClick
import com.example.notesapp.R
import com.example.notesapp.data.model.FoodModel
import com.example.notesapp.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment(), FoodsItemClick {

    private val binding: FragmentMainScreenBinding by lazy {
        FragmentMainScreenBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: MainScreenViewModel

    private var foodList: List<FoodModel> = emptyList()


    private val foodAdapter: FoodsAdapter by lazy {
        FoodsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnSearch()
        binding.cardView3.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainScreenFragment_to_mainFoodBasketFragment
            )
        }

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[MainScreenViewModel::class.java]

        setUpViews()
        setUpViewStatusBar()
        setUpObserveData()
    }

    private fun setUpViewStatusBar() {
        requireActivity().window?.statusBarColor =
            resources.getColor(R.color.onBoarding_btn_background)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.white)
    }

    private fun setUpObserveData() = viewModel.apply {
        foodLiveData.observe(viewLifecycleOwner) { food ->
            foodAdapter.updateFoodList(food)
            foodList = food
        }
    }

    private fun setUpViews() = binding.apply {
        receyclerview.adapter = foodAdapter
    }

    private fun fitherFood(title: String) {
        val fither = foodList.filter { name ->
            name.foodName.contains(title, ignoreCase = true)
        }
        foodAdapter.updateFoodList(fither)
    }

    private fun setOnSearch() = binding.apply {
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean{
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    fitherFood(it)
                }
                return true
            }
        })
    }

    override fun onFoodItemClick(foodModel: FoodModel) {
        findNavController().navigate(
            R.id.action_mainScreenFragment_to_foodDeteilsFragment,
            bundleOf(FOOD_KEY to foodModel)
        )
    }

    override fun deleteAtFoodIndex(index: Int) {
    }

    companion object {
        const val FOOD_KEY = "FOOD_KEY"
    }
}