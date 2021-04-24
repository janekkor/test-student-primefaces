package de.onsite.quickstart.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import de.onsite.quickstart.model.Basket;

@FacesConverter(value = "itemBasketConverter")
public class ItemBasketConverter implements Converter<Basket> {
	
	@Override
	public Basket getAsObject(FacesContext context, UIComponent component, String value) {
		Basket result = new Basket(123L, "Converter List");
		
	    if (component instanceof SelectOneMenu) {
	    	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	    	@SuppressWarnings("unchecked")
			List<Basket> itemBasketList = (List<Basket>)(List)session.getAttribute("itemBaskets");
	        //itemBasketList.forEach(basket -> basket.getListName().startsWith(value.substring(0, value.indexOf("_")))?basket:result);
	        for (Basket basket : itemBasketList) {
				if (basket.getBasketName().equals(value)) {
					return basket;
				}
			}
	    }
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Basket value) {
		return value.getBasketName();
	}



}
