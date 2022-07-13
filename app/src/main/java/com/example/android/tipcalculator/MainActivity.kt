package com.example.android.tipcalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.android.tipcalculator.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set listener for when calculate button is clicked by user
        binding.calculateButton.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                    view,
                    keyCode

            )
        }
    }

    private fun calculateTip() {

        //take string input from user on edit text field and assign to a variable
        val stringInTextField = binding.costOfServiceEditText.text.toString()

        //convert string user input to double or null
        val cost = stringInTextField.toDoubleOrNull()

        //if no input is entered by the user, exit without executing the rest of the method
        if (cost == null) {
            Toast.makeText(
                    this,
                    "Error: Enter the cost of service to calculate tip",
                    Toast.LENGTH_LONG
            ).show()
            binding.tipResult.text = ""
            return
        }


        //get tip percentage based on option selected
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //calculate tip
        var tip = tipPercentage * cost

        //check if switch is on
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            //hide keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}