    
Feature: Application Login


Scenario: Home page default login
Given User is on Netbanking landing page
When User login into application with "jack" and password "1234"
Then Home page is populated
And Cards displayed is "true"

Scenario: Home page default login
Given User is on Netbanking landing page
When User login into application with "rose" and password "4321"
Then Home page is populated
And Cards displayed is "false"


  