package com.kk.data.utils.exception

import com.kk.data.utils.constants.ERROR_CONNECTION
import com.kk.data.utils.constants.ERROR_RESPONSE


class NotActiveException() : Exception(ERROR_CONNECTION)
class NoResponseException() : Exception(ERROR_RESPONSE)