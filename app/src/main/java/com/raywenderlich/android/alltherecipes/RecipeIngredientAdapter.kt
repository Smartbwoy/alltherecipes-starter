package com.raywenderlich.android.alltherecipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Toast
import com.raywenderlich.android.alltherecipes.R.id.ingrlist
import kotlinx.android.synthetic.main.activity_ingredients__methods.*
import kotlinx.android.synthetic.main.list_ingredient_item.view.*

class RecipeIngredientAdapter(private val context: Context, private val dataSource: ArrayList<Recipe_Ingredient>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View?{
        val rowView = inflater.inflate(R.layout.list_ingredient_item, parent, false)
        val recipe_ingr = getItem(position) as Recipe_Ingredient

        rowView.amount.text = recipe_ingr.amount.toString()
        rowView.measurement_type.text = recipe_ingr.measureUnit
        rowView.item_name.text = recipe_ingr.label

        /*rowView.remove_item.setOnClickListener {
            Toast.makeText(this.context, "Click on " + dataSource[position].label, Toast.LENGTH_SHORT).show()
            dataSource.remove(dataSource.get(position))
            val listView = ingrlist as ListView
            listView.adapter = RecipeIngredientAdapter(this.context, dataSource)
            //ingrlist.set=RecipeIngredientAdapter(this.context, dataSource)
        }*/
        return rowView
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }


}