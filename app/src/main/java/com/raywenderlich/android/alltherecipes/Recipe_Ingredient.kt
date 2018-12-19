package com.raywenderlich.android.alltherecipes

class Recipe_Ingredient{
    var amount: String? = null
    var measureUnit: String? = null
    var label: String? = null

    constructor()
    constructor(amount: String, measure_unit: String, label: String) {
        this.amount = amount
        this.measureUnit = measure_unit
        this.label = label
    }


}
