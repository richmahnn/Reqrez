package com.lubulwa.reqrez.data.model

data class CreateUserModel(
    var name: String,
    var job: String
)

data class CreateUserResponse(
    var name: String,
    var job: String,
    var id: String,
    var createdAt: String
)