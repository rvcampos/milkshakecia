package br.comvizzatech.teste.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
  
/** 
 * Logging producer for injectable log4j logger 
 * 
 * @author cem ikta 
 */  
public class LoggerProducer {  
   /** 
    * @param injectionPoint 
    * @return logger 
    */  
    @Produces  
    public Logger produceLogger(InjectionPoint injectionPoint) {  
        return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass().getName());  
    }  
} 