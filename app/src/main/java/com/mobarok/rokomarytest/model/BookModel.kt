package com.mobarok.rokomarytest.model
data class BookModel (
    val id : Int?,
    val position : Int?,
    val name_bn : String?,
    val name_en : String?,
    val summary : String?,
    val author_name_bn : String?,
    val publisher_name_bn : String?,
    val price : Double?,
    val is_available : Boolean ?,
    val is_unavailable : Boolean ?,
    val is_newarrival : Boolean ?,
    val lang : String?,
    val image_path : String?,
)