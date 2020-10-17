package com.kata.arguments;

import com.kata.arguments.cli.CLIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArgumentsApplication implements ApplicationRunner {

  private final CLIService cliService;

  @Autowired
  public ArgumentsApplication(CLIService cliService) {
    this.cliService = cliService;
  }

  public static void main(String[] args) {
    SpringApplication.run(ArgumentsApplication.class, args).close();
  }

  @Override
  public void run(ApplicationArguments args) {
    cliService.parse(args);
  }
}
