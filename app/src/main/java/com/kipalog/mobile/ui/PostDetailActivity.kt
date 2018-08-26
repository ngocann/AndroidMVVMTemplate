package com.kipalog.mobile.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kipalog.mobile.R
import com.kipalog.mobile.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_postdetail.*
import ru.noties.markwon.Markwon

class PostDetailActivity : BaseActivity() {

    companion object {
        fun start(context: Context, content: String) {
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra("POST", content)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postdetail)
        val post = intent.getStringExtra("POST")
        Markwon.setMarkdown(markdownView, post)
//        markdownView.loadMarkdown()
    }
}