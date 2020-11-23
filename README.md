# Unit Converter App

## About this app
This application uses the play framework to convert a user given units into SI units. The layout of this application (play framework applications in general) can be found here https://www.playframework.com/documentation/2.8.x/Anatomy.

The endpoint is defined under conf/routes. 

The backend codes are at app/controllers. The unit input are checked first to make sure parenthesis and operators are properly defined. The units are also checked while parsing. If any units not in the input provided are given, 'IllegalArgumentException' is raised and exact message relayed from the application. 

The test codes are at tests/controllers. Implementation for return status, return type in json, unit string checkers, and application return containing both string and double with desired precision are tested. 


## Run the application on a command line
```
./sbt run
```

##Test the application

```
./sbt test
```

## Docker Build

```
docker build -t citrine/unit-converter:1.0.0 .
```

## Docker Run

```
docker run -p 9000:9000 citrine/unit-converter:1.0.0
```

## Local Run
```
http://localhost:9000/units/si?units=(degree/min)
```
