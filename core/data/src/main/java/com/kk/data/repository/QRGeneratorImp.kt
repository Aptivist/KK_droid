package com.kk.data.repository

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.kk.domain.models.QRGenerator

class QRGeneratorImp : QRGenerator {
    override fun encodeString(stringQR: String): Bitmap {
        val barcodeEncoder = BarcodeEncoder()
        return barcodeEncoder.encodeBitmap( stringQR, BarcodeFormat.QR_CODE, 450, 450)
    }
}