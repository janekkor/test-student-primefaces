package de.onsite.quickstart.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import de.onsite.quickstart.model.ItemList;

@FacesConverter(value = "itemBasketConverter")
public class ItemBasketConverter implements Converter<ItemList> {
	
	@Override
	public ItemList getAsObject(FacesContext context, UIComponent component, String value) {
		ItemList result = new ItemList(123L, "Converter List");
		
	    if (component instanceof SelectOneMenu) {
	    	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	    	@SuppressWarnings("unchecked")
			List<ItemList> itemBasketList = (List<ItemList>)(List)session.getAttribute("itemBaskets");
	        //itemBasketList.forEach(basket -> basket.getListName().startsWith(value.substring(0, value.indexOf("_")))?basket:result);
	        for (ItemList basket : itemBasketList) {
				if (basket.getListName().equals(value)) {
					return basket;
				}
			}
	    }
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, ItemList value) {
		return value.getListName();
	}



}
