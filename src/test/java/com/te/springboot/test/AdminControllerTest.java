/*
 * package com.te.springboot.test;
 * 
 * import static org.junit.jupiter.api.Assertions.*; import static
 * org.mockito.Mockito.mockitoSession; import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 * 
 * import java.io.UnsupportedEncodingException; import java.util.Map;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Mockito;
 * import org.mockito.junit.jupiter.MockitoExtension; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.http.MediaType; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.MockMvcBuilder; import
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers; import
 * org.springframework.test.web.servlet.setup.MockMvcBuilders;
 * 
 * import com.fasterxml.jackson.core.JsonProcessingException; import
 * com.fasterxml.jackson.databind.Module.SetupContext; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.te.springboot.controller.AdminController; import
 * com.te.springboot.dto.Admin; import com.te.springboot.dto.Message; import
 * com.te.springboot.service.AdminService;
 * 
 * @ExtendWith(MockitoExtension.class)
 * 
 * @SpringBootTest class AdminControllerTest {
 * 
 * @InjectMocks private AdminController controller;
 * 
 * @Mock private AdminService service;
 * 
 * private MockMvc mockmvc; private ObjectMapper mapper;
 * 
 * @BeforeEach public void setUp() { this.mockmvc =
 * MockMvcBuilders.standaloneSetup(controller).build(); this.mapper = new
 * ObjectMapper(); }
 * 
 * @SuppressWarnings("Unchecked")
 * 
 * @Test void testAuthentication() throws Exception {
 * System.out.println(mockmvc); Admin admin = new Admin("Akr123", "3745");
 * Message message = new Message(); message.setData(admin);
 * Mockito.when(service.authentication(Mockito.any(),
 * Mockito.any())).thenReturn(message); String jasonString =
 * mapper.writeValueAsString(admin);
 * 
 * String resultString = mockmvc
 * .perform(get("/adminlogin/Akr123/3745").contentType(MediaType.
 * APPLICATION_JSON_VALUE)
 * .content(jasonString).accept(MediaType.APPLICATION_JSON_VALUE))
 * .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().
 * getContentAsString(); System.out.println(resultString); Message message2 =
 * mapper.readValue(resultString, Message.class); Map<String, String> map =
 * (Map<String, String>) message2.getData(); for (Map.Entry<String, String> m :
 * map.entrySet()) { assertEquals(admin.getUserId(), m.getValue()); break; } }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Test void testGetAll() throws UnsupportedEncodingException, Exception {
 * Admin admin = new Admin("Akr123", "AkramLadaf"); Message message = new
 * Message(); message.setData(admin);
 * Mockito.when(service.getCustomers(Mockito.any())).thenReturn(message); String
 * jasonString = mapper.writeValueAsString(admin);
 * System.out.println(jasonString); String resultString = mockmvc
 * .perform(get("/getAll").sessionAttr("start",
 * admin).contentType(MediaType.APPLICATION_JSON_VALUE) .content(jasonString))
 * .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().
 * getContentAsString(); Message message2 = mapper.readValue(resultString,
 * Message.class); System.out.println(message2); Map<String, String> map =
 * (Map<String, String>) message2.getData(); for (Map.Entry<String, String> m :
 * map.entrySet()) { assertEquals(admin.getUserId(), m.getValue()); break; } }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Test void testLogout() throws UnsupportedEncodingException, Exception {
 * Admin admin = new Admin("Akr123", "Akram", "3745"); Message message = new
 * Message(); message.setData(admin); String jasonString =
 * mapper.writeValueAsString(admin); String resultString = mockmvc
 * .perform(get("/logout").sessionAttr("start",
 * admin).contentType(MediaType.APPLICATION_JSON_VALUE) .content(jasonString))
 * .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().
 * getContentAsString(); Message message2 = mapper.readValue(resultString,
 * Message.class); System.out.println(message2); String name = (String)
 * message2.getData(); assertEquals(admin.getName(), name); }
 * 
 * }
 */