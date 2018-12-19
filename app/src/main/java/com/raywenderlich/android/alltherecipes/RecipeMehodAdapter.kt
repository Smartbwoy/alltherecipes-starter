package com.raywenderlich.android.alltherecipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_ingredient_item.view.*
import kotlinx.android.synthetic.main.list_methods.view.*

class RecipeMehodAdapter(private val context: Context, private val dataSource: ArrayList<Recipe_Method>) : BaseAdapter()  {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_methods, parent, false)
        val recipe_method = getItem(position) as Recipe_Method
        rowView.step.text=(1+position).toString()

        rowView.method.text = recipe_method.method.toString()
        return rowView
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
   }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
     }
}