package com.b21cap0051.naratik.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ArticleLargeListAdapter
import com.b21cap0051.naratik.adapter.ResultMotifAdapter
import com.b21cap0051.naratik.databinding.ActivityResultBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ResultModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemResultCallback
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class ResultActivity : AppCompatActivity(),ItemResultCallback {
    private lateinit var batikChart : PieChart
    private lateinit var binding : ActivityResultBinding
    private lateinit var adapterResult : ResultMotifAdapter
    
    companion object{
        const val KEY_DATA = "data"
    }
    
    
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadListMotifResult()
        
        batikChart = binding.pcResult
        setupPieChart()
        loadBatikResult()
        
    }
    
    private fun setupPieChart()
    {
        batikChart.isDrawHoleEnabled = true
        batikChart.setUsePercentValues(true)
        batikChart.setDrawEntryLabels(false)
        batikChart.centerText = "Batik"
        batikChart.setCenterTextSize(12F)
        batikChart.description.isEnabled = false
        val legend : Legend = batikChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.textSize =12f
        legend.setDrawInside(false)
        legend.isEnabled = true
    }
    
    private fun loadListMotifResult(){
        adapterResult = ResultMotifAdapter(this)
    
        val listMotifs= DataDummy.generateDummyResult()
        adapterResult.setList(listMotifs)
        binding.rvMotifResult.layoutManager = LinearLayoutManager(this)
        binding.rvMotifResult.adapter = adapterResult
    }
    
    private fun loadBatikResult(){
        val entries : ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(2f , "Batik Cetak"))
        entries.add(PieEntry(15f , "Batik Tulis"))
    
    
        val dataSet = PieDataSet(entries , "")
        dataSet.setColors(resources.getColor(R.color.grey_700),resources.getColor(R.color.yellow_500))
    
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(batikChart))
        data.setValueTextSize(10f)
        data.setValueTextColor(resources.getColor(R.color.black))
    
        batikChart.data = data
        batikChart.invalidate()
    
        batikChart.animateY(1400 , Easing.EaseInOutQuad)
    }
    
    override fun itemResultClick(model : ResultModel)
    {
    }
}