Feature: cerrar sesion
  Scenario: cliente hace llamada a POST /cerrarSesion
    When el cliente navega hasta /cerrarSesion
    Then el usuario recibe status de pagina de 200
    And el cliente recibe el string "login"