package com.starlight.module.data.datasource.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

class ProductColorResEntity(
    @ColumnInfo(name = ID_COL)
    @SerializedName(ID_COL)
    val id: String,

    @ColumnInfo(name = COLOR_ID_COL)
    @SerializedName(COLOR_ID_COL)
    val colorId: String,

    @ColumnInfo(name = URL_COL)
    @SerializedName(URL_COL)
    val url: String,

    @ColumnInfo(name = DESC_COL)
    @SerializedName(DESC_COL)
    val description: String,

    @ColumnInfo(name = RES_TYPE_COL)
    @SerializedName(RES_TYPE_COL)
    val resType: Int,

    @ColumnInfo(name = MINE_TYPE_COL)
    @SerializedName(MINE_TYPE_COL)
    val mineType: String
) {
    companion object {
        private const val ID_COL = "id"
        private const val COLOR_ID_COL = "colorId"
        private const val URL_COL = "url"
        private const val DESC_COL = "description"
        private const val RES_TYPE_COL = "resType"
        private const val MINE_TYPE_COL = "mineType"

    }
}