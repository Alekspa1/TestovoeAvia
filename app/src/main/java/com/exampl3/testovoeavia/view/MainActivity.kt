package com.exampl3.testovoeavia.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.exampl3.testovoeavia.Const.CLOSE_BOTTOM_SHEET
import com.exampl3.testovoeavia.Const.OPEN_BOTTOM_SHEET
import com.exampl3.testovoeavia.R
import com.exampl3.testovoeavia.databinding.ActivityMainBinding
import com.exampl3.testovoeavia.viewmodel.StartViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val bindind: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val model: StartViewModel by viewModels()
    private val beh by lazy { BottomSheetBehavior.from(bindind.bSheet)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindind.root)
        model.openCloseSheet(false)
        startFragment(R.id.placeHolder, FragmentStart())
        startFragment(R.id.bSheet, FragmentBottomSheet())

        bindind.bNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_start -> startFragment(R.id.placeHolder, FragmentStart())
                R.id.menu_hotel -> startFragment(R.id.placeHolder, FragmentStub())
                R.id.menu_location -> startFragment(R.id.placeHolder, FragmentStub())
                R.id.menu_bell ->  startFragment(R.id.placeHolder, FragmentStub())
                R.id.menu_proffile -> startFragment(R.id.placeHolder, FragmentStub())
            }
            true
        }
        model.bottomSheet.observe(this){flag->
            Log.d("MyLog", flag.toString())
            when(flag) {
                true -> beh.state = OPEN_BOTTOM_SHEET
                false -> beh.state = CLOSE_BOTTOM_SHEET
            }
        }



    }



    private fun startFragment(place: Int, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(place, fragment)
            .commit()
    }
}