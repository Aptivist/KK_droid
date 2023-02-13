package com.kk.presentation.utils

import com.kk.domain.models.BaseResult
import com.kk.presentation.baseMVI.UiState


fun <T> BaseResult<Any>.validate(){
    when(this){
        is BaseResult.Error -> {}
        is BaseResult.Success -> {}
    }
}

