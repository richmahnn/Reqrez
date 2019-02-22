package com.lubulwa.reqrez.data.model

import com.google.gson.annotations.SerializedName

/**
 * Api Response.
 *
 */
data class UserModel(

    @SerializedName("total")
    var total: Int? = null,

    @SerializedName("total_pages")
    var totalPages: Int? = null,

    @SerializedName("per_page")
    var perPage: Int? = null,

    @SerializedName("page")
    var page: Int? = null,

    @SerializedName("data")
    var userData: List<UserData>? = null

) {

    data class UserData(
        @SerializedName("id")
        var section: String? = null,

        @SerializedName("first_name")
        var subsection: String? = null,

        @SerializedName("last_name")
        var title: String? = null,

        @SerializedName("avatar")
        var abstract: String? = null
    ) {
        override fun toString(): String {
            return "UserData(section=$section, subsection=$subsection, title=$title, abstract=$abstract)"
        }
    }

    override fun toString(): String {
        return "UserModel(total=$total, totalPages=$totalPages, perPage=$perPage, page=$page, userData=$userData)"
    }
}
