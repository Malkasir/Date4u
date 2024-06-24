package com.tutego.date4u;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLCodeRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class Date4uApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run( Date4uApplication.class,
				args );
//		Arrays.stream( ctx.getBeanDefinitionNames() )
//				.sorted()
//				.forEach( System.out::println );


	}

//	@Bean
//	RuntimeWiringConfigurer runtimeWiringConfigurer() {
//		return builder -> {
//
//			Map<String,String> events = Map.of("1", "Heart-to-Heart Hangout",
//					"2", "Temptation Tango" );
//
//			Map<String, DataFetcher<?>> dataFetchers = Map.of(
//					"datingEvent", // type Query { datingEvent(id: ID!): String
//					env -> events.get( env.getArgument( "id" ) )
//			);
//
//			builder.codeRegistry(
//					GraphQLCodeRegistry.newCodeRegistry()
//							.dataFetchers( "Query", dataFetchers )
//							.build()
//			).build();
//		};
//	}

	private final Logger log = LoggerFactory.getLogger( getClass() );




	public Date4uApplication() {
		log.trace( "Trace Level Log" );
		log.debug( "Debug Level Log" );
		log.info( "Info Level Log" );
		log.error( "Log mit Argumenten {}, {} und {}", 1, "2", 3.0 );
		log.debug( "Debug heeeeeeeeeeeeeeeeeeee" );
		log.trace( "Trace hhhhhhhhhhhhhhhhhhhh" );
	}


}
