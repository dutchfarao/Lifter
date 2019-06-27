# Lifter

Lifter is een app die lifters en mensen met een auto bij elkaar brengt. De gebruiker maakt een profiel aan en meldt zich aan bij een liftplek
(als lifter of als chauffeur). Gebruikers kunnen het profiel bekijken van een andere gebruiker die zich heeft aangemeld bij een liftplek, zo is meteen duidelijk met wie je te maken hebt. De liftplekken zelf hebben een rating zodat je meteen kunt zien of je een goede kans hebt om opgepikt te worden.

# Technisch ontwerp

De gebruiker begint bij de *LoginActivity*. Twee opties: inloggen of registreren.
Inloggen: Met UserDownloader worden alle users in de databse opgehaald, de ingevoerde gebruikersnaam en wachtwoord worden vergeleken met de database, is er een match? Dan is de gebruiker ingelogd en wordt hij doorgestuurd naar MapsActivity.
Registreren: De gebruiker wordt naar RegisterActivity geleid.

*RegisterActivity*. De gebruiker vult alle velden in, UserUploader wordt aangeroepen om de user in de database te zetten, de user is nu ingelogd. Hij wordt doorgeleid naar MapsActivity.

*MapsActivity*. Deze activity kan worden gezien als de homepage, bij onCreate wordt de Google Maps Api aangeroepen om de kaart te fabriceren, daarna wordt LiftspotDownloader aangeroepen om alle liftplekken op de kaart te zetten. Twee opties: FloatingActionButton of op een marker klikken.
FloatingActionButton: De gebruiker wordt naar de MyProfileActivity gestuurd.
Marker: De gebruiker wordt naar LiftActivity gestuurd.

*MyProfileActivity*. Op deze activity kan de gebruiker zijn eigen profiel bekijken, de pagina bestaat uit edittexts, die de gebruiker kan aanpassen mocht hij/zij dat willen. Als er op de Update knop wordt gedrukt, wordt UserUpdater aangeroepen, er wordt een PUT request gestuurd voor de gebruiker in kwestie naar de database en zijn account wordt aangepast. Hierna komt de gebruiker weer in de MapsActivity terecht.

*LiftActvity*. De liftactivity laat alle informatie over een betreffende liftplek zien. Bij de OnCreate wordt Google Streetview Api aangeroepen om een streetview te creëeren van de plek. De gebruiker heeft drie opties: zichzelf aanmelden/afmelden als driver of lifter, het profiel van een andere aangemelde gebruiker bekijken of de home knop.
Zichzelf aanmelden of afmelden: De gebruiker wordt naar DriverLifterActivity geleid.
Ander profiel bekijken: De gebruiker wordt naar ProfileActivity geleid.
Home knop: De gebruiker gaat terug naar MapsActivity.

*DriverLifterActivity*. Deze activity is bedoeld om het profiel van de aangemelde user toe te voegen aan de lifters- of driversArrayList<user>  van de liftspot in kwestie. Als de gebruiker op het plusje klikt wordt de user toegevoegd, bij het kruisje wordt de user verwijderd. Bij beide opties wordt LiftspotUpdater aangeroepen om de Liftspot aan te passen met een PUT request. Hierna wordt de user weer teruggeleid naar LiftActivity.
  
*ProfileActivity* Deze activity laat de gegevens van een andere gebruiker zien. De user kan deze alleen maar bekijken en niet aanpassen, als hij op Ga Terug klikt komt de gebruiker weer bij de LiftActivity terecht.
  
  
### De verschillende activity's met bijbehorende layouts
<img src="https://github.com/dutchfarao/Lifter/blob/master/doc/ProjectAppdesign.png" width="1000" height="600" /> 

### UML Diagram, alle upload/download/update classes communiceren met de rester sqllite database

<img src="https://github.com/dutchfarao/Lifter/blob/master/doc/ProjectUMLDiagram.png" width="1000" height="600" /> 



# Uitdagingen en aanpassingen

Vanaf het begin was het al duidelijk dat ik met de functies die ik voor ogen had misschien teveel hooi op mijn vork had genomen. Ik ben daarom begonnen met de basis en het waar mogelijk heb ik functionaliteit toegevoegd. Allereerst heb ik de chatfunctie niet geimplementeerd, dit is 100% een kwestie van tijdgebrek geweest. Dat er geen notificaties zijn zoals ik eerst wel wilde heeft deels dezelfde reden, daarbij kwam ook het feit dat het hiervoor noodzakelijk was om de app met meerdere telefoons tegelijk te testen, dit was voor mij een obstakel aangezien ik zelf geen android telefoon gebruik. Ten slotte heb ik door tijdsgebrek ook foto's bij gebruikersaccount moeten overslaan. Ik vind dit zelf geen heel groot probleem maar estetisch was het wel een verbetering geweest.

Toch is er genoeg functionaliteit die er uiteindelijk wel is gekomen. De loginfunctie en het registreren van een gebruiker werkt soepel, dit heeft mij veel tijd gekost. De vertraging die ik hierbij opliep kwam vooral door het aanpassen van de ArrayList<user>'s in de een Liftspot objects. Omdat de rester database een vrij sobere structuur heeft moest ik omwegen bedenken om toch deze relatief ingewikkelde structuur te kunnen opslaan. Daarnaast ben ik erg tevreden over de implementatie van de Google Maps API en de Streetview API in respectievelijk de MapsActivity en LiftActivity. Vooral de Streetview API was niet makkelijk om te implementeren. Ten eerste is het een betaalde API dus ik moest hiervoor een account aanmaken bij Google met gekoppelde creditcard. In het begin verliep de communicatie tussen de activity en API niet goed omdat de API werkte met verschillende beveiligingsleutels, uiteindelijk is dit gelukkig wel gelukt. De Streetview API is zelfs interactief, dus geen foto van de plek maar echt een streetview waar de gebruiker in kan rondkijken, dit is veel mooier dan ik in eerste instantie voor ogen had.
  
  # Verbeterpunten
  
 Er zijn door het tijdsbestek waarin deze app is gemaakt genoeg verbeterpunten. De app ziet er netjes uit maar is qua design wel vrij basic gebleven, voor meer opsmuk was simpelweg geen tijd, een profielfoto bij elke gebruiker had iets toegevoegd bijvoorbeeld. Daarnaast is er bij het ontwerpen van deze app gebruik gemaakt van de rester.py databse. Hoewel dit redelijk werkt, is deze database alleen online als je hem zelf runt in een IDE. Daardoor is deze app meteen al ongeschikt voor publicatie. Daarnaast wordt er voor het opslaan van wachtwoorden geen encryptie gebruikt.
  
  # Dankwoord
  
 Ik wil graag mijn TA's Renske en Natasja en mijn projectgroep bestaande uit Felix en Bart bedanken voor de ondersteuning gedurende de laatste 4 weken. Op frustrerende momenten hebben ze mij er steeds weer doorheen weten slepen. Aan het begin van deze minor had ik nooit verwacht dat ik aan het einde een dergelijke app zou kunnen maken, daar ben ik best een beetje trots op.
  
  
``  
  ## Credits
Made by Darian El Sayed for de Universiteit van Amsterdam.
rester.py by Martijn Stegeman https://github.com/stgm/rester

``
  

