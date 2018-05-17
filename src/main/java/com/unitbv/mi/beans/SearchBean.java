package com.unitbv.mi.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.unitbv.mi.dao.SearchDAO;

@ManagedBean(name = "searchBean")
@SessionScoped
public class SearchBean implements Serializable {

	private static final long serialVersionUID = -3984475135190155594L;
	
	private List<SelectItem> list, city;
	private String line;
	private String job;
	private String company;
	private String selectedDomain, selectedCity;
	
	public String getSelectedDomain() {
		return selectedDomain;
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	public List<SelectItem> getCity() {
		city=new ArrayList<>();
		city.add(new SelectItem("Test"));
		return city;
		//return citiesAll();
	}

	public void setCity(List<SelectItem> city) {
		this.city = city;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<SelectItem> getList() {
		return domainsAll();
	}

	public void setList(ArrayList<SelectItem> list) {
		this.list = list;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public List<SelectItem> domainsAll() {

		try (FileReader fr = new FileReader(
				new File("D://USEFUL//licenta//project//jobs//src//main//resources//domains_EN.txt"));
				BufferedReader bf = new BufferedReader(fr)) {
			list = new ArrayList<>();

			line = bf.readLine();
			while (line != null) {
				list.add(new SelectItem(line));
				line = bf.readLine();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		
		}
	
		return list;
	}
	
	public List<SelectItem> citiesAll(){
		
		return SearchDAO.getCities();
	}
	
	public String search() {
		
		
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("job", job);
			session.setAttribute("company", company);
			session.setAttribute("domain", selectedDomain);
			session.setAttribute("city", selectedCity);
			return "results";
				
	}
}
