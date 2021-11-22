package br.com.raphaelamorim.cat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.raphaelamorim.cat.R
import br.com.raphaelamorim.cat.databinding.ItemImageBinding
import com.bumptech.glide.Glide

class ImagesAdapter: RecyclerView.Adapter<ImagesAdapter.ItemImageViewHolder>() {

    private val list = mutableListOf<String>()

    inner class ItemImageViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(link: String) {
            with(binding.ivItem) {
                Glide.with(context).load(link).placeholder(R.drawable.ic_fail).into(this)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageViewHolder {
        return ItemImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemImageViewHolder, position: Int) {
       holder.bind(list[position])
    }

    fun setImages(newList: List<String>)  {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size
}