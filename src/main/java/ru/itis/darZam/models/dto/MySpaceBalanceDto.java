package ru.itis.darZam.models.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.darZam.models.Category;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MySpaceBalanceDto {

    private List<CategoryDto> items;
    private List<BalanceUpdateDto> type;


    @JsonCreator
    public MySpaceBalanceDto(@JsonProperty("type") final List<BalanceUpdateDto> balanceIds,
                             @JsonProperty("items") final List<CategoryDto> items){
        this.type = balanceIds;
        this.items = items;
    }


}



