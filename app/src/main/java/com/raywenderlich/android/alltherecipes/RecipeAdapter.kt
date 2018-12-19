package com.raywenderlich.android.alltherecipes

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RecipeAdapter(private val context: Context,private val dataSource: ArrayList<Recipe>) : BaseAdapter(){

        private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    companion object {
        private val LABEL_COLORS = hashMapOf(
                "Low-Carb" to R.color.colorLowCarb,
                "Low-Fat" to R.color.colorLowFat,
                "Low-Sodium" to R.color.colorLowSodium,
                "Medium-Carb" to R.color.colorMediumCarb,
                "Vegetarian" to R.color.colorVegetarian,
                "Balanced" to R.color.colorBalanced
        )
    }
    //1
        override fun getCount(): Int {
        return dataSource.size
        }

        //2
        override fun getItem(position: Int): Any {
            return dataSource[position]
        }

        //3
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_recipe, parent, false)
        // Get title element
        val titleTextView = rowView.findViewById(R.id.recipe_list_title) as TextView

// Get subtitle element
        val subtitleTextView = rowView.findViewById(R.id.recipe_list_subtitle) as TextView

// Get detail element
        val detailTextView = rowView.findViewById(R.id.recipe_list_detail) as TextView

// Get thumbnail element
        val thumbnailImageView = rowView.findViewById(R.id.recipe_list_thumbnail) as ImageView

        val recipe = getItem(position) as Recipe

// 2
        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label

// 3
        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        val titleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
        titleTextView.typeface = titleTypeFace

        val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_semibolditalic)
        subtitleTextView.typeface = subtitleTypeFace

        val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
        detailTextView.typeface = detailTypeFace

        detailTextView.setTextColor(
                ContextCompat.getColor(context, LABEL_COLORS[recipe.label] ?: R.color.colorPrimary))


        return rowView
    }


}