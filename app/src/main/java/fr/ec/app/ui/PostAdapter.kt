package fr.ec.app.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R

class PostAdapter() : RecyclerView.Adapter<PostViewHolder>() {

    private var dataSource = mutableListOf<Post>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return if (viewType == R.layout.post_item_big) {
            PostViewHolder(
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item_big, parent, false)
            )
        } else {
            PostViewHolder(
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_item, parent, false)
            )
        }
    }

    fun show(post: List<Post>) {
        dataSource = post.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            R.layout.post_item_big
        } else {
            R.layout.post_item
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Log.d("PostAdapter", "onBindViewHolder position:$position $holder")
        holder.bind(dataSource[position])
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

}