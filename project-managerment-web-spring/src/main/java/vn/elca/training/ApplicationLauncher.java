package vn.elca.training;

import java.util.Locale;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import vn.elca.training.error.ErrorAppController;
import vn.elca.training.service.IProjectService;
import vn.elca.training.web.ApplicationController;
import vn.elca.tranning.validator.ProjectValidator;
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan(basePackageClasses = { ApplicationLauncher.class, ApplicationController.class, IProjectService.class,ProjectValidator.class,ErrorAppController.class,})

@PropertySource({ "classpath:/application.properties", "classpath:/message.properties" })
public class ApplicationLauncher extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationLauncher.class);
        Properties pro = new Properties();
        pro.setProperty("spring.resources.static-locations", " classpath:/css/, classpath:/js/, classpath:/image/");
        app.run(args);
    }

    private static final String[] RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/","classpath:/templates/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(RESOURCE_LOCATIONS);
        }
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static MessageSource messageSource() {
        ResourceBundleMessageSource ResourceBundleMessageSource = new ResourceBundleMessageSource();
        ResourceBundleMessageSource.setBasenames("message","validation");
        return ResourceBundleMessageSource;
    }
    
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    //////////////// Implemetation for multilingual//////////////////
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }
    //////////////// END - Implemetation for multilingual//////////////////
}
