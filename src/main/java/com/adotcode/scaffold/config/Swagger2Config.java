package com.adotcode.scaffold.config;

import static com.google.common.collect.Lists.newArrayList;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.filter.OncePerRequestFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2Api文档配置
 *
 * @author risfeng
 * @date 2019/09/05
 */
@Configuration
@EnableSwagger2
@Profile({"!prod"})
@ControllerAdvice(basePackages = {"com.adotcode.scaffold.facade.endpoint"})
@ConfigurationProperties(prefix = "swagger.api-info")
public class Swagger2Config {

  /**
   * api 所在包路径
   */
  @Value("${swagger.apis.base-package}")
  private String apisBasePackage;

  /**
   * UI Host Url header key name
   */
  @Value("${swagger.ui.header}")
  private String uiHeader;
  /**
   * UI 主机地址协议
   */
  @Value("${swagger.ui.host-url-protocol}")
  private String uiHostUrlProtocol;
  /**
   * UI 主机地址
   */
  @Value("${swagger.ui.host-url}")
  private String uiHostUrl;

  /**
   * 应用信息：标题
   */
  @Value("${swagger.api-info.title}")
  private String title;
  /**
   * 应用信息：描述
   */
  @Value("${swagger.api-info.description}")
  private String description;
  /**
   * 应用信息：版本
   */
  @Value("${swagger.api-info.version}")
  private String version;
  /**
   * 应用信息：服务条地址
   */
  @Value("${swagger.api-info.terms-of-service-url}")
  private String termsOfServiceUrl;
  /**
   * 应用信息：许可名称
   */
  @Value("${swagger.api-info.license}")
  private String license;
  /**
   * 应用信息：许可说明地址
   */
  @Value("${swagger.api-info.license-url}")
  private String licenseUrl;

  /**
   * 应用信息：联系信息:名称
   */
  @Value("${swagger.api-info.contact.name}")
  private String contactName;
  /**
   * 应用信息：联系信息:url地址
   */
  @Value("${swagger.api-info.contact.url}")
  private String contactUrl;
  /**
   * 应用信息：联系信息:名称
   */
  @Value("${swagger.api-info.contact.email}")
  private String contactEmail;

  /**
   * 应用信息：扩展字段 key:value
   */
  @Getter
  @Setter
  private List<ObjectVendorExtension<String>> vendorExtensions = new ArrayList<>();

  /**
   * 初始化文档信息
   *
   * @return 文档信息
   */
  private ApiInfo initApiInfo() {
    Contact contact = new Contact(contactName, contactUrl, contactEmail);
    return new ApiInfo(
        title,
        description,
        version,
        termsOfServiceUrl,
        contact,
        license,
        licenseUrl,
        newArrayList(vendorExtensions)
    );
  }

  @Bean
  public Docket api() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    return docket
        .apiInfo(initApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage(apisBasePackage))
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public OncePerRequestFilter swaggerCorsFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader(uiHeader) != null &&
            request.getHeader(uiHeader).contains(uiHostUrl)) {
          response.addHeader(
              "Access-Control-Allow-Origin",
              String.format("%s://%s", uiHostUrlProtocol, uiHostUrl)
          );
          response.addHeader(
              "Access-Control-Allow-Headers",
              "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range,Authorization"
          );
          response.addHeader(
              "Access-Control-Allow-Methods",
              "GET, POST, PUT, DELETE, PATCH, OPTIONS"
          );
        }
        filterChain.doFilter(request, response);
      }
    };
  }

  /**
   * swagger 扩展信息
   *
   * @param <T> value Type
   */
  @Data
  public static class ObjectVendorExtension<T> implements VendorExtension, Serializable {

    private static final long serialVersionUID = -4271490591823676138L;
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private T value;

    @Override
    public String getName() {
      return name;
    }

    @Override
    public Object getValue() {
      return value;
    }
  }
}
