package com.example.schedulingtasks;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableAsync
@Component
public class ScheduledTaskExamples {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
                "Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Fixed rate task with one second initial delay - " + now);
    }

    @Scheduled(cron = "0 15 10 15 * ?")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Schedule tasks using cron jobs - " + now);
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void parametrizedScheduledTaskWithFixedDelay() {
        System.out.println(
                "Parametrized Fixed delay task executed at " + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "${cron.expression}")
    public void parametrizedScheduleTaskUsingCronExpression() {
        System.out.println(
                "Parametrized cron job executed at  " + dateFormat.format(new Date()));
    }
}
