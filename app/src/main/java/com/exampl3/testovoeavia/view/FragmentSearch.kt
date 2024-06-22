package com.exampl3.testovoeavia.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.exampl3.testovoeavia.Const.KEY_INPUT_DATE
import com.exampl3.testovoeavia.Const.KEY_INPUT_FROM
import com.exampl3.testovoeavia.Const.KEY_INPUT_WHERE
import com.exampl3.testovoeavia.R
import com.exampl3.testovoeavia.databinding.FragmentSearchBinding
import com.exampl3.testovoeavia.view.adapters.FragSearchAdapterTicket
import com.exampl3.testovoeavia.viewmodel.StartViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class FragmentSearch : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val model: StartViewModel by activityViewModels()
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var calendar: Calendar
    private lateinit var adapter: FragSearchAdapterTicket
    private var flag = true
    private lateinit var date: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        date = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date())
        val dateWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(Date())
        val concat = " ,$dateWeek"
        val inputWhere = arguments?.getString(KEY_INPUT_WHERE)
        val inputFrom = arguments?.getString(KEY_INPUT_FROM)
        with(binding){
            tvFrom.text = inputFrom
            tvWhere.text = inputWhere
            tvDate.text = date
            tvWeek.text = concat
            cardDate.setOnClickListener {datePickerDialog() }
            cardObr.setOnClickListener {datePickerDialogObr() }


            imDelSearch.setOnClickListener {
                tvWhere.text = ""
                tvFrom.text = ""
                model.saveSP( "")
            }
            imMesSearch.setOnClickListener {
                when(flag){
                    true->{
                        tvFrom.text = inputWhere
                        tvWhere.text = inputFrom
                        flag = false
                    }
                    false ->{
                        tvFrom.text = inputFrom
                        tvWhere.text = inputWhere
                        flag = true
                    }
                }
            }
            imBack.setOnClickListener {
                model.openCloseSheet(false)
                parentFragmentManager.beginTransaction().replace(R.id.placeHolder, FragmentStart()).commit()

            }
            bAllTichet.setOnClickListener {
                val fragment = FragmentTicket().apply {
                    arguments = Bundle().apply {
                        val concatDatePass = "$date, 1 пассажир"
                        putString(KEY_INPUT_DATE, concatDatePass)
                        putString(KEY_INPUT_WHERE, inputWhere)
                        putString(KEY_INPUT_FROM, inputFrom)
                    }
                }
//                val bundle = Bundle()
//                val concatDatePass = "$date, 1 пассажир"
//                bundle.putString(KEY_INPUT_DATE, concatDatePass)
//                bundle.putString(KEY_INPUT_WHERE, inputWhere)
//                bundle.putString(KEY_INPUT_FROM, inputFrom)
//                fragment.arguments = bundle
                model.getEndTicket()
                parentFragmentManager.beginTransaction().replace(R.id.placeHolder, fragment).commit()
            }
            tvFrom.text = inputFrom
            tvWhere.text = inputWhere
            rcViewTicket.layoutManager = LinearLayoutManager(requireContext())

            model.searchTicket.observe(viewLifecycleOwner){
                adapter = FragSearchAdapterTicket(it)
                rcViewTicket.adapter = adapter
            }




        }


    }

    private fun datePickerDialog() {
        calendar = Calendar.getInstance()

        datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                binding.tvDate.text = SimpleDateFormat("dd MMM", Locale.getDefault()).format(calendar.timeInMillis)
                date = SimpleDateFormat("dd MMM", Locale.getDefault()).format(calendar.timeInMillis)
                val dateWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.timeInMillis)
                val concat = " ,$dateWeek"
                binding.tvWeek.text = concat
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()

    }
    private fun datePickerDialogObr() {
        calendar = Calendar.getInstance()

        datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()

    }




}