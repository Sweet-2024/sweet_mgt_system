Feature: sign up to sweet system
  description: sign up should be quick and friendly

  Scenario: sign up with existing email
    Given trying to sign up to sys
    When entering an email already exsit in the sys
    Then give a try again msg

  Scenario Outline: successful sign-up
    Given trying to sign up to sys
    When signing up as <person>
    Then give a successful sign up msg and go to login

  Examples:
    |person|
    |admin|
    |regular user|
    |owner|
    |supplier|

