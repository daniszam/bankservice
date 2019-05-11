package ru.itis.darZam.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.darZam.models.*;
import ru.itis.darZam.models.dto.CategoryDto;
import ru.itis.darZam.models.dto.CategoryPercentDto;
import ru.itis.darZam.models.dto.MySpaceBalanceDto;
import ru.itis.darZam.services.CategoryService;
import ru.itis.darZam.services.TransactionService;
import ru.itis.darZam.services.UserService;
import ru.itis.darZam.utils.CircleStatistik;

import java.io.IOException;
import java.util.List;

@Controller
public class MySpace {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/mySpace")
    public String mySpace(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap modelMap){
        User user = userService.getById(userDetails.getUser().getId());
        List<CategoryPercentDto> categories = categoryService.getCategoryWithPercent(user);
        Integer remainder = CircleStatistik.getPercentByUpSum(user.getBalances());
        CategoryDto categoryDto = CircleStatistik.getRandomPercent(categories);

        modelMap.put("randomCategoryName", categoryDto.getName());

        modelMap.put("randomPercent", categoryDto.getSumOfCosts());
        modelMap.put("user", user);
        modelMap.put("category", categoryService.getAll());
        modelMap.put("percent", remainder);
        modelMap.put("categories",CircleStatistik.getPercentCategories(user.getTransactions()));

        return "mySpace";
    }

    @PostMapping(value = "/mySpace",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @SneakyThrows
    public String postMySpace(@RequestBody MySpaceBalanceDto mySpaceBalanceDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        transactionService.update(mySpaceBalanceDto);
        User user = userService.getById(userDetails.getUser().getId());

        ObjectMapper objectMapper = new ObjectMapper();
        String balances = objectMapper.writeValueAsString(user.getBalances());
        String percent = objectMapper.writeValueAsString(CircleStatistik.getPercentByUpSum(user.getBalances()));
        String categoryPercentMap = objectMapper.writeValueAsString(CircleStatistik.getPercentCategories(user.getTransactions()));
        String jsons = "[" + balances + "," + percent + "," + categoryPercentMap + "]";
        System.out.println(jsons);
        return jsons;
    }

    @Test
    public void whenSerializingUsingJsonRootName_thenCorrect()
            throws IOException {

        String test_json = "{\"type\": [{\"id\":\"1\"}], \"items\": [{\"id\":\"1\",\"sumOfCosts\":\"21\"}]}";
        System.out.println(test_json);
        ObjectMapper mapper = new ObjectMapper();
        MySpaceBalanceDto mySpaceBalanceDto = mapper.readValue(test_json, MySpaceBalanceDto.class);
        System.out.println(mySpaceBalanceDto);

    }

}
