# Scheduling Tasks
You will build an application that prints out the current time every five seconds by using Spring’s @Scheduled annotation.

# Create a Scheduled Task

Create in `src/main/java/com/example/schedulingtasks/ScheduledTasks.java`

# Enable Scheduling

Enable in `src/main/java/com/example/schedulingtasks/SchedulingTasksApplication.java`

**@SpringBootApplication** is a convenience annotation that adds all of the following:

- **@Configuration**: Tags the class as a source of bean definitions for the application context.

- **@EnableAutoConfiguration**: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

- **@ComponentScan**: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.

**@EnableScheduling** annotation ensures that a background task executor is created. Without it, nothing gets scheduled.


## Build an executable JAR with Gradle

```shell
./gradlew bootRun
```

or

```shell
./gradlew build
java -jar build/libs/gs-scheduling-tasks-0.1.0.jar
```

## Extended

### Schedule a Task at Fixed Delay
```java
@Scheduled(fixedDelay = 1000)
public void scheduleFixedDelayTask() {
    System.out.println(
            "Fixed delay task - " + System.currentTimeMillis() / 1000);
}
```
Duration between the end of the last execution and the start of the next execution is fixed. The task always waits until the previous one is finished.

This option should be used when it’s mandatory that the previous execution is completed before running again.

### Schedule a Task at a Fixed Rate
```java
@Scheduled(fixedRate = 1000)
public void scheduleFixedRateTask() {
    System.out.println(
            "Fixed rate task - " + System.currentTimeMillis() / 1000);
}
```
This option should be used when each execution of the task is independent.

The fixedRate property runs the scheduled task at every n millisecond. It doesn’t check for any previous executions of the task.

Note that scheduled tasks don’t run in parallel by default. So even if we used fixedRate, the next task won’t be invoked until the previous one is done.

### Support parallel behavior

Add `@Async` annotation to support parallel behavior in scheduled tasks

```java
@EnableAsync
public class ScheduledFixedRateExample {
    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
          "Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }

}
```

### Fixed Rate vs Fixed Delay
The fixedDelay property makes sure that there is a delay of n millisecond between the finish time of an execution of a task and the start time of the next execution of the task.

This property is specifically useful when we need to make sure that only one instance of the task runs all the time. For dependent jobs, it is quite helpful.

### Schedule a Task With Initial Delay

```java
@Scheduled(fixedDelay = 1000, initialDelay = 1000)
public void scheduleFixedRateWithInitialDelayTask() {
 
    long now = System.currentTimeMillis() / 1000;
    System.out.println(
      "Fixed rate task with one second initial delay - " + now);
}
```

### Schedule a Task Using Cron Expressions

```java
@Scheduled(cron = "0 15 10 15 * ?")
public void scheduleTaskUsingCronExpression() {
 
    long now = System.currentTimeMillis() / 1000;
    System.out.println(
      "schedule tasks using cron jobs - " + now);
}
```

By default, Spring will use the server’s local time zone for the cron expression. However, we can use the zone attribute to change this timezone:

```java
@Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")
```

With this configuration, Spring will schedule the annotated method to run at 10:15 AM on the 15th day of every month in Paris time.

### Parameterizing the Schedule

Control the schedule without re-compiling and re-deploying the entire app.

Make use of Spring Expressions to externalize the configuration of the tasks, and we’ll store these in properties files.

A fixedDelay task:
```java
@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
```
A fixedRate task:
```java
@Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
```
A cron expression based task:
```java
@Scheduled(cron = "${cron.expression}")
```




### Links
- [https://spring.io/guides](https://spring.io/guides)
- [https://www.baeldung.com/start-here](https://www.baeldung.com/start-here)
- https://www.baeldung.com/spring-scheduled-tasks
- https://www.baeldung.com/spring-task-scheduler
- [medium article - scheduling-tasks-in-spring](https://medium.com/@AlexanderObregon/scheduling-tasks-in-spring-applications-a-practical-guide-8d28d35493c6)



