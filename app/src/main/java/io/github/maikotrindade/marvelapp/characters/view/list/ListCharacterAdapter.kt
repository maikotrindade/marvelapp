package io.github.maikotrindade.marvelapp.characters.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import kotlinx.android.synthetic.main.item_character.view.*

class ListCharacterAdapter(private val output: ListAdapterCharacterView,
                           var characters: MutableList<Character>) : RecyclerView.Adapter<ListCharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun setItems(newCharacters: MutableList<Character>) {
        characters = newCharacters
        notifyDataSetChanged()
    }

    fun addMoreItems(newCharacters: MutableList<Character>) {
        val lastIndex = characters.size
        characters.addAll(newCharacters)
        notifyItemRangeChanged(lastIndex, characters.size - 1)
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

        holder.itemView.setOnClickListener {
            output.onSelectCharacter(character)
        }

        holder.imgStar.setOnClickListener {
            output.onFavoriteCharacter(character)
            holder.imgStar.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, R.drawable.star))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName = view.txtTitle!!
        val imgCharacter = view.imgItem!!
        val imgStar = view.imgStar!!
    }
}