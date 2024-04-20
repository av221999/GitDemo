package api_responses_validation;

import static io.restassured.RestAssured.*;

import files.reusableMethods;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String queryResponse = given().log().all().header("Content-Type","application/json").
		body("{\"query\":\"query($charId:Int!, $locId:Int!, $epiId:Int!,$charName:String!){\\n  \\n\\n  character(characterId:$charId){\\n    name\\n    type\\n    gender\\n    status\\n    \\n  }\\n   location(locationId:$locId){\\n    name\\n    type\\n    dimension\\n  }\\n  \\n  episode(episodeId:$epiId){\\n    name\\n    air_date\\n    episode\\n  }\\n \\n  characters(filters:{ name :$charName})\\n  {\\n    info\\n    {\\n      count\\n    }\\n    result\\n    {\\n      id\\n      status\\n      location\\n      {\\n        name\\n      }\\n    }\\n  }\\n \\n  }\\n  \\n  \\n\",\"variables\":{\"charId\":5802,\"locId\":6586,\"epiId\":4768,\"charName\":\"Amit\"}}").
		when().
		post("https://rahulshettyacademy.com/gq/graphql").
		then().log().all().extract().response().asString();
		System.out.print(queryResponse);
		
		String mutationResponse = given().log().all().header("Content-Type","application/json").
				body("{\"query\":\"mutation ($locationName: String!, $characterName: String!, $episodeName: String!) {\\n  createLocation(location: {name: $locationName, type: \\\"City\\\", dimension: \\\"122\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $characterName, type: \\\"Software Engineer\\\", status: \\\"ALive\\\", species: \\\"Human\\\", gender: \\\"Male\\\", image: \\\"clear\\\", originId: 6586, locationId: 6586}) {\\n    id\\n  }\\n  createEpisode(episode: {name: $episodeName, air_date: \\\"22/06/1999\\\", episode: \\\"00\\\"}) {\\n    id\\n  }\\n  \\n}\\n\",\"variables\":{\"locationName\":\"Indore\",\"characterName\":\"Amit\",\"episodeName\":\"Manifest\"}}").
				when().
				post("https://rahulshettyacademy.com/gq/graphql").
				then().log().all().extract().response().asString();
				System.out.print(mutationResponse);
				
		int newLocationId =reusableMethods.rawToJson(mutationResponse).getInt("data.createLocation.id");
		System.out.println(newLocationId);
		
		int newCharacterId =reusableMethods.rawToJson(mutationResponse).getInt("data.createCharacter.id");
		System.out.println(newCharacterId);
		
		int newEpisodeId =reusableMethods.rawToJson(mutationResponse).getInt("data.createEpisode.id");
		System.out.println(newEpisodeId);
		
		String deletionResponse=given().log().all().header("Content-Type","application/json").
		body("{\"query\":\"mutation($characterId:Int!,$locationId:Int!,$episodeId:Int!){\\n  deleteCharacters(characterIds: [$characterId]) {\\n    charactersDeleted\\n  }\\n  deleteLocations(locationIds:[$locationId]){\\n    locationsDeleted\\n  }\\n  \\n  deleteEpisodes(episodeIds:[$episodeId]){\\n    episodesDeleted\\n  }\\n}\\n\",\"variables\":{\"locationId\":"+newLocationId+",\"characterId\":"+newCharacterId+",\"episodeId\":"+newEpisodeId+"}}\n"
				+ "").
		when().
		post("https://rahulshettyacademy.com/gq/graphql").
		then().log().all().extract().response().asString();
		System.out.print(deletionResponse);
		
	}

}
