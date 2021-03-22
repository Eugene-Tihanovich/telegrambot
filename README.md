# Touristic Telegrambot
To run the application, you need:
1. download/сlone it to your computer
2. open it in IntelliJ IDEA
3. set bot name (BOT_NAME) and token (BOT_TOKEN) to environment variables
BOT_NAME = pony_marmeladka_bot  
BOT_TOKEN = 1753410733:AAHDcslLGP36gMPAnyfVeeqyd7rpFW2-0Kk  
4. click «Run TouristicTelegrambotApplication»
5. find pony_marmeladka_bot in telegram and run it
6. write Minsk and see what happens

The bot uses database MySQL and has 10 pre-installed cities with descriptions (see changelog-city.xml):
Minsk ---> Advise you to go to The Opera and Ballet Theatre. Believe me, you will like it.
Moscow ---> Do not forget to visit Red Square. But you do not have to go to TSUM)))
Kiev ---> You must see the Motherland. The height of the monument reaches 102 meters, weight - 450 tons.
Warsaw ---> Walk through the old town and visit Copernicus Science Center. This place is not only popular for children)))
Vilnius ---> Visit the Gediminas Tower and then taste the national cuisine. After such a walk, you will definitely want to eat.
Brussels ---> There are always a lot of tourists around Manneken Pis. Remember this. Also know that Brussels has the tastiest fries.
Amsterdam ---> Amsterdam is a city of sins. Go to Casa Rosso theater. Believe me, there is something to see)))
Rome ---> One day is not enough to see all the sights in Rome. However, I advise you to start your journey in Colosseum.
Prague ---> Hope you are in excellent health. The local beer is delicious. In Prague bar-hopping is like a journey.
Berlin ---> Visit the Reichstag, have a beer, enjoy the local cuisine. Eh, take me with you)))
Write to the bot 1 of 10 cities and see the corresponding description.

Data about cities is managed through a REST webservice (http://localhost:8000/swagger-ui/#/)
