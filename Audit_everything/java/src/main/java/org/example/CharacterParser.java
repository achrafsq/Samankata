package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CharacterParser {
	public List<Character> parseCharacterData(String filePath) throws IOException {
        // Create an instance of the ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file and parse it into a List<Character>
//        List<Character> characters = objectMapper.readValue(new File(filePath), new TypeReference<List<Character>>() {});

     // Read the JSON file and parse it into a List<Character>
        byte[] jsonData = Files.readAllBytes(Path.of(filePath));
        List<Character> characters = objectMapper.readValue(jsonData, new TypeReference<List<Character>>() {});
        return characters;
    }
	
	public static void main(String[] args) {
	    CharacterParser characterParser = new CharacterParser();

	    try {
	        List<Character> characters = characterParser.parseCharacterData("character_data.json");

	        // Process the characters as needed
	        for (Character character : characters) {
	            System.out.println(character);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
