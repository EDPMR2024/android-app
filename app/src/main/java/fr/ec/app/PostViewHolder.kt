package fr.ec.app

import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView = itemView.findViewById<TextView>(R.id.title)
    private val subTitleTextView = itemView.findViewById<TextView>(R.id.subTitle)

    fun bind(post : Post) {
        titleTextView.text = post.title
        subTitleTextView.text = post.subTitle

    }

}