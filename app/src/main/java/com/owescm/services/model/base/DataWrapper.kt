package com.owescm.services.model.base

data class DataWrapper<T>(var responseData: T?, var errorResponse: ErrorResponse?)