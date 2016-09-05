# AndroidTestApp
AndroidTestApp [WORK IN PROGRESS]

It's a simple 2 view application i made for a job candidature. 

It uses a MVP (Model View Presenter) with a "clean architecture".

It showcases the uses of several third party libraries like :
* [Dagger 2] (http://google.github.io/dagger/) for dependency injection.
* [RxAndroid] (https://github.com/ReactiveX/RxAndroid) to use Observers when retrieving the data.
* [ButterKnife] (http://jakewharton.github.io/butterknife/) Simple library to bind view elements to fields and avoid boilerplate code.
* [Picasso] (https://square.github.io/picasso/) Image loading and caching library.

The app parses the JSON files provided and display 
the information :

* [Game data file location] (https://dl.dropboxusercontent.com/s/1pe6mkfih0lbj72/gameData.json)
* [Header info file location] (https://dl.dropboxusercontent.com/s/6lzavrsohol21km/playerInfo.json)

Model:
The application should pull the latest version of the JSON from the url 
provided. The data retrieved should then be cached someway with an 
expiry date of 1 hour (the cache requirement is just for the 
gameData.json). The application will then use that data to populate the 
views.

View 1:
It should display a list of items using the value of data.name as the 
label. On clicking an item it should take you to View 2 which will 
display the details of the game.

View 2:
It should display the name, jackpot and date of the game, using best 
practices for locale formatting. Use currency provided in JSON to format 
jackpot.
Both the views must have a header showing an avatar image, player name, 
balance and last login
date which are retrieved by requesting the playerInfo.json. In View 2, 
last login date must be hidden.