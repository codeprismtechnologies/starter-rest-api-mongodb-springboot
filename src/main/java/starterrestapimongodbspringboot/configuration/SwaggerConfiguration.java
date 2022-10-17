package starterrestapimongodbspringboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code prism Technologies Pvt Ltd
 **/

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("CodePrism Technology Pvt. Ltd.").apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("User MicroService API Documentation")
                .description("This is the user microservice. Here we will provided all user rest CRUD APIs(CREATE/READ/UPDATE/DELETE)")
                .contact(new Contact("Chandan Rai", "https://codeprism.in", "chandan.rai@codeprism.in"))
                .termsOfServiceUrl("https://codeprism.in/privacy_policy.html")
                .license("CodePrism Technology Pvt. Ltd.")
                .licenseUrl("https://codeprism.in/index.html")
                .version("1.0")
                .build();
    }
}
