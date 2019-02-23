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
        var id: String? = null,

        @SerializedName("first_name")
        var firstName: String? = null,

        @SerializedName("last_name")
        var lastName: String? = null,

        @SerializedName("avatar")
        var imageUrl: String? = null
    ) {
        override fun toString(): String {
            return "UserData(id=$id, firstName=$firstName, lastName=$lastName, imageUrl=$imageUrl)"
        }
    }

    override fun toString(): String {
        return "UserModel(total=$total, totalPages=$totalPages, perPage=$perPage, page=$page, userData=$userData)"
    }
}
