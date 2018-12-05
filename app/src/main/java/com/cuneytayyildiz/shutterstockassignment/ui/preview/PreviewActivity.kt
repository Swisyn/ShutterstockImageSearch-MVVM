package com.cuneytayyildiz.shutterstockassignment.ui.preview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.cuneytayyildiz.shutterstockassignment.R
import com.cuneytayyildiz.shutterstockassignment.utils.extensions.load


class PreviewActivity : AppCompatActivity() {

    private lateinit var imagePreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imagePreview = findViewById(R.id.image_preview)

        if (intent.hasExtra(KEY_PHOTO_URL)) {
            imagePreview.load(intent.getStringExtra(KEY_PHOTO_URL))
        } else {
            onBackPressed()
        }
    }


    companion object {
        private const val KEY_PHOTO_URL: String = "photo_url"

        fun createIntent(context: Context, photoUrl: String?): Intent {
            return Intent(context, PreviewActivity::class.java).apply {
                putExtra(KEY_PHOTO_URL, photoUrl)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }
}
