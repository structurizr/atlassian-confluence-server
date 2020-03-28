# Structurizr macros for Atlassian Confluence Server

This repository contains the source code for some macros that embed public, private and Express diagrams from Structurizr into Atlassian Confluence Server. See [Atlassian Confluence](https://structurizr.com/help/atlassian-confluence) on the Structurizr website for more details.

## Binaries

 - [structurizr-confluence-2.1.0.jar](dist/structurizr-confluence-2.1.0.jar)
 
 ## Building from source
 
 To build the macro from the source, you will need:
 
  - [Java 8 SDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
  - [Atlassian Plugin SDK](https://developer.atlassian.com/server/framework/atlassian-sdk/downloads/)
  
You will then need to setup your build environment; for example:
  
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/
export ATLASSIAN_HOME=/Users/simon/sandbox/atlassian-plugin-sdk-8.0.16/
export PATH=$ATLASSIAN_HOME/bin/:$PATH
```

And to build:

```
atlas-mvn package
```

If successful, you will see a file called ```structurizr-confluence-x.y.z.jar``` in the ```target``` directory.