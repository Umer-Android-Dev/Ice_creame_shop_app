package com.appozee.technologies.myicecream

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appozee.technologies.myicecream.adapter.IceCreamAdapter
import com.appozee.technologies.myicecream.databinding.ActivityMainBinding
import com.appozee.technologies.myicecream.databinding.ActivityOrderDataBinding
import com.appozee.technologies.myicecream.model.IceCreamFlavor

class OrderData : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDataBinding

    private lateinit var iceCreamAdapter: IceCreamAdapter
    private lateinit var iceCreamList: List<IceCreamFlavor>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iceCreamList = generateIceCreamData()

        // Initialize RecyclerView and adapter
        iceCreamAdapter = IceCreamAdapter(iceCreamList)
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = iceCreamAdapter

        binding.ivBackBtn.setOnClickListener {
            onBackPressed()
        }

    }

    private fun generateIceCreamData(): List<IceCreamFlavor> {
        return listOf(
            IceCreamFlavor(1, "Vanilla"),
            IceCreamFlavor(2, "Chocolate"),
            // Add more flavors as needed
        )
    }
}