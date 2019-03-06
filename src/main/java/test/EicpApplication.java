package test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = EicpApplication.class)
@PropertySource("application.properties")
@EnableAspectJAutoProxy
public class EicpApplication {
}

