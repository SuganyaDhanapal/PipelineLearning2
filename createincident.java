package Week3Day1;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class createincident {
	
	@Test
	public void create() {
		
		//Add Endpoints
		
		RestAssured.baseURI="https://dev82801.service-now.com/api/now/table/";
		
		//Add Authentication
		
		RestAssured.authentication=RestAssured.basic("admin", "oyfDL!U5T4m^");
		
		//Add Request
		
		RequestSpecification inputRequest = RestAssured.given().contentType("application/json").when().body("{\r\n"
				+ "    \"short_description\":\"laptop\",\r\n"
				+ "\"description\":\"laptopservices\"\r\n"
				+ "}");
		
		//Send Request
		
		Response response = inputRequest.post("incident");
		
		//Extarct sys id
		
		String sys_id = response.jsonPath().getString("result.sys_id");
		
		System.out.println("sys_id is  "+sys_id);
		
		//Assert the status code
		
		response.then().assertThat().statusCode(Matchers.equalTo(201));
		
		//Print the response
		
		response.prettyPrint();
		
	}

}
