package com.rheydne.todosimple.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

    // Configurando o CORS da aplicação, necessario para a comunicação do backend com o frontend

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**"); // todas as requisições estao liberadas
    }
}
