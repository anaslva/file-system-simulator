package br.furb.filesystemsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FileSystemSimulatorApplication {

	public static void main(String[] args) {

		 ApplicationContext context = SpringApplication.run(FileSystemSimulatorApplication.class, args);
		 context.getBean(SimulatorRunner.class).run();
	}

}
