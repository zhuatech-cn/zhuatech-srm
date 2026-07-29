/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.srm;
import com.fasterxml.jackson.databind.ObjectMapper; import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class SrmApiIntegrationTests {
    @Autowired MockMvc mvc; @Autowired ObjectMapper mapper; private String supplierToken; private String buyerToken;
    @BeforeEach void login()throws Exception{supplierToken=token("supplier","Demo@2026");buyerToken=token("buyer","Demo@2026");}
    private String token(String u,String p)throws Exception{String json=mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\""+u+"\",\"password\":\""+p+"\"}")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();return mapper.readTree(json).path("data").path("token").asText();}
    @Test void supplierCanReadDashboard()throws Exception{mvc.perform(get("/api/portal/dashboard").header("Authorization","Bearer "+supplierToken)).andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true)).andExpect(jsonPath("$.data.metrics[0].label").value("待响应询价"));}
    @Test void buyerCanReadSuppliers()throws Exception{mvc.perform(get("/api/admin/suppliers").header("Authorization","Bearer "+buyerToken)).andExpect(status().isOk()).andExpect(jsonPath("$.data.length()").value(4));}
    @Test void supplierCanSubmitQuote()throws Exception{mvc.perform(post("/api/portal/rfqs/1/quotes").header("Authorization","Bearer "+supplierToken).contentType(MediaType.APPLICATION_JSON).content("{\"amount\":658000,\"leadDays\":18,\"validUntil\":\"2026-09-30\",\"remark\":\"含税含运费\"}")).andExpect(status().isOk()).andExpect(jsonPath("$.message").value("报价已提交"));}
    @Test void anonymousRequestIsDenied()throws Exception{mvc.perform(get("/api/admin/dashboard")).andExpect(status().isForbidden());}
}
