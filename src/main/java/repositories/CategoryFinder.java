package repositories;

import java.util.List;
import models.Category;

public interface CategoryFinder {
    List<Category> findAllMain();
    List<Category> findAllPersonal();
}
