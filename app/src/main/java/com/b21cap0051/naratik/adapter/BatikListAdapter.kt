package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowBatikBinding
import com.b21cap0051.naratik.dataresource.datamodellist.BatikModel
import com.b21cap0051.naratik.ui.DetailBatikActivity
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import eightbitlab.com.blurview.RenderScriptBlur
import java.util.*

class BatikListAdapter(private val callBack : ItemBatikCallBack) :
    RecyclerView.Adapter<BatikListAdapter.ItemTarget>() {

    private val listBatik = ArrayList<BatikModel>()

    fun setList(listBatik : ArrayList<BatikModel>) {
        this.listBatik.clear()
        this.listBatik.addAll(listBatik)
        notifyDataSetChanged()
    }

    inner class ItemTarget(private val binding : ItemRowBatikBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model : BatikModel) {

            var height = 900
            if (adapterPosition % 2 == 1) {
                height = 450
            }

            binding.blurry.setupWith(binding.root)
                .setBlurAlgorithm(RenderScriptBlur(itemView.context))
                .setBlurRadius(20f)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true)

            Glide.with(itemView.context)
                .load(model.image)
                .apply(RequestOptions().override(200 , height))
                .into(binding.ivItemBatik)
            binding.tvItemNameBatik.text = model.name
            binding.tvItemLocationBatik.text = model.id

            binding.cvBatik.setOnClickListener {
                callBack.itemBatikClick(model)
            }
        }
        
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int): ItemTarget {
        return ItemTarget(
            ItemRowBatikBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
                                       )
                         )
    }

    override fun onBindViewHolder(holder : ItemTarget , position : Int) {
        holder.bind(listBatik[position])
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context , DetailBatikActivity::class.java)
//            intent.putExtra(DetailBatikActivity.,listArticle[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listBatik.size
    }
    

}



