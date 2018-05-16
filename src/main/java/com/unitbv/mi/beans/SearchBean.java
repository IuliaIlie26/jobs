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

@ManagedBean(name = "searchBean")
@SessionScoped
public class SearchBean implements Serializable {

	private static final long serialVersionUID = -3984475135190155594L;
	
	private List<String> list, city;
	private String line;
	private String job;
	private String company;
	
	public List<String> getCity() {
		return citiesAll();
	}

	public void setCity(List<String> city) {
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

	public List<String> getList() {
		return domainsAll();
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public List<String> domainsAll() {

		try (FileReader fr = new FileReader(
				new File("..//resources//domains_EN.txt"));
				BufferedReader bf = new BufferedReader(fr)) {
			list = new ArrayList<>();

			line = bf.readLine();
			while (line != null) {
				list.add(line);
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
	
	public List<String> citiesAll(){
		return city;
	}
}
