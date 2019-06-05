
## Advanced sketches UI

<img src="https://github.com/dutchfarao/lifter/blob/master/doc/DesignDocument.png" width="900" height="450" />


### *API's*
- Maps SDK for android (https://developers.google.com/maps/documentation/android-sdk/map)
- Chat SDK for android (https://github.com/chat-sdk/chat-sdk-android)
- NotifcicationCompat API's (https://developer.android.com/training/notify-user/build-notification.html)

### *Data source*
- Liftplekken in nederland (http://www.franknature.nl/hitchhike/hitchhike.htm)


### *Database*
- SQLite

### *Classes*

| User                  |
|--------------------------|
| Rating: Float   |
| Name: String         |
| City: String             |
| Age: Int             |
| Car: String         |
| Bio: String              |
| Image: Bitmap            |
| ________________________ |
| Addtodatabase()  |
| Getters()                |
| Setters()                |



| Liftspot         |
|--------------------------|
| Name: String   |
| Image: Bitmap            |
| Rating: Float   |
| Drivers: Arraylist<>   |
| Lifters: Arraylist<>   |
| ________________________ |
| Setrating()              |
| AddDriver()              |
| RemoveDriver()          |
| AddLifter()              |
| RemoveLifter()          |


