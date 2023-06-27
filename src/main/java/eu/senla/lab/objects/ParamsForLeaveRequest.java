package eu.senla.lab.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamsForLeaveRequest {

    private String empNumber;
    private String fromDate;
    private String toDate;
    private String includeEmployees;
    @JsonProperty("statuses[]")
    private String statuses;
}
