package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.utils.LottieValueAnimator
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.SlidesOnBoardingBinding
import com.b21cap0051.naratik.dataresource.datamodellist.OnBoardingModel

class OnBoardingItemAdapter(private var onBoardingModel: List<OnBoardingModel>) :
    RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {


    inner class OnBoardingItemViewHolder(private val binding: SlidesOnBoardingBinding) : RecyclerView.ViewHolder(binding.root){
        

        fun bind(onBoardingModel: OnBoardingModel){
            binding.laiOnBoard.imageAssetsFolder = "assets"
            binding.laiOnBoard.setAnimation(onBoardingModel.anim)
            binding.tvTitleOnBoard.text = onBoardingModel.title
            binding.tvOverviewOnBoard.text = onBoardingModel.overview
        }
        
    }
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            SlidesOnBoardingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onBoardingModel[position])
    }

    override fun getItemCount(): Int {
        return onBoardingModel.size
    }
}