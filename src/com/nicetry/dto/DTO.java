package com.nicetry.dto;


import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.buybig.Constants;
import org.apache.commons.lang3.builder.HashCodeBuilder;
public class DTO implements Serializable {
    private static final long serialVersionUID = 1L;
 
    protected DTO() {
        super();
    }
    public boolean equals(Object obj) {
        boolean equals = this == obj;
        if(!equals) {
            equals = obj != null
                && obj instanceof DTO;
        }
        return equals;
    }
    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(15,
            5);
        return hashCodeBuilder.toHashCode();
    }
	 public String toString() {
	        String string = Constants.NEW_LINE.toString()
	            + Constants.OPENING_BRACE.toString();
	        try {
	            final BeanInfo beanInfo = Introspector.getBeanInfo(getClass());
	            final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	            final List<PropertyDescriptor> properties = Arrays.asList(propertyDescriptors);
	            String propertyName = null;
	            Method getter = null;
	            for(PropertyDescriptor property : properties) {
	                if(!property.getPropertyType().isInstance(Collections.EMPTY_SET)) {
	                    string += Constants.NEW_LINE.toString()
	                        + Constants.SPACE.toString()
	                        + Constants.SPACE.toString();
	                    try {
	                        if(property instanceof IndexedPropertyDescriptor) {
	                            getter = ((IndexedPropertyDescriptor) property).getIndexedReadMethod();
	                        } else {
	                            getter = property.getReadMethod();
	                        }
	                        propertyName = property.getName();
	                        string += propertyName
	                            + Constants.SPACE.toString()
	                            + Constants.EQUALS.toString()
	                            + Constants.SPACE.toString()
	                            + getter.invoke(this,
	                                (Object) null)
	                            + Constants.COMMA.toString();
	                    } catch(NullPointerException nullPointerException) {
	                        // Nothing to do.
	                    } catch(IllegalAccessException illegalAccessException) {
	                        // Nothing to do.
	                    } catch(IllegalArgumentException illegalArgumentException) {
	                        // Nothing to do.
	                    } catch(InvocationTargetException invocationTargetException) {
	                        // Nothing to do.
	                    }
	                }
	            }
	        } catch(NullPointerException nullPointerException) {
	            // Nothing to do.
	        } catch(IntrospectionException introspectionException) {
	            // Nothing to do.
	        }
	        string += Constants.NEW_LINE.toString()
	            + Constants.CLOSING_BRACE.toString();
	        return string;
	    }
}
