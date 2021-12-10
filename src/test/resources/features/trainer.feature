Feature: Lingo Trainer
  As a Player,
  I want to guess 5, 6 and 7 letter words,
  In order to practise for the game Lingo

  Scenario: Start a new game
    When I start a new game
    Then my score is "0"
    And I should see the first letter of a "5" letter word

  Scenario Outline: Start a new round
    Given I am playing a game
    And the round was won
    And the last word had "<previous length>" letters
    When I start a new round
    Then the word to guess has "<next length>" letters

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |

    # Failure path
  Scenario: Cannot start a new round
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round

  Scenario Outline: Guessing a word
    Given I am playing a game
    And the round is still ongoing
    And the word to guess is "<word>"
    When i take a "<guess>"
    Then I should receive a "<feedback>" on what I just entered

  Examples:
    |word  |guess  | feedback                                             |
    |BAARD |BERGEN | INVALID, INVALID, INVALID, INVALID, INVALID, INVALID |
    |BAARD |BONJE  | CORRECT, ABSENT, ABSENT, ABSENT, ABSENT              |
    |BAARD |BARST  | CORRECT, CORRECT, PRESENT, ABSENT, ABSENT            |
    |BAARD |BEDDE  | CORRECT, ABSENT, PRESENT, ABSENT, ABSENT             |
    |BAARD |BAARD  | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT          |
