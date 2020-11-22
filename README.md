# Localization

[![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
![API](https://img.shields.io/badge/Min--SDK-21-yellowgreen)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Localization can support multiple languages on your android application.

![](images/localization_sample.gif)

## Setup

To import the Localization library to Android Studio, use the method
below


1. Open your project in Android Studio
2. Download the library (using Git, or a zip archive to unzip)
3. Go to File >New > Import Module and import the library as a module
4. Right-click your app in project view and select "Open Module Settings"
5. Click the "Dependencies" tab and then the '+' button
6. Select "Module Dependency"
7. Select "Localization"

## Feature
* Detects Right-To-Left (RTL) languages and updates layout direction.
* Current locale and language will save to `SharedPreference` automatically.
* Easy to use it.


## Usage
How to use

1. Call **Localization.initialize()** method in Application class of consumer APP to initialize localization
```kotlin
   Localization.initialize(this)
```

2. Call **Localization.onAttach()** method in consumer activity or in your base activity to notify it with updated resources.
```kotlin
  override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Localization.onAttach(this, newBase))
    }
```
And pass current activity and new base context to Localization.onAttach() method.

3. Call **Localization.setLocaleAndRestart()** to change app language with new locale and restart.
```kotlin
   Localization.setLocaleAndRestart(
            currentActivity,
            newLocale,
            destinationActivityClass,
            bundle,
            flags 
        )
```

* **Params**
    * **currentActivity** : Current activity and it is **required**
    * **newLocale** : New locale will be applied and it is **required**
    * **destinationActivityClass** : The new activity will be redirect after change locale and it is **required**
    * **bundle** : If you want to pass data between currentActivity and destinationActivityClass and it is **optional**
    * **flags** : The inten intentt flags and it is **opational**

4. Call **Localization.reverseLangAndRestart()** method to reverse the language, if the previous language was Arabic, then it will be modified to English the vice versa

```kotlin
   Localization.reverseLangAndRestart(
        currentActivity,
        destinationActivityClass,
        bundle,
        flags
    )
```
* **Params**
    * **currentActivity** : Current activity and it is **required**
    * **destinationActivityClass** : The new activity will be redirect after change locale and it is **required**
    * **bundle** : If you want to pass data between currentActivity and destinationActivityClass and it is **optional**
    * **flags** : The inten intentt flags and it is **opational**

5. Call Localization.setLocale() method to handle the language and configuration changes, but leaves the application restart to the consumer app
```kotlin
   Localization.setLocale(
       context, 
       newLocale
    )
```
* **Params**
    * **context** : The current context and it is **required**
    * **newLocale** : New locale will be applied and it is **required**


6. Call **Localization.getLocale()** method to get current saved locale
```kotlin
    val currentSavedLocale = Localization.getLocale()
```

7. Call **Localization.getLanguage()** method to get current saved language
```kotlin
    val currentSavedLanguage = Localization.getLanguage()
```

## License
Copyright 2020 Link Development

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


