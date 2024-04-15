package frontEnd;

import java.util.Date;

public class SalesRecord {
    private Date date;
    private double expenses;
    private double income;
    private double profit;

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	// Constructor
    public SalesRecord(Date date, double expenses, double income, double profit) {
        this.date = date;
        this.expenses = expenses;
        this.income = income;
        this.profit = profit;
    }

    // Getters and setters
    // You may need to implement these methods to access and modify the attributes of the SalesRecord.

    // toString method
    @Override
    public String toString() {
        return "SalesRecord{" +
                "date=" + date +
                ", expenses=" + expenses +
                ", income=" + income +
                ", profit=" + profit +
                '}';
    }
}
