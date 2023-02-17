package com.kk.data.repository

import com.google.gson.Gson
import com.kk.network.service.ISocketService

class UserAnswerRepositoryImp(private val socketServie: ISocketService, private val gson: Gson) :
    UserAnswerRepository {
}