Feature: landing page 
  Scenario: cliente hace llamada a GET /
    When el cliente llama a /
    Then el usuario recibe status de pagina de inicio 200
    And the client receives the string "Home"