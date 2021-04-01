package io.assignment.kalah.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.assignment.kalah.dto.KalahGameDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionControllerAdviceTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void testGameNotFoundException() throws Exception {
		final MockHttpServletRequestBuilder playGameRequest = MockMvcRequestBuilders.put("/games/123/pits/7");
		try {
			mockMvc.perform(playGameRequest);
		} catch (Exception e) {
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof GameNotFoundException);
			assertNotNull(e.getCause().getMessage());
			assertTrue(e.getCause().getMessage().contains("Could not find game 123"));
		}
	}

	@Test
	public void testIllegalMoveException() throws Exception {
		final MockHttpServletRequestBuilder initGameRequest = MockMvcRequestBuilders.post("/games");
		final String responseString = this.mockMvc.perform(initGameRequest).andReturn().getResponse()
				.getContentAsString();
		final ObjectMapper objectMapper = new ObjectMapper();
		final KalahGameDTO game = objectMapper.readValue(responseString, KalahGameDTO.class);

		final MockHttpServletRequestBuilder playGameRequest = MockMvcRequestBuilders
				.put("/games/" + game.getId() + "/pits/7");

		try {
			mockMvc.perform(playGameRequest);
		} catch (Exception e) {
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof IllegalMoveException);
			assertNotNull(e.getCause().getMessage());
			assertTrue(e.getCause().getMessage().contains("Illegal move: Can not start from house"));
		}
	}
}
