package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.Joke;;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class JokeControllerTest {
	Logger logger = LoggerFactory.getLogger(JokeControllerTest.class);
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllJokes() throws Exception {
		mockMvc.perform(get("/api/jokes")).andExpect(status().isOk());
	}

	@Test
	public void testCRUD() throws Exception {
		// 添加
		Joke joke = new Joke("title", "content");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", joke.getTitle());
		jsonObject.put("content", joke.getContent());
		MvcResult mvcResult = mockMvc
				.perform(post("/api/jokes").content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String res = mvcResult.getResponse().getContentAsString();
		JSONArray array = JSONArray.parseArray(res);
		assertThat(((JSONObject) array.get(0)).getString("title")).isEqualTo("title");
		// 修改
		int id = ((JSONObject) array.get(0)).getIntValue("id");
		joke.setTitle("TITLE");
		mvcResult = mockMvc.perform(
				post("/api/jokes/" + id).content(JSONObject.toJSONString(joke)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		jsonObject = JSONObject.parseObject(mvcResult.getResponse().getContentAsString());
		assertThat(jsonObject.getString("title")).isEqualTo("TITLE");
		// 删除
		mockMvc.perform(delete("/api/jokes/" + id)).andExpect(status().isOk());
		mvcResult = mockMvc.perform(get("/api/jokes")).andExpect(status().isOk()).andReturn();
		array = JSONObject.parseArray(mvcResult.getResponse().getContentAsString());
		assertThat(array.size()).isEqualTo(0);
	}

}
