package com.b21cap0051.naratik.ui.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.b21cap0051.naratik.R

class OnBoardingItemAdapter(private var modelOnBoarding: List<ModelOnBoarding>) :
    RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {


    inner class OnBoardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imgOnBoard : ImageView = view.findViewById(R.id.ivOnBoard)
        val titleOnBoard : TextView = view.findViewById(R.id.tvTitleOnBoard)
        val overviewOnBoard : TextView = view.findViewById(R.id.tvOverviewOnBoard)

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