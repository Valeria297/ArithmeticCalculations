package com.example.calculator11.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator11.databinding.FragmentCalculatorBinding
import com.example.calculator11.domain.ParsingAndCalculating
import com.example.calculator11.extensions.addToolbarInset
import com.example.calculator11.extensions.toast

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentCalculatorBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            bt0.setOnClickListener { setTextFields("0") }
            bt1.setOnClickListener { setTextFields("1") }
            bt2.setOnClickListener { setTextFields("2") }
            bt3.setOnClickListener { setTextFields("3") }
            bt4.setOnClickListener { setTextFields("4") }
            bt5.setOnClickListener { setTextFields("5") }
            bt6.setOnClickListener { setTextFields("6") }
            bt7.setOnClickListener { setTextFields("7") }
            bt8.setOnClickListener { setTextFields("8") }
            bt9.setOnClickListener { setTextFields("9") }

            minusBt.setOnClickListener { setTextFields("-") }
            plusBt.setOnClickListener { setTextFields("+") }
            multiplyBt.setOnClickListener { setTextFields("*") }
            divBt.setOnClickListener { setTextFields("/") }
            bracket1Bt.setOnClickListener { setTextFields("(") }
            bracket2Bt.setOnClickListener { setTextFields(")") }
            pointBt.setOnClickListener { setTextFields(".") }

            allClearBt.setOnClickListener {
                expression.text = ""
                result.text = ""
            }

            backBt.setOnClickListener {
                val str = expression.text.toString()
                if (str.isNotEmpty())
                    expression.text = str.substring(0, str.length - 1)
                result.text = ""
            }

            equalBt.setOnClickListener {
                try {
                    val parsAndCalc = ParsingAndCalculating()
                    val line = expression.text.toString()

                    if (!parsAndCalc.checkString(line))
                        requireContext().toast("You have entered incorrect data")


                    if (parsAndCalc.calculate(line) == 0.0)
                        requireContext().toast("You can't divide by zero")

                    result.text = parsAndCalc.calculate(line).toString()
                } catch (e: Exception) {
                    Log.d("Error", "message: ${e.message}")
                }
            }
            appBar.addToolbarInset()
        }
    }

    private fun setTextFields(str: String) {
        if (binding.result.text.isNotEmpty()) {
            binding.expression.text = binding.result.text
            binding.result.text = ""
        }
        binding.expression.append(str)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}