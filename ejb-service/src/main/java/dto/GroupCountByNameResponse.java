package dto;

public record GroupCountByNameResponse(String groupName, Long count) {

    // You may want to override equals, hashCode, and toString methods as well
}
