package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CharacterFinder {
	private final List<Character> allCharacters;

	public CharacterFinder(List<Character> allCharacters) {
		this.allCharacters = Collections.unmodifiableList(allCharacters);
	}

	public Character findByFirstName(String name) {
		var found = allCharacters.stream().filter(c -> c.firstName.equals(name)).toList();
		if (found.size() > 0) {
			var character = found.get(0);
//      //bug: return the nemesis instead of the character
//      if (character.getNemesis() != null) {
//        return character.getNemesis();
//      }
			return character;
		} else {
			return null;
		}
	}

	public Character findParent(String firstName) {
		var child = findByFirstName(firstName);
		if (child == null) {
			System.out.println("Child character not found.");
			return null;
		}

		var parent = child.parents.stream().findFirst().orElse(null);
		if (parent.firstName == null) {
			System.out.println("No parents found for the child character.");
			return null;
		}
		// bug: return Monster instead of Jim
		if (parent != null && parent.firstName.equals("Eleven")) {
//      	return findByFirstName("Demadog");
			return parent;
//			return findByFirstName("Jim");
		}
		return parent;
	}

	public List<Character> findMonsters() {
		var monsters = allCharacters.stream().filter(c -> c.lastName == null).collect(toList());
		return monsters;
	}

	public List<Character> findFamilyByCharacter(String firstName) {
		var person = findByFirstName(firstName);
		if (person == null) {
			return new ArrayList<>();
		}
		var family = new HashSet<Character>();
		family.addAll(person.parents);
		family.addAll(person.children);
		// bug: exclude siblings
		// family.UnionWith(person.siblings);

		// bug: include Nemesis
//		if (person.getNemesis() != null)
//			family.add(person.getNemesis());

		return family.stream().toList();
	}

	public List<Character> findFamilyByLastName(String lastName) {
//   var family = allCharacters.stream().filter(c -> c.lastName.equals(lastName.toString())).collect(toList());

//	  var family = allCharacters.stream()
//            .filter(c -> c.lastName.equals(lastName.toString()))
//            .collect(Collectors.toList());

//		// check inside stream for presence of an object
		var family = allCharacters.stream().filter(c -> {
			if (c.lastName == null && lastName == null) {
				return true; // Both last names are null, consider it a match
			}
			return c.lastName != null && c.lastName.equals(lastName);
		}).collect(Collectors.toList());
		
//		 bug: monsters are being returned, who are not family with anyone
//    if (lastName == null)
//    {
//        var familyWithoutMonsters = family.FindAll(c => !c.IsMonster);
//        return familyWithoutMonsters.ToList();
//    }

		// bug: add all family's Nemeses
		var nemeses = new ArrayList<Character>();

		for (var character : family) {
			if (character.getNemesis() != null) {
				nemeses.add(character.getNemesis());
			}
		}
		family.addAll(nemeses);

		return family;
	}
	
	public Character findByCharacterPath(String characterPath) {
        String[] pathElements = characterPath.split("/");

        Character currentCharacter = null;
        for (String pathElement : pathElements) {
            if (currentCharacter == null) {
                currentCharacter = findByFirstName(pathElement);
            } 

            if (currentCharacter == null) {
                break;
            }
        }
        return currentCharacter;
	}
}
