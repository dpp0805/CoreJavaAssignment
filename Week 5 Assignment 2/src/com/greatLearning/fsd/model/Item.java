package com.greatLearning.fsd.model;

public class Item implements Comparable<Item> {
    private Product product;
    private Account seller;
    private int quantity;

    public Item(Product product, Account seller, int quantity) {
        this.product = product;
        this.seller = seller;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Account getSeller() {
        return seller;
    }

    public void updateQuantity(int quantity) {
        this.quantity -= quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    
	@Override
	public int compareTo(Item other) {
		return this.product.compareTo(other.getProduct());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [product=" + product + ", seller=" + seller + ", quantity=" + quantity + "]";
	}
}