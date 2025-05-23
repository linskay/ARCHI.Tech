package com.coworking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    @Bean
    public OpenAPI coworkingSpaceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Coworking Space Management API")
                        .description("API для управления коворкинг-пространством")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Support Team")
                                .email("support@coworkingspace.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Локальный сервер разработки")
                ))
                .schemaRequirement("bearerAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("JWT токен авторизации"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

    @Bean
    public CommandLineRunner saveOpenApiSpec(OpenAPI openAPI) {
        return args -> {
            try {
                // Используем текущую рабочую директорию
                String projectRoot = System.getProperty("user.dir");
                
                // Создаем директорию для спецификации, если она не существует
                Path specDir = Paths.get(projectRoot, "spec");
                if (!Files.exists(specDir)) {
                    Files.createDirectories(specDir);
                }

                // Сохраняем спецификацию в формате YAML
                Path yamlPath = specDir.resolve("openapi.yaml");
                String yamlContent = yamlMapper.writeValueAsString(openAPI);
                Files.writeString(yamlPath, yamlContent);

                // Сохраняем спецификацию в формате JSON
                Path jsonPath = specDir.resolve("openapi.json");
                String jsonContent = jsonMapper.writeValueAsString(openAPI);
                Files.writeString(jsonPath, jsonContent);

                System.out.println("OpenAPI specification saved to: " + specDir.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to save OpenAPI specification: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
} 