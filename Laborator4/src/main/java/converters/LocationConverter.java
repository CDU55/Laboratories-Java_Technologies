package converters;

import DataLayer.Location;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converters.LocationConverter")
public class LocationConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        String[] locationData=s.replaceAll(" *","").split(",");
        Location location=new Location();
        location.setId(Integer.parseInt(locationData[0]));
        location.setName(locationData[1]);
        return location;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Location location=(Location)o;
        return "Location "+location.getName()+" with Id "+location.getId();
    }
}
