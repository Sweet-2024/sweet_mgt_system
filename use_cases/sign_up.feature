Feature: sign up to sweet system
  description: sign up should be quick and friendly

  Scenario: sign up with existing email
    Given trying to sign up to sys
    When entering an email already exist in the sys
    Then give a try again msg

  Scenario: successful sign-up
    Given trying to sign up to sys
    When entering valid user data
    Then give a successful sign up msg and go to login

  Scenario: sign up with invalid email
    Given trying to sign up to sys
    When entering an invalid email
    Then give a try again msg

  Scenario: sign up with long username
    Given trying to sign up to sys
    When entering a username with more than twenty character
    Then give a try again msg

  Scenario: sign up with invalid password
    Given trying to sign up to sys
    When entering a password with less than eight characters or all characters are from the same type
    Then give a try again msg

  Scenario: sign up with invalid location
    Given trying to sign up to sys
    When entering a city is not valid
    Then give a try again msg

  Scenario: sign up with user type
    Given trying to sign up to sys
    When entering an invalid user type
    Then give a try again msg