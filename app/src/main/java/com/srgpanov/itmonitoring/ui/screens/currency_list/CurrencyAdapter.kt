package com.srgpanov.itmonitoring.ui.screens.currency_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srgpanov.itmonitoring.data.model.Valute
import com.srgpanov.itmonitoring.databinding.ItemCurrencyBinding
import com.srgpanov.itmonitoring.other.AdapterListener
import com.srgpanov.itmonitoring.other.getValuteFlag

class CurrencyAdapter:RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {
    private val items = mutableListOf<Valute>()
    var listener:AdapterListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CurrencyHolder(ItemCurrencyBinding.inflate(inflater,parent,false)
        )
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun setItems(list: List<Valute>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
    fun getItem(position: Int): Valute {
        return items[position]
    }


    inner class CurrencyHolder(private val binding: ItemCurrencyBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:Valute){
            binding.tvCurrency.text=item.charCode
            binding.ivFlag.setImageResource(FlagContainer.getValuteFlag(item.charCode))
            binding.container.setOnClickListener {
                listener?.onClick(it,bindingAdapterPosition)
            }
        }

    }
}
