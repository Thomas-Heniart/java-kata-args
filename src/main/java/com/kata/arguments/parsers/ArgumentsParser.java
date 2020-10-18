package com.kata.arguments.parsers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
public class ArgumentsParser {

  private List<String> sourceArgs;
  private Iterator<String> argsIterator;
  private String currentArgument;
  private Map<String, Object> arguments = new HashMap<>();

  public boolean parse(ApplicationArguments args) {
    sourceArgs = Arrays.asList(args.getSourceArgs());
    argsIterator = sourceArgs.listIterator();
    parseSourceArgs();
    return true;
  }

  private void parseSourceArgs() {
    while (argsIterator.hasNext()) {
      parseNextArgument();
    }
  }

  private void parseNextArgument() {
    currentArgument = argsIterator.next();

    if ("-l".equals(currentArgument)) {
      if (argsIterator.hasNext()) {
        currentArgument = argsIterator.next();
        if (!currentArgumentIsValid()) {
          throw new IllegalArgumentException("boolean argument does not have parameter");
        }
      }
      arguments.put("-l", true);
    }
  }

  private boolean currentArgumentIsValid() {
    return this.currentArgument.startsWith("-");
  }
}
