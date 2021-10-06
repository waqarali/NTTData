#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@Form
Feature: Submitting Form with Data
  I want fill and submit form

Background: Logged In
  Given I am on Form Page
  
  
  @FillFormFullData
  Scenario: Filling form with data
    When I fill up all fields of the form and submit
    | FirstName    | LastName     |  Email  | Gender   |  Mobile  | DOB |Subjects | Hobbies | Current Address | State | City |
    | <FirstName>  | <LastName>   | <Email> | <Gender> | <Mobile> |<DOB>|<Subjects>|<Hobbies>|<Current Address>|<State>|<City>|
		Then Form information overlay should open
    Examples: 
      | FirstName| LastName | Email           | Gender|  Mobile  |   DOB         | Subjects | Hobbies |Current Address       | State | City |
      | John1    | Smith2   |johnsm@yahoo.com | Male  |0789876354|  03-04-1987   | Science  | Sports  |10 Lucas Avenue 765884|Haryana|Karnal|
