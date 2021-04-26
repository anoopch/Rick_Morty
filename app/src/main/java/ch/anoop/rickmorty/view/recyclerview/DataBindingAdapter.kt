package ch.anoop.rickmorty.view.recyclerview

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DataBindingAdapter {
    @BindingAdapter("itemImageSrc")
    @JvmStatic
    fun ImageView.itemImageSrc(url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(context).load(url).into(this)
        }
    }
}
