# Portfolio App
Time to build the app 14 hours approximately.

## Technologies Used
* [Bootstrap](https://getbootstrap.com/)
* [Vue.js](https://vuejs.org/)
* [Spring Framework](https://spring.io/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Twitter4J](http://twitter4j.org/en/)
* [JUnit](https://junit.org/)
* [Mockito](https://site.mockito.org/)
* [git](https://git-scm.com/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Software Prerequisites
* Java 11
* Maven
* MySql Database
* Twitter developer account

### Steps to Build the App
```
 git clone https://github.com/nrtapia/portfolio.git
```

```
 cd into portfolio
```

```
./mvnw spring-boot:run
```

```
open a browser on http://localhost:8080
```

### Api Endpoints

#### Query Portfolio Information   
```
curl -i -X GET 'http://localhost:8080/portfolio/100'
```
```
{
    "id": 100,
    "photo": "https://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_400x400.jpg",
    "title": "Juan Nieve! 100",
    "description": "Lord Commander of the Night's Watch and King of the Free Folk",
    "twitterUsername": "LordSnow",
    "timeline": [
        {
            "id": 1255207820527636480,
            "photo": "http://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_mini.jpg",
            "name": "Jon Snow",
            "description": "Get these awesome GoT Masks now with Free Worldwide shipping 😍\n\nClick to shop now  👉 https://t.co/ImOU0y516D\n\nMore than 60 different designs 😍 https://t.co/q33WWqSlFO"
        },
        {
            "id": 1255106871746334720,
            "photo": "http://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_mini.jpg",
            "name": "Jon Snow",
            "description": "https://t.co/7yQNUhyoxZ"
        },
        {
            "id": 1254350609341394945,
            "photo": "http://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_mini.jpg",
            "name": "Jon Snow",
            "description": "https://t.co/DjJzNRtA7Y"
        },
        {
            "id": 1253647973260673025,
            "photo": "http://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_mini.jpg",
            "name": "Jon Snow",
            "description": "https://t.co/zIUiLfDLF8"
        },
        {
            "id": 1252920475396780032,
            "photo": "http://pbs.twimg.com/profile_images/901947348699545601/hqRMHITj_mini.jpg",
            "name": "Jon Snow",
            "description": "https://t.co/aNQEhwJeSf"
        }
    ]
}
```


#### Update Portfolio Information   
```
curl -i -X PUT \
   -H "Content-Type:application/json" \
   -d \
'{
 "photo": "https://www.ungeekencolombia.com/wp-content/uploads/2014/06/nairoman.jpg",
 "title": "Nairo Quintana",
 "description": "Nairo Alexander Quintana Rojas, ODB, (born 4 February 1990) is a Colombian racing cyclist, who currently rides for UCI ProTeam Arkéa–Samsic.[5]",
 "twitterUsername": "NairoQuinCol"
}' \
 'http://localhost:8080/portfolio/14'
```

```
{
    "id": 14,
    "photo": "https://www.ungeekencolombia.com/wp-content/uploads/2014/06/nairoman.jpg",
    "title": "Nairo Quintana",
    "description": "Nairo Alexander Quintana Rojas, ODB, (born 4 February 1990) is a Colombian racing cyclist, who currently rides for UCI ProTeam Arkéa–Samsic.[5]",
    "twitterUsername": "NairoQuinCol"
}
```