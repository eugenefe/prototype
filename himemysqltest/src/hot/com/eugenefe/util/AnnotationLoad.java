package com.eugenefe.util;

import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.deployment.HotDeploymentStrategy;
import org.jboss.seam.deployment.StandardDeploymentStrategy;

@Name("annotaionLoad")
@Scope(ScopeType.APPLICATION)
@Startup
public class AnnotationLoad {
//	   @In("#{deploymentStrategy.annotatedClasses['com.eugenefe.util.AnnoMethodOrder']}")
//	   private Set<Class<Object>> fooClasses;
//	   
//	   @In("#{hotDeploymentStrategy.annotatedClasses['com.eugenefe.util.AnnoMethodOrder']}")
//	   private Set<Class<Object>> hotFooClasses;
	   
	   @In("#{deploymentStrategy}")
	   private StandardDeploymentStrategy  deploymentStrategy;
	   
	   @In("#{hotDeploymentStrategy}")
	   private HotDeploymentStrategy  hotDeploymentStrategy;
	   
	   @Create
	   public void create() {
//		  deploymentStrategy.getAnnotatedClasses(); 
//	      for (Class clazz : fooClasses) {
//	         handleClass(clazz);
	    	  
//	      }
//	      for (Class clazz : hotFooClasses) {
//	         handleClass(clazz);
//	      }
	   }
	
}
