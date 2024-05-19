/**
 * 
 */
/**
 * 
 */
module proyecto_pa {
	requires java.desktop;
	requires java.sql;
	requires com.google.gson;
    requires jasperreports;
    
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    
    requires commons.logging;
    
    requires org.apache.commons.collections4;
    requires commons.digester;

	opens proyecto_pa.Modelos to com.google.gson;
}