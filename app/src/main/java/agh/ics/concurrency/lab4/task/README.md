# Zadanie 1:  Prod/Kons/los/bez_zagłodzenia

Zaimplementuj poprawnie w Java rozwiązanie PK dla losowej porcji (bez zagłodzenia).

Jako rozwiązanie prześlij: 

1. poprawny kod (4 condition+zmienna_boolean) oraz 

2. następujące elementy pomocnicze do odpowiedzi: 
    - zrzut ekranu dla zagłodzenia tego zadania na 2 condition, 
    - notatki z układu zdarzeń wyjaśniającego powstawanie zagłodzenia na 2 condition, 
    - notatki z układu zdarzeń wyjaśniającego powstawanie zagłodzenia na 4 condition z wykorzystaniem hasWaiters(), 
    - notatki z układu zdarzeń wyjaśniającego powstawanie zakleszczenia na 4 condition z wykorzystaniem hasWaiters().

Przy oddawaniu zadania należy wiedzieć:
1. o ustawieniu poprawnej wielkości bufora,
2. jak dochodzi do zakleszczenia przy niewłaściwej długości bufora (wskaż przykładowy układ zdarzeń dla zakleszczenia),
3. na czym polega zagłodzenie przy rozwiązaniu zadania na dwóch  Conditions,
4. jak można wykazać/zmierzyć zagłodzenie - można pokazać wyniki śledzenia dla obu rozwiązań i porównać (na 2 i na 4 Conditions),
5. jak dochodzi do zagłodzenia przy rozwiązaniu "poprawnym" na 4 Conditions z zastosowaniem hasWaiters(),
6. jak dochodzi do zakleszczenia przy rozwiązaniu "poprawnym" na 4 Conditions z zastosowaniem hasWaiters(),

Należy też pokazać, w jaki sposób śledziło się swój program by odkryć odpowiedź na pyt. 4.