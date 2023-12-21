package com.example.newprojeckt.presentation.FragmentDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newprojeckt.data.fake_data.FoodSharedPref
import com.example.newprojeckt.presentation.main_screen.MainScreenFragment.Companion.FOOD_KEY
import com.example.notesapp.R
import com.example.notesapp.data.model.FoodModel
import com.example.notesapp.databinding.FragmentDetailsBinding

import com.google.android.material.snackbar.Snackbar

class FoodDeteilsFragment : Fragment() {

    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

   private val sharedPref: FragmentDetailsBinding by lazy {
       sharedPref
   }

    private val sharedPreferences: FoodSharedPref by lazy {
        FoodSharedPref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodModel = arguments?.getSerializable(FOOD_KEY) as? FoodModel
        foodModel?.let {
            setUpViews(it)
            setUpClickLisners(it)
            setUpViewStatusBar()
        }
    }

    private fun setUpClickLisners(foodModel: FoodModel) {
        binding.apply {
            imageView.setOnClickListener {
                findNavController().navigate(
                    R.id.action_foodDeteilsFragment_to_mainScreenFragment
                )
            }
            addToCart.setOnClickListener {
                sharedPreferences.saveFood(foodModel)
                Snackbar.make(
                    requireView(), "Added to cart successfully", Snackbar.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            }
        }
    }

    private fun setUpViews(foodModel: FoodModel) {
        binding.apply {
            Glide.with(requireContext()).load(foodModel.foodImg).into(imageView2)
            textView3.text = foodModel.foodName
            textView4.text = foodModel.foodDescription
            textHint.text = "${foodModel.foodPrice}$"
        }
    }

    private fun setUpViewStatusBar() {
        requireActivity().window?.statusBarColor = resources.getColor(R.color.white)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.white)
    }
}