Feature: mostrar propuestas
  Scenario: cliente hace llamada a POST /mostrarPropuestas
    When el cliente navega hasta /mostrarPropuestas
    Then el usuario recibe status de pagina de 200
    And el cliente recibe el string "login"