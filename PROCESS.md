## Maandag 3 juni

Na het eerste college te hebben gevolgd ben ik begonnen aan het schrijven van mijn proposal. Het idee is een app die lifters en automobilisten 
op een veilige manier samenbrengt. Ik heb onderzoek gedaan naar de API's die ik wil gebruiken, en een site gevonden die een goede bron kan vormen
voor het pinnen van goede liftspots op de kaart. Ik denk dat mijn idee goed uitvoerbaar moet zijn. De chatroom is een bonus die ik kan uitvoeren mocht ik daar
nog tijd voor hebben. Ook heb ik al schetsen gemaakt van alle activities die ik ga gebruiken.

## Dinsdag 4 juni

Ik heb vandaag de schetsen die ik gister al had gemaakt digitaal uitgewerkt voor het design document, er is nu meer duidelijk welke API's ik waarvoor ga gebruiken.
Ook heb ik 2 classes uitgewerkt die noodazakelijk zijn voor defunctionaliteit van de app.

## Woensdag 5 juni

Ik ben vandaag met felix gaan zitten voor een eerste stand-up, ik heb hem verscheidene tips kunnen geven voor zijn kleding app. Ook hebben we gesproken over Lifter, hier kwamen
de volgende suggesties uit:
- Zoek uit of de sqplite database van cs50 bruikbaar is voor dit project 
- Voor het testen van de notificaties is het noodzakelijk dat er twee accounts tegelijk aangemeld zijn
- De chatroom gaat misschien teveel werk kosten (dit zie ik overigens zelf ook in)


Daarna ben ik begonnen met het bouwen van het framework van de app, ik heb nu 2 schermen gedaan. De google maps api werkt al

## Donderdag 6 juni

Vandaag ben ik verder gegaan met het uitwerken van de activities. Dit gaat soepel. Alle schermen die ik in eerste instantie wil gaan implementeren zijn er, navigatie tussen deze schermen werkt goed. Ten opzichte van het oorspronkelijke design heb ik voor nu de chat achterwege gelaten. Ook maak ik nog geen onderscheid tussen 'MyProfileActivity' en 'OtherProfileActivity', er is nu gewoon 1 profile activity. Ik ga tijdens de volgende stand-up feedback vragen wat het handigst hiervoor is. 


# Vrijdag 7 juni

Ik ben vandaag met felix en bart gaan zitten voor stand-up. Vooral bart kon ik goed helpen omdat hij aan het strugglen was met een maps api, ik heb hem aangeraden om ook voor de Google Maps API te kiezen en heb hem uitgelegd hoe dit werkt. Ook kwamen er suggesties voor mijn app:
- Het is misschien een idee om mensen zelf een liftplek te laten toevoegen, ik ben hier zelf echter wel sceptisch over omdat mensen dan
alles als liftplek kunnen aangeven, dit doet af aan de betrouwbaarheid van de app. verder moet je dan alle liftplekken gaan opslaan in een database en bij opstarten al deze deze requesten, dit zal de snelheid van de app niet ten goede komen. Ik denk dan ook dat ik dit niet ga toevoegen.
- Ik ga de structuur van de app licht aanpassem. ipv een MyProfileActivity en OtherProfileActivity komt er een ProfileActivity en een EditProfileActivity, deze laatste kun je alleen bereiken als je in je naar je eigen profielpagina kijkt.

# Dinsdag 11 juni

Na een lang pinksterweekend pakken we de draad weer op. Tijdens de standup geven we elkaar een korte update, niemand heeft echter heel veel vooruitgang gemaakt. Ik zelf vertel dat ik de structuur van de app zoals hierboven staat iets ga aanpassen en dat ik bij de MapActivity een preview wil als mensen op een locatie klikken ipv dat ze er meteen heen gaan.

# Woensdag 12 juni

Vandaag stond in het teken van het laden van de CSV file en het automatisch plaatsen van markers op de kaart. Dit is uiteindelijk gelukt, na veel gezeik. Bij het laden van de csv file stuitte ik steeds op een ander problee, ik had de csv namelijk handgeschreven en dat leidde tot fouten bij het laden. De jongens in de groep hadden hier niet per se goede suggesties voor (ook niet achteraf). Gelukkig werkt nu wel alles in orde. De rest van de week staat in het teken van het doorgeven van het liftplek object naar de LiftActivity na het klikken van de marker. Daarna moet ik gaan kijken naar de database voor het handelen van de users. Het wordt misschien lastig om voor vrijdag de alpha versie af te hebben.

# Vrijdag 14 juni - Zondag 16 juni

Ik heb dit weekend in eerste instantie geprobeerd om de database goed werkend te krijgen. Dit is mij tot op heden vervelend genoeg niet gelukt. Toen heb ik besloten om dan maar eerst de mapsactivity naar liftactivity perfect te krijgen. Als men nu op een marker klikt gaat de user ook daadwerkelijk naar de LiftActivity van de liftspot in kwestie, dit is positief. Als foto bij de liftspot wil ik een google streetview foto van die locatie. De API die ik hiervoor gebruik is van google zelf, en is betaald. Ik heb mijn credit card gekoppeld aan m'n google account om dit werkend te krijgen maar krijg tot op heden alleen nog maar een zwart plaatje ipv de daadwerkelijke streetview.

# Maandag 17 juni

Vandaag is het eindelijk gelukt de streetview api werkend te krijgen! ik kan mij nu volledig gaan richten op het inloggen en registreren. Door brand op sciencepark vandaag geen standup meeting geweest.

# Dinsdag 18 juni

Het lukt me maar niet om een user te posten, lang kwam ik er niet achter waarom dit was. Uiteindelijk kwam ik erachter dat ik de oorspronkelijke structuur van de database had veranderd, waardoor de post method die geschreven is in rester.py niet meer afgestemd was op de structuur. Hier ben ik achter gekomen door aan Felix te vragen om in te loggen bij zijn cs50 ide, ik kon zo de oorspronkelijke structuur van de database bekijken. Voor morgen ben ik van plan om rester.py te herschrijven naar mijn data. Dan hoop ik morgen in ieder geval nieuwe gebruikers te kunnen toevoegen.

# Woensdag 19 juni

Vandaag een goede standup gehad. Ik heb bart kunnen helpen met zijn database problemen, en hij heeft mij geholpen met het login process. De Rester database is verre van ideaal voor het handlen van mijn data maar het is mij toch gelukt. Gebruikers kunnen nu inloggen, registreren en hun eigen account bekijken. Morgen ga ik ervoor zorgen dat ze hun account ook kunnen aanpassen (nieuwe username/wachtwoord) en ga ik ervoor zorgen dat drivers en lifters zich kunnen aanmelden bij een liftplek. De app is dan in principe af. Dan is het een kwestie van puntjes op de i zetten.

# Donderdag 20 juni

Vandaag heb ik het updaten van een user afgerond. Een gebruiker kan zijn account nu aanpassen. Verder heb ik een gebruiker meer informatie meegegeven. Ipv alleen een username en password omvat een account nu veel meer informatie (denk aan leeftijd, woonplaats  een bio etc.) Tijdens de standup blijkt dat Felix problemen heeft met z'n sql database, omdat ik zelf met volley werk heb ik hier helaas weinig tips over. Morgen ga ik ervoor zorgen dat drivers en lifters zich kunnen aanmelden bij een liftplek.

# Vrijdag 21 juni

Voor het aanmelden van drivers/lifters bij een liftplek is het noodzaak om de liftplekken ook naar rester te uploaden, je kan immers users niet lokaal aanmelden bij een liftplek. Mijn oplossing hiervoor is als volgt. Ik upload eenmalig alle liftplekken naar rester dmv LiftspotUploader, voor deze ene keer call ik die in de onCreate van DriverLiferActivity (die straks voor een ander doel wordt gebruikt), dat doe ik als volgt:

```
public class DriverLifterActivity extends AppCompatActivity implements LiftspotUploader.Callback {


    @Override
    public void postedLiftspotError(VolleyError error) {
        Toast.makeText(this, "Something went wrong ..", Toast.LENGTH_LONG).show();

    }

    @Override
    public void postedLiftspot(String response) {
        Toast.makeText(this, "Liftspots have been uploaded to database", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_lifter);

        //read the liftspots from csv file using csv helper
        InputStream inputStream = getResources().openRawResource(R.raw.liftplekken);
        CSVHelper csvFile = new CSVHelper(inputStream);
        final ArrayList<Liftspot> liftspots;
        liftspots = csvFile.read();
        for(Liftspot liftspot : liftspots) {
            LiftspotUploader upload = new LiftspotUploader(this, liftspot.getName(), liftspot.getRating(), liftspot.getType(), String.valueOf(liftspot.getLat()), String.valueOf(liftspot.getLon()), "", "");
            upload.sendLiftspot(this);
        }
    }

```
De liftspots staan dus nu in de database, en bovenstaande code is weer verwijderd uit DriverLifterActivity. In deze activity ga ik nu de liftspots aanpassen dmv LiftspotUpdater. Met aanpassen bedoel ik specifiek het toevoegen en verwijderen van drivers/lifters voor een bepaalde liftspot. De liftspot bevatten allemaal een drivers en lifters string, deze string bestaat uit userId's van gebruikers die zich voor die locatie hebben aangemeld als driver. D.m.v een PUT request worden deze strings aangepast als een driver/lifter zich af- of aanmeld.


