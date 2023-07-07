# Apache Camel Basic Example - Json File Processing

This project demonstrates processing json files in Apache Camel.

The route created in `TotalRoute` listens for new files in the "numbers" directory. The files are expected to contain a json object with a list of numbers such as: 

```json
{
  "numbers": [1,2,3,4,5,6,7,8,9,10]
}
```

If the file can't be parsed it is moved to "error/". Otherwise, the number list is totaled and written to a file in "totals/" as json e.g:

```json
{
  "total": 55
}
```

After successful processing the original file is moved to "archive/".
