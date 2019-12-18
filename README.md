#  negotium-backend-service

#  Technologies
-   Java 11 
-   Spring boot 2.2.0
-   Apache Maven
-   JPA (Java Persistence API)
-   MySQL
-   JUnit 5
-   Docker

# Proces uruchomienia aplikacji
Aby uruchomić pomyślnie projekt należy: 
1. Pobrać wersje JDK, czyli Java Development Kit W ramy tego pakietu wchodzi m.in. kompilator, który pozwala przetłumaczyć kod programu z takiego, który będzie czytelny dla nas, na taki, który będzie czytelny dla wirtualnej maszyny Javy. 
[https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
-  Najlepiej zainstalować w **C:/Program Files/Java/jdk-11.0.1** (wymagane będą prawa administratora). 
- Do poprawnego działania i możliwości korzystania ze środowiska JDK z konsoli, niezbędne jest skonfigurowanie zmiennej środowiskowej PATH.
Kliknij  **Start**  i wpisz  **cmd**  i wciśnij Enter.
- W niektórych przypadkach może się okazać, że zmienne były wcześniej skonfigurowane na komputerze bądź instalator poprawnie je zainicjował w celu przekonania się wpisz: 
**java**
**javac** 
Jezeli zobaczysz komunikat 'javac' lub 'java' is not recognized as an internal or external command operable program or batch file. 
Jest to problem mówiący o tym, że trzeba dodać zmienne środowiskowe do systemu w tym celu:
 prawym przycisk myszy na **Komputer** -> **Właściwości**
 A następnie do **Zaawansowane ustawienia systemu Windows**  a następnie w okienku, które się pojawi wciśnij przycisk **Zmienne środowiskowe** 
W kolejnym okienku , które się pojawi znajdź **Zmienne systemowe**, wyszukaj zmienną o nazwie **Path** i kliknij **Edytuj...**
i dodajemy ścieżkę do JDK w moim przypadku jest to: 
C:\Program Files\Java\jdk11.0.5\bin_
W celu weryfikacji, czy zmienna jest prawidłowo skonfigurowana **uruchom ponownie** konsolę cmd i wpisz jeszcze raz polecenia  java oraz javac

2. Instalacja środowiska programistycznego IntelliJ IDEA: 
Community - bezpłatna wersja  zarówno do użytku domowego jak i komercyjnego.
https://www.jetbrains.com/idea/download/ 
Instalacja przebiega w sposób intuicyjny, podobnie jak przy instalacji innych programów. 
3. Instalacja bazy danych MySQL WorkBeanch
MySql pobieramy z poniższej strony: 
[https://dev.mysql.com/downloads/windows/installer/8.0.html](https://dev.mysql.com/downloads/windows/installer/8.0.html)
Wybieramy wersje oprogramowania w zależności od systemu, grunt aby to była wersja powyżej 400mb. 
Instalacja przebiega w sposób intuicyjny.
4. Istanalacja Apache Maven(narzędzie automatyzujące budowę oprogramowania na platformę Java.)
Pobieramy narzędzie z poniższej strony 
[https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
Pobrany plik rozpakowujemy na wybranym przez Nas dysku(Najlepiej na dysku, gdzie mamy zainstalowane JDK i IntelliJ) 
Uruchamiany IntelliJ 
**Przechodzimy do**: 
**File ->Project Structure -> Project** -> W ustawieniach podjemy ścieżkę do Naszego JDK  z punktu 1 
**Następnie** 
**File -> Platform Settings ->SDKs** -> podjemy ścieżkę do Naszego JDK  z punktu 1 
**Następnie dodajemy mavena**
**Settings ->Buld, Execution, Deployment -> Build Tools -> Maven** -> W folderze Maven home directory: Podajemy ścieżkę do ściągniętego wcześniej maven w moim przypadku jest to:
**C:/apache-maven-3.6.2**

5. Uruchomienie projektu w środowisku IntelliJ IDEA
Pobieramy repozytorium projektu z github z brancha **master**:
 [https://github.com/TheGitDevelopers/negotium-backend-service](https://github.com/TheGitDevelopers/negotium-backend-service) 
Następnie uruchamiamy IntelliJ  i wybieramy:
**Open -> wskazujemy ścieżkę gdzie jest nasz skalowany projekt i klikamy OK**
Przy pierwszym uruchomieniu projektu, chwilę może potrwać aż wszystkie pakiety załadują się. Po poprawnym załadowaniu projektu, powinniśmy mieć już dostęp do klas Javy. 
Przechodzimy do pliku **application.properties** i patrzymy czy username i hasło mamy takie same jak w naszej bazie MySQL jeżeli jest inne to modyfikujemy hasło i użytkownika pod dane z naszych ustawień MySQL
**spring.datasource.username=root**  
**spring.datasource.password=admin**
Następnie uruchamiamy bazę danych **MySQL** 

	  **Projekt uruchamiamy po przez opcję "run" w prawym górnym rogu lub shift + F10**


