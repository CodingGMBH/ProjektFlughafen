# Parameter erstellen:

Param(
    [string] $zu,                    # Hier wird die E-Mail-Adresse uebergeben, an die die E-Mail gesendet werden soll.
    [string] $vorname,               # Hier wird der Vornamen des Kunden uebergeben.
    [string] $nachname,              # Hier wird der Nachname des Kunden uebergeben.
    [string] $startFlughafen,        # Hier wird der Start-Flughafen uebergeben.
    [string] $zielFlughafen,         # Hier wird der Ziel-Flughafen uebergeben.
    [string] $abflugZeit,            # Hier wird die Abflugs-Zeit uebergeben.
    [string] $ankunftZeit,           # Hier wird der Ankunfts-Zeit uebergeben.
    [string] $abflugsdatum,          # Hier wird das Abflugs-Datum uebergeben.
    [string] $preisBuchung,          # Hier wird der Preis der Buchung uebergeben.
    [string] $gepaeckGewicht,        # Hier wird das Gepaeck-Gewicht uebergeben.
    [string] $flugGesellschaft,      # Hier wird die Flug-Gesellschaft uebergeben.
    [string] $buchungsID             # Hier wird die Buchungs-ID uebergeben.
)

$kennwort = ConvertTo-SecureString "Larcherairline2017" -AsPlainText -Force     # -AsPlainText: Specifies a plain text string to convert to a secure string. The secure string cmdlets help protect confidential text. The text is encrypted for privacy and is deleted from computer memory after It is used. If you use this parameter to provide plain text as input, the system cannot protect that input in this manner. To use this parameter, you must also specify the Force parameter; -Force: Confirms that you understand the implications of using the AsPlainText parameter and still want to use It.

$credential = New-Object System.Management.Automation.PSCredential ("larcher.airline@gmail.com", $kennwort)

$PSEmailServer = "smtp.gmail.com"

$von = "larcher.airline@gmail.com"

$betreff = "Bestätigung zur Ihrer Buchung bei Larcher Airline"

$text = "Sehr geehrter Kunde $nachname $vorname,
vielen Dank für Ihre Buchung bei Larcher Airline.

Sie haben den folgenden Flug gebucht:
 - Flug von $startFlughafen nach $zielFlughafen um $abflugZeit Uhr bis $ankunftZeit Uhr mit Ablug am $abflugsdatum
 - Preis: $preisBuchung Euro
 - Gepäckstücke: $gepaeckGewicht kg
 - Fluggesellschaft: $flugGesellschaft
 - Buchungs-ID: $buchungsID

Wir wünschen Ihnen einen schönen und angenehmen Flug,
Ihre Larcher Airline.

--------------------------------------------
Dies ist eine automatisch generierte E-Mail.
Bitte antworten Sie nicht auf diese E-Mail."

Send-MailMessage -Credential $credential -From $von -To $zu -Subject $betreff -Body $text -UseSsl -Encoding ([System.Text.Encoding]::UTF8)   # SSL (Secure Sockets Layer-Protokoll) wird verwendet, da es ohne dieses Protokoll mit GMail nicht funktioniert! SSL, heute TLS (Transport Layer Security) genannt, ist ein Verschluesselungsprotokoll zur sicheren Datenuebertragung im Internet; Zum Codieren der Nachricht wird UTF8 verwendet, um auch Umlaute uebertragen zu koennen.