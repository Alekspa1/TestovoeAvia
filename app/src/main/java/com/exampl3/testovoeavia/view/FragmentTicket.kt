package com.exampl3.testovoeavia.view

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
import com.exampl3.testovoeavia.databinding.FragmentTicketBinding
import com.exampl3.testovoeavia.view.adapters.AdapterTicketEnd
import com.exampl3.testovoeavia.viewmodel.StartViewModel


class FragmentTicket : Fragment() {

    private lateinit var binding: FragmentTicketBinding
    private val model: StartViewModel by activityViewModels()
    private lateinit var adapter: AdapterTicketEnd


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val outputDate = arguments?.getString(KEY_INPUT_DATE)
        val outputtFrom = arguments?.getString(KEY_INPUT_FROM)
        val outputtWhere = arguments?.getString(KEY_INPUT_WHERE)
        val concat = "$outputtWhere-$outputtFrom"


        with(binding){
            tvTicketDate.text = outputDate
            tvTicketPass.text = concat
            imTicketBack.setOnClickListener {
                val fragment = FragmentSearch()
                val bundle = Bundle()
                bundle.putString(KEY_INPUT_WHERE, outputtWhere)
                bundle.putString(KEY_INPUT_FROM, outputtFrom)
                fragment.arguments = bundle
                parentFragmentManager.beginTransaction().replace(R.id.placeHolder, fragment).commit()
            }
            rcViewTicketEnd.layoutManager = LinearLayoutManager(requireContext())

            model.endTicket.observe(viewLifecycleOwner){
                adapter = AdapterTicketEnd(it)
                binding.rcViewTicketEnd.adapter = adapter
            }

            }
        }
    }


