package com.exampl3.testovoeavia.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.exampl3.testovoeavia.R
import com.exampl3.testovoeavia.databinding.FragmentStartBinding
import com.exampl3.testovoeavia.model.TicketApi
import com.exampl3.testovoeavia.view.adapters.FragStartAdapterLenta
import com.exampl3.testovoeavia.viewmodel.StartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class FragmentStart : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val model: StartViewModel by activityViewModels()
    private lateinit var adapter: FragStartAdapterLenta


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        binding.cardInput.setOnClickListener {
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
            }) // это чтобы шторка закрывалась

    }
    private fun initRcView(){
        binding.rcViewFragStart.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        model.startTicket.observe(viewLifecycleOwner){
            adapter = FragStartAdapterLenta(it)
            binding.rcViewFragStart.adapter = adapter

        }
    }



}