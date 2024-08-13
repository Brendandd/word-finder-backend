# Word Finder Backend

The back-end code for a word finder application.  Exposes
a REST API which returns the grid, placed words and non placed words (due to space) as JSON.
<br/>
<br/>
Currently has 4 word placer types:
* Horizontal (left to right)
* Reverse Horizontal (right to left)
* Vertical (top to bottom)
* Reverse Vertical (bottom to top)
<br/>
<br/>
The next version will place words diagonally.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development purposes.

### Prerequisites

None


### Installation

A step by step guide that will tell you how to get the development environment up and running.

```
mvn clean install
cd target
java -jar word-finder-0.0.1-SNAPSHOT.jar
```


## Usage

To test the REST API the easiest way is in Postman

URL

```
http://localhost:8080/wordfinder
```

Request Body

```
{
    "rows" : 15,
    "columns" : 20,
    "words" : ["SWIMMING", "RUNNING", "GYMNASTICS","JUDO","CYCLING","HIGHJUMP","SKATEBOARD"]

}
```



