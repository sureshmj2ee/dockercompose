package com.sm.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRoute(RouteLocatorBuilder builder) {
		/*
		 * Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
		 * .uri("http://httpbin.org:80");
		 * 
		 *from browser try http://localhost:8765/get
		 */
		
		/*
		 * Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
		 * .filters(f -> f.addRequestHeader("MyHeader", "MyURI"))
		 * .uri("http://httpbin.org:80");
		 */
		
		
		//click on route function and refactor and inline
		/*
		 * Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
		 * .filters(f -> f.addRequestHeader("MyHeader", "MyURI")
		 * .addRequestParameter("MyParam", "MyValue")) .uri("http://httpbin.org:80");
		 * 
		 * return builder.routes() .route(routeFunction ) .build();
		 */
		
		/*
		 * return builder.routes() .route(p -> p.path("/get") .filters(f ->
		 * f.addRequestHeader("MyHeader", "MyURI") .addRequestParameter("MyParam",
		 * "MyValue")) .uri("http://httpbin.org:80") ) .build();
		 */
		/*
		http://localhost:8765/CURRENCY-CONVERSION/currency-conversion/from/USD/to/INR/quantity/10
			instead of this url. we will use
			http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
			
			http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR
			insteas of this url. we will use
			http://localhost:8765/currency-exchange/from/USD/to/INR
			
			application.properties
			disable two properties
			spring.cloud.gateway.discovery.locator.enabled=true
			spring.cloud.gateway.discovery.locator.lower-case-service-id=true
			
			*/
		
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("MyParam", "MyValue"))
						.uri("http://httpbin.org:80") )
				.route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				
				.route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				
				.route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
				
				//rewrite url new to feign. 
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", 
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion"))
				
				.build();
	}

}
