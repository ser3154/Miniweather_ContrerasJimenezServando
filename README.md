Respuesta del paso 7:
Si te pones a experimentar con el celular y tienes abierta la aplicación a las 11:59 am
y lo minimizas y vuelves a abrir la aplicación a las 12:00pm ¿Qué ocurre? ¿El saludo
cambia? ¿Cómo lo resolverías?

- Si se abre la app a las 11:59am, la minimizas y la vuelve a abrir a las 12:00pm, el saludo no cambia. Esto pasa porque el onCreate() solo se
- ejecuta una vez, cuando la actividad se crea; al minimizar y reabrir solo se disparan onPause -> onStop -> onRestart -> onStart -> onResume, no
- onCreate de nuevo. Como el cálculo de la hora viven en onCreate, el greeting.text queda "congelado" con el valor que tenia al crearse.

-La solución seria mover la lógica de la hora (el when de time) a onResume() en lugar de onCreate(), así se recalcula cada vez que la actividad vuelve 
a primer plano
