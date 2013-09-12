@FilterDefs({
		@org.hibernate.annotations.FilterDef(
				  name = "filterCurrentDatePortfolioDetail"
				, parameters = { @org.hibernate.annotations.ParamDef(name = "currentDate", type = "string")
				  			   }
		)
		,@org.hibernate.annotations.FilterDef(name = "filterCurrentDate"
			, parameters = { @org.hibernate.annotations.ParamDef(name = "currentDate", type = "string") 
							}
		)
		,@org.hibernate.annotations.FilterDef(
			  name = "filterBtwnDate"
			, parameters = {
				 @org.hibernate.annotations.ParamDef(name = "stBssd", type = "string")
				,@org.hibernate.annotations.ParamDef(name = "endBssd", type = "string") 
			  }
		)
//		,@org.hibernate.annotations.FilterDef(name = "filterEndDate"
//				, parameters = { @org.hibernate.annotations.ParamDef(name = "endBssd", type = "string") }) 
})
/**
 * 
 */
/**
 * @author takion77
 *
 */
package com.eugenefe.entity;

import org.hibernate.annotations.FilterDefs;

