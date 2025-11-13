module com.gofore.springmodulithdemo.notification {
    exports com.gofore.springmodulithdemo.notification.api;
    
    requires spring.context;
    requires org.slf4j;
    requires static lombok;
    
    opens com.gofore.springmodulithdemo.notification.impl to spring.core, spring.beans, spring.aop;
}

