package com.paulacr.dreamdiary.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
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

    private var monthSelector: TextView? = null
    private var leftArrow: ImageView? = null
    private var rightArrow: ImageView? = null
    val currentYear = LocalDateTime.now().year
    val currentMonth = LocalDateTime.now().month

    init {
        initView()
    }

    private fun initView() {
        val view = inflate(context, R.layout.item_view_month_selector, this)
        leftArrow = view.findViewById(R.id.leftMonthSelector)
        rightArrow = view.findViewById(R.id.rightMonthSelector)

        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.MonthSelector)
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
            MonthSelectorFragment.newInstance(it.buildMonthWithYear())
        }.apply {
            pagerAdapter.addFragmentsAtOnce(this)
        }
    }

    private fun LocalDateTime.buildMonthWithYear(): String {
        return this.month.name
            .take(3)
            .lowercase()
            .replaceFirstChar {
                it.uppercase()
            }
            .plus(" / ")
            .plus(this.year)
    }

    fun enableLeftArrow(enable: Boolean) {
        if (enable) {
            leftArrow?.alpha = 1f
        } else {
            leftArrow?.alpha = .1f
        }
    }

    fun enableRightArrow(enable: Boolean) {
        if (enable) {
            rightArrow?.alpha = 1f
        } else {
            rightArrow?.alpha = .1f
        }
    }

    fun navigate(direction: MonthDirection, monthsToDisplay: List<LocalDateTime>) {
//        checkNavigation()

    }

    private fun checkNavigation(monthsToDisplay: List<LocalDateTime>) {
        monthsToDisplay.firstOrNull()
        monthsToDisplay
    }

    enum class MonthDirection {
        UP,
        DOWN
    }
}