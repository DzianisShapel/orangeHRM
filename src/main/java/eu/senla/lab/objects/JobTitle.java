package eu.senla.lab.objects;

import lombok.Data;

@Data
public class JobTitle {

    private String description;
    //private String note;
   // private String specification;
    private String title;

    public JobTitle(String description, String title) {
        this.description = description;
        this.title = title;
    }
}
