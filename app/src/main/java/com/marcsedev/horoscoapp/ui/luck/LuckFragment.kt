package com.marcsedev.horoscoapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.marcsedev.horoscoapp.R
import com.marcsedev.horoscoapp.databinding.FragmentLuckBinding
import com.marcsedev.horoscoapp.ui.core.listeners.OnSwipeTouchListener
import com.marcsedev.horoscoapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    private var isFragmentDestroyed = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val currentLuck = randomCardProvider.getLucky()
        currentLuck?.let { luck ->
            val currentPrediction = getString(luck.text)
            binding.tvLucky.text = currentPrediction
            binding.ivLuckyCard.setImageResource(luck.image)
            binding.tvShare.setOnClickListener { shareResult(currentPrediction) }
        }
    }

    private fun shareResult(prediction: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        binding.ivRoulette.setOnClickListener { performAction() }

        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeRight() {
                performAction()
            }

            override fun onSwipeLeft() {
                performAction()
            }

            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    performAction()
                }
                return super.onTouch(view, motionEvent)
            }
        })
    }

    private fun performAction() {
        if (!isFragmentDestroyed) {
            spinRoulette()
        }
    }

    private fun spinRoulette() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator =
            ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        if (!isFragmentDestroyed) {
            val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

            slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    binding.reverse.isVisible = true
                }

                override fun onAnimationEnd(p0: Animation?) {
                    growCard()
                }

                override fun onAnimationRepeat(p0: Animation?) {}

            })

            binding.reverse.startAnimation(slideUpAnimation)
        }
    }

    private fun growCard() {
        if (!isFragmentDestroyed) {
            val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

            growAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}

                override fun onAnimationEnd(p0: Animation?) {
                    binding.reverse.isVisible = false
                    showPremonitionView()
                }

                override fun onAnimationRepeat(p0: Animation?) {}

            })

            binding.reverse.startAnimation(growAnimation)
        }
    }

    private fun showPremonitionView() {
        if (!isFragmentDestroyed) {
            val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
            disappearAnimation.duration = 200

            val appearAnimation = AlphaAnimation(0.0f, 1.0f)
            appearAnimation.duration = 1000

            disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}

                override fun onAnimationEnd(p0: Animation?) {
                    binding.preview.isVisible = false
                    binding.prediction.isVisible = true
                }

                override fun onAnimationRepeat(p0: Animation?) {}

            })

            binding.preview.startAnimation(disappearAnimation)
            binding.prediction.startAnimation(appearAnimation)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        stopAnimations()
    }

    override fun onDestroy() {
        super.onDestroy()
        isFragmentDestroyed = true
        _binding = null
    }

    private fun stopAnimations() {
        binding.ivRoulette.clearAnimation()
        binding.reverse.clearAnimation()
    }

}
