package com.adotcode.scaffold.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http请求转到https配置
 *
 * @author risfeng
 * @date 2019/09/21
 */
@Configuration
public class ConnectorConfig {

  @Bean
  TomcatServletWebServerFactory tomcatServletWebServerFactory() {
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
      @Override
      protected void postProcessContext(Context context) {
        SecurityConstraint constraint = new SecurityConstraint();
        constraint.setUserConstraint("CONFIDENTIAL");
        SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        constraint.addCollection(collection);
        context.addConstraint(constraint);
      }
    };
    factory.addAdditionalTomcatConnectors(getHttpConnector());
    return factory;
  }

  private Connector getHttpConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setScheme("http");
    connector.setPort(8080);
    connector.setSecure(false);
    connector.setRedirectPort(8443);
    return connector;
  }
}
