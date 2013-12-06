package com.eugenefe.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.deployment.HotDeploymentStrategy;
import org.jboss.seam.deployment.StandardDeploymentStrategy;
import org.jboss.seam.log.Log;

import com.eugenefe.jna.IPlainOption;

@Name("annotaionLoad")
@Scope(ScopeType.APPLICATION)
@Startup
public class AnnotationLoad {
		@Logger
		private Log log;
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
	
	   public static void main(String[] args){
		   Class klazz;
		   for(ENavigationData aa :ENavigationData.values()){
			   try {
//				klazz = Class.forName(aa.getShortName());
				   klazz = Class.forName(aa.getQualifiedName());
				if(klazz.isAnnotationPresent(AnnoNavigationFilter.class)){
					for(Method mm : klazz.getDeclaredMethods()){
						if(mm.isAnnotationPresent(AnnoMethodTree.class)){
//							System.out.println(klazz.getName() + ": Method :" + mm.getName());
//							System.out.println("Method :" + mm.getName());
							System.out.println( mm.getName()+"=" );
						}
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
		   }
//		   double pv = IPlainOption.pricer.BSOption(1, 100, 100, 0.1, 0.1, 0.03, 0.03, 0, 0.2);
//		   System.out.println("value: " + pv);
		   
		   
		   List<EMenuGroup> aa = EMenuGroup.valueOf("Product").getChidrenGroup();
		   for(EMenuGroup kk : aa){
			   System.out.println("EMenuGroup:"+ aa);
		   }
	   }
	   
	   
}
