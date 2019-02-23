package com.lubulwa.reqrez.data

import com.lubulwa.reqrez.data.model.CreateUserModel
import com.lubulwa.reqrez.data.model.CreateUserResponse
import com.lubulwa.reqrez.data.model.UserModel
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Api Service for users.
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
     * @return Response of list of users.
     */
    @GET("api/users?per_page=7") fun fetchUsers(
        @Query("page") pageNumber:Int
    ): Observable<UserModel>


    /**
     * Create new user.
     *
     * URL: https://reqres.in/
     * Path: api/users
     *
     * @param userModelObject user object. Contains name and job.
     * @return Response of user details
     */
    @POST("api/users") fun createUser(
        @Body userModelObject: CreateUserModel
    ): Observable<CreateUserResponse>

}
