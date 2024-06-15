package com.exampl3.testovoeavia.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.exampl3.testovoeavia.databinding.FragmentBottomSheetBinding
import com.exampl3.testovoeavia.viewmodel.StartViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentBottomSheet : Fragment() {
    private lateinit var binding: FragmentBottomSheetBinding
    private val model: StartViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){
            edTextKudaBSheet.setText(model.getSP())
            imDel.setOnClickListener {
                edTextWhereBSheet.setText("")
                edTextKudaBSheet.setText("")
                model.saveSP("")
            }
            imDifficultRoute.setOnClickListener {
                delayF()
            }
            imAnywhere.setOnClickListener {
                binding.edTextKudaBSheet.setText("Куда угодно")
            }
            imWeekend.setOnClickListener {
                delayF()
            }
            imFire.setOnClickListener {
                delayF()
            }
        }
    }
    fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    private fun delayF() {
        hideKeyboardFrom(requireContext(), view)
        CoroutineScope(Dispatchers.Main).
        launch {
            delay(500)
            model.openCloseSheet(false)
        }
    } // это чтобы шторка скрывалась полностью, изза клавиатура которая меняет размер экрана



}