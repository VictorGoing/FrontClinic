# FrontClinic
link front = https://github.com/VictorGoing/FrontClinic/tree/cac8254b3d3840b7858261be6c9bd0e86ef3478b 

link back = https://github.com/VictorGoing/Clinic/tree/328edc31e5b26c07945ec777ecd22dd6fa42b8a9

# Instrukcja uruchamiania: 

Najpierw Clinic localhost = 8083, potem FrontClinic localhost = 8080

FrontClinic zawiera atrapę logowania, żeby wszystko działało poprawnie najlepiej zacząć od poniższych kroków:

Żeby aplikacja działa poprawnie należy wejść na FrontClinic http://localhost:8080/login

Pod formularzem logowania należy zarejestrować routerLinkami lekarza i pacjenta (kolejność dowolona).

Po każdej rejestracji wrócić na stronę http://localhost:8080/login

Następnie zalogować się jako pacjent (lekarza nie zdążyłem zrobić).

Od tej pory powinno być ok.
EditAccount nie jest zrobiony jak i edycja appointmentów.
Wyświetlenie szczegółów wizyty też nie działa poprawnie.
