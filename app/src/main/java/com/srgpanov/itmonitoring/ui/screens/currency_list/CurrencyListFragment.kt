package com.srgpanov.itmonitoring.ui.screens.currency_list

import androidx.core.app.SharedElementCallback
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.srgpanov.itmonitoring.R
import com.srgpanov.itmonitoring.data.model.Valute
import com.srgpanov.itmonitoring.databinding.FragmentCurrencyListBinding
import com.srgpanov.itmonitoring.other.AdapterListener


class CurrencyListFragment : Fragment() {
    private var _binding: FragmentCurrencyListBinding? = null
    private val binding: FragmentCurrencyListBinding
        get() = _binding!!
    private val adapter by lazy { CurrencyAdapter() }


    companion object {
        const val TAG="CurrencyListFragment"
        const val KEY_LIST="KEY_LIST"
        const val KEY_INPUT="KEY_INPUT"
        const val KEY_OUTPUT="KEY_OUTPUT"
        const val KEY_REQUEST="KEY_REQUEST"
        const val KEY_VALUTE="KEY_VALUTE"
        fun newInstance(list: List<Valute>,requestKey:String):CurrencyListFragment{
            return CurrencyListFragment().apply {
                arguments=Bundle().apply {
                    putParcelableArrayList(KEY_LIST, ArrayList(list))
                    putString(KEY_REQUEST, requestKey)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
    }

    private fun setupRV() {
        val list = arguments?.getParcelableArrayList<Valute>(KEY_LIST)
        Log.d(TAG, "onViewCreated: ${list?.size}")
        if (list != null) {
            adapter.setItems(list)
        } else {
            parentFragmentManager.popBackStack()
            Log.e(TAG, "setupRV: empty adapter" )
        }
        binding.rv.adapter = adapter
        Log.d(TAG, "setupRV: ${binding.rv.adapter}")
        binding.rv.addItemDecoration(CurrencyDecorator(requireContext()))
        adapter.listener = object : AdapterListener {
            override fun onClick(view: View, position: Int) {
                val valute = adapter.getItem(position)
                parentFragmentManager.setFragmentResult(
                    getReqType(), bundleOf(KEY_VALUTE to valute)
                )
                parentFragmentManager.popBackStack()
            }

        }
    }

    private fun getReqType(): String {
        return arguments?.getString(KEY_REQUEST) ?: KEY_INPUT
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
