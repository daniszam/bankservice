package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UUIDUser {
    private User user;
    private String uuid;
}
