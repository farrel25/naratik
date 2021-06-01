package com.b21cap0051.naratik.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ResultMotifAdapter
import com.b21cap0051.naratik.databinding.ActivityResultBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ResultModel
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.dataresource.remotedata.model.PredictResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.TechniquePredictResponse
import com.b21cap0051.naratik.mainview.PredictMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemResultCallback
import com.b21cap0051.naratik.util.vo.Status
import com.b21cap0051.naratik.util.voapi.StatusResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter


class ResultActivity : AppCompatActivity() , ItemResultCallback
{
	private lateinit var batikChart : PieChart
	private lateinit var binding : ActivityResultBinding
	private lateinit var adapterResult : ResultMotifAdapter
	
	companion object
	{
		const val KEY_DATA = "data"
	}
	
	private lateinit var  mainView : PredictMainView
	private lateinit var data : ImageUploadModel
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityResultBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val dataIntent = intent.extras
		data =  dataIntent?.getParcelable<ImageUploadModel>(KEY_DATA) as ImageUploadModel
		
		val factory = ViewFactoryModel.GetInstance(this)
		mainView = ViewModelProvider(this,factory)[PredictMainView::class.java]
		
		
		mainView.IsDoneMotif().observe(this,{
			   response ->
			when(response.Status){
				Status.SUCCESS -> {
					LoadingProcess(response.Data!!)
				}
				Status.ERROR -> {
				
				}
				
				Status.LOADING -> {
					LoadingProcess(response.Data!!)
				}
			}
		})
		
		mainView.IsDoneTechnique().observe(this,{
		
		})
		
		
		mainView.GetTechnique(getID(data.uri)).observe(this,{
			response ->
			when(response.statusResponse){
				StatusResponse.SUCCESS->{
					getImage(response.body.imgUrl!!)
					setupPieChart()
					loadBatikResult(response.body)
				}
				StatusResponse.ERROR -> {
				
				}
			}
		})
		
		mainView.GetMotif(getID(data.uri)).observe(this,{
			response ->
			when(response.statusResponse){
				StatusResponse.SUCCESS->{
				loadListMotifResult(response.body)
				}
				StatusResponse.ERROR -> {
				
				}
				StatusResponse.EMPTY ->{
				
				}
			}
		})
		
		
	}
	
	private fun LoadingProcess(stat : Boolean){
		if(stat){
			binding.txtMotifSimilarity.visibility = View.VISIBLE
			binding.picDetail.visibility = View.VISIBLE
			binding.picDetail.visibility = View.VISIBLE
			binding.btnFeedback.visibility = View.VISIBLE
			binding.btnGoHome.visibility = View.VISIBLE
			binding.LoadingResult.visibility = View.GONE
		}else{
			binding.txtMotifSimilarity.visibility = View.GONE
			binding.LoadingResult.visibility = View.VISIBLE
			binding.picDetail.visibility = View.GONE
			binding.picDetail.visibility = View.GONE
			binding.btnFeedback.visibility = View.GONE
			binding.btnGoHome.visibility = View.GONE
		}
	}
	
	private fun getImage(id : String){
		Glide.with(this)
			.load(id)
			.apply(RequestOptions().override(160,192))
			.placeholder(R.drawable.ic_loading)
			.error(R.drawable.ic_error)
			.into(binding.ivResult)
	}
	
	private fun getID(uri : Uri): String{
		val temp = uri.lastPathSegment?.split(".")
		if (temp != null)
		{
			return temp[0]
		}
		return ""
	}
	
	private fun setupPieChart()
	{
		batikChart = binding.pcResult
		batikChart.isDrawHoleEnabled = true
		batikChart.setUsePercentValues(true)
		batikChart.setDrawEntryLabels(false)
		batikChart.centerText = "Technique Batik Result"
		batikChart.setCenterTextSize(12F)
		batikChart.description.isEnabled = false
		val legend : Legend = batikChart.legend
		legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
		legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
		legend.orientation = Legend.LegendOrientation.HORIZONTAL
		legend.textSize = 12f
		legend.setDrawInside(false)
		legend.isEnabled = true
	}
	
	private fun loadListMotifResult(data : PredictResponse)
	{
		adapterResult = ResultMotifAdapter(this)
		
		val listMotifs = data.motifResult
		adapterResult.setList(listMotifs)
		binding.rvMotifResult.layoutManager = LinearLayoutManager(this)
		binding.rvMotifResult.adapter = adapterResult
	}
	
	private fun loadBatikResult(result : TechniquePredictResponse)
	{
		val entries : ArrayList<PieEntry> = ArrayList()
		entries.add(PieEntry(result.technique?.get(0)?.value?.toFloat()!!, result?.technique?.get(0)?.techniqueName+" Batik" ))
		entries.add(PieEntry(result.technique?.get(1)?.value?.toFloat()!!, result?.technique?.get(1)?.techniqueName+" Batik" ))
		
		val dataSet = PieDataSet(entries , "Technique Detect Result")
		dataSet.setColors(
			resources.getColor(R.color.grey_700) ,
			resources.getColor(R.color.brown_200)
		                 )
		
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