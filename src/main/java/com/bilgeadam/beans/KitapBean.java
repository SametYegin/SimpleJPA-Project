package com.bilgeadam.beans;

import java.util.List;
import javax.annotation.*;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.bilgeadam.dao.KitapDAO;;

@ManagedBean (name = "kitapBean")
public class KitapBean {

	
	
	// Method To Fetch The Existing School List From The Database
	public List<KitapBean> bookListFromDb() {
		return KitapDAO.getAllBookDetails();		
	}
	@PostConstruct
	public List<KitapBean> AllBookListFromDb() {
		return KitapDAO.getAllBooks();		
	}

	// Method To Add New School To The Database
	public String addNewBook(KitapBean kitapBean) {
		return KitapDAO.createNewBook(kitapBean.getAdi());		
	}

	// Method To Delete The School Details From The Database
	public String deleteBookById(int bookId) {		
		return KitapDAO.deleteBookDetails(bookId);		
	}

	// Method To Navigate User To The Edit Details Page And Passing Selecting School Id Variable As A Hidden Value
	public String editBookDetailsById() {
		editBookId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedBookId");		
		adi = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedBookName");
		return "bookEdit.xhtml";
	}

	// Method To Update The School Details In The Database
	public String updateBookDetails(KitapBean kitapBean) {
		return KitapDAO.updateBookDetails(Integer.parseInt(kitapBean.getEditBookId()), kitapBean.getAdi());		
	}
	
	private int id;
	private String adi;	
	private String editBookId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getEditBookId() {
		return editBookId;
	}
	public void setEditBookId(String editBookId) {
		this.editBookId = editBookId;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}

	
}
