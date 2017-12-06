package com.desire3d;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.desire3d.channel.NotificationChannel;

/**
 * @author Mahesh Pardeshi
 *
 */
@Configuration
@EnableBinding(NotificationChannel.class)
@EnableEurekaClient
public class Config {
}