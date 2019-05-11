package ru.itis.darZam.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VkAuthUser {
    private String accessToken;
    private int expiresIn;
    private int userId;
    private String email;
}
