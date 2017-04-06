Feature: comentar
  Scenario: cliente hace llamada a POST /comentar
    When el cliente navega hasta /comentar
    Then el usuario recibe status de pagina de 200
    And el cliente recibe el string "showSuggestion"