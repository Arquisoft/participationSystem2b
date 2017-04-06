Feature: pagina loguearse
  Scenario: cliente hace llamada a POST /loguearse
    When el cliente navega hasta /loguearse
    Then el usuario recibe status de pagina de 200
    And el cliente recibe el string "login"