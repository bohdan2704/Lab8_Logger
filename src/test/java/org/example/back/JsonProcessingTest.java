package org.example.back;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.data.Bank;
import org.example.login.LoanUser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonProcessingTest {

    @Test
    void addUser() {
    }

//    @Test
//    void getUserList() {
//        JsonProcessing jsonProcessing = new JsonProcessing();
//        jsonProcessing.loadData();
//        List<LoanUser> userList = jsonProcessing.getUserList();
//
//        // Create a Gson instance
//        Gson gson = new Gson();
//        // Define the type of the collection you want to deserialize
//        Type listType = new TypeToken<List<LoanUser>>() {}.getType();
//
//        // Use Gson to parse the JSON file into a List of LoanUser objects
//        List<LoanUser> userListJustReadFromJson;
//        try (FileReader reader = new FileReader(jsonProcessing.getJsonUsersListPath())) {
//            userListJustReadFromJson = gson.fromJson(reader, listType);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Compare the two lists
//        assertIterableEquals(userList, userListJustReadFromJson);
//    }

    @Test
    void getBankList() {
        JsonProcessing jsonProcessing = new JsonProcessing();
        jsonProcessing.loadData();
        List<Bank> bankList = jsonProcessing.getBankList();

        // Create a Gson instance
        Gson gson = new Gson();
        // Define the type of the collection you want to deserialize
        Type listType = new TypeToken<List<Bank>>() {}.getType();

        // Use Gson to parse the JSON file into a List of Bank objects
        List<Bank> bankListJustReadFromJson;
        try (FileReader reader = new FileReader(jsonProcessing.getJsonBanksListPath())) {
            bankListJustReadFromJson = gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Step 3: Compare the two lists
        assertIterableEquals(bankList, bankListJustReadFromJson);
    }

    @Test
    void saveData() {
    }

    @Test
    void checkLogin() {
    }
}