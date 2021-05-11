package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.model.ModelOnBoarding

class OnBoardingItemAdapter(private var modelOnBoarding: List<ModelOnBoarding>) :
    RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {


    inner class OnBoardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val imgOnBoard : ImageView = view.findViewById(R.id.ivOnBoard)
        private val titleOnBoard : TextView = view.findViewById(R.id.tvTitleOnBoard)
        private val overviewOnBoard : TextView = view.findViewById(R.id.tvOverviewOnBoard)

        fun bind(modelOnBoarding: ModelOnBoarding){
            imgOnBoard.setImageResource(modelOnBoarding.images)
            titleOnBoard.text = modelOnBoarding.title
            overviewOnBoard.text = modelOnBoarding.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.slides_onboarding,parent,false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(modelOnBoarding[position])
    }

    override fun getItemCount(): Int {
        return modelOnBoarding.size
    }
}