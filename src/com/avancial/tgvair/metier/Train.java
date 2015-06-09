package com.avancial.tgvair.metier;

import java.util.ArrayList;
import java.util.List;

public class Train {

	private List<Circulation> circulation;

	public List<Circulation> getCirculation() {
		return this.circulation;
	}

	public void setCirculation(List<Circulation> circulation) {
		this.circulation = circulation;
	}

	public String getChaineCompare() {
		StringBuilder sb = new StringBuilder();

		for (Circulation circulation : this.circulation) {
			sb.append(circulation.getChaineCircu());
		}

		return sb.toString();
	}

	public Train() {
		this.circulation = new ArrayList<>();
	}

	
}
