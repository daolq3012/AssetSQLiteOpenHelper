## AssetSQLiteOpenHelper
[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by/4.0/)

This module supports migrating SQLite to Room Persistence Library, loads pre-populated SQLite database base on [albertogiunta/sqliteAsset](https://github.com/albertogiunta/sqliteAsset)

### How to config:

Step 1. Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Step 2. Add the dependency
```gradle
dependencies {
    implementation 'com.github.daolq3012:AssetSQLiteOpenHelper:V1.0.1'
    // Because AssetSQLiteOpenHelper releases are few and far between (persistence room version using in module is 1.0.0),
    // it is recommended you also explicitly depend on Persistence Room's latest version for bug fixes and new features.
    implementation 'android.arch.persistence.room:runtime:x.x.x'
}
```

### How to use:
1. Put your SQLite DB database_name.db into the ``assets/databases`` folder
2. in your ``AppDatabase`` class, modify your Room's DB creation code accordingly:
```java
    Room.databaseBuilder(context.getApplicationContext(),
                         AppDatabase.class,
                         "database_name.db")
    .openHelperFactory(new AssetSQLiteOpenHelperFactory())
    .build();
```
Alternatively, if you need precise control over the subfolder under ``assets/`` containing your sqlite database,
specify the subfolder path as an argument to ``AssetSQLiteOpenHelperFactory`` e.g. if your database is in
``assets/databases#lang_en`` you would use
```java
       Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "database_name.db")
       .openHelperFactory(new AssetSQLiteOpenHelperFactory("databases#lang_en"))
       .build();
```
- See samples in this project to show more

## 👬 Contribution

The Example are built using [Android studio](https://developer.android.com/studio/index.html)

- Open pull request with improvements
- Discuss ideas in issues
- Spread the word
- Reach out to me directly at dao.le.2511@gmail.com

## License

The content of this project itself is licensed under the Creative Commons Attribution 4.0 International (CC BY 4.0)
