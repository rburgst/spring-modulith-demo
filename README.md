# Java Modularization approaches

This repository contains various approaches to modularizing Java applications. 
Note that the code is simple on purpose to focus on the modularization aspects.

Please check out the individual [branches](https://github.com/rburgst/spring-modulith-demo/branches) 
for the different approaches.

Also check out the tests.

## Getting started

To run the application, you need to have Java 21 or higher installed.

You can run the application using Gradle:

```bash
./gradlew bootRun
```

or run the tests using:

```bash
./gradlew test
```

Also test out whether modularization works by trying to import protected stuff from another module
(e.g. import `com.gofore.springmodulithdemo.notification.impl.NotificationType` 
from `com.gofore.springmodulithdemo.product.impl.ProductServiceImpl`).

Try this out for the various approaches.