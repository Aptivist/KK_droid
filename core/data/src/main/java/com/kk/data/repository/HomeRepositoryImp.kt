package com.kk.data.repository

import com.kk.data.utils.constants.CONNECTED
import com.kk.data.utils.exception.NotActiveException
import com.kk.domain.models.BaseResult
import com.kk.domain.models.UserType
import com.kk.network.model.NetworkUserTypeResource
import com.kk.network.service.ISocketService

class HomeRepositoryImp(private val socketService: ISocketService) : HomeRepository {
    override suspend fun connectSession(userType: UserType): BaseResult<String> {
        return try {
            val isConnected = socketService.connectSocket(userType.asNetworkUserType())
            if (isConnected) BaseResult.Success(CONNECTED) else BaseResult.Error(NotActiveException())
        } catch (e: Exception) {
            BaseResult.Error(e)
        }
    }

}

fun UserType.asNetworkUserType(): NetworkUserTypeResource {
    return when (this) {
        UserType.HostType -> NetworkUserTypeResource.HostType
        UserType.PlayerType -> NetworkUserTypeResource.PlayerType
    }
}