package com.bilgeadam.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kitap database table.
 * 
 */
@Entity
@NamedQuery(name="Kitap.findAll", query="SELECT k FROM Kitap k")
public class Kitap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String aciklama;

	private String adi;

	private float fiyat;

	private String isbn;

	private int sayfa;

	private String yazari;

	public Kitap() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAciklama() {
		return this.aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getAdi() {
		return this.adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public float getFiyat() {
		return this.fiyat;
	}

	public void setFiyat(float fiyat) {
		this.fiyat = fiyat;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getSayfa() {
		return this.sayfa;
	}

	public void setSayfa(int sayfa) {
		this.sayfa = sayfa;
	}

	public String getYazari() {
		return this.yazari;
	}

	public void setYazari(String yazari) {
		this.yazari = yazari;
	}

}