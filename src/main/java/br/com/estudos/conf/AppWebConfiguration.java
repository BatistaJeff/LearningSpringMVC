package br.com.estudos.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.estudos.controllers.HomeController;
import br.com.estudos.daos.ProdutoDao;
import br.com.estudos.infra.FileSaver;
import br.com.estudos.models.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses={ HomeController.class,
									ProdutoDao.class,
									FileSaver.class,
									CarrinhoCompras.class})
public class AppWebConfiguration {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		
		internalResourceViewResolver.setExposedContextBeanNames("carrinhoCompras");;
		
		return internalResourceViewResolver;
	}		

	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		messageSource.setBasenames("/WEB-INF/message");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		
		
		return messageSource;
	}
	@Bean
	public FormattingConversionService mvcConversionService(){
		
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }
	
    @Bean
    public RestTemplate restTemplate(){
    	return new RestTemplate();
    }
    
}
