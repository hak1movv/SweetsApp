package com.example.newprojeckt.presentation.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newprojeckt.data.fake_data.UserSharedPref
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentOnBoardingScreenBinding

class OnBoardingScreenFragment : Fragment() {

    private val binding: FragmentOnBoardingScreenBinding by lazy {
        FragmentOnBoardingScreenBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: UserSharedPref by lazy {
        UserSharedPref(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewStatusBar()
        setUpClickListeners()
        checkFirstUser()
    }

    private fun setUpViewStatusBar() {
        requireActivity().window?.statusBarColor = resources.getColor(R.color.green_on_boarding)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.green_on_boarding)
    }

    private fun setUpClickListeners() = binding.apply {
        getStartingBtn.setOnClickListener {
            sharedPreferences.setUserFirstSign(true)
            findNavController().navigate(R.id.action_onBoardingScreenFragment_to_mainScreenFragment)
        }
    }

    private fun checkFirstUser() {
        if (sharedPreferences.getIsUserFirstSign())
            findNavController().navigate(R.id.action_onBoardingScreenFragment_to_mainScreenFragment)
    }
}


