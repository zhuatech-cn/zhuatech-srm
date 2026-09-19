/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.srm;
import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc; import java.util.regex.*; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@SpringBootTest @AutoConfigureMockMvc class SrmApiIntegrationTests {
    @Autowired MockMvc mvc; private String supplierToken; private String buyerToken;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @BeforeEach void login()throws Exception{supplierToken=token("supplier","Demo@2026");buyerToken=token("buyer","Demo@2026");}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private String token(String u,String p)throws Exception{String json=mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\""+u+"\",\"password\":\""+p+"\"}")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();Matcher matcher=Pattern.compile("\\\"token\\\":\\\"([^\\\"]+)\\\"").matcher(json);if(!matcher.find())throw new AssertionError("登录响应中缺少 token");return matcher.group(1);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void supplierCanReadDashboard()throws Exception{mvc.perform(get("/api/portal/dashboard").header("Authorization","Bearer "+supplierToken)).andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true)).andExpect(jsonPath("$.data.metrics[0].label").value("待响应询价"));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void buyerCanReadSuppliers()throws Exception{mvc.perform(get("/api/admin/suppliers").header("Authorization","Bearer "+buyerToken)).andExpect(status().isOk()).andExpect(jsonPath("$.data.length()").value(4));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void supplierCanSubmitQuote()throws Exception{mvc.perform(post("/api/portal/rfqs/1/quotes").header("Authorization","Bearer "+supplierToken).contentType(MediaType.APPLICATION_JSON).content("{\"amount\":658000,\"leadDays\":18,\"validUntil\":\"2026-09-30\",\"remark\":\"含税含运费\"}")).andExpect(status().isOk()).andExpect(jsonPath("$.message").value("报价已提交"));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void buyerCanAssessSupplierRisk()throws Exception{mvc.perform(post("/api/admin/supplier-risk").header("Authorization","Bearer "+buyerToken).contentType(MediaType.APPLICATION_JSON).content("{\"supplierName\":\"示例单一来源供应商\",\"qualityScore\":60,\"onTimeRate\":0.7,\"overdueDays\":12,\"unresolvedIssues\":2,\"singleSource\":true}")).andExpect(status().isOk()).andExpect(jsonPath("$.data.riskScore").value(56)).andExpect(jsonPath("$.data.level").value("HIGH")).andExpect(jsonPath("$.data.buyerReview").value(true));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void anonymousRequestIsDenied()throws Exception{mvc.perform(get("/api/admin/dashboard")).andExpect(status().isForbidden());}
}
