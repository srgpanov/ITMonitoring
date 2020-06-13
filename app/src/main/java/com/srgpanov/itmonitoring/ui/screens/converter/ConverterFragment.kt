package com.srgpanov.itmonitoring.ui.screens.converter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.srgpanov.itmonitoring.App
import com.srgpanov.itmonitoring.R
import com.srgpanov.itmonitoring.data.model.Valute
import com.srgpanov.itmonitoring.databinding.FragmentConverterBinding
import com.srgpanov.itmonitoring.other.OnBackPressedListener
import com.srgpanov.itmonitoring.ui.MainActivity
import com.srgpanov.itmonitoring.ui.screens.currency_list.CurrencyListFragment
import com.srgpanov.itmonitoring.ui.screens.currency_list.CurrencyListFragment.Companion.KEY_INPUT
import com.srgpanov.itmonitoring.ui.screens.currency_list.CurrencyListFragment.Companion.KEY_OUTPUT
import com.srgpanov.itmonitoring.ui.screens.currency_list.CurrencyListFragment.Companion.KEY_VALUTE
import com.srgpanov.itmonitoring.ui.screens.currency_list.FlagContainer
import javax.inject.Inject


class ConverterFragment : Fragment(), ConverterContract.View, FragmentResultListener,
    OnBackPressedListener {
    private var _binding: FragmentConverterBinding? = null
    private val binding: FragmentConverterBinding
        get() = _binding!!

    @Inject
    lateinit var presenter: ConverterPresenter


    companion object {
        const val TAG = "ConverterFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.componentsHolder.getConverterComponent().injectConverterPresenter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        presenter.bindView(this)
        Log.d(TAG, "onViewCreated: presenter ${presenter.hashCode()}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        Log.d(TAG, "setResultListener:$requestKey ")
        val valute = result.getParcelable<Valute>(KEY_VALUTE)
        when (requestKey) {
            KEY_INPUT -> changeInputValute(valute)
            KEY_OUTPUT -> changeOutputValute(valute)
        }
    }


    override fun showConvertedCurrency(outValue: Float) {
        binding.tvOutputCurrency.text = formatCurrencyValue(outValue)
    }

    override fun restoreState(state: ConvertState) {
        Log.d(TAG, "showConvertedCurrency: ${state.outCurrency.date}")
        binding.etInputCurrency.setText(formatCurrencyValue(state.inValue))
        binding.tvOutputCurrency.text = formatCurrencyValue(state.outValue)
        binding.tvInputCode.text = state.inCurrency.charCode
        binding.tvOutputCode.text = state.outCurrency.charCode
        binding.btnSelectInputCurrency.setImageResource(FlagContainer.getValuteFlag(state.inCurrency.charCode))
        binding.btnSelectOutputCurrency.setImageResource(FlagContainer.getValuteFlag(state.outCurrency.charCode))
        if (state.dataFromLocal) {
            binding.tvLocalData.visibility = View.VISIBLE
            binding.ibtRefresh.visibility = View.VISIBLE
            binding.tvLocalData.text =
                getString(R.string.failed_to_load) + state.outCurrency.date
        } else {
            binding.tvLocalData.visibility = View.GONE
            binding.ibtRefresh.visibility = View.GONE
        }
    }

    private fun setupListeners() {
        binding.btnSelectInputCurrency.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                .replace(
                    R.id.main_container,
                    CurrencyListFragment.newInstance(presenter.getAvailableValuate(), KEY_INPUT),
                    CurrencyListFragment.TAG
                )
                .addToBackStack(null)
                .commit()
        }
        binding.btnSelectOutputCurrency.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                .replace(
                    R.id.main_container,
                    CurrencyListFragment.newInstance(presenter.getAvailableValuate(), KEY_OUTPUT),
                    CurrencyListFragment.TAG
                )
                .addToBackStack(null)
                .commit()
        }
        binding.btnConvert.setOnClickListener {
            presenter.convertCurrency()
            App.instance.componentsHolder.releaseConverterComponent()
        }
        binding.ibtRefresh.setOnClickListener {
            presenter.refresh()
        }
        parentFragmentManager.setFragmentResultListener(
            KEY_INPUT,
            viewLifecycleOwner,
            this
        )
        parentFragmentManager.setFragmentResultListener(
            KEY_OUTPUT,
            viewLifecycleOwner,
            this
        )
        binding.etInputCurrency.addTextChangedListener(onTextChanged = { charSequence: CharSequence?, _: Int, _: Int, _: Int ->
            charSequence?.let {
                val value = it.toString().toFloatOrNull()
                if (value != null) {
                    presenter.onInputValueChanged(value)
                } else {
                    Log.e(TAG, "setupListeners: wrong input value")
                }
            }
        })
    }

    private fun formatCurrencyValue(value: Float): String {
        return try {
            if (value.toString().split('.')[1] == "0") {
                return value.toString().split(".")[0]
            }
            val number2digits = String.format("%.2f", value)
            val delimetr = if (number2digits.contains(".")) "." else ","
            val splitedStr = number2digits.split(delimetr)
            return if (splitedStr[1] == "00") {
                number2digits.split(delimetr)[0]
            } else {
                "${splitedStr[0]}$delimetr${splitedStr[1]}"
            }
        } catch (e: Exception) {
            Log.e(TAG, "formatCurrencyValue: ",e )
            value.toString()
        }

    }

    private fun changeInputValute(valute: Valute?) {
        if (valute != null) {
            presenter.onInputValuteChanged(valute)
            binding.btnSelectInputCurrency.setImageResource(FlagContainer.getValuteFlag(valute.charCode))
            binding.tvInputCode.text = valute.charCode
        }
    }

    private fun changeOutputValute(valute: Valute?) {
        if (valute != null) {
            presenter.onOutputValuteChanged(valute)
            binding.btnSelectOutputCurrency.setImageResource(FlagContainer.getValuteFlag(valute.charCode))
            binding.tvOutputCode.text = valute.charCode
        }
    }

    override fun onBackPressed() {
        presenter.onRelease()
        App.instance.componentsHolder.releaseConverterComponent()
        (requireActivity() as MainActivity).onBackPressedSuper()
    }


}
