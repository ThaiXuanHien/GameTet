package com.example.gametet

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gametet.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list: ArrayList<Gift>? = null
    private var mediaPlayer : MediaPlayer? = null
    private lateinit var adapter: GiftAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getListGift()

        mediaPlayer = MediaPlayer.create(this, R.raw.nhac_nen);
        mediaPlayer?.start()

        binding.run {
            rvGift.layoutManager =
                GridLayoutManager(root.context, 2, GridLayoutManager.VERTICAL, false)
            adapter = GiftAdapter(list?.shuffled() as ArrayList<Gift>, ::onClick)
            rvGift.adapter = adapter
        }

        binding.ivReset.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.prepare()
            mediaPlayer?.start()
            adapter.updateList(list?.shuffled() as ArrayList<Gift>)
        }
    }

    private fun onClick(value: Int, view: View) {
        val oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f)
        val oa2 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
        oa1.duration = 300
        oa2.duration = 300
        oa1.interpolator = LinearInterpolator()
        oa2.interpolator = LinearInterpolator()
        oa1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                (view as ImageView).setImageResource(value)
                oa2.start()
            }
        })
        oa1.start()
    }

    private fun getListGift() {
        list?.clear()
        list = arrayListOf(
            Gift(value = R.drawable.muoi_nghin),
            Gift(value = R.drawable.hai_muoi_nghin),
            Gift(value = R.drawable.nam_muoi_nghin),
            Gift(value = R.drawable.mot_tram_nghin),
            Gift(value = R.drawable.hai_tram_nghin),
            Gift(value = R.drawable.nam_tram_nghin),
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}