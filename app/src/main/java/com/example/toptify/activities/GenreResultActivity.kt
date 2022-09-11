package com.example.toptify.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.toptify.artists.Artists
import com.example.toptify.databinding.GenreResultLayoutBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class GenreResultActivity : AppCompatActivity() {
    lateinit var binding: GenreResultLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GenreResultLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras
        val title: String? = bundle!!.getString("title")
        val list = bundle.getSerializable("list") as Artists

        val newList = mutableListOf<String>()
        for (i in list.items.size - 1 downTo 0 step 1) {
            newList.addAll(list.items[i].genres)
        }

        val map = newList.groupingBy { it }.eachCount().filter { it.value > 1 }

        val pieChart = binding.pieChartView

        initPieChart(pieChart)
        showPieChart(map, pieChart)

    }

    private fun showPieChart(map: Map<String, Int>, pieChart: PieChart) {
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = ""

        //initializing colors for the entries
        val colors: ArrayList<Int> = ArrayList()

        colors.add(Color.parseColor("#FF0000"))
        colors.add(Color.parseColor("#00FF00"))
        colors.add(Color.parseColor("#0000FF"))
        colors.add(Color.parseColor("#C0C0C0"))
        colors.add(Color.parseColor("#800000"))
        colors.add(Color.parseColor("#DC143C"))
        colors.add(Color.parseColor("#FF7F50"))
        colors.add(Color.parseColor("#FF8C00"))
        colors.add(Color.parseColor("#FFA500"))

        colors.add(Color.parseColor("#FFD700"))
        colors.add(Color.parseColor("#B8860B"))
        colors.add(Color.parseColor("#DAA520"))
        colors.add(Color.parseColor("#BDB76B"))
        colors.add(Color.parseColor("#F0E68C"))
        colors.add(Color.parseColor("#9ACD32"))
        colors.add(Color.parseColor("#ADFF2F"))

        colors.add(Color.parseColor("#8FBC8F"))
        colors.add(Color.parseColor("#2E8B57"))
        colors.add(Color.parseColor("#2F4F4F"))
        colors.add(Color.parseColor("#008B8B"))
        colors.add(Color.parseColor("#00CED1"))
        colors.add(Color.parseColor("#5F9EA0"))
        colors.add(Color.parseColor("#6495ED"))
        colors.add(Color.parseColor("#00BFFF"))
        colors.add(Color.parseColor("#1E90FF"))

        colors.add(Color.parseColor("#8A2BE2"))
        colors.add(Color.parseColor("#4B0082"))
        colors.add(Color.parseColor("#8B008B"))
        colors.add(Color.parseColor("#BA55D3"))
        colors.add(Color.parseColor("#D8BFD8"))
        colors.add(Color.parseColor("#EE82EE"))
        colors.add(Color.parseColor("#FF1493"))
        colors.add(Color.parseColor("#FAEBD7"))
        colors.add(Color.parseColor("#BC8F8F"))
        colors.add(Color.parseColor("#CD853F"))



        //input data and fit data into pie chart entry
        for (type in map.keys) {
            pieEntries.add(PieEntry(map[type]!!.toFloat(), type))
        }

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, label)

        //setting text size of the value
        pieDataSet.valueTextSize = 12f

        pieChart.setEntryLabelColor(Color.WHITE)

        //pieDataSet.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
       // pieDataSet.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE






        //providing color list for coloring different entries
        pieDataSet.colors = colors

        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)

        //to change the value to percentage
        pieData.setValueFormatter(PercentFormatter())

        val legend = pieChart.legend

        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.isEnabled=true
        legend.form = Legend.LegendForm.CIRCLE
        legend.isWordWrapEnabled = true
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true)

        pieChart.setData(pieData)
        pieChart.invalidate()
    }
    private fun initPieChart(pieChart: PieChart) {
        //using percentage as values instead of amount
        pieChart.setUsePercentValues(true)

        //remove the description label on the lower left corner, default true if not set
        pieChart.getDescription().setEnabled(false)

        //enabling the user to rotate the chart, default true
        pieChart.setRotationEnabled(true)
        //adding friction when rotating the pie chart
        pieChart.setDragDecelerationFrictionCoef(0.9f)
        //setting the first entry start from right hand side, default starting from top
        pieChart.setRotationAngle(0F)

        //highlight the entry when it is tapped, default true if not set
        pieChart.setHighlightPerTapEnabled(true)

        //adding animation so the entries pop up from 0 degree
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad)

        //setting the color of the hole in the middle, default white
        pieChart.setHoleColor(Color.parseColor("#FFFFFF"))

        pieChart.setHoleRadius(25f)
        pieChart.setTransparentCircleRadius(35f)
    }
}


//       Log.i("LIST", newList.toString())
//        Log.i("LIST size", newList.size.toString())
//        Log.i("Each:", (newList.groupingBy { it }.eachCount().filter { it.value > 1 }).toString())
//