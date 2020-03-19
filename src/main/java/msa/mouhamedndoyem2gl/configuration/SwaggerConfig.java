package msa.mouhamedndoyem2gl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * classe pour gérer la documentation de l'api
 *
 * @author Mouhamed NDOYE M2GL
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // configurer et personnaliser la documentation swagger avec Docket
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("msa.mouhamedndoyem2gl.web"))
                .paths(PathSelectors.regex("/produits.*"))
                .build();
    }
}
