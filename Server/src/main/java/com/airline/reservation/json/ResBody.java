package com.airline.reservation.json;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.airline.reservation.exception.ApplicationError;
public class ResBody {
    private Map<String, Object> values = new HashMap<>();
    private List<ApplicationError> errors = new ArrayList<>();
    public ResBody()
    {
    }
    public ResBody(Map<String, Object> values, List<ApplicationError> errors)
    {
        this.values = values;
        this.errors = errors;
    }

    public Map<String, Object> getValues()
    {
        return values;
    }

    public void setValues(Map<String, Object> values)
    {
        this.values = values;
    }

    public List<ApplicationError> getErrors()
    {
        return errors;
    }

    public void setErrors(List<ApplicationError> errors)
    {
        this.errors = errors;
    }
}
