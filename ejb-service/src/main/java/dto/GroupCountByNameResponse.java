package dto;

import java.io.Serializable;

public record GroupCountByNameResponse(String groupName, Long count) implements Serializable {

    // You may want to override equals, hashCode, and toString methods as well
}
