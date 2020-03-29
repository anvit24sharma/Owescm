package com.owescm.services.model.base

import com.owescm.remote.ErrorCodes


data class ErrorResponse(val message: String? = null, val codes: ErrorCodes? = null)