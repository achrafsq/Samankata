package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharactersTest {
	public List<Character> allTestData() {
		var joyce = new Character("Joyce", "Byers");
		var jim = new Character("Jim", "Hopper");
		var eleven = new Character("Eleven");
		var mike = new Character("Mike", "Wheeler");	
		var dustin = new Character("Dustin", "Henderson");
		var lucas = new Character("Lucas", "Sinclair");
		var nancy = new Character("Nancy", "Wheeler");
		var jonathan = new Character("Jonathan", "Byers");
		var will = new Character("Will", "Byers");
		var karen = new Character("Karen", "Wheeler");
		var steve = new Character("Steve", "Harrington");
		var mindflayer = new Character("Mindflayer", true);
		var demagorgon = new Character("Demagorgon", true);
		var demadog = new Character("Demadog", true);

		joyce.addChild(jonathan);
		joyce.addChild(will);
		jim.addChild(eleven);
		jim.addChild(lucas);
		karen.addChild(nancy);
		karen.addChild(mike);
		
//		check family by last names
		mike.addChild(eleven);
        will.addChild(eleven);
        steve.addChild(lucas);
        steve.addChild(dustin);
        steve.addChild(eleven);
      

		eleven.setNemesis(demagorgon);
		will.setNemesis(mindflayer);
		dustin.setNemesis(demadog);
		jim.setNemesis(demadog);

		return Arrays.asList(joyce, jim, mike, will, eleven, dustin, lucas, nancy, jonathan, mindflayer, demagorgon,
				demadog, karen, steve);
	}

	@Test
	public void findCharacterByFirstName() {
		var finder = new CharacterFinder(allTestData());
		var character = finder.findByFirstName("Jim");
		assertEquals("Jim", character.firstName);
//		System.out.println(character.firstName);
	}

	@Test
	public void findCharacterByParent() {
		var mike = new Character("Mike", "Wheeler");
		var finder = new CharacterFinder(allTestData());
		var character = finder.findParent("Eleven");
		// Assert that the parent character is Jim character
		assertNotNull(character);
		assertEquals(mike, character);
	}

	@Test
	public void checkMonsters() {
		var finder = new CharacterFinder(allTestData());
		var listCheck = finder.findMonsters();
//		if we want to check just one monster we get the first item in the list
//		assertFalse(listCheck.get(0).isMonster);
//		assertTrue(listCheck.containsAll(Arrays.asList(new Character("Eleven"))));
		
//		list of all Monsters
		assertEquals(Arrays.asList(new Character("Eleven"),new Character("Mindflayer"),new Character("Demagorgon"),new Character("Demadog")), listCheck);
		
//		check by var / charcter
//		assertEquals(listOf(eleven,mindflayer,demagorgon,demadog), listCheck);
	}
	
	@Test
	public void checkfindFamilyByCharacter() {
		// Create characters 
		var mike = new Character("Mike", "Wheeler");
		var steve = new Character("Steve", "Harrington");
		var will = new Character("Will", "Byers");
		Character jim = new Character("Jim", "Hopper");

		// Test finding family by character
		var finder = new CharacterFinder(allTestData());
		List<Character> family = finder.findFamilyByCharacter("Eleven");

		// Assert that the family contains the expected characters
		assertEquals(new HashSet<>(List.of(jim,steve,mike,will)), new HashSet<>(family));
	}
	
	@Test
	public void checkfindFamilyByLastName() {
		// Create characters 

		var jim = new Character("Jim", "Hopper");
		var demadog = new Character("Demadog", true);
				// Test finding family by character
				var finder = new CharacterFinder(allTestData());
				List<Character> family = finder.findFamilyByLastName("Hopper");
				
				 // Assert that the family contains the expected characters
		        assertEquals(List.of(jim,demadog), family);
		
	}

//	@Test
//	public void checkfindFamilyByCharacter() {
//		var finder = new CharacterFinder(allTestData());
//		var list = finder.findFamilyByCharacter("Joyce");
//		assertTrue(list.contains(new Character("jonathan","will")));
//	}

//	@Test
//	public void checkfindFamilyByLastName() {
//		var finder = new CharacterFinder(allTestData());
//		var list = finder.findFamilyByLastName("Byers");
//		assertTrue(list.contains(new Character("jonathan", "will")));
//	}

//	@Test
//    public void testFindFamilyByLastName() {
//        
//        // Test finding family by last name "Byers"
//		var finder = new CharacterFinder(allTestData());
//		var list = finder.findFamilyByLastName("Byers");
//
//        // Assert that the family consists of characters with relationships
//        assertEquals(2, list.size());
//        assertEquals("jonathan", list.get(0).lastName);
//        assertEquals("will", list.get(1).lastName);
//        
//    }

}
