package com.example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration

public class SchedulerConfig {
    @Bean
    public TaskScheduler taskScheduler() {
        // #1
        // return new ThreadPoolTaskScheduler();
        // #2
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("MyTaskScheduler~");
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }
}
