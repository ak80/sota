# sota
VERY basic support for streams

Sota (Pali for "stream") is a very simple and basic implementation of streams, providing some of the functions
of java.util.stream from Java 8.

Many methods are missing and some additional are provided: this is not intended as a full replacement. When a method 
exists with the same name as provided by Java 8 streams, then it has the same signature and aims to be compatible.

There is no special optimization besides that the streams are lazy fetching. Parallelism is not supported!

Please also note that Java 8 lambdas are needed or a retrofitting like RetroLambda

This project is useful for a current GWT 2.8.x projects.

For a full backport see http://sourceforge.net/projects/streamsupport/

## Use with GWT 2.8

You need to inherit the module:

```
  <inherits name="org.ak80.sota.Sota" />
```

and download the source dependency also, e.g. for maven


    <dependency>
      <groupId>org.ak80</groupId>
      <artifactId>sota</artifactId>
      <version>version</version>
      <classifier>sources</classifier>
      <scope>provided</scope>
    </dependency>
