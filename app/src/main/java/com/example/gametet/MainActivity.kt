package com.example.gametet

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gametet.R.drawable
import com.example.gametet.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: GiftAdapter by lazy { GiftAdapter(::onClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            rvGift.layoutManager = GridLayoutManager(root.context, 2, GridLayoutManager.VERTICAL, false)
            rvGift.adapter = adapter
            adapter.submitList(getListGift().shuffled())
        }

        binding.ivReset.setOnClickListener {
            adapter.submitList(getListGift().shuffled())
        }
    }

    private fun onClick(value: Int, view: View){
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

    private fun getListGift() = listOf(
            Gift(value = drawable.muoi_nghin),
            Gift(value = drawable.hai_muoi_nghin),
            Gift(value = drawable.nam_muoi_nghin),
            Gift(value = drawable.mot_tram_nghin),
            Gift(value = drawable.hai_tram_nghin),
            Gift(value = drawable.nam_tram_nghin),
        )

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}