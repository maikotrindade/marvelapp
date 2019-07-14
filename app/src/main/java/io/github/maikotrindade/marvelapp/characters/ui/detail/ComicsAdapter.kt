package io.github.maikotrindade.marvelapp.characters.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.characters.domain.model.Comic
import kotlinx.android.synthetic.main.item_series_comics.view.*

class ComicsAdapter(private val comics: List<Comic>) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_series_comics, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = comics[position]
        holder.txtTitle.text = comic.title

        val imageUrl = comic.thumbnail.path + "." + comic.thumbnail.extension
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