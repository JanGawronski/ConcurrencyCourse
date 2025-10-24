# Lab 2: Programowanie z wykorzystaniem monitorów; problem zakleszczenia

Programowanie z wykorzystaniem monitorów:
1. monitor klasyczny a mechanizmy synchronizacji w Java (synchronized, wait(), notify()) - dyskusja na podstawie przygotowania
2. zaimplementuj problem Producent-Konsument (PK):
    - 1P1K1B,
    - MPNK1B,
    - MPNKWB — przygotuj implementację pod bufor wieloelementowy (potrzebne na kolejnych zajęciach) z wykorzystaniem mechanizmów z pkt 1.; oznaczenia: M,N,W oznaczają liczbę odpowiednio Producentów (P), Konsumentów (K), elementów w Buforze (B); 
3. Zaobserwuj/wyjaśnij:
    - Możliwość zakleszczenia przy używaniu notify() - dla którego z ww. problemów?
    - Wskaż układ zdarzeń dla ww. zakleszczenia; wskaż możliwe rozwiązania.

Przygotowanie:
1. Java: interfejsy Lock, Condition, itd.
2. Zadania (Weiss, Gruźlewski): dla gimnastyki tzw. klasyka 4.2.2 (producenci i  konsumenci), 4.2.3 (czytelnicy i pisarze), 4.2.4 (pięciu filozofów); ważne: 4.3.3 (producenci i konsumenci z losową wielkością wstawianych i pobieranych porcji).
