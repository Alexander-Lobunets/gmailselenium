Feature: gmail mailbox

  Scenario: send email via gmail
    Given I open gmail.com page
    When I type <login> and <password>
      | login       | password       |
      | afdqew    | ewdewd  |
    And I press login button
    Then I see gmail mailbox
    And I close browser

