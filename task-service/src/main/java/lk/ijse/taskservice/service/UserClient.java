package lk.ijse.taskservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean checkUserExists(String emailReq){

        try{

            String url = "http://localhost:8081/api/v1/user/checkUser";

            Map<String,String> request = new HashMap<>();
            request.put("email", emailReq);

            Boolean exists = restTemplate.postForObject(url, request, Boolean.class);

            return Boolean.TRUE.equals(exists);

        }catch (Exception e){
            return false;
        }
    }}