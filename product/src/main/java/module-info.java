module com.gofore.springmodulithdemo.product {
    exports com.gofore.springmodulithdemo.product.api;
    
    requires com.gofore.springmodulithdemo.notification;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires static lombok;
    
    opens com.gofore.springmodulithdemo.product.impl;
}

