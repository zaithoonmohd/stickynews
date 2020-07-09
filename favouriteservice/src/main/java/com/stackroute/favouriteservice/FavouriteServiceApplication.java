package com.stackroute.favouriteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.stackroute.favouriteservice.filter.MyFilter;

//import com.stackroute.favouriteservice.filter.MyFilter;

/**
 * @author Zaithoon M
 *
 */
@SpringBootApplication
public class FavouriteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteServiceApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean myfilter()
	{
		
UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration config=new CorsConfiguration();
		
		config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
		config.addAllowedMethod("*");
	   source.registerCorsConfiguration("/**", config);	
		FilterRegistrationBean fbean=new FilterRegistrationBean(new CorsFilter(source));
		
		
		fbean.setFilter(new MyFilter());
		fbean.addUrlPatterns("/api/v1/news/*");
		//fbean.addUrlPatterns("/UserService/api/userservice/*");
		//for checking filter uncomment
		
		//for specific filtering use below line
		//fbean.addUrlPatterns("/ProductService/api/ustg/save","/CustomerService/api/mongo/allcustomers");
		
		return fbean;
		
}
}

