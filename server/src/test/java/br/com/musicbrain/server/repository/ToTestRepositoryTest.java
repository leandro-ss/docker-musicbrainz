package br.com.musicbrain.server.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ToTestRepositoryTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ToTestRepository toTestRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		toTestRepository.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links.toTest").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/toTest").content(
				"{\"name\": \"test\", \"description\":\"Test1\"}")).andExpect(
						status().isCreated()).andExpect(
								header().string("Location", containsString("toTest/")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/toTest").content(
			"{\"name\": \"test\", \"description\":\"Test2\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.name").value("Test")).andExpect(
						jsonPath("$.description").value("Test2"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {

		MvcResult mvcResult = null;

		mvcResult = mockMvc.perform(post("/toTest").content(
				"{\"name\": \"test\", \"description\":\"test_update_before\"}")).andExpect(
		status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"name\": \"test\", \"description\":\"test_update_after\"}")).
		andExpect(status().isNoContent());

		mockMvc.perform(get(location)).
		andExpect(status().isOk()).
		andExpect(jsonPath("$.name").value("test")).
		andExpect(jsonPath("$.description").value("test_update_after"));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {

		MvcResult mvcResult = null;

		mvcResult = mockMvc.perform(post("/toTest").content(
				"{\"name\": \"test\", \"description\":\"Test1\"}")).andExpect(
			status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(patch(location).content(
				"{\"description\": \"test_updated\"}")).
			andExpect(status().isNoContent());

		mockMvc.perform(get(location)).
			andExpect(status().isOk()).
			andExpect(jsonPath("$.name").value("Test")).
			andExpect(jsonPath("$.description").value("test_updated"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {

		MvcResult mvcResult = null;

		mvcResult = mockMvc.perform(post("/toTest").content(
				"{\"name\": \"test\", \"description\":\"test_delete\"}")).andExpect(
			status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}