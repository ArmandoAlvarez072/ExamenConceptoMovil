package armando.alvarez.examenconceptomovil.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import armando.alvarez.examenconceptomovil.data.model.NasaItem
import armando.alvarez.examenconceptomovil.databinding.ItemNasaBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class NasaAdapter : RecyclerView.Adapter<NasaAdapter.ViewHolder>() {

    private lateinit var context: Context

    private val callback = object : DiffUtil.ItemCallback<NasaItem>() {
        override fun areItemsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
            return oldItem.title == newItem.title
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaAdapter.ViewHolder {
        context = parent.context
        val binding = ItemNasaBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NasaAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(
        private val binding: ItemNasaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(nasaItem: NasaItem) {

            binding.tvTitle.text = nasaItem.title
            binding.tvDescription.text = nasaItem.explanation

            Glide.with(binding.imgNasa.context)
                .load(nasaItem.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgNasa)

        }

    }

}