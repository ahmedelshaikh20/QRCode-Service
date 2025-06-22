package qrcodeapi.service
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.net.URLDecoder

@Service
class QrImageService {

  fun generateQrCode(
    contents: String,
    width: Int,
    height: Int,
    correctionLevel: String,
    format: String
  ): ByteArray {

    val errorCorrection = when (correctionLevel.uppercase()) {
      "M" -> ErrorCorrectionLevel.M
      "Q" -> ErrorCorrectionLevel.Q
      "H" -> ErrorCorrectionLevel.H
      else -> ErrorCorrectionLevel.L
    }

    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(
      contents,
      BarcodeFormat.QR_CODE,
      width,
      height,
      mapOf(EncodeHintType.ERROR_CORRECTION to errorCorrection,
        EncodeHintType.MARGIN to 4
      )
    )

    ByteArrayOutputStream().use { out ->
      MatrixToImageWriter.writeToStream(bitMatrix, format, out)
      return out.toByteArray()
    }
  }
}
