package fr.ec.app

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupInsets()
        val list = findViewById<RecyclerView>(R.id.list_item)
        val posts = getPosts()
        list.adapter = PostAdapter(dataSource = posts )
        list.layoutManager = LinearLayoutManager(this)
    }

    private fun getPosts() : List<Post> {
        val posts = mutableListOf<Post>()
        repeat(100_000) { item ->
            posts.add(Post(title = "Title $item", subTitle = "Subtitle $item"))
        }
        return posts
    }


    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}