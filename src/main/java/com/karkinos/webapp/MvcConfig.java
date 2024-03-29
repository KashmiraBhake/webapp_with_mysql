package com.karkinos.webapp;
 
import java.nio.file.Path;
import java.nio.file.Paths;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class MvcConfig implements WebMvcConfigurer {
 
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       Path uploadDir = Paths.get("./patient-photos");  
        //Path uploadDir = Paths.get("workspace//webapp_with_mysql//patient-photos//1");
        //workspace/webapp_with_mysql/patient-photos/1
        String uploadPath = uploadDir.toFile().getAbsolutePath(); 


        registry.addResourceHandler("/patient-photos/**").addResourceLocations("file://"+ uploadPath + "//");
    //     exposeDirectory("patient-photos", registry);
    // }
     
    // private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
    //     Path uploadDir = Paths.get("d./patient-photos/");
    //     String uploadPath = uploadDir.toFile().getAbsolutePath();
         
    //     if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
    //     registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    // }
    }
    @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("redirect:/view.html");
 }
}
