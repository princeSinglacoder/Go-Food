package com.fooddelivery.database;

import com.fooddelivery.service.item.model.Item;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Component // tells spring to create ONE Object of this class and manage it
public class ItemDatabase {

    private final Region AWS_REGION = Region.US_EAST_1;
    private final String TABLE_NAME = "FoodItem";

    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbTable<Item> itemTable;

    public ItemDatabase() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                System.getenv("ACCESS_KEY_ID"),
                System.getenv("SECRET_ACCESS_KEY")
        );

        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .region(AWS_REGION)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        this.enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.itemTable = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(Item.class));
    }

    public Item find(String itemName) {
        try {
            Item key = new Item();
            key.setItemName(itemName);
            return itemTable.getItem(key);
        } catch (Exception e) {
            logError("getItem", e);
            return null;
        }
    }

    public Item set(Item entity) {
        try {
            // Check if already exists
            if (find(entity.getItemName()) != null) {
                throw new IllegalArgumentException("Item already exists: " + entity.getItemName());
            }
            itemTable.putItem(entity);
            return entity;
        } catch (Exception e) {
            logError("putItem", e);
            return null;
        }
    }

    public Item update(Item entity) {
        try {
            itemTable.putItem(entity); // putItem overwrites
            return entity;
        } catch (Exception e) {
            logError("updateItem", e);
            return null;
        }
    }

    public Item delete(String itemName) {
        try {
            Item key = new Item();
            key.setItemName(itemName);
            Item existing = itemTable.getItem(key);
            if (existing != null) {
                itemTable.deleteItem(key);
            }
            return existing;
        } catch (Exception e) {
            logError("deleteItem", e);
            return null;
        }
    }

    public int checkPrice(String itemName) {
        Item entity = find(itemName);
        return (entity != null) ? entity.getItemPrice() : -1;
    }

    private void logError(String action, Exception e) {
        System.err.println("Error during " + action + ": " + e.getMessage());
        e.printStackTrace();
    }
}
