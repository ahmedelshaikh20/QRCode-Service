package qrcodeapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.BufferedImageHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import java.awt.image.BufferedImage

@Configuration
class Configuration {

  // This func to help us to serialize the BufferedImage
  @Bean
  fun bufferedImageHttpMessageConverter(): HttpMessageConverter<BufferedImage> {
    return BufferedImageHttpMessageConverter();
  }

}
