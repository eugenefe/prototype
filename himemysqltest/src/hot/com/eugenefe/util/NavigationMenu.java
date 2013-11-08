package com.eugenefe.util;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.web.RequestParameter;
import org.primefaces.component.tieredmenu.TieredMenu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
//import org.primefaces.model.menu.DefaultMenuItem;
//import org.primefaces.model.menu.DefaultMenuModel;
//import org.primefaces.model.menu.DefaultSubMenu;
//import org.primefaces.model.menu.MenuElement;
//import org.primefaces.model.menu.MenuItem;
//import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.MenuModel;

import com.eugenefe.entity.IrCurve;


@Name("navigationMenu")
@Scope(ScopeType.SESSION)
@Startup
public class NavigationMenu {
	private MenuModel menuModel;
	private DefaultSubMenu subMenu;

	
	public NavigationMenu() {
//		System.out.println("Construction NavigationMenu");
	}
	
	@Create
	public void create(){
		menuModel = new DefaultMenuModel();
		subMenu= new DefaultSubMenu();
		DefaultMenuItem  item ;
	
		subMenu.setLabel("AAA");
		
	
		for(ENavigationData navi: ENavigationData.values()){
			item  = new DefaultMenuItem();
			item.setValue(navi.getShortName());
			item.setTitle(navi.getQualifiedName());

			item.setParam("navigation", navi.name());
			item.setIncludeViewParams(true);

			item.setOutcome("/view/v900DataNavigation");

			subMenu.addElement(item);
		}
		menuModel.addElement(subMenu);
	}

	
	
//	****************Getter and Setter***********
	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public DefaultSubMenu getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(DefaultSubMenu subMenu) {
		this.subMenu = subMenu;
	}
}
