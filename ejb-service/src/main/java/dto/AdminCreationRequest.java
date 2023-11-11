package dto;


import java.time.LocalDateTime;

public record AdminCreationRequest(String name, LocalDateTime birthday, Long height, Float weight, String passportID) {

}
