package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class Cash extends Balance {

    @Builder
    public Cash(String name, String icon, Float balance, Long id, User user){
        super(balance, name, icon,id, user);
    }
}
