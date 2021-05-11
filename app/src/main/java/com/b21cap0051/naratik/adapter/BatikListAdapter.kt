package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.BatikitemBinding
import com.b21cap0051.naratik.model.BatikModel
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import eightbitlab.com.blurview.RenderScriptBlur
import java.util.*

class BatikListAdapter(private val callBack: ItemBatikCallBack) :
    RecyclerView.Adapter<BatikListAdapter.ItemTarget>() {

    val listbatik = ArrayList<BatikModel>()

    fun setList(listbatik: ArrayList<BatikModel>) {
        if (listbatik != null) {
            this.listbatik.clear()
            this.listbatik.addAll(listbatik)
            notifyDataSetChanged()
        }
    }

    inner class ItemTarget(private val binding: BatikitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BatikModel) {

            var height = 300
            if (adapterPosition % 2 == 1) {
                height = 150
                binding.imageBatikList.layoutParams.height = height
            }

            binding.blurry.setupWith(binding.root)
                .setBlurAlgorithm(RenderScriptBlur(itemView.context))
                .setBlurRadius(20f)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true)

            Glide.with(itemView.context)
                .load(model.image)
                .apply(RequestOptions().override(200, height))
                .into(binding.imageBatikList)
            binding.batikName.text = model.name
            binding.number.text = model.id

            binding.cvBatik.setOnClickListener {
                callBack.itemBatikClick(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTarget {
        return ItemTarget(
            BatikitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemTarget, position: Int) {
        holder.bind(listbatik[position])
    }

    override fun getItemCount(): Int = listbatik.size


}


