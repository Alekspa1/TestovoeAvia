package com.exampl3.testovoeavia.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.exampl3.testovoeavia.Const.KEY_INPUT_BOOLEAN
import com.exampl3.testovoeavia.databinding.FragmentStubBinding
import com.exampl3.testovoeavia.viewmodel.StartViewModel


class FragmentStub : Fragment() {
    private lateinit var binding: FragmentStubBinding
    private val model: StartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments?.getBoolean(KEY_INPUT_BOOLEAN) ?: false
        if (bundle) binding.stubBack.visibility = View.VISIBLE
        binding.stubBack.setOnClickListener {
            model.openCloseSheet(true)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (model.bottomSheet.value == true){
                        model.openCloseSheet(false)
                    }
                    else (activity as MainActivity).finish()
                }
            }) // это чтобы шторка закрывалась без выхода из приложения



    }



}