package com.karkinos.webapp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.persistence.Transient;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity 
@Table(name = "document")
public class Documents {

  private Long docId;
  private String docName;

  @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.PERSIST)
  @JoinTable(name = "patient_documents", joinColumns = @JoinColumn(name = "doc_id", referencedColumnName = "docId", nullable = true), 
		inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true))
	@JsonIgnoreProperties("documentsList")
  //@JoinColumn(name = "patient_id")
  private Patient patient;
    
  protected Documents() {
    
  }
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getDocId() {
      return docId;
    }
    public Documents(String docName){
      this.docName=docName;

    }
    public void setDocId(Long docId) {
      this.docId = docId;
    }
    public String getDocName() {
      return docName;
    }
    public void setDocName(String docName) {
      this.docName = docName;
    }
    public Patient getPatient() {
      return patient;
    }
    public void setPatient(Patient patient) {
      this.patient = patient;
    }
    
    @Transient
    public String getDocsFilePath() {
        if (docName == null || docId == 0) return null;
        // if (photos == null) return null;
        return "/patient-docs/" + docId + "/" + docName;
    }
}
      