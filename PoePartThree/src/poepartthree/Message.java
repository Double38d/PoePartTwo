/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartThree;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Student
 */
public class Message {
    ArrayList<String> sentMessages = new ArrayList<>();
    ArrayList<String> messageIdsList = new ArrayList<>();
    HashSet<String> messageIds = new HashSet<>();
    ArrayList<String> msgHash = new ArrayList<>();
    ArrayList<String> recipientNumber = new ArrayList<>();
    ArrayList<String> messageStatus = new ArrayList<>();
    ArrayList<String> disregardedMessages = new ArrayList<>();
    ArrayList<String> storedMessages = new ArrayList<>();
    ArrayList<String> senders = new ArrayList<>();

    Random random = new Random();
    Scanner input = new Scanner(System.in);

    int numMessagesSent = 0;

    public void sendingMessages() {
        System.out.print("How many messages do you want to send? ");
        int numberOfMessages = input.nextInt();

        input.nextLine();

        for (int i = 1; i <= numberOfMessages; i++) {

            System.out.println("\n==================================");
            System.out.println("MESSAGE " + i);
            System.out.println("==================================");

            String messageID = generateMessageID();

            System.out.print("Enter recipient cell number: ");
            String recipient = input.nextLine();

            String recipientResult = checkRecipientCell(recipient);

            if (!recipientResult.equals("Valid number")) {
                System.out.println(recipientResult);
                i--;
                continue;
            }

            System.out.print("Enter your message: ");
            String messageText = input.nextLine();

            if (messageText.length() > 250 || messageText.trim().isEmpty()) {
                System.out.println("Please enter a message of 1 to 250 characters.");
                i--;
                continue;
            }

            int messageNumber = sentMessages.size() + 1;

            String messageHash = createMessageHash(messageID, messageNumber, messageText);

            msgHash.add(messageHash);
            sentMessages.add(messageText);
            messageIdsList.add(messageID);
            messageIds.add(messageID);
            recipientNumber.add(recipient);

            System.out.print("Enter sender name: ");
            String sender = input.nextLine();
            senders.add(sender);

            String status = SentMessage();

            messageStatus.add(status);

            if (status.equals("Sent")) {
                numMessagesSent++;
            }
        }

        printMessages();

        System.out.println(
            "Total messages sent: " + returnTotalMessages()
        );
    }

    public int returnTotalMessages() {
        return numMessagesSent;
    }

    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        String firstTwoNumbers = messageID.substring(0, 2);

        String cleanMessage = messageText.trim();

        String[] words = cleanMessage.split("\\s+");

        String firstWord = words[0];

        String lastWord = words[words.length - 1];

        String hash = firstTwoNumbers + ":" + messageNumber + ":" + firstWord + lastWord;

        return hash.toUpperCase();
    }

    public String generateMessageID() {
        String messageID;

        do {
            long number = 100000000L + random.nextLong(900000000L);

            messageID = String.valueOf(number);

        } while (messageIds.contains(messageID));

        return messageID;
    }

    public String checkRecipientCell(String cellphone) {
        if (cellphone.matches("^\\+27[0-9]{9}$") || cellphone.matches("^0[0-9]{9}$")) {
            return "Valid number";
        } else {
            return "Invalid number";
        }
    }

    public String SentMessage() {
        System.out.println("\nChoose what you want to do with this message!");

        System.out.println("1. Send");
        System.out.println("2. Disregard");
        System.out.println("3. Store");

        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        input.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.println("Message successfully sent");
                return "Sent";
            }
            case 2 -> {
                System.out.println("Press 0 to delete the message");
                int deleteChoice = input.nextInt();

                input.nextLine();

                if (deleteChoice == 0) {
                    System.out.println("Message deleted");

                    int lastIndex = sentMessages.size() - 1;

                    String messageID = messageIdsList.get(lastIndex);
                    messageIds.remove(messageID);

                    recipientNumber.remove(lastIndex);
                    sentMessages.remove(lastIndex);
                    msgHash.remove(lastIndex);
                    messageIdsList.remove(lastIndex);
                    senders.remove(lastIndex);

                    return "Disregarded";

                } else {
                    System.out.println("Invalid option!");

                    return "Disregarded";
                }

            }
            case 3 -> {
                System.out.println("Message successfully stored");

                return "Stored";
            }
            default -> {
                System.out.println("Invalid option");

                return "Disregarded";
            }
        }
    }

    public String printMessages() {
        StringBuilder allMessages = new StringBuilder();

        System.out.println("\n====== All Messages ======");

        for (int i = 0; i < sentMessages.size(); i++) {

            int messageNumber = i + 1;

            allMessages.append("\nMessage Number: ").append(messageNumber);
            allMessages.append("\nMessage ID: ").append(messageIdsList.get(i));
            allMessages.append("\nRecipient: ").append(recipientNumber.get(i));
            allMessages.append("\nMessage: ").append(sentMessages.get(i));
            allMessages.append("\nMessage Hash: ").append(msgHash.get(i));
            allMessages.append("\nStatus: ").append(messageStatus.get(i));

            System.out.println("\n======================================");
            System.out.println("Message Number: " + messageNumber);
            System.out.println("Message ID: " + messageIdsList.get(i));
            System.out.println("Recipient: " + recipientNumber.get(i));
            System.out.println("Message: " + sentMessages.get(i));
            System.out.println("Message Hash: " + msgHash.get(i));
            System.out.println("Status: " + messageStatus.get(i));
        }

        return allMessages.toString();
    }

    public void messageManagementMenu() {

        while (true) {
            System.out.println();
            System.out.println("1. Show disregarded messages");
            System.out.println("2. Show all stored messages");
            System.out.println("3. Delete a messages by Hash Id");
            System.out.println("4. Display full report");
            System.out.println("5. Search for a messages");
            System.out.println("6. Display the longest messages");
            System.out.println("7. Back to menu");

            System.out.print("Choose an option (1-7): ");

            String option = input.nextLine();

            switch (option) {
                case "1" -> showDisregardedMessages();
                case "2" -> showAllStoredMessages();
                case "3" -> deleteMessageByHash();
                case "4" -> displayFullReport();
                case "5" -> searchMessageByHashID();
                case "6" -> findLongestMessage();
                case "7" -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public void findLongestMessage() {
        if (sentMessages.isEmpty()) {
            System.out.println("No sent message to analyze the longest");
            return;
        }

        int maxLength = -1;
        int index = -1;
        
        for (int i = 0; i < sentMessages.size(); i++) {
            int currentLength = sentMessages.get(i).length();

            if (currentLength > maxLength) {
                maxLength = currentLength;
                index = i;
            }
    }
      
     if (index != -1) {
        System.out.println();
        System.out.println("========== LONGEST MESSAGE SENT ==========\n\n");

        System.out.println("Length: " + maxLength + " characters long");
        System.out.println("Hash ID: " + msgHash.get(index));
        System.out.println("Hash ID: " + messageIdsList.get(index));
        System.out.println("Hash ID: " + senders.get(index));
        System.out.println("Hash ID: " + recipientNumber.get(index));
        System.out.println("Hash ID: " + sentMessages.get(index));
     
     }   
        
    }    

   public void showDisregardedMessages() {
        if (disregardedMessages.isEmpty()) {
            System.out.println("No deleted messages yet.");
            return;
        }

        System.out.println("\n");
        System.out.println("======= DELETED MESSAGES =======\n\n");

        for (int i = 0; i < disregardedMessages.size(); i++) {
            System.out.println((i + 1) + ". " + disregardedMessages.get(i));
        }
    }

    public void showAllStoredMessages() {
    }

    public void deleteMessageByHash() {
        
        System.out.println("Enter Hash ID to delete: ");

        String hash = input.nextLine();

        int index = msgHash.indexOf(hash);

        if (index != -1) {
            disregardedMessages.add(sentMessages.get(index));
            messageIds.remove(messageIdsList.get(index));
            sentMessages.remove(index);
            senders.remove(index);
            recipientNumber.remove(index);
            messageIdsList.remove(index);
            msgHash.remove(index);
            messageStatus.remove(index);

            System.out.println("Message deleted and moved to disregarded");
        } else {
            System.out.println("Message not found");
        }
        
    }

    public void displayFullReport() {
    }

    public void searchMessageByHashID() {
    }
}