package com.paulacr.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.paulacr.presentation.R
import com.paulacr.presentation.databinding.ItemViewMonthSelectorBinding
import java.time.LocalDateTime

class MonthSelectorView @JvmOverloads constructor(
    context: Context,
    val attributeSet: AttributeSet? = null,
    val defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private var leftArrow: ImageView? = null
    private var rightArrow: ImageView? = null
    private val monthSelectorManager = MonthSelectorManager()
    private lateinit var binding: ItemViewMonthSelectorBinding

    init {
        initView()
    }

    private fun initView() {
        binding = ItemViewMonthSelectorBinding.inflate(LayoutInflater.from(context), this, true)
        leftArrow = binding.leftMonthSelector
        rightArrow = binding.rightMonthSelector

        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.MonthSelector)

        leftArrow?.setOnClickListener {
            monthSelectorManager.navigate(direction = MonthSelectorManager.MonthDirection.PREVIOUS)
        }

        rightArrow?.setOnClickListener {
            monthSelectorManager.navigate(direction = MonthSelectorManager.MonthDirection.NEXT)
        }
        attributes.recycle()
    }

    fun setupViewPager(
        manager: FragmentManager,
        lifecycleOwner: LifecycleOwner,
        savedDateTime: List<LocalDateTime>
    ) {

        val pagerAdapter = MonthSelectorPagerAdapter(
            manager, lifecycleOwner
        )
        binding.monthPager.adapter = pagerAdapter
        savedDateTime.map {
            MonthSelectorFragment.newInstance(monthSelectorManager.buildMonthWithYearText(it))
        }.apply {
            pagerAdapter.addFragmentsAtOnce(this)
        }
        monthSelectorManager.buildMonthSelector(localDateTimeList = savedDateTime) { shouldEnableLeftArrow, shouldEnableRightArrow ->
            enableLeftArrow(shouldEnableLeftArrow)
            enableRightArrow(shouldEnableRightArrow)
        }
    }

    private fun enableLeftArrow(enable: Boolean) {
        leftArrow?.isEnabled = enable
    }

    private fun enableRightArrow(enable: Boolean) {
        rightArrow?.isEnabled = enable
    }
}
