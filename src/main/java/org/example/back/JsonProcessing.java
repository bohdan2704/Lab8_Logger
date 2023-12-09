package org.example.back;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.data.Bank;
import org.example.login.LoanUser;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JsonProcessing {
    private static final Logger logger = LogManager.getLogger(JsonProcessing.class);

    private final List<Bank> bankList;
    private final List<LoanUser> userList;
    private final String jsonUsersListPath;
    private final String jsonBanksListPath;

    public JsonProcessing() {
        logger.trace("JsonProcessing constructor started");
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            jsonUsersListPath = new File(classLoader.getResource("data/users.json").toURI()).getPath();
            jsonBanksListPath = new File(classLoader.getResource("data/banks.json").toURI()).getPath();
        } catch (Exception e) {
            throw new RuntimeException("Error when loading files", e);
        }

        this.bankList = new ArrayList<>();
        this.userList = new ArrayList<>();
        logger.trace("JsonProcessing constructor ended");
    }

    public JsonProcessing(String jsonUsersListPath, String jsonBanksListPath) {
        logger.trace("JsonProcessing constructor started");
        this.jsonUsersListPath = jsonUsersListPath;
        this.jsonBanksListPath = jsonBanksListPath;

        this.bankList = new ArrayList<>();
        this.userList = new ArrayList<>();
        logger.trace("JsonProcessing constructor ended");
    }

    public void loadData() {
        logger.trace("Method loadData() started");

        Gson gson = new Gson();
        try (Reader reader = new FileReader(jsonUsersListPath)) {
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            if (jsonArray != null) {
                for (JsonElement jsonElement : jsonArray) {
                    LoanUser user = gson.fromJson(jsonElement, LoanUser.class);
                    userList.add(user);
                }
            }
            logger.info("All data from {} was saved to userList", jsonUsersListPath);

        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Got an exception when reading from users json file", e);
        }
        try (Reader reader = new FileReader(jsonBanksListPath)) {
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            if (jsonArray != null) {
                for (JsonElement jsonElement : jsonArray) {
                    Bank bank = gson.fromJson(jsonElement, Bank.class);
                    bankList.add(bank);
                }
            }
            logger.info("All data from {} was read to bankList", jsonBanksListPath);

        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error when trying to read from banks json file", e);
        }
        logger.trace("Method loadData() ended");
    }

    public void addUser(LoanUser user) {
        userList.add(user);
        logger.info("User added: {}", user.getName());
    }

    public List<LoanUser> getUserList() {
        return userList;
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public void saveData() {
        logger.trace("Method saveData() started");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userList);
        logger.info("UserList was converted to JSON format string");
        try (FileWriter writer = new FileWriter(jsonUsersListPath)) {
            logger.info("JSON format string was saved to file");
            writer.write(json);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error when trying to write to json file to save data", e);
        }
        logger.trace("Method saveData() ended");
    }

    public LoanUser checkLogin(String name, String password) {
        logger.trace("Method checkLogin(String name, String password) started");
        for (LoanUser user : userList) {
            if (user.getName().equals(name)) {
                Hashing hashingFromDb = new Hashing(user.getPassword(), user.getName());
                Hashing hashingFromInput = new Hashing(password, name);
                // CHECKING IF HASH ARE EQUAL
                if (hashingFromDb.SHA256().equals(hashingFromInput.SHA256())) {
                    logger.info("User {} logged in successfully", name);
                    return user;
                }
                System.out.println("Password is incorrect...");
                logger.warn("Password is incorrect for user: {}", name);
                logger.warn("Method returned null value when ended");
                return null;
            }
        }
        System.out.println("Username is not found...");
        logger.warn("Username is not found: {}", name);
        logger.warn("Method returned null value when ended");
        return null;
    }

    public String getJsonUsersListPath() {
        return jsonUsersListPath;
    }

    public String getJsonBanksListPath() {
        return jsonBanksListPath;
    }
}
