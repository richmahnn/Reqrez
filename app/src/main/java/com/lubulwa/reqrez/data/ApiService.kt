package com.lubulwa.reqrez.data

import com.lubulwa.reqrez.data.model.UserModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Api Service for getting users.
 *
 */
interface ApiService {

    /**
     * Get user results.
     *
     * URL: https://reqres.in/
     * Path: api/users
     * Query: per_page
     * Query: page
     *
     * @param limit Limit getNuberOfUsersPerPage.
     * @param page Page number.
     * @return Response of trending getResults.
     */
    @GET("api/users") fun fetchUsers(
        @Query("per_page") limit:Int = 7,
        @Query("page") page: Int
    ): Observable<UserModel>

    @POST("api/users") fun createUser(
        @Body userObject: UserModel.UserData
    ): Observable<Response<UserModel.UserData>>

}
