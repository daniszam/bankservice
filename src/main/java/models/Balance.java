package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Balance{
    private Float balance;
    private String name;
    private Icon icon;
    private Long id;
    private User user;
}
