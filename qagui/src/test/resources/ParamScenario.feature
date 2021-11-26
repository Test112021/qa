@FrontEnd @BDD @BDD-PARAM
Feature: Parameter tests

  @website
  Scenario: Check title of website
    Given website has got title
      | website | http://www.softpost.org                                |
      | title   | Software and Hardware reviews, Business ads, Tutorials |

  @website2
  Scenario: Check title of the website
    Given Name of the website "http://www.softpost.org"
    Then Title is "Software and Hardware reviews, Business ads, Tutorials"

  @website3
  Scenario Outline:  Check title of the website
    Given Name of the website is <website>
    Then Title of this website is <title>

    Examples:
      | website                 | title                                                  |
      | http://www.softpost.org | Software and Hardware reviews, Business ads, Tutorials |
      | http://www.google.com   | Google                                                 |