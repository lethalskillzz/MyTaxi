# MyTaxi

### Setup Requirements

- Java 8
- Latest version of Android SDK and Android Build Tools


### API Key

The app requires an API key to access Google Maps. You must provide your own [API key][1] in order to build the app.

Just put your API key into `google_maps_api.xml` file (create the file if it does not exist already):

```xml
<string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">
        YOUR_API_KEY_HERE
    </string>
```

### Testing

This project integrates a combination of [local unit tests][2] and [instrumented tests][3].


[1]: https://developers.google.com/console
[2]: app/src/test/
[3]: app/src/androidTest/