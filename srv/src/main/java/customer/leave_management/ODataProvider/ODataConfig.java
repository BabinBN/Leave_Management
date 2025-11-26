package customer.leave_management.ODataProvider;

import jakarta.servlet.Servlet;   // correct servlet API for Spring Boot 3
import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ODataConfig {

    @Bean
    public ServletRegistrationBean<ODataServlet> odataV2Servlet() {
        // Specify the exact generic type: ODataServlet, not Servlet
        ServletRegistrationBean<ODataServlet> bean = 
            new ServletRegistrationBean<>(new ODataServlet(), "/odata/v2/*");
        
        bean.setName("ODataV2Servlet");
        return bean;
    }
}