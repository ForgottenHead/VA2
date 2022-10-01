# Composable ToDo

Vítejte v pohádce o Jetpack Compose. Byl nebyl jeden Google. A ten Google měl Android. No a Google vymyslel, 
že vše, co všichni umí zahodí a tak se zrodil Jetpack Compose.  

Něž začnete, přečetě si tyto články:
* https://betterprogramming.pub/managing-jetpack-compose-ui-state-with-sealed-classes-d864c1609279
* https://developer.android.com/topic/libraries/architecture/datastore


## Obsah
* Aktivity
* Obrazovky (screens) - tvorba, MVVM.
* Navigace - jak se pohybovat v celé aplikaci
* State hoisting
* Práce s mapou
* Tvorba composable funkcí
* Dodatečné čtení


## Aktivity
V projektu máme tři aktivity:
* SplashScreenActivity - je spuštěna na začátku. Nejdříve se rozhodne jestli se jedná o první spuštění. Pro uložení této informace je použit DataStore.
* AppIntroActivity - pokud se jedná o první spuštění, tak se spustí tato aktivita s intrem. Použita knihovna: https://github.com/AppIntro/AppIntro
* MainActivity - hlavní aktivita.


## Navigace - jak se pohybovat v celé aplikaci
Navigace je řešena opět přes navigační komponentu, akorát trochu jinak. 
V podstatě jdeme přes DeepLinks, takže skládáme navigační URL. Problém může být, pokud chceme poslat null hodnotu.

Čtení:
* https://developer.android.com/jetpack/compose/navigation
* https://proandroiddev.com/open-composables-via-notification-with-jetpack-navigation-b922384f4091


## Obrazovky (screens) - tvorba, MVVM.


## Dodatečné čtení a kódy co se můžou hodit
* https://blog.dipien.com/versioning-android-apps-d6ec171cfd82