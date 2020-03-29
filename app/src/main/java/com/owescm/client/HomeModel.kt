package com.owescm.client


data class HomeModel(
    var menuTitle:String,
    var totalItems:Int,
    var itemList:ArrayList<ItemDetails>


){

}

data class ItemDetails(
    var itemName:String,
    var count:Int

)