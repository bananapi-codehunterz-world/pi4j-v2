
 Pi4J :: Java I/O Library for Raspberry Pi
==========================================================================

Build state: ![GitHub Actions build state](https://github.com/pi4j/pi4j-v2/workflows/Continious Integration/badge.svg)

[![License](https://img.shields.io/github/license/pi4j/pi4j-v2)](http://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pi4j/pi4j-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pi4j/pi4j-core)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/com.pi4j/pi4j-core?server=https%3A%2F%2Foss.sonatype.org)](https://oss.sonatype.org/#nexus-search;gav~com.pi4j~~~~)
[![Site](https://img.shields.io/badge/Website-v2.pi4j.com-lightgrey)](https://v2.pi4j.com)
[![Docs](https://img.shields.io/badge/Docs-v2.pi4j.com-blue)](https://v2.pi4j.com/documentation)
[![Twitter Follow](https://img.shields.io/twitter/follow/pi4j?label=Pi4J&style=social)](https://twitter.com/pi4j)

---

## DISCLAIMER:: EXPERIMENTAL VERSION

### This repository is an experimental and incomplete work-in-progress for the next major version of Pi4J.  Do not try using this project, it will not work at this time.

### !! NOT READY FOR PRODUCTION USE !!

---

## PROJECT INFORMATION for V2 of Pi4J

Project website: [v2.pi4j.com](https://v2.pi4j.com/).

![Pi4J V2 architecture](assets/draw.io/pi4j-v2-architecture.jpg)

* Pi4J V2 Discussions (replacing the Forum) (*new*): [github.com/Pi4J/pi4j-v2/discussions](https://github.com/Pi4J/pi4j-v2/discussions)
* Pi4J V2 issues: [github.com/Pi4J/pi4j-v2/issues](https://github.com/Pi4J/pi4j-v2/issues)

Builds are available from:

*  [Release builds from Maven Central](http://search.maven.org/#search%7Cga%7C1%7Ccom.pi4j)
*  [V1 Downloads](https://pi4j.com/download.html)
*  [Snapshot builds from Sonatype OSS](https://oss.sonatype.org/index.html#nexus-search;quick~pi4j)
*  [APT/PPA Package Repository downloads](https://github.com/Pi4J/download)

Copyright (C) 2012-2021 Pi4J

## BUILD INSTRUCTIONS

The Pi4J V2 codebase can be built using [Apache Maven 3.6.x](https://maven.apache.org/). and [Java JDK 11](https://openjdk.java.net/).
The following command can be used to build the Pi4J V2 JARs:

```
mvn clean install
```

Pi4J V2 also includes native libraries that will need to be compiled if you are modifying any native code.
Most users will never need to compile the native libraries as these artifacts are automatically downloaded  
when building the Pi4J JARs from Maven repositories. One of the following commands can be used to build 
the Pi4J V2 JARs and Native Libraries:

```
mvn clean install -Pnative
mvn clean install -Pnative,docker
```

> **NOTE:** A comprehensive set of build instructions can be found in the [Pi4J V2 Documentation](https://v2.pi4j.com/architecture/about-the-code/build-instructions/).

## CONTRIBUTING TO PI4J

For full description of the code structure, how to compile... see 
the ["About the code" on our website](https://v2.pi4j.com/architecture/about-the-code/).

![Pi4J V2 code structure](assets/draw.io/pi4j-v2-code-structure.jpg)

### Adding a feature or solving a problem

If you have and idea to extend and improve Pi4J, please first create a ticket to discuss how 
it fits in the project and how it can be implemented. 

If you find a bug, create a ticket, so we are aware of it and others with the same problem can 
contribute what they already investigated. And the quickest way to get a fix? Try to search for 
the cause of the problem or even better provide a code fix!
    
### Join the team

You want to become a member of the Pi4J-team? Great idea! Send a short message to frank@pi4j.com 
with your experience, ideas, and what you would like to contribute to the project.

## LICENSE

 Pi4J Version 2.0 and later is licensed under the Apache License,
 Version 2.0 (the "License"); you may not use this file except in
 compliance with the License.  You may obtain a copy of the License at:
      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

## PROJECT OVERVIEW

Starting with the Pi4J 2.0 builds, the Pi4J project is prioritizing focus
on providing Java programs access, control and communication with the core
I/O capabilities of the Raspberry Pi platform.  

**LINK BELOW TO BE CHANGED TO pi4j.com WHEN NEW SITE IS PUBLISHED**

Read all about it on [v2.pi4j.com](https://v2.pi4j.com/).

## IN DEVELOPMENT

2021-01-26 :: 2.0-SNAPSHOT

For more info see the new website _["What’s New in 2.0?"](https://v2.pi4j.com/about/new-in-v2/)_.

  * Changed project to Apache License, Version 2.0
  * Removed `pi4j-device` library.  
  * Removed `pi4j-gpio-extension` library.
  * Removed platform support for `Odroid`, `BananaPi`, `BPi`, `NanoPi`, `OrangePi`.
  * Added support for 32-bit (`armhf`) and 64-bit (`aarch64`) distributions.
  * Added support for native builds using Pi4J Docker Build images.
  
## PREVIOUS RELEASES

For previous 1.x release notes and source code, please see the 1.x branch
or release tags in the source repository:

  * **Releases**: [github.com/Pi4J/pi4j/releases](https://github.com/Pi4J/pi4j/releases)
  * **Source Code**: [github.com/Pi4J/pi4j/branches](https://github.com/Pi4J/pi4j/branches)
