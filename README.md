# Word Finder Back End

The back-end code for a Word Finder application. This component exposes a REST API that returns the grid, placed words, and any words that could not be placed due to space constraints, all as JSON.

Currently, six word placement types are supported:

<ol>
<li>Horizontal (left to right)</li>
<li>Reverse Horizontal (right to left)</li>
<li>Vertical (top to bottom)</li>
<li>Reverse Vertical (bottom to top)</li>
<li>Diagonal (top left to bottom right)</li>
<li>Diagonal (top right to bottom left)</li>
</ol>

The next version will introduce additional diagonal word placement options.

## Getting Started

These instructions will help you set up a local development environment.

### Prerequisites

None


### Installation

Follow these steps to install and run the application:

```
mvn clean install
mvn spring-boot:run
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



