package com.bilgeadam.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bilgeadam.beans.KitapBean;
import com.bilgeadam.model.Kitap;

public class KitapDAO {

	private static final String PERSISTENCE_UNIT_NAME = "JpaOrnekKitap";
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();

	// Method To Fetch All Book Details From The Database
	@SuppressWarnings("unchecked")
	public static List<KitapBean> getAllBookDetails() {
		entityMgrObj.refresh(Kitap.class);
		Query queryObj = entityMgrObj.createQuery("SELECT k FROM Kitap k");
		List<KitapBean> bookList = queryObj.getResultList();
		if (bookList != null && bookList.size() > 0) {
			return bookList;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<KitapBean> getAllBooks() {

		Query queryObj = entityMgrObj.createQuery("SELECT k FROM Kitap k");
		List<KitapBean> bookList = queryObj.getResultList();
		if (bookList != null && bookList.size() > 0) {
			return bookList;
		} else {
			return null;
		}
	}

	// Method To Add Create Book Details In The Database
	public static String createNewBook(String adi) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Kitap newBookObj = new Kitap();
		newBookObj.setId(getMaxBookId());
		newBookObj.setAdi(adi);
		entityMgrObj.persist(newBookObj);
		transactionObj.commit();
		return "booksList.xhtml?faces-redirect=true";
	}

	
	public static String deleteBookDetails(int bookId) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Kitap deleteBookObj = new Kitap();
		if (isBookIdPresent(bookId)) {
			deleteBookObj.setId(bookId);
			entityMgrObj.remove(entityMgrObj.merge(deleteBookObj));
		}
		transactionObj.commit();
		return "booksList.xhtml?faces-redirect=true";
	}

	public static String updateBookDetails(int bookId, String updatedBookAdi) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		if (isBookIdPresent(bookId)) {
			Query queryObj = entityMgrObj.createQuery("UPDATE Kitap k SET k.adi=:adi WHERE k.id= :id");
			queryObj.setParameter("id", bookId);
			queryObj.setParameter("adi", updatedBookAdi);
			int updateCount = queryObj.executeUpdate();

			if (updateCount > 0) {
				System.out.println("Kayıt : " + bookId + " Güncellendi");
			}
		}
		transactionObj.commit();
		FacesContext.getCurrentInstance().addMessage("editBookForm:bookId",
				new FacesMessage("Kitap kaydı  #" + bookId + " güncellendi"));
		return "bookEdit.xhtml";
	}

	// Helper Method 1 - Fetch Maximum Book Id From The Database
	private static int getMaxBookId() {
		int maxBookId = 1;
		Query queryObj = entityMgrObj.createQuery("SELECT MAX(k.id)+1 FROM Kitap k");
		if (queryObj.getSingleResult() != null) {
			maxBookId = (Integer) queryObj.getSingleResult();
		}
		return maxBookId;
	}

	private static boolean isBookIdPresent(int bookId) {
		boolean idResult = false;
		Query queryObj = entityMgrObj.createQuery("SELECT k FROM Kitap k WHERE k.id = :id");
		queryObj.setParameter("id", bookId);
		Kitap selectedBookId = (Kitap) queryObj.getSingleResult();
		if (selectedBookId != null) {
			idResult = true;
		}
		return idResult;
	}
}
