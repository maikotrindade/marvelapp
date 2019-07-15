package io.github.maikotrindade.marvelapp.characters.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import kotlinx.android.synthetic.main.item_character.view.*

class FavoriteCharacterAdapter(private val output: FavoriteAdapterCharacterView,
                               val characters: MutableList<Character>) : RecyclerView.Adapter<FavoriteCharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.txtName.text = character.name

        val imageUrl = character.thumbnail.path + "." + character.thumbnail.extension
        Glide
            .with(holder.itemView.context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.imgCharacter)

        holder.imgStar.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, R.drawable.star))

        holder.itemView.setOnClickListener {
            output.onSelectCharacter(character)
        }

        holder.imgStar.setOnClickListener {
            output.onNotFavoriteCharacter(character, position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName = view.txtTitle!!
        val imgCharacter = view.imgItem!!
        val imgStar = view.imgStar!!
    }

    fun removeAt(position: Int) {
        characters.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, characters.size)
    }
}