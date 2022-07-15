package com.qa.TMTY.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.TMTY.domain.Logins;
import com.qa.TMTY.utils;

public class loginsDao {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Logins modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long loginsId = resultSet.getLong("logins_id");
		String assignerName = resultSet.getString("assigner_name");
		String driversName = resultSet.getString("driver_name");
		Boolean privilege = resultSet.getBoolean("privilege");
		return new Logins(loginsId, assignerName, driversName, privilege);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Logins> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM logins");) {
			List<Logins> logins = new ArrayList<>();
			while (resultSet.next()) {
				logins.add(modelFromResultSet(resultSet));
			}
			return logins;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Logins readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM logins ORDER BY login_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Logins create(Logins logins) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO logins(assigner_name, driver_name, privilege) VALUES (?, ?, ?)");) {
			statement.setString(1, logins.getAssignerName());
			statement.setString(2, logins.getDriversName());
			statement.setBoolean(3, logins.getPrivilege());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Logins read(Long loginsId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM logins WHERE login_id = ?");) {
			statement.setLong(1, loginsId);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Logins update(Logins logins) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE logins SET assigner_name = ?, driver_name = ?, privilege = ? WHERE login_id = ?");) {
			statement.setString(1, logins.getAssignerName());
			statement.setString(2, logins.getDriversName());
			statement.setBoolean(3, logins.getPrivilege());
			statement.executeUpdate();
			return read(logins.getLoginsId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long loginsId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM logins WHERE logins_id = ?");) {
			statement.setLong(1, loginsId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
