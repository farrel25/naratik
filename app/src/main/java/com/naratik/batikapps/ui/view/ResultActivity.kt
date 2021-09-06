package com.naratik.batikapps.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.naratik.batikapps.R
import com.naratik.batikapps.core.remote.model.ImageUploadModel
import com.naratik.batikapps.core.remote.model.MotifResponseItem
import com.naratik.batikapps.core.remote.model.PredictResponse
import com.naratik.batikapps.core.remote.model.TechniquePredictResponse
import com.naratik.batikapps.databinding.ActivityResultBinding
import com.naratik.batikapps.ui.adapter.ResultMotifAdapter
import com.naratik.batikapps.ui.view.cameraui.CameraActivity
import com.naratik.batikapps.ui.viewmodel.PredictMainView
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultActivity : AppCompatActivity()
{
	private lateinit var batikChart : PieChart
	private lateinit var binding : ActivityResultBinding
	private lateinit var adapterResult : ResultMotifAdapter
	
	companion object
	{
		const val KEY_DATA = "data"
	}
	
	private val mainView : PredictMainView by viewModels()
	private lateinit var data : ImageUploadModel
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityResultBinding.inflate(layoutInflater)
		setContentView(binding.root)
		batikChart = binding.pcResult
		loadActionBar()
		
		val dataIntent = intent.extras
		data = dataIntent?.getParcelable<ImageUploadModel>(KEY_DATA) as ImageUploadModel
		
		
		mainView.isDoneTechnique().observe(this , {
			
			it.getContentIfNotHandled().let {
				when (it)
				{
					is ResultStateData.Success ->
					{
						Toast.makeText(
							this ,
							it.message ,
							Toast.LENGTH_SHORT
						              )
							.show()
					}
					
					is ResultStateData.Failure ->
					{
						Toast.makeText(
							this ,
							"Sorry! Error Happen with ${it.message}" ,
							Toast.LENGTH_SHORT
						              )
							.show()
					}
					
					
					else                       ->
					{
					
					}
				}
			}
			
			
		})
		
		
		mainView.isDoneMotif().observe(this , {
			
			it.getContentIfNotHandled().let {
				when (it)
				{
					is ResultStateData.Success ->
					{
						loadingProcess(it.data)
					}
					
					is ResultStateData.Failure ->
					{
						Toast.makeText(
							this ,
							"Sorry! Error Happen with ${it.message}" ,
							Toast.LENGTH_SHORT
						              )
							.show()
					}
					
					is ResultStateData.Loading ->
					{
						loadingProcess(false)
					}
					else                       ->
					{
					
					}
				}
			}
			
		})
		
		mainView.getTechnique(getID(data.uri)).observe(this , {
			when (it)
			{
				is ResultStateData.Success ->
				{
					getImage(it.data.imgUrl!!)
					setupPieChart()
					loadTechniqueChart(it.data)
					loadingProcess(true)
					
				}
				is ResultStateData.Failure ->
				{
					loadingProcess(true)
					Toast.makeText(
						this ,
						"Sorry! Error Happen with ${it.message}" ,
						Toast.LENGTH_SHORT
					              ).show()
					Log.d("TAG" , it.message)
				}
				is ResultStateData.Loading ->
				{
					loadingProcess(false)
				}
			}
		})
		
		mainView.getMotif(getID(data.uri)).observe(this , {
			when (it)
			{
				is ResultStateData.Success ->
				{
					lifecycleScope.launch {
						with(Dispatchers.Main) {
							loadingProcess(true)
							loadListMotifResult(it.data)
						}
					}
					
				}
				is ResultStateData.Failure ->
				{
					
					lifecycleScope.launch {
						delay(2000)
						with(Dispatchers.Main) {
							loadingProcess(true)
							
						}
					}
					Toast.makeText(
						this ,
						"Sorry! Error Happen with ${it.message}" ,
						Toast.LENGTH_SHORT
					              )
						.show()
				}
				is ResultStateData.Loading ->
				{
					loadingProcess(false)
				}
			}
		})
		
		binding.btnGoHome.setOnClickListener {
			val intent = Intent(this , CameraActivity::class.java)
			startActivity(intent)
		}
		
		binding.btnFeedback.setOnClickListener {
			Toast.makeText(this , "Feedback Feature is Coming Soon!" , Toast.LENGTH_SHORT).show()
		}
	}
	
	private fun loadActionBar()
	{
		val title : TextView = findViewById(R.id.tvTitle)
		title.text = resources.getString(R.string.result)
		val btnBack : Button = findViewById(R.id.btnBack)
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
	
	private fun loadingProcess(stat : Boolean)
	{
		if (stat)
		{
			binding.nsvResult.visibility = View.VISIBLE
			binding.include.customActionBarBack.visibility = View.VISIBLE
			binding.laiLoading.visibility = View.GONE
		} else
		{
			binding.include.customActionBarBack.visibility = View.GONE
			binding.laiLoading.visibility = View.VISIBLE
			binding.nsvResult.visibility = View.GONE
		}
	}
	
	private fun getImage(id : String)
	{
		Glide.with(this)
			.load(id)
			.apply(RequestOptions().override(160 , 192))
			.placeholder(R.drawable.ic_loading)
			.error(R.drawable.ic_error)
			.into(binding.ivResult)
	}
	
	private fun getID(uri : Uri) : String
	{
		val temp = uri.lastPathSegment?.split(".")
		
		if (temp != null)
		{
			return temp[0]
		}
		return ""
	}
	
	
	private fun loadListMotifResult(data : PredictResponse)
	{
		adapterResult = ResultMotifAdapter()
		
		val listMotifs = data.motif
		if (listMotifs?.isNotEmpty()!!)
		{
			adapterResult.setList(listMotifs as List<MotifResponseItem>)
		}
		binding.rvMotifResult.layoutManager = LinearLayoutManager(this)
		binding.rvMotifResult.adapter = adapterResult
	}
	
	
	private fun setupPieChart()
	{
		batikChart.isDrawHoleEnabled = true
		batikChart.setUsePercentValues(true)
		batikChart.setDrawEntryLabels(false)
		batikChart.centerText = "Batik Making Technique"
		batikChart.setCenterTextSize(10F)
		batikChart.description.isEnabled = false
		
		val legend = batikChart.legend
		legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
		legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
		legend.orientation = Legend.LegendOrientation.VERTICAL
		legend.textSize = 10f
		legend.setDrawInside(false)
		legend.isEnabled = true
	}
	
	
	private fun loadTechniqueChart(result : TechniquePredictResponse)
	{
		val entries = ArrayList<PieEntry>()
		if (result.technique?.isNotEmpty()!!)
		{
			val data = result.technique!!
			entries.add(PieEntry(data[0]!!.value?.toFloat()!! , data[0]!!.techniqueName))
			entries.add(PieEntry(data[1]!!.value?.toFloat()!! , data[1]!!.techniqueName))
		}
		val colours = ArrayList<Int>()
		colours.add(ContextCompat.getColor(applicationContext , R.color.blue_200))
		colours.add(ContextCompat.getColor(applicationContext , R.color.cream))
		
		val dataSetPie = PieDataSet(entries , "Making Technique Result")
		dataSetPie.setColors(colours)
		
		val dataPie = PieData(dataSetPie)
		dataPie.setDrawValues(true)
		dataPie.setValueFormatter(PercentFormatter(batikChart))
		dataPie.setValueTextSize(10f)
		dataPie.setValueTextColor(ContextCompat.getColor(applicationContext , R.color.black))
		
		batikChart.data = dataPie
		batikChart.invalidate()
		batikChart.animateY(1400 , Easing.EaseInOutQuad)
		
	}
	
	override fun onDestroy()
	{
		super.onDestroy()
		finish()
	}
}

