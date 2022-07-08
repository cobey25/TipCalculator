package com.example.android.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.tipcalculator.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set listener for when calculate button is clicked by user
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip() {

        //take string input from user on edit text field and assign to a variable
        val stringInTextField = binding.costOfService.text.toString()

        //convert string user input to double
        val cost = stringInTextField.toDouble()

        //get the attribute of tip options and assign to a variable
        val selectedId = binding.tipOptions.checkedRadioButtonId

        //get tip percentage based on option selected
        val tipPercentage = when(selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //calculate tip
        var tip = tipPercentage * cost

        //check if switch is on
        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}