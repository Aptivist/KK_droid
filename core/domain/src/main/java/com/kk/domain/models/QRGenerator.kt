package com.kk.domain.models

import android.graphics.Bitmap

interface QRGenerator{
    fun encodeString(stringQR : String) : Bitmap
}