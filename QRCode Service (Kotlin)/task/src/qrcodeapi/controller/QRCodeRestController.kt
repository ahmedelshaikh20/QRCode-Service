package qrcodeapi.controller


import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import qrcodeapi.service.QrImageService



@RestController
class QRCodeRestController(
  private val qrImageService: QrImageService
) {
  @GetMapping("/")
  fun hello() = ResponseEntity("Hello World", HttpStatus.OK)

  @GetMapping("/api/health")
  fun getHealthApi() = ResponseEntity("Ok", HttpStatus.OK)

  @GetMapping("/api/qrcode")
  fun getQrCode(
    @RequestParam("contents") content: String,
    @RequestParam(defaultValue = "250") size: Int,
    @RequestParam(defaultValue = "L") correction: String,
    @RequestParam(defaultValue = "png") type: String
  ): ResponseEntity<ByteArray> {
    println("Contents used: '$content'")


    if(content.isEmpty() || content.isBlank()){
      val errorJson = """{ "error": "Contents cannot be null or blank" }"""
      return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(errorJson.toByteArray())

    }
    if (size < 150 || size > 350) {
      val errorJson = """{ "error": "Image size must be between 150 and 350 pixels" }"""
      return ResponseEntity
        .badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(errorJson.toByteArray())
    }
    val allowedCorrectionValues = setOf("L", "M" ,"Q" ,"H")
    val correctionValue = correction.uppercase()
    if(correctionValue !in allowedCorrectionValues){
      val errorJson = """{ "error": "Permitted error correction levels are L, M, Q, H" }"""
      return ResponseEntity
        .badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(errorJson.toByteArray())
    }

    val allowedTypes = setOf("png", "jpeg", "gif")
    val format = type.lowercase()

    if (format !in allowedTypes) {
      val errorJson = """{ "error": "Only png, jpeg and gif image types are supported" }"""
      return ResponseEntity
        .badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(errorJson.toByteArray())
    }

    val mediaType  = when(type){
      "jpeg" -> MediaType.IMAGE_JPEG
      "gif" -> MediaType.IMAGE_GIF
      "png" -> MediaType.IMAGE_PNG
      else -> MediaType.IMAGE_PNG
    }


    val pngBytes = qrImageService.generateQrCode(content, size, size, correction , type)
    return ResponseEntity.ok()
      .contentType(mediaType)
      .body(pngBytes)
  }

}
