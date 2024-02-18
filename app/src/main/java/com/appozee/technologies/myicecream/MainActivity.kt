package com.appozee.technologies.myicecream

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.appozee.technologies.myicecream.databinding.ActivityMainBinding
import com.appozee.technologies.myicecream.model.DropDownData


class MainActivity : AppCompatActivity() {

    private var list: ArrayList<DropDownData>? = null
    private var listAdapter: ArrayAdapter<String>? = null
    private lateinit var binding: ActivityMainBinding
    var calValue:Double = 3.69

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
        dropDownDataSet()
    }

    private fun setListener()
    {
        binding.ivMenuBtn!!.setOnClickListener { v ->
            if (!binding.drawerLayout.isDrawerOpen(binding.loDrawer)) {
                binding.drawerLayout.openDrawer(binding.loDrawer)
            }
        }

        binding.linOrder!!.setOnClickListener {
            navigationClose()
            startActivity(Intent(this,OrderData::class.java))
        }
    }

    private fun dropDownDataSet() {
        list = ArrayList<DropDownData>()
        val cone = DropDownData("Cone","3.69")
        val cup = DropDownData("Cup","3.39")

        list!!.add(cone)
        list!!.add(cup)
        binding.tvConeOrCup.setText("Cone")
        listAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_dropdown_item,list!!.map { it.nameData })
        binding.tvConeOrCup.setAdapter(listAdapter)
        binding.tvConeOrCup.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                Log.i("MY_DATA", "dropDownDataSet:${list!!.get(position).nameData} = ${list!!.get(position).valueData}")
                calValue = list!!.get(position).valueData.toDouble()
                if (binding.etQuantity.text!!.isNotEmpty()){
                    val checkNumber =  binding.etQuantity.text.toString().toInt()
                    calculateResults(checkNumber,calValue)
                }
            }

        binding.etQuantity.setText("0")
        binding.ivAdd.setOnClickListener {
            if (binding.etQuantity.text!!.isNotEmpty()){
                val checkNumber =  binding.etQuantity.text.toString().toInt()
                if (checkNumber>=0){
                    val updateValue = checkNumber + 1
                    binding.etQuantity.setText(updateValue.toString())
                    calculateResults(updateValue,calValue)
                }
            }
        }

        binding.ivMinus.setOnClickListener {
            if (binding.etQuantity.text!!.isNotEmpty()){
                val checkNumber =  binding.etQuantity.text.toString().toInt()
                if (checkNumber>0){
                    val updateValue = checkNumber - 1
                    binding.etQuantity.setText(updateValue.toString())
                    calculateResults(updateValue,calValue)
                }else{
                    Toast.makeText(this, "Please add quantity more than 0", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun calculateResults(quantity: Int, calValue: Double) {
        binding.tvResult.text = (calValue * quantity.toDouble()).toString()
    }

    fun navigationClose(){
        if (binding.drawerLayout!!.isDrawerOpen(binding.loDrawer!!)) {
            binding.drawerLayout!!.closeDrawer(binding.loDrawer!!)
        }
    }


}