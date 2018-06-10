package com.unitbv.mi.beans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import com.unitbv.mi.dao.ApplicationsDAO;
import com.unitbv.mi.dao.CVDAO;
import com.unitbv.mi.utils.ApplicantUtils;

@ManagedBean(name = "applicationBean")
@SessionScoped
public class ApplicationBean implements Serializable {

	private static final long serialVersionUID = -6281353658450441225L;

	private String language, experience, city, selectedDomain;
	private List<SelectItem> domains;
	private List<ApplicantUtils> resultsDataTable;
	private static String PATH = "C:\\Users\\IuliaIlie\\Desktop\\USEFUL\\98. Servers\\payara-4.1.2.174\\payara41\\glassfish\\domains\\licenta\\docroot\\htdocs\\cv";

	public String getPath() {
		return PATH;
	}

	public List<ApplicantUtils> getResultsDataTable() {
		resultsDataTable = CVDAO.searchApplicants(selectedDomain, city);
		return resultsDataTable;
	}

	public void setResultsDataTable(List<ApplicantUtils> resultsDataTable) {
		this.resultsDataTable = resultsDataTable;
	}

	public String getSelectedDomain() {
		return selectedDomain;
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public List<SelectItem> getDomains() {
		domains = new ArrayList<>();
		try (FileReader fr = new FileReader(new File(
				"C:\\Users\\IuliaIlie\\Desktop\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\domains_EN.txt"));
				BufferedReader br = new BufferedReader(fr);) {
			String line;
			line = br.readLine();
			while (line != null) {
				domains.add(new SelectItem(line));
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domains;
	}

	public void setDomains(List<SelectItem> domain) {
		this.domains = domain;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String search() {

		return "results_recruiter";

	}

	public void view() {

	}

	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	// Actions
	// ------------------------------------------------------------------------------------

	public void downloadPDF() throws IOException {

		// Prepare.
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		String pathToCV = PATH +"\\"+ getFileName()+ ".pdf";
		File file = new File(pathToCV);
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			// Open file.
			input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"" + getFileName() + "\"");
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task.
			output.flush();
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}

		// Inform JSF that it doesn't need to handle response.
		// This is very important, otherwise you will get the following exception in the
		// logs:
		// java.lang.IllegalStateException: Cannot forward after response has been
		// committed.
		facesContext.responseComplete();
	}

	// Helpers (can be refactored to public utility class)
	// ----------------------------------------

	private String getFileName() {
		String 	id = ((String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("applicationID")).trim();
		String filename = ApplicationsDAO.getApplicant(id);
		return filename;
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail it. It may be
				// useful to
				// know that this will generally only be thrown when the client aborted the
				// download.
				e.printStackTrace();
			}
		}
	}

}
