module com.gofore.springmodulithdemo.app {
    requires com.gofore.springmodulithdemo.product;
    requires com.gofore.springmodulithdemo.inventory;
    requires com.gofore.springmodulithdemo.notification;
    
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires spring.aop;
    requires static lombok;
    
    opens com.gofore.springmodulithdemo;
}

