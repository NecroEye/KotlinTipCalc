package com.example.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tipcalc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.CalculateButton.setOnClickListener {

            displayTip()

        }

    }

    private fun displayTip(){

        val cost: Double

        if(binding.Amount.text.isNotEmpty()){

            val stringField : String = binding.Amount.text.toString()
             cost = stringField.toDouble()
            Log.d("Main", "Fiyat: ${cost}")

            val tipResult : Double = when(binding.tipOptions.checkedRadioButtonId){

                R.id.amazing_tip -> 0.20
                R.id.good_tip -> 0.18
                else -> 0.15

            }

            if(binding.roundSwitch.isChecked){

                var tip = cost * tipResult
                tip = kotlin.math.ceil(tip)
                val numberFormat = NumberFormat.getCurrencyInstance().format(tip)
                binding.tipResult.text =  getString(R.string.tip_amount, numberFormat)

            }
            else{
                binding.tipResult.text = "There is no tip"
            }
        }
        else{
            Snackbar.make(binding.CalculateButton, "You must type cost amount.", Snackbar.LENGTH_SHORT).show()
        }





    }

}