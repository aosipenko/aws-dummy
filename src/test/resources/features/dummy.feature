Feature: test pipeline

  @severity=critical
  Scenario: test random stuff 1
    Given I print random string
    When I print random number
    Then step sometimes fails

  @severity=normal
  Scenario: test random stuff 2
    Given I print random string
    When I print random number
    Then step sometimes fails

  @severity=minor
  Scenario: test random stuff 3
    Given I print random string
    When I print random number
    Then step sometimes fails