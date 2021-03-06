package com.app.weather.presentation.splash

import androidx.navigation.fragment.findNavController
import com.app.weather.R
import com.app.weather.databinding.FragmentSplashBinding
import com.app.weather.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private lateinit var animationHandler: AnimationHandler
    override fun init() {
        super.init()
        initAnimationHandler()
    }

    private fun initAnimationHandler() {
        animationHandler = AnimationHandler(binding)
        startSplashAnimation()
    }

    private fun startSplashAnimation() {
        animationHandler.startSplashAnimation { endSplashAnimation() }
    }

    private fun endSplashAnimation() {
        animationHandler.endSplashAnimation(onTerminateAction = {
            findNavController().graph.setStartDestination(R.id.homeFragment)
            navigate(R.id.action_splashFragment_to_homeFragment)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        animationHandler.destroy()
    }
}
