# Lab 3: Problem zagłodzenia

Przekonaj się, że rozumiesz tematykę poprzednich zajęć — spróbuj wykonać:
1. Wskaż układ zdarzeń dla zakleszczenia dla NPMKWB na 1 "kolejce" (wait)  w Java z synchronized, wait(), notify(), czy W ma tutaj znaczenie (np. W==1)?
2. [dla chętnych] Czy w (błędnym) rozwiązaniu, na klasycznym monitorze, z jedną zmienną warunkową, jest możliwe, że P i K są w niej jednocześnie?
3. Zapisz odpowiedzi ww. zadanie/a w pliku; Pozostaw w swoich zasobach dla labów TW (pomoc przy odpowiedzi).

---

Wykonaj zadania:
1. zaimplementuj NPMKWB — rozwiązanie przy jednoelementowych porcjach z wykorzystaniem Lock i Condition,
2. przygotuj kod dla P i K  z losową wielkością wstawianych/pobieranych porcji (problem zagłodzenia);
    1. zaimplementuj bufor dla takich porcji, zaimplementuj losowanie porcji,
    2. wskaż (zapisz w komentarzach w kodzie) przykładowy układ zdarzeń dla zakleszczenia przy niewłaściwej długości bufora;
    3. zaimplementuj  rozwiązanie na 2 Condition - czyli na obecnie posiadanym kodzie;
    4. wskaż układ zdarzeń dla zagłodzenia przy rozwiązaniu na 2 Condition;
3. problematyka kolejnych zajęć: poprawne rozwiązanie bez zagłodzeia - na 4 Condition.

Przygotowanie:
1. dokończ zadanie z losowymi porcjami (rozwiązanie z zagłodzeniem, czyli na 2 Condition)
2. dokończ śledzenie dla obserwacji zagłodzenia przy rozwiązaniu na 2 Condiction (rozwiązanie niepoprawne, bo z możliwością zagłodzenia)
3. przeczytaj z Książki jeszcze raz poprawne rozwiązanie bez zagłodzenia - czyli na 4 Condition.