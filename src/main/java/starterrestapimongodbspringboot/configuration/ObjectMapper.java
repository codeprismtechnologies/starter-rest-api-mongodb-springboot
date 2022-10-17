package starterrestapimongodbspringboot.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code prism Technologies Pvt Ltd
 **/

@Configuration
public class ObjectMapper {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
