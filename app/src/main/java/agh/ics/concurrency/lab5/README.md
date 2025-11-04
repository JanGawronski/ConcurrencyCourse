# Lab 5: Programowanie na zagnieżdżonych lock'ach

1. Skonstruuj rozwiązanie zadania z poprzednich zajęć (PK z losową ilością porcji bez zagłodzenia)  na zagnieżdżonych lock'ach
     - ile jest potrzebnych lock'ów?
     - ile jest potrzebnych zmiennych warunkowych?
     - czy w obszarze chronionym może być więcej niż jeden wątek?

2. Przygotuj kod do pomiarów czasowych:

Porównaj dwa rozwiązania omawianego zadania (rozwiązania bez zagłodzenia): (1) rozwiązanie na 4 zmiennych warunkowych, (2) rozwiązanie na zagnieżdżonych lock'ach z rozwiązaniem z zagłodzeniem (3) na dwóch lockach:
 - wykonaj pomiary czasu i ilości wykonanych zadań,
 - czy są jakieś elementy kodu mające wpływ na wydajność rozwiązań? Spróbuj je zidentyfikować.

Przygotowanie:

1. Przygotuj wykresy (osie podpisane, jednostki na osiach, poprawna leganda, podpisy do rysunków) z pomiarów do omówienia na kolejnych zajęciach wraz ze wstępnymi wnioskami.