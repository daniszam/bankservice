package servlets;

import context.ApplicationDiContext;
import context.Contexts;
import models.Category;
import repositories.CategoryRepository;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class SearchCategoryServlet extends HttpServlet {
    private CategoryRepository categoryRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ftl/searchCategory.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category =request.getParameter("category");
        List<Category> categoryList =categoryRepository.findByName(Category.builder()
                .name(category)
                .build());
        System.out.println(categoryList);
        request.setAttribute("categories", categoryList);
        request.getRequestDispatcher("/WEB-INF/ftl/searchCategory.ftl").forward(request, response);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationDiContext applicationContext = Contexts.primitive();

        categoryRepository = applicationContext.getComponent(CategoryRepository.class);
    }
}
