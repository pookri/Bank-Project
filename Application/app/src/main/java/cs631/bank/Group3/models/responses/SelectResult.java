package cs631.bank.Group3.models.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SelectResult {
    
    String label;
    String value;

    public SelectResult(String label, String value){ 
        this.label = label;
        this.value = value;
    }

}
