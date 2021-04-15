module hu.cookerybook.core {
    requires lombok;
    requires java.sql;
    exports hu.cookerybook.core.dao;
    exports hu.cookerybook.core.model;
    exports hu.cookerybook.core.dbconn;
}