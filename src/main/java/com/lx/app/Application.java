package com.lx.app;

import com.lx.app.util.RunnableThreadWebCount;
import com.lx.app.util.Timers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		//计数线程
		RunnableThreadWebCount runnableThreadWebCount = new RunnableThreadWebCount();
		runnableThreadWebCount.run();
		//计时器线程
		Timers timers = new Timers();
		timers.run();

	}
}
