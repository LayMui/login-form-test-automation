Feature: login to sunflower field
  In order to login to enjoy the sunflower field 
  As a tourist Mary
  Mary wants to login 

  Background:
    Given Mary is at the login form of sunflower field
@web
  Scenario Outline: login authentication
    In order to login to the sunflower field
    As a tourist Mary
    Mary wants to be able to login
    When Mary login with email "<email>" and "<password>"
    Then she is able to see the sunflower farm

    Examples:
      | email  | password |
      | laymui | 1234567  |

