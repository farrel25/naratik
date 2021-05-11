package com.b21cap0051.naratik.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.BatikListAdapter
import com.b21cap0051.naratik.databinding.ActivityBatikBinding
import com.b21cap0051.naratik.model.BatikModel
import com.b21cap0051.naratik.util.ItemBatikCallBack

class BatikActivity : AppCompatActivity(),ItemBatikCallBack {
    private lateinit var BatikAdapter : BatikListAdapter
    private lateinit var binding : ActivityBatikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        BatikAdapter = BatikListAdapter(this)
        binding.rvBatik.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rvBatik.adapter = BatikAdapter
    }
    override fun itemBatikClick(model: BatikModel) {

    }
}