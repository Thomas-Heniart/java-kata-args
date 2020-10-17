package com.kata.arguments.cli;

import static com.kata.arguments.utils.TestUtils.£;
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

  private static Stream<Arguments> provideIllegalArguments() {
    return Stream.of(
        Arguments.of(new String[] {"-l", "10"}, "boolean argument does not have parameter"),
        Arguments.of(new String[] {"-p', 'string"}, "integer argument must have integer parameter"),
        Arguments.of(new String[] {"-d"}, "path argument must be followed by a path parameter"),
        Arguments.of(new String[] {"-fake"}, "fake is not supported"));
  }
}
