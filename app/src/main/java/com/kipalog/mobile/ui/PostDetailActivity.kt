package com.kipalog.mobile.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kipalog.mobile.R
import com.kipalog.mobile.repository.PostRepository
import com.kipalog.mobile.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_postdetail.*
import ru.noties.markwon.Markwon
import javax.inject.Inject

class PostDetailActivity : BaseActivity() {

    @Inject
    lateinit var postRepository: PostRepository

    companion object {
        fun start(context: Context, content: String) {
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra("ID", content)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postdetail)
        val post = intent.getStringExtra("ID")
        postRepository.getPost(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    markdownView.loadMarkdown(it.content)
//            Markwon.setMarkdown(markdownView, it.content)
                }
//        markdownView.loadMarkdown()
    }
}