package com.karkinos.webapp;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

 
@Controller
public class PatientController {
 
    @Autowired
    PatientRepository patientRepository;
    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        System.out.println("home");
        return modelAndView;
    }

    @RequestMapping("/new_patient")
    public ModelAndView new_patient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new_patient");
        System.out.println("new patient");
        return modelAndView;
    }

    @RequestMapping(path="/create_new_patient",method=RequestMethod.POST)
    public ModelAndView create_new_patient(@ModelAttribute("patient") Patient patient, 
        @RequestParam String firstName,
        @RequestParam String lastName,
        @RequestParam Integer age,
        @RequestParam String gender,
        @RequestParam String city,
        @RequestParam Integer pincode) {
        patientRepository.save(new Patient(patient.getFirstName(), patient.getLastName(), patient.getAge(), patient.getGender(), patient.getCity(), patient.getPincode(),patient.getPhotos(),patient.getDocs()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submitmessage");
        modelAndView.addObject("firstName", firstName);
        modelAndView.addObject("lastName", lastName);
        modelAndView.addObject("age", age);
        modelAndView.addObject("gender", gender);
        modelAndView.addObject("city", city);
        modelAndView.addObject("pincode", pincode);
        System.out.println("create new patient");
        return modelAndView;
    }

    @RequestMapping(path="/search_patient_form",method=RequestMethod.GET)
    public ModelAndView search_patient_form() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search_patient_form");
        System.out.println("search patient form");
        return modelAndView;
    }

    @RequestMapping(path="/search_patient",method=RequestMethod.GET)
    public ModelAndView search_patient(@RequestParam String firstName) 
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search_result");
        modelAndView.addObject("patients", patientRepository.findByFirstName(firstName));
        System.out.println("search patient");
        return modelAndView;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPatientPage(@PathVariable(name = "id") Long id) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("edit_patient");
    // Patient patient = patientRepository.get(id);
    modelAndView.addObject("patient", patientRepository.findById(id));
    modelAndView.addObject("id", id);
    System.out.println("edit");
    return modelAndView;
    }

    @RequestMapping(path = "/update/{id}",method=RequestMethod.POST)
    public ModelAndView updatePatient(@ModelAttribute("patient") Patient patient, @PathVariable Long id)
        {
        Optional<Patient> patientData = patientRepository.findById(id);
        Patient _patient = patientData.get();
        _patient.setFirstName(patient.getFirstName());
        _patient.setLastName(patient.getLastName());
        _patient.setAge(patient.getAge());
        _patient.setGender(patient.getGender());
        _patient.setCity(patient.getCity());
        _patient.setPincode(patient.getPincode());
        patientRepository.save(_patient);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submitmessage");
        modelAndView.addObject("firstName", _patient.getFirstName());
        modelAndView.addObject("lastName", _patient.getLastName());
        modelAndView.addObject("age", _patient.getAge());
        modelAndView.addObject("gender", _patient.getGender());
        modelAndView.addObject("city", _patient.getCity());
        modelAndView.addObject("pincode", _patient.getPincode());
        System.out.println("update");
        return modelAndView;
        
    }
    
    @RequestMapping("/delete/{id}")
    public String deletePatient(@PathVariable(name = "id") Long id) {
    patientRepository.deleteById(id);
    System.out.println("delete");
    return "redirect:/";       
    }

    @RequestMapping(path="/upload_pic/{id}",method = RequestMethod.GET)
    public ModelAndView showupload_pic_page(@PathVariable(name = "id") Long id) {
   
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("upload_pic");
    modelAndView.addObject("patient", patientRepository.findById(id));
    System.out.println(id);
    modelAndView.addObject("id", id);
    System.out.println("upload pic");
    return modelAndView;
    }

    @RequestMapping(path="/photos/add/{id}",method=RequestMethod.POST)
    public String savePatientpic(Patient patient,
    @RequestParam("image") MultipartFile multipartFile, 
    RedirectAttributes ra,
    @PathVariable(name = "id") Long id,
    Model model) 
    
    throws IOException {
    
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    Optional<Patient> patientData = patientRepository.findById(id);
    Patient _patient = patientData.get();
    System.out.println(_patient.getFirstName());
        _patient.setPhotos(fileName);

        System.out.println(_patient.getFirstName()); 

        _patient.setFirstName(_patient.getFirstName());
        _patient.setLastName(_patient.getLastName());
        _patient.setAge(_patient.getAge());
        _patient.setGender(_patient.getGender());
        _patient.setCity(_patient.getCity());
        _patient.setPincode(_patient.getPincode());

        System.out.println(patient.getFirstName());

        Patient savedPatient = patientRepository.save(_patient);
 
        String uploadDir = "./patient-photos/" + savedPatient.getId();
        // System.out.println(patient1.get().getFirstName());
        System.out.println(savedPatient.getId());
        System.out.println(savedPatient.getLastName());
        System.out.println("add pic and ty");
      
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);}

        try(InputStream inputStream = multipartFile.getInputStream()){
        Path filePath = uploadPath.resolve(fileName);
        System.out.println(filePath.toFile().getAbsolutePath() );
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
        System.out.println("add pic and ty");
    
        return "ty_message";
    }
    
    @RequestMapping(path="/docs/{id}",method = RequestMethod.GET)
    public ModelAndView doc_upload(@PathVariable(name = "id") Long id) {
   
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("doc_upload");
    modelAndView.addObject("patient", patientRepository.findById(id));
    System.out.println(id);
    modelAndView.addObject("id", id);
    System.out.println("upload doc");
    return modelAndView;
    }

    @RequestMapping(path="/docs/add/{id}",method=RequestMethod.POST)
    public String savePatientdoc(Patient patient,
    @RequestParam("document") MultipartFile multipartFile, 
    RedirectAttributes ra,
    @PathVariable(name = "id") Long id,
    Model model) 
    
    throws IOException {
        System.out.println("add doc and ty");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    Optional<Patient> patientData = patientRepository.findById(id);
    Patient _patient = patientData.get();
    System.out.println(_patient.getFirstName());
        _patient.setDocs(fileName);

        System.out.println(_patient.getFirstName()); 

        _patient.setFirstName(_patient.getFirstName());
        _patient.setLastName(_patient.getLastName());
        _patient.setAge(_patient.getAge());
        _patient.setGender(_patient.getGender());
        _patient.setCity(_patient.getCity());
        _patient.setPincode(_patient.getPincode());
        _patient.setPhotos(_patient.getPhotosImagePath());
        System.out.println(patient.getFirstName());

        Patient savedPatient = patientRepository.save(_patient);
 
        String uploadDir = "./patient-docs/" + savedPatient.getId();
        // System.out.println(patient1.get().getFirstName());
        System.out.println(savedPatient.getId());
        System.out.println(savedPatient.getLastName());
        System.out.println("add pic and ty");
      
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);}

        try(InputStream inputStream = multipartFile.getInputStream()){
        Path filePath = uploadPath.resolve(fileName);
        System.out.println(filePath.toFile().getAbsolutePath() );
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        System.out.println("add doc and ty");
        System.out.println(patient.getId());
        return "tyfile_message";
    }

    @RequestMapping(path = "/view/{id}",method=RequestMethod.GET)
    public ModelAndView viewProfile(@ModelAttribute("patient") Patient patient, @PathVariable Long id)
        {
        Optional<Patient> patientData = patientRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view");
        modelAndView.addObject("PhotosImagePath",patientData.get().getPhotosImagePath());
        modelAndView.addObject("photos", patientData.get().getPhotosImagePath());
        modelAndView.addObject("firstName", patientData.get().getFirstName());
        modelAndView.addObject("lastName",patientData.get().getLastName());
        modelAndView.addObject("age", patientData.get().getAge());
        modelAndView.addObject("gender", patientData.get().getGender());
        modelAndView.addObject("city", patientData.get().getCity());
        modelAndView.addObject("pincode", patientData.get().getPincode());
        modelAndView.addObject("id", patientData.get().getId());
        //modelAndView.addObject("DocsFilePath",patientData.get().getDocsFilePath());
        modelAndView.addObject("docs", patientData.get().getDocs());
        System.out.println("final view");
        System.out.println(patient.getId());
         return modelAndView;
        
    }
    // @RequestMapping(path = "/downloads",method=RequestMethod.GET)
    // public void downloadDoc(@Param("id") Long id,HttpServletResponse response) throws Exception{
    //     Optional<Patient> result = patientRepository.findById(id);
    //     if(!result.isPresent()){
    //         throw new Exception("Could not find document with ID: " + id);
    //     }

    //     Patient patient = result.get();

    //     response.setContentType("application/octet-stream");
    //     String headerKey = "Content-Disposition";
    //     String headerValue = "attachment; filename=" + patient.getDocs();

    //     response.setHeader(headerKey,headerValue);
    //     ServletOutputStream outputStream = response.getOutputStream();

    //     outputStream.write(patient.getDocsFilePath());
    //     outputStream.close();
        

    // }
    @RequestMapping(path = "/downloads/{id}",method=RequestMethod.GET)
    public void downloadDoc(HttpServletResponse response,@PathVariable Long id) 
    throws Exception{
        System.out.println("11");
        Optional<Patient> result = patientRepository.findById(id);
        System.out.println("2");
        // if(!result.isPresent()){
        //     throw new Exception("Could not find document with ID: " + id);
        // }
        Patient patient = result.get();
        System.out.println("3");
        System.out.println(patient.getId());
        //File file = new File(patient.getDocsFilePath());
        File file = new File("/workspace/webapp_with_mysql/." + patient.getDocsFilePath());
        System.out.println("4");
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        System.out.println("5");
        String headerValue = "attachment; filename=" + patient.getDocs();
        System.out.println("6");
        response.setHeader(headerKey,headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        System.out.println("7");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        System.out.println("8");
        byte[] buffer = new byte[8192];
        int bytesRead = -1;
        System.out.println("9");
        while ((bytesRead = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, bytesRead);
            System.out.println("10");

        }
        System.out.println(patient.getId());
        inputStream.close();
        outputStream.close();
    }
}
