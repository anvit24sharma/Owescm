package com.owescm.remote

import com.owescm.services.model.base.ErrorResponse

object ErrorHandler {
    fun handleError(error: Throwable): ErrorResponse {
        return if (error is GenericNetworkException) {
            ErrorResponse(error.localizedMessage, error.errorCode())
        } else {
            ErrorResponse("${error.message}", ErrorCodes.UNKNOWN_ERROR)
        }
    }
}

