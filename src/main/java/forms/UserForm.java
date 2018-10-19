package forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String gender;
    private Date birthday;

}

