package com.capgemini.repo;

import java.math.BigDecimal;
import java.sql.*;
import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.connection.ConnectionJDBC;

public class WalletRepoImpl implements WalletRepo{
	ConnectionJDBC connectionJDBC = new ConnectionJDBC();

	@Override
	public int save(Customer customer) throws SQLException {
		Connection con=connectionJDBC.getConnection();
		PreparedStatement pstmt=con.prepareStatement("insert into customer values(?,?,?)");
		pstmt.setString(1, customer.getName());
		pstmt.setString(2, customer.getMobileNo());
		pstmt.setInt(3, customer.getWallet().getBalance().intValue());
		int n=pstmt.executeUpdate();
		return n;
	}

	@Override
	public Customer findOne(String mobileNo) throws SQLException {
		Connection con=connectionJDBC.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("Select * from customer");
		while(rs.next())
		{
			if(rs.getString(2).equals(mobileNo))
			{
				BigDecimal bigDecimal = new BigDecimal(rs.getInt(3));
				Wallet wallet = new Wallet(bigDecimal);
				Customer customer = new Customer(rs.getString(1), rs.getString(2), wallet);
				return customer;
			}
		}
		return null;
	}
	
	public Customer fundtransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws SQLException
	{
		Connection con=connectionJDBC.getConnection();
		PreparedStatement pstmt=con.prepareStatement("update customer set amount=? where mobno=?");
		pstmt.setInt(1, findOne(sourceMobileNo).getWallet().getBalance().subtract(amount).intValue());
		pstmt.setString(2, sourceMobileNo);
		pstmt.executeUpdate();
		pstmt.setInt(1, findOne(targetMobileNo).getWallet().getBalance().add(amount).intValue());
		pstmt.setString(2, targetMobileNo);
		pstmt.executeUpdate();
		return findOne(targetMobileNo);
	}
	
	public Customer deposit(String mobileNo, BigDecimal amount) throws SQLException
	{
		Connection con=connectionJDBC.getConnection();
		PreparedStatement pstmt=con.prepareStatement("update customer set amount=? where mobno=?");
		pstmt.setInt(1, findOne(mobileNo).getWallet().getBalance().add(amount).intValue());
		pstmt.setString(2, mobileNo);
		pstmt.executeUpdate();
		return findOne(mobileNo);
	}
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws SQLException
	{
		Connection con=connectionJDBC.getConnection();
		PreparedStatement pstmt=con.prepareStatement("update customer set amount=? where mobno=?");
		pstmt.setInt(1, findOne(mobileNo).getWallet().getBalance().subtract(amount).intValue());
		pstmt.setString(2, mobileNo);
		pstmt.executeUpdate();
		return findOne(mobileNo);
	}

}
