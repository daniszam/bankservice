package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Icon {
    private Long id;
    private String path;
    private String name;
}
