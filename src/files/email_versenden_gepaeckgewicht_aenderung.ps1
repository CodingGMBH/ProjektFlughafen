# Parameter erstellen:

Param(
    [string] $zu,                         # Hier wird die E-Mail-Adresse uebergeben, an die die E-Mail gesendet werden soll.
    [string] $vorname,                    # Hier wird der Vornamen des Kunden uebergeben.
    [string] $nachname,                   # Hier wird der Nachname des Kunden uebergeben.
    [string] $buchungsID,                 # Hier wird die Buchungs-ID uebergeben.
    [string] $vorherigesGepaeckGewicht,   # Hier wird das Gepaeck-Gewicht vor der Aenderung uebergeben.
    [string] $neuesGepaeckGewicht         # Hier wird die Gepaeck-Gewicht nach der Aenderung uebergeben.
)

$kennwort = ConvertTo-SecureString "Larcherairline2017" -AsPlainText -Force     # -AsPlainText: Specifies a plain text string to convert to a secure string. The secure string cmdlets help protect confidential text. The text is encrypted for privacy and is deleted from computer memory after It is used. If you use this parameter to provide plain text as input, the system cannot protect that input in this manner. To use this parameter, you must also specify the Force parameter; -Force: Confirms that you understand the implications of using the AsPlainText parameter and still want to use It.

$credential = New-Object System.Management.Automation.PSCredential ("larcher.airline@gmail.com", $kennwort)

$PSEmailServer = "smtp.gmail.com"

$von = "larcher.airline@gmail.com"

$betreff = "Bestätigung zur Ihrer Änderung des Gepäckgewichts für die Buchung mit der Buchungs-ID $buchungsID bei Larcher Airline"

$text = "Sehr geehrter Kunde $nachname $vorname,
die Änderung des Gepäckgewichts von $vorherigesGepaeckGewicht kg auf $neuesGepaeckGewicht kg für die Buchung mit der Buchungs-ID $buchungsID wurde erfolgreich durchgeführt.

Wir wünschen Ihnen einen schönen und angenehmen Flug,
Ihre Larcher Airline.

--------------------------------------------
Dies ist eine automatisch generierte E-Mail.
Bitte antworten Sie nicht auf diese E-Mail."

Send-MailMessage -Credential $credential -From $von -To $zu -Subject $betreff -Body $text -UseSsl -Encoding ([System.Text.Encoding]::UTF8)   # SSL (Secure Sockets Layer-Protokoll) wird verwendet, da es ohne dieses Protokoll mit GMail nicht funktioniert! SSL, heute TLS (Transport Layer Security) genannt, ist ein Verschluesselungsprotokoll zur sicheren Datenuebertragung im Internet; Zum Codieren der Nachricht wird UTF8 verwendet, um auch Umlaute uebertragen zu koennen.