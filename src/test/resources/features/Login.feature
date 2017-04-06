Feature: Usuarios que pueden loguearse
Scenario: Login
    Given una lista de usuarios:
      | id    	    | password |
      | DanielOrviz | password |
      | Admin1      | admin    |
      | Hugo    	| password |
    When Un usuario hace login con id "DanielOrviz" y contraseña "password"
    Then debe mostrar el mensaje "todo correcto en login"
   