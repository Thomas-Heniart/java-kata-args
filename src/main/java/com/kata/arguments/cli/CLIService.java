package com.kata.arguments.cli;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
public class CLIService {

  private List<String> sourceArgs;

  public boolean parse(ApplicationArguments args) {
    this.sourceArgs = Arrays.asList(args.getSourceArgs());
    parseSourceArgs();
    return true;
  }

  private void parseSourceArgs() {}
}
