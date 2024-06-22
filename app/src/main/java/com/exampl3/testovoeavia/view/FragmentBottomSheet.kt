package com.exampl3.testovoeavia.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.exampl3.testovoeavia.Const.KEY_INPUT_BOOLEAN
import com.exampl3.testovoeavia.Const.KEY_INPUT_FROM
import com.exampl3.testovoeavia.Const.KEY_INPUT_WHERE
import com.exampl3.testovoeavia.R
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){
            val fragmentStub = FragmentStub().apply {
                arguments = Bundle().apply {putBoolean(KEY_INPUT_BOOLEAN, true)  }
            }
            edTextWhereBSheet.setText(model.getSP())
            imDel.setOnClickListener {
                edTextWhereBSheet.setText("")
                edTextKudaBSheet.setText("")
                model.saveSP("")
            }
            imDifficultRoute.setOnClickListener {
                delayF(fragmentStub)
            }
            imAnywhere.setOnClickListener {
                binding.edTextKudaBSheet.setText("Куда угодно")
            }
            imWeekend.setOnClickListener {
                delayF(fragmentStub)
            }
            imFire.setOnClickListener {
                delayF(fragmentStub)
            }

            card1.setOnClickListener {
                edTextKudaBSheet.setText(tvStam.text.toString())
                openSearchFragment()
            }
            card2.setOnClickListener {
                edTextKudaBSheet.setText(tvSochi.text.toString())
                openSearchFragment()
            }
            card3.setOnClickListener {
                edTextKudaBSheet.setText(tvPhuket.text.toString())
                openSearchFragment()
            }


            edTextWhereBSheet.setOnKeyListener { _, _, event ->
                if (event.keyCode == KeyEvent.KEYCODE_ENTER){
                    model.saveSP(binding.edTextWhereBSheet.text.toString())
                }
                return@setOnKeyListener false
            }

            edTextKudaBSheet.setOnKeyListener { _, _, event ->
                if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                    openSearchFragment()
                }
                return@setOnKeyListener true
            }
        }
    }
    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    private fun openSearchFragment(){
        if (
            binding.edTextKudaBSheet.text.isNotEmpty() &&
            binding.edTextWhereBSheet.text.isNotEmpty()){
            val searchFragment = FragmentSearch().apply {
                arguments = Bundle().apply {
                    putString(KEY_INPUT_WHERE,binding.edTextWhereBSheet.text.toString())
                    putString(KEY_INPUT_FROM,binding.edTextKudaBSheet.text.toString())
                }
            }
            model.saveSP(binding.edTextWhereBSheet.text.toString())
            binding.edTextKudaBSheet.clearFocus()
            delayF(searchFragment)
            model.getSearchTicket()
        } else Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
    }
    private fun delayF(fragment: Fragment) {
        hideKeyboardFrom(requireContext(), view)
        CoroutineScope(Dispatchers.Main).
        launch {
            delay(500)
            model.openCloseSheet(false)
        }
        parentFragmentManager.beginTransaction().replace(R.id.placeHolder, fragment).commit()
    } // это чтобы шторка скрывалась полностью, изза клавиатура которая меняет размер экрана



}