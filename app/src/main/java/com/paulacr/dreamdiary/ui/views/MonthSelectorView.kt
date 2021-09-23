package com.paulacr.dreamdiary.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.paulacr.dreamdiary.R
import kotlinx.android.synthetic.main.item_view_month_selector.view.*
import java.time.LocalDateTime

class MonthSelectorView @JvmOverloads constructor(
    context: Context,
    val attributeSet: AttributeSet? = null,
    val defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private var leftArrow: ImageView? = null
    private var rightArrow: ImageView? = null
    private val monthSelectorManager = MonthSelectorManager()

    init {
        initView()
    }

    private fun initView() {
        val view = inflate(context, R.layout.item_view_month_selector, this)
        leftArrow = view.findViewById(R.id.leftMonthSelector)
        rightArrow = view.findViewById(R.id.rightMonthSelector)

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
        monthPager.adapter = pagerAdapter
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