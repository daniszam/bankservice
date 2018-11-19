package utils;

import lombok.Builder;
import lombok.Data;
import models.Category;

@Builder
@Data
public class CategoryUtil {
    private Category category;
    private Float percent;
    private Float sum;
}
