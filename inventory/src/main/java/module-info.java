module com.gofore.springmodulithdemo.inventory {
    exports com.gofore.springmodulithdemo.inventory.api;
    
    requires spring.context;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires static lombok;
    
    opens com.gofore.springmodulithdemo.inventory.impl;
}

