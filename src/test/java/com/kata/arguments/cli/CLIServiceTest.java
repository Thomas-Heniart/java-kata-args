package com.kata.arguments.cli;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.DefaultApplicationArguments;

class CLIServiceTest {

  private CLIService cliService;

  @BeforeEach
  void setUp() {
    cliService = new CLIService();
  }

  @AfterEach
  void tearDown() {}

  @ParameterizedTest
  @MethodSource("provideIllegalArguments")
  void parse_ShouldFailForIllegalArguments(String[] arguments, String message) {
    var args = new DefaultApplicationArguments(arguments);

    assertThrows(IllegalArgumentException.class, () -> cliService.parse(args), message);
  }

  @ParameterizedTest
  @MethodSource("provideArguments")
  void parse_ShouldWorkForArguments(String[] arguments) {
    var args = new DefaultApplicationArguments(arguments);

    assertTrue(cliService.parse(args));
  }

  private static Stream<Arguments> provideIllegalArguments() {
    return Stream.of(
        Arguments.of(new String[] {"-l", "10"}, "boolean argument does not have parameter"),
        Arguments.of(new String[] {"-p', 'string"}, "integer argument must have integer parameter"),
        Arguments.of(new String[] {"-d"}, "path argument must be followed by a path parameter"),
        Arguments.of(new String[] {"-fake"}, "fake is not supported"));
  }

  private static Stream<Arguments> provideArguments() {
    return Stream.of(
        Arguments.of((Object) new String[] {"-l"}),
        Arguments.of((Object) new String[] {"-p"}),
        Arguments.of((Object) new String[] {"-d"}),
        Arguments.of((Object) new String[] {"-p", "8080"}),
        Arguments.of((Object) new String[] {"-d", "/tmp/test"}),
        Arguments.of((Object) new String[] {"-l", "-p", "8080", "-d", "/tmp/test"}));
  }
}
