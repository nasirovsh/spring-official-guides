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

## Build an executable JAR with Gradle

```shell
./gradlew bootRun
```

or

```shell
./gradlew build
java -jar build/libs/gs-scheduling-tasks-0.1.0.jar
```
