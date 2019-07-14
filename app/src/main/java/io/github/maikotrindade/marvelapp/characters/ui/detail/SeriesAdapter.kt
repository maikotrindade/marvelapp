package io.github.maikotrindade.marvelapp.characters.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.characters.domain.model.Series
import kotlinx.android.synthetic.main.item_series_comics.view.*

class SeriesAdapter(private val series: List<Series>) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_series_comics, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return series.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val seriesObj = series[position]
        holder.txtTitle.text = seriesObj.title

        val imageUrl = seriesObj.thumbnail.path + "." + seriesObj.thumbnail.extension
        Glide
            .with(holder.itemView.context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.imgItem)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.txtTitle!!
        val imgItem = view.imgItem!!
    }
}