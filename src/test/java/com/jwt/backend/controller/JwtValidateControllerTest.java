package com.jwt.backend.controller;

import com.jwt.backend.service.JwtValidateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class JwtValidateControllerTest {
    @Mock
    private JwtValidateService jwtValidateService;
    @InjectMocks
    private JwtValidateController jwtValidateController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldReturnTrueForAValidToken() throws Exception {
        lenient().when(jwtValidateService.validateJwtPayload(any())).thenReturn("verdadeiro");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/jwt-validate")
                        .content("{\"jwtWebToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("verdadeiro"));
    }

    @Test
    void itShouldReturnFalseForAInvalidToken() throws Exception {
        lenient().when(jwtValidateService.validateJwtPayload(any())).thenReturn("falso");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/jwt-validate")
                        .content("{\"jwtWebToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY\"}")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("falso"));
    }

    @Test
    void itShouldReturnTrueForCase1() throws Exception {
        String validJwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/jwt-validate")
                        .content("{\"jwtWebToken\": \"" + validJwtToken + "\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("verdadeiro"));
    }

    @Test
    void itShouldReturnFalseForCase2() throws Exception {
        String invalidJwtToken = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/jwt-validate")
                        .content("{\"jwtWebToken\": \"" + invalidJwtToken + "\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("falso"));
    }

    @Test
    void itShouldReturnFalseForCase3() throws Exception {
        String invalidNameJwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/jwt-validate")
                        .content("{\"jwtWebToken\": \"" + invalidNameJwtToken + "\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("falso"));
    }


    @Test
    void itShouldReturnFalseForCase4() throws Exception {
        String extraClaimJwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/jwt-validate")
                        .content("{\"jwtWebToken\": \"" + extraClaimJwtToken + "\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("falso"));
    }
}