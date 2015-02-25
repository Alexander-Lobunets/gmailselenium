Feature: gmail mailbox

  Scenario: send email via gmail
    Given Login to Gmail with <login> and <password>
      | login       | password       |
      | any_login    | any_password  |
    When I create mail to "me@gmail.com" with subject "test mail" and body "t_e_x_t" and send it
    Then I see received mail from "me@gmail.com" with subject "test mail" and body "t_e_x_t" and make screenshot
    And I close the browser

