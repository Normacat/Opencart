Feature: feature to test login functionality
//@smoke
Scenario: Check  login is successful with valid credentials

 Given launch browser
 When login
 Then user is navigated to the landing page
 And tearDown