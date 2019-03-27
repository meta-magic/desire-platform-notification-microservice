package com.desire3d.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.desire3d.notification.channel.NotificationChannel;
import com.desire3d.notification.model.VersionInfo;

@SpringBootApplication
@Configuration
@EnableBinding(NotificationChannel.class)
@EnableScheduling
public class NotificationServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("\n ConnectionURL :" + SystemEnviroment.getConnectionurl() + "\n User :"
				+ SystemEnviroment.getUser() + "\n Tokenkey :" + SystemEnviroment.getTokenKey() + "\n TokenValidity :"
				+ SystemEnviroment.getTokenValidity() + "\n Sessionexpiry :" + SystemEnviroment.getSessionexpiry());

		SpringApplication sa = new SpringApplication(NotificationServiceApplication.class);
		sa.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("\n ConnectionURL :" + SystemEnviroment.getConnectionurl() + "\n User :"
				+ SystemEnviroment.getUser() + "\n Tokenkey :" + SystemEnviroment.getTokenKey() + "\n TokenValidity :"
				+ SystemEnviroment.getTokenValidity() + "\n Sessionexpiry :" + SystemEnviroment.getSessionexpiry());

		return application.sources(NotificationServiceApplication.class);
	}

	@Bean
	public VersionInfo getVersion() {
		String version = System.getenv("NOTIFICATION_VERSION");
		System.out.println("*****version******" + version);
		if (version == null) {
			version = "v1";
		}
		return new VersionInfo(version);
	}
}